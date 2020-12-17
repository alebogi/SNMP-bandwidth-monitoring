package snmp;

import java.io.IOException;
import java.util.ArrayList;

import com.ireasoning.protocol.snmp.MibUtil;
import com.ireasoning.protocol.snmp.SnmpConst;
import com.ireasoning.protocol.snmp.SnmpSession;
import com.ireasoning.protocol.snmp.SnmpTableModel;
import com.ireasoning.protocol.snmp.SnmpTarget;
import com.ireasoning.util.MibParseException;

import system.Interface;
import system.MySystem;
import system.Router;

/**
 * Util class for collecting data for interfaces, using SNMP
 * 
 * @author Aleksandra Bogicevic
 *
 */
public class InterfaceDataGetter {

	private SnmpSession session;
	private SnmpTarget target;

	public static final int PORT = 161;
	public static final String COMMUNITY = "si2019";

	public static final int COLUMN_NUM_ID = 0;
	public static final int COLUMN_NUM_DESCR = 1;
	public static final int COLUMN_NUM_ADMIN_STATUS = 6;
	public static final int COLUMN_NUM_IN_OCTETS = 9;
	public static final int COLUMN_NUM_IN_PKTS = 10;
	public static final int COLUMN_NUM_OUT_OCTETS = 15;
	public static final int COLUMN_NUM_OUT_PKTS = 16;

	public static final int ADMIN_STATUS_UP = 1;
	public static final int ADMIN_STATUS_DOWN = 2;

	/**
	 * Method for establishing connection with ireasoning API.
	 * 
	 * @param ipAddr - ip addres of router we want to observe
	 */
	public void establishSession(String ipAddr) {
		try {
			target = new SnmpTarget(ipAddr, PORT, COMMUNITY, COMMUNITY, SnmpConst.SNMPV2);
			session = new SnmpSession(target);
			session.setTimeout(5000);
			session.setRetries(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closing connection with ireasoning API.
	 */
	public void endSession() {
		if (session != null)
			session.close();
	}

	/**
	 * Method that collects data using SNMP about interfaces of router sent by
	 * argument.
	 * 
	 * @param r - router that is being watched
	 * @return - interfaces data for router r
	 */
	public ArrayList<Interface> getRoutersInterfaces(Router r) {
		ArrayList<Interface> interfaces = new ArrayList<Interface>();
		String ipAddr = r.getIpAddressForSnmp();

		establishSession(ipAddr);

		try {
			MibUtil.loadMib("mibs/IF-MIB");
			SnmpTableModel resultTable = session.snmpGetTable("ifTable");

			for (int rowNum = 0; rowNum < resultTable.getRowCount(); rowNum++) {

				if (Integer.parseInt(
						resultTable.getValueAt(rowNum, COLUMN_NUM_ADMIN_STATUS).toString()) == ADMIN_STATUS_DOWN) {
					continue;
				}

				Interface interf = new Interface();

				interf.setId(Integer.parseInt(resultTable.getValueAt(rowNum, COLUMN_NUM_ID).toString()));
				interf.setName(resultTable.getValueAt(rowNum, COLUMN_NUM_DESCR).toString());
				interf.setInOctets(Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_IN_OCTETS).toString()));
				interf.setInPkts(Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_IN_PKTS).toString()));
				interf.setOutOctets(Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_OUT_OCTETS).toString()));
				interf.setOutPkts(Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_OUT_PKTS).toString()));

				interfaces.add(interf);
			}

		} catch (IOException | MibParseException e) {
			e.printStackTrace();
		}

		endSession();

		return interfaces;
	}

	public void updateData(String ipAddr, String interfName) {
		Router r = MySystem.getRouterByIp(ipAddr);
		Interface interf = r.getInterfaceByName(interfName);

		establishSession(ipAddr);

		try {
			MibUtil.loadMib("mibs/IF-MIB");
			SnmpTableModel resultTable = session.snmpGetTable("ifTable");

			for (int rowNum = 0; rowNum < resultTable.getRowCount(); rowNum++) {

				if (interf.getId() == Integer.parseInt(resultTable.getValueAt(rowNum, COLUMN_NUM_ID).toString())) {
					interf.setInOctets(Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_IN_OCTETS).toString()));
					interf.setInPkts(Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_IN_PKTS).toString()));
					interf.setOutOctets(
							Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_OUT_OCTETS).toString()));
					interf.setOutPkts(Long.parseLong(resultTable.getValueAt(rowNum, COLUMN_NUM_OUT_PKTS).toString()));

				}

			}

		} catch (IOException | MibParseException e) {
			e.printStackTrace();
		}

		endSession();
	}
}

package snmp;

import java.io.IOException;

import com.ireasoning.protocol.snmp.MibUtil;
import com.ireasoning.protocol.snmp.SnmpConst;
import com.ireasoning.protocol.snmp.SnmpSession;
import com.ireasoning.protocol.snmp.SnmpTableModel;
import com.ireasoning.protocol.snmp.SnmpTarget;
import com.ireasoning.util.MibParseException;

public class SNMPGetter {

	private SnmpSession session;
	private SnmpTarget target;
	
	SNMPGetter(String ipAddr, int port){
		try {
			target = new SnmpTarget(ipAddr, port, "SI2019", "SI2019", SnmpConst.SNMPV2);
			session = new SnmpSession(target);
			session.setTimeout(3000); 
			session.setRetries(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getInfo() {
		try {
			MibUtil.loadMib("IF-MIB");
			SnmpTableModel resultTable = session.snmpGetTable("ifTable");
			 for(int i = 0; i < resultTable.getRowCount(); i++) {
				 
			 }
		} catch (MibParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

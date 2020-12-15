package snmp;

import java.io.IOException;

import com.ireasoning.protocol.snmp.MibUtil;
import com.ireasoning.protocol.snmp.SnmpConst;
import com.ireasoning.protocol.snmp.SnmpSession;
import com.ireasoning.protocol.snmp.SnmpTableModel;
import com.ireasoning.protocol.snmp.SnmpTarget;
import com.ireasoning.util.MibParseException;

public class TestKlasaSNMP {

	private SnmpSession session;
	private SnmpTarget target;
	
	public TestKlasaSNMP(String ipAddr, int port){
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
				 System.out.println(resultTable.getValueAt(i, 7).toString());
				 System.out.println("id - " + Integer.parseInt(resultTable.getValueAt(i, 0).toString()));
				 System.out.println("name - " + resultTable.getValueAt(i, 1).toString());
				 System.out.println("in octets -" + Long.parseLong(resultTable.getValueAt(i, 9).toString()));
				 System.out.println("out octets - " + Long.parseLong(resultTable.getValueAt(i, 15).toString()));
				 System.out.println("in count - " + Long.parseLong(resultTable.getValueAt(i, 10).toString()));
				 System.out.println("out count -" + Long.parseLong(resultTable.getValueAt(i, 16).toString()));
				 System.out.println(Long.parseLong("if speed - " + resultTable.getValueAt(i, 4).toString()));
                 System.out.println("-------------------");
			 }
		} catch (MibParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestKlasaSNMP t = new TestKlasaSNMP("192.168.10.1", 161);
		t.getInfo();
	}
}

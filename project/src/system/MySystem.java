package system;

import java.util.ArrayList;
import snmp.*;

public class MySystem {

	private ArrayList<Router> routers = new ArrayList<Router>();
	public static String selectedR;
	public static String selectedIf;
	
	public static String getSelectedR() {
		return selectedR;
	}

	public static void setSelectedR(String selectedR) {
		MySystem.selectedR = selectedR;
		System.out.println("Selected router: "+selectedR);
	}

	public static String getSelectedIf() {
		return selectedIf;
	}

	public static void setSelectedIf(String selectedIf) {
		MySystem.selectedIf = selectedIf;
		System.out.println("Selected interface: " + selectedIf);
	}

	public MySystem() {
		Router r1 = new Router("192.168.10.1");
		Router r2 = new Router("192.168.20.1");
		Router r3 = new Router("192.168.30.1");
		
		InterfaceDataGetter idg = new InterfaceDataGetter();
		
		r1.setInterfaces(idg.getRoutersInterfaces(r1));
		r2.setInterfaces(idg.getRoutersInterfaces(r2));
		r3.setInterfaces(idg.getRoutersInterfaces(r3));
		
		routers.add(r1);
		routers.add(r2);
		routers.add(r3);
		
	}

	public ArrayList<Router> getRouters() {
		return routers;
	}

	public void setRouters(ArrayList<Router> routers) {
		this.routers = routers;
	}
	
	
}

package system;

import java.util.ArrayList;

import snmp.InterfaceDataGetter;

/**
 * 
 * @author Aleksandra Bogicevic
 *
 */
public class MySystem {

	private static ArrayList<Router> routers = new ArrayList<Router>();
	public static String selectedR;
	public static String selectedIf;

	public static String getSelectedR() {
		return selectedR;
	}

	public static void setSelectedR(String selectedR) {
		MySystem.selectedR = selectedR;
		System.out.println("Selected router: " + selectedR);
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

	public static ArrayList<Router> getRouters() {
		return routers;
	}

	public static void setRouters(ArrayList<Router> r) {
		routers = r;
	}

	public static Router getRouterByIp(String ipAddr) {
		Router ret = null;

		for (int i = 0; i < routers.size(); i++) {
			if (routers.get(i).getIpAddressForSnmp().equals(ipAddr)) {
				ret = routers.get(i);
			}
		}

		return ret;
	}

}

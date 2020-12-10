package snmp;

import java.util.ArrayList;

import system.Interface;
import system.Router;

public class InterfaceDataGetter {
	
	

	public ArrayList<Interface> getRoutersInterfaces(Router r){
		ArrayList<Interface> interfaces = new ArrayList<Interface>();
		String ipAddr = r.getIpAddressForSnmp();
		
		//using snmp we catch all info about routers interfaces
		Interface i = new Interface();
		interfaces.add(i);
		
		return interfaces;
	}
}

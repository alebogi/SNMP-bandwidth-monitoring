package system;

import java.util.ArrayList;

public class Router {

	private String ipAddressForSnmp;
	private ArrayList<Interface> interfaces;
	private int port;
	private String communityValue;
	
	public Router(String ip){
		ipAddressForSnmp = ip;
		port = 161;
		communityValue = "SI2019";
	}

	public String getIpAddressForSnmp() {
		return ipAddressForSnmp;
	}

	public ArrayList<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(ArrayList<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	
	public void addInterface(Interface i) {
		interfaces.add(i);
	}
}

package system;

import java.util.ArrayList;

/**
 * Interface of router.
 * 
 * @author Aleksandra Bogicevic
 *
 */
public class Interface {

	private String name;
	private int id;
	private long inOctets;
	private long outOctets;
	private long inPkts; // for left chart
	private long outPkts; // for left chart
	private double inBandwidth; // for right chart
	private double outBandwidth; // for right chart

	private ArrayList<Long> inBytes = new ArrayList<Long>();
	private ArrayList<Long> outBytes = new ArrayList<Long>();

	public long getInPkts() {
		return inPkts;
	}

	public void setInPkts(long inPkts) {
		this.inPkts = inPkts;
	}

	public long getOutPkts() {
		return outPkts;
	}

	public void setOutPkts(long outPkts) {
		this.outPkts = outPkts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getInOctets() {
		return inOctets;
	}

	public void setInOctets(long inOctets) {
		this.inOctets = inOctets; // data we get from snmp
		inBytes.add(inOctets); // data we need
		setInBandwidth();
	}

	public long getOutOctets() {
		return outOctets;
	}

	public void setOutOctets(long outOctets) {
		this.outOctets = outOctets; // data we get from snmp
		outBytes.add(outOctets); // data we need
		setOutBandwidth();
	}

	public double getInBandwidth() {
		return inBandwidth;
	}

	/**
	 * Bandwidth = bits per second.
	 * 
	 */
	public void setInBandwidth() {
		long lastBytes = (long) 0;
		long currentBytes;

		int arraySize = inBytes.size();
		currentBytes = inBytes.get(arraySize - 1);

		if (arraySize > 1) {
			lastBytes = inBytes.get(arraySize - 2);
		}

		inBandwidth = 8 * (currentBytes - lastBytes) / 10;
	}

	public double getOutBandwidth() {
		return outBandwidth;
	}

	/**
	 * Bandwidth = bits per second.
	 * 
	 */
	public void setOutBandwidth() {
		long lastBytes = (long) 0;
		long currentBytes;

		int arraySize = outBytes.size();
		currentBytes = outBytes.get(arraySize - 1);

		if (arraySize > 1) {
			lastBytes = outBytes.get(arraySize - 2);
		}

		outBandwidth = 8 * (currentBytes - lastBytes) / 10;
	}

}

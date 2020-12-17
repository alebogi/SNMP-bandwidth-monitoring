package system;

import java.util.ArrayList;

/**
 * Class that provides data for charts
 * 
 * @author Aleksandra Bogicevic
 *
 */
public class GraphData {

	private ArrayList<Double> inDataBandwidth = new ArrayList<Double>();
	private ArrayList<Double> outDataBandwidth = new ArrayList<Double>();
	private ArrayList<Long> inDataPkts = new ArrayList<Long>();
	private ArrayList<Long> outDataPkts = new ArrayList<Long>();

	private static int X_AXIS_STEP = 10;

	private Router r;
	private Interface interf;

	public GraphData() {
		r = MySystem.getRouterByIp(MySystem.getSelectedIf());
		interf = r.getInterfaceByName(MySystem.getSelectedIf());
	}

	public ArrayList<Double> bandwidthGraphIn() {

		return inDataBandwidth;
	}

	public ArrayList<Double> bandwidthGraphOut() {

		return outDataBandwidth;
	}

	public ArrayList<Long> packetsGraphIn() {

		return inDataPkts;
	}

	public ArrayList<Long> packetsGraphOut() {

		return outDataPkts;
	}

}

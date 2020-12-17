package system;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import snmp.InterfaceDataGetter;

public class RealtimeChart implements Runnable {

	private Thread thr;

	private XYChart chartPkts;
	private JPanel panelChartPkts;

	private XYChart chartBandwidth;
	private JPanel panelChartBandwidth;

	ArrayList<Integer> x = new ArrayList<Integer>();
	private int cntX;

	private ArrayList<Double> inDataBandwidth = new ArrayList<Double>();
	private ArrayList<Double> outDataBandwidth = new ArrayList<Double>();
	private ArrayList<Long> inDataPkts = new ArrayList<Long>();
	private ArrayList<Long> outDataPkts = new ArrayList<Long>();

	private Router r;
	private Interface interf;

	private boolean thrStarted;
	private boolean thrPaused;

	public RealtimeChart() {
		cntX = 0;
		x.add(cntX);
		inDataPkts.add((long) 0);
		outDataPkts.add((long) 0);
		inDataBandwidth.add((double) 0);
		outDataBandwidth.add((double) 0);

		chartPkts = QuickChart.getChart("Packages", "Time", "Packages", "in", x, inDataPkts);
		chartPkts.addSeries("out", x, outDataPkts);
		panelChartPkts = new XChartPanel<XYChart>(chartPkts);

		chartBandwidth = QuickChart.getChart("Bandwidth", "Time", "bit/s", "in", x, inDataBandwidth);
		chartBandwidth.addSeries("out", x, outDataBandwidth);
		panelChartBandwidth = new XChartPanel<XYChart>(chartBandwidth);

		thrStarted = false;
		thrPaused = true;
	}

	public void startGraphRepaint() {
		thr = new Thread(this);
		thrStarted = true;
		thrPaused = false;
		thr.start();
	}

	public void resumeGraphRepaint() {
		thrPaused = false;
	}

	public void pauseGraphRepaint() {
		thrPaused = true;
		cntX = 0;
		x.clear();
		x.add(cntX);

		inDataPkts.clear();
		outDataPkts.clear();
		inDataBandwidth.clear();
		outDataBandwidth.clear();

		inDataPkts.add((long) 0);
		outDataPkts.add((long) 0);
		inDataBandwidth.add((double) 0);
		outDataBandwidth.add((double) 0);

		chartPkts.updateXYSeries("in", x, inDataPkts, null);
		chartPkts.updateXYSeries("out", x, outDataPkts, null);
		panelChartPkts.repaint();

		chartBandwidth.updateXYSeries("in", x, inDataBandwidth, null);
		chartBandwidth.updateXYSeries("out", x, outDataBandwidth, null);
		panelChartBandwidth.repaint();
	}

	public boolean isThrStarted() {
		return thrStarted;
	}

	public void setThrStarted(boolean thrStarted) {
		this.thrStarted = thrStarted;
	}

	public boolean isThrPaused() {
		return thrPaused;
	}

	public void setThrPaused(boolean thrPaused) {
		this.thrPaused = thrPaused;
	}

	public JPanel getPanelChartPkts() {
		return panelChartPkts;
	}

	public void setPanelChartPkts(JPanel panelChart) {
		this.panelChartPkts = panelChart;
	}

	public XYChart getChartPkts() {
		return chartPkts;
	}

	public void setChartPkts(XYChart chart) {
		this.chartPkts = chart;
	}

	public XYChart getChartBandwidth() {
		return chartBandwidth;
	}

	public void setChartBandwidth(XYChart chartBandwidth) {
		this.chartBandwidth = chartBandwidth;
	}

	public JPanel getPanelChartBandwidth() {
		return panelChartBandwidth;
	}

	public void setPanelChartBandwidth(JPanel panelChartBandwidth) {
		this.panelChartBandwidth = panelChartBandwidth;
	}

	@Override
	public void run() {
		try {
			InterfaceDataGetter interfDataGetter = new InterfaceDataGetter();
			while (thrStarted == true && thrPaused == false) {
				r = MySystem.getRouterByIp(MySystem.getSelectedR());
				interf = r.getInterfaceByName(MySystem.getSelectedIf());
				if (interf.equals(""))
					continue;

				updateGraph();
				interfDataGetter.updateData();

				chartPkts.updateXYSeries("in", x, inDataPkts, null);
				chartPkts.updateXYSeries("out", x, outDataPkts, null);
				panelChartPkts.repaint();

				chartBandwidth.updateXYSeries("in", x, inDataBandwidth, null);
				chartBandwidth.updateXYSeries("out", x, outDataBandwidth, null);
				panelChartBandwidth.repaint();

				thr.sleep(1000 * MySystem.REFRESH_TIME);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Double> bandwidthGraphIn() {
		inDataBandwidth.add(interf.getInBandwidth());

		return inDataBandwidth;
	}

	public ArrayList<Double> bandwidthGraphOut() {
		outDataBandwidth.add(interf.getOutBandwidth());

		return outDataBandwidth;
	}

	public ArrayList<Long> packetsGraphIn() {
		inDataPkts.add(interf.getInPkts());

		return inDataPkts;
	}

	public ArrayList<Long> packetsGraphOut() {
		outDataPkts.add(interf.getOutPkts());

		return outDataPkts;
	}

	public void updateGraph() {
		r = MySystem.getRouterByIp(MySystem.getSelectedR());
		interf = r.getInterfaceByName(MySystem.getSelectedIf());
		if (interf.equals(""))
			return;

		cntX += 10;
		x.add(cntX);
		bandwidthGraphIn();
		bandwidthGraphOut();
		packetsGraphIn();
		packetsGraphOut();
	}

}

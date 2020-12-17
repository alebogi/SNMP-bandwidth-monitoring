package main;

import java.awt.EventQueue;

import gui.GuiWindow;

/**
 * 
 * @author Aleksandra Bogicevic
 *
 */
public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiWindow window = new GuiWindow();
					window.frmSnmpBandwidthMonitoring.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

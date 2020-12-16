package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import snmp.InterfaceDataGetter;
import system.Interface;
import system.MySystem;
import system.Router;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class GuiWindow {
	
	private MySystem sys;
	private ArrayList<Router> routers;
	
	private JFrame frmSnmpBandwidthMonitoring;

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

	/**
	 * Create the application.
	 */
	public GuiWindow() {
		sys = new MySystem();
		routers = sys.getRouters();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSnmpBandwidthMonitoring = new JFrame();
		frmSnmpBandwidthMonitoring.setTitle("SNMP Bandwidth Monitoring");
		frmSnmpBandwidthMonitoring.getContentPane().setBackground(new Color(135, 206, 250));
		frmSnmpBandwidthMonitoring.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		frmSnmpBandwidthMonitoring.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSelectRouter = new JLabel("Select router:");
		lblSelectRouter.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 20));
		lblSelectRouter.setBounds(73, 12, 158, 40);
		panel.add(lblSelectRouter);
		
		
		String[] routers_string = {"", "", ""};
		for(int i = 0; i < routers.size(); i++) {
			routers_string[i] = routers.get(i).getIpAddressForSnmp();
		}
		
		
		JComboBox comboBox = new JComboBox();
		
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"192.168.10.1", "192.168.20.1", "192.168.30.1"}));
		comboBox.setModel(new DefaultComboBoxModel(routers_string));
		comboBox.setMaximumRowCount(3);
		comboBox.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 20));
		comboBox.setBounds(234, 12, 181, 40);
		panel.add(comboBox);
			
		
		
		JLabel lblSelectInterface = new JLabel("Select interface:");
		lblSelectInterface.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 20));
		lblSelectInterface.setBounds(493, 12, 193, 40);
		panel.add(lblSelectInterface);
		
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 20));
		comboBox_1.setBounds(688, 12, 181, 40);
		panel.add(comboBox_1);
		
		
		//selekcija rutera
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MySystem.setSelectedR(comboBox.getSelectedItem().toString());
				String selectedIp = MySystem.getSelectedR();
				String[] interfaces_string = {""};
				
				for(int i = 0; i < routers.size(); i++) {
					if(routers.get(i).getIpAddressForSnmp().equals(selectedIp)) {
						ArrayList<Interface> ifs = routers.get(i).getInterfaces();
						for(int j = 0; j < ifs.size(); j++) {
							interfaces_string[j] = ifs.get(j).getName();
						}
					}
				}
				
				comboBox_1.setModel(new DefaultComboBoxModel(interfaces_string));
			}
		});
		
		//selekcija interfejsa
		comboBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO prikazati odredjen grafik
				MySystem.setSelectedIf(comboBox_1.getSelectedItem().toString());
			}
		});
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250));
		panel_1.setBounds(29, 63, 942, 459);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 30, 0));
		frmSnmpBandwidthMonitoring.setBounds(100, 100, 1000, 621);
		frmSnmpBandwidthMonitoring.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		double[] x = {1.0, 2.0, 3.0};
		double[] y = {1.0, 2.0, 3.0};
		XYChart chartLeft =
		        QuickChart.getChart(
		            "Jedan grafik", "x", "y", "f(x)", x, y);
		JPanel panelChartLeft = new  XChartPanel<XYChart>(chartLeft);
		panel_1.add(panelChartLeft);
		
		double[] x2 = {1.0, 2.0, 3.0};
		double[] y2 = {2.0, 4.0, 6.0};
		XYChart chartRight =
		        QuickChart.getChart(
		            "Drugi grafik", "x", "y", "f(x)", x2, y2);
		JPanel panelChartRight = new  XChartPanel<XYChart>(chartRight);
		panel_1.add(panelChartRight);
		
		
		JMenuBar menuBar = new JMenuBar();
		frmSnmpBandwidthMonitoring.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUserGuide = new JMenuItem("User Guide");
		mntmUserGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//prikazi sliku userGuide.jpg
			}
		});
		mnHelp.add(mntmUserGuide);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//prikazi sliku about.jpg
				JFrame f = new JFrame();
				JDialog dialog = new JDialog(f, "About", true);
				dialog.getContentPane().setLayout(new BorderLayout());
				ImageIcon pic = new ImageIcon("././attachments/about.jpg");
				JLabel label = new JLabel();
				label.setIcon(pic);
				dialog.getContentPane().add(label, "Center");
				JButton b =new JButton("OK");
				b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
						f.dispose();
					}
				});
				dialog.getContentPane().add(b, "South");
				
				dialog.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent e) {
						dialog.dispose();
						f.dispose();
					}
					
					
				});
				dialog.setResizable(false);
				f.setResizable(false);
				dialog.setBounds(100, 100, 675, 350);
				dialog.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
	}
}

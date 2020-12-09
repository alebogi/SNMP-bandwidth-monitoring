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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class GuiWindow {
	
	

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"192.168.10.1", "192.168.20.1", "192.168.30.1"}));
		comboBox.setMaximumRowCount(3);
		comboBox.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 20));
		comboBox.setBounds(234, 12, 181, 40);
		panel.add(comboBox);
		
		JLabel lblSelectInterface = new JLabel("Select interface:");
		lblSelectInterface.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 20));
		lblSelectInterface.setBounds(493, 12, 193, 40);
		panel.add(lblSelectInterface);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setMaximumRowCount(3);
		comboBox_1.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 20));
		comboBox_1.setBounds(688, 12, 181, 40);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(29, 63, 942, 459);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 30, 0));
		frmSnmpBandwidthMonitoring.setBounds(100, 100, 1000, 621);
		frmSnmpBandwidthMonitoring.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
				dialog.setLayout(new BorderLayout());
				ImageIcon pic = new ImageIcon("././attachments/about.jpg");
				JLabel label = new JLabel();
				label.setIcon(pic);
				dialog.add(label, "Center");
				JButton b =new JButton("OK");
				b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
						f.dispose();
					}
				});
				dialog.add(b, "South");
				
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

package ca.lakeeffectrobotics.scoutingreader.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ca.lakeeffectrobotics.scoutingreader.Main;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public RobotPanel robotPanel;
	
	public Main main;

	/**
	 * Create the frame.
	 */
	
	public MainFrame(Main main) {
//	    try {
//	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1400, 1000);
		setLocationRelativeTo(null);
		robotPanel = new RobotPanel(main);
		robotPanel.setAlignmentX(200);
		add(robotPanel);
		
//		add(new RobotPanel());
//		add(new RobotPanel());
	}
}

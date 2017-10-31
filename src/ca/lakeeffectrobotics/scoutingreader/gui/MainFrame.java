package ca.lakeeffectrobotics.scoutingreader.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private RobotPanel robot;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
//	    try {
//	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 1000);
		setLocationRelativeTo(null);
		robot = new RobotPanel();
		robot.setAlignmentX(200);
		add(robot);
//		add(new RobotPanel());
//		add(new RobotPanel());


	}

}

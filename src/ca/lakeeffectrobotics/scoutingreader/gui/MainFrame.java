<<<<<<< HEAD
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
=======
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
>>>>>>> 9d27cad64a3d481f8744330f844b8c012bfd57f5

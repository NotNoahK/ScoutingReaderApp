package ca.lakeeffectrobotics.scoutingreader.gui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import ca.lakeeffectrobotics.scoutingreader.Main;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public ArrayList<RobotPanel> robotPanels = new ArrayList<RobotPanel>();
	
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
		JSplitPane pane = new JSplitPane();
		pane.setResizeWeight(0.5);
		add(pane);
		
		
		RobotPanel panel = new RobotPanel(main);
		pane.add(panel, JSplitPane.LEFT);
		robotPanels.add(panel);
		
		RobotPanel panel1 = new RobotPanel(main);
		pane.add(panel1, JSplitPane.RIGHT);
		robotPanels.add(panel1);
		
//		add(new RobotPanel());
//		add(new RobotPanel());
	}
}

package ca.lakeeffectrobotics.scoutingreader.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ca.lakeeffectrobotics.scoutingreader.Main;
import ca.lakeeffectrobotics.scoutingreader.Match;
import ca.lakeeffectrobotics.scoutingreader.Robot;

public class DataSelection extends JFrame implements ItemListener, MouseListener, KeyListener{
	
	/**Main JPanel*/
	JPanel contentPanel;
	
	int selectedRobot = 1075;
	
	static JFrame frame;
	
	/**JPanel with match selection checkboxes*/
	JPanel matchPanel;
	/**JPanel with data selection checkboxes*/
	JPanel dataPanel;
	/**JPanel with robot selection checkboxes*/
	JPanel robotNumberPanel;
	
	
	JCheckBox allMatches;
	/**Check box to select all data*/
	JCheckBox allData;
	
	TextField robotSearch;
	
	/** Match view type selector */
	JComboBox<Integer> matchViewTypeSelector;
	
	Main main;
	
	ArrayList<Integer> selectedMatches = new ArrayList<>();
	ArrayList<String> selectedLabels = new ArrayList<>();
	ArrayList<String> selectedRobots = new ArrayList<>();
	
	//the checkboxes sorted into seperate lists
	ArrayList<JCheckBox> matchBoxes = new ArrayList<>();
	ArrayList<JCheckBox> dataBoxes = new ArrayList<>();
	
	//was a new robot just selected
	boolean loadingNewRobot = false;
		
	/**
	 * Create the frame.
	 */
	public DataSelection(Main main) {
		this.main = main;
		 try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 500);
		setTitle("Data Selection");
		
		//create the content JPanel
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(null);
		setContentPane(contentPanel);
		
		Integer[] robots = new Integer[main.robots.size()];
		
		for(int i=0;i<robots.length;i++){
			robots[i] = main.robots.get(i).number;
		}
		Arrays.sort(robots);

		/** Main split pane */
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setBorder(null);
		splitPane.setDividerSize(0);
	
		
		//add the split pane to the window 
		contentPanel.add(splitPane, BorderLayout.CENTER);
		
		JLabel title = new JLabel();
		title.setFont(new Font("Tahoma", Font.BOLD, 23));
		title.setText("Data Selection");
		title.setHorizontalAlignment(JLabel.CENTER);
		contentPanel.add(title, BorderLayout.NORTH);
		
		/** split pane containing robots and matches */
		JSplitPane robotSplitPane = new JSplitPane();
		robotSplitPane.setResizeWeight(0.5);
		robotSplitPane.setBorder(null);
		robotSplitPane.setDividerSize(0);
		splitPane.add(robotSplitPane, JSplitPane.LEFT);
		
		/** splitpane containg match numbers and spinner */
		JSplitPane matchPane = new JSplitPane();
		matchPane.setResizeWeight(0.95);
		matchPane.setBorder(null);
		matchPane.setDividerSize(0);
		matchPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		robotSplitPane.add(matchPane, JSplitPane.RIGHT);
		
		/** splitpane containg data and checkboxes all data and all robots */
		JSplitPane dataPane = new JSplitPane();
		dataPane.setResizeWeight(0.95);
		dataPane.setBorder(null);
		dataPane.setDividerSize(0);
		dataPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.add(dataPane, JSplitPane.RIGHT);
		
		/** splitpane containg robot numbers and search box */
		JSplitPane robotNumberPane = new JSplitPane();
		robotNumberPane.setResizeWeight(0.95);
		robotNumberPane.setBorder(null);
		robotNumberPane.setDividerSize(0);
		robotNumberPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		robotSplitPane.add(robotNumberPane, JSplitPane.LEFT);
		
		
		JScrollPane leftPane = new JScrollPane();
		robotNumberPane.add(leftPane, JSplitPane.TOP);
		leftPane.getVerticalScrollBar().setUnitIncrement(20);
		
		JScrollPane centerPane = new JScrollPane();
		matchPane.add(centerPane, JSplitPane.TOP);
		centerPane.getVerticalScrollBar().setUnitIncrement(20);
		
		JScrollPane rightPane = new JScrollPane();
		dataPane.add(rightPane, JSplitPane.TOP);
		rightPane.getVerticalScrollBar().setUnitIncrement(20);
		
		robotNumberPanel = new JPanel();
		leftPane.setViewportView(robotNumberPanel);
		robotNumberPanel.setLayout(new BoxLayout(robotNumberPanel, BoxLayout.Y_AXIS));
		
		
		robotSearch = new TextField();
		robotSearch.addKeyListener(this);
		robotNumberPane.add(robotSearch, JSplitPane.BOTTOM);
		
		matchPanel = new JPanel();
		centerPane.setViewportView(matchPanel);
		matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.Y_AXIS));
		
		JButton clearRobotNumberButton = new JButton("Clear Robot Numbers");
		matchPane.setRightComponent(clearRobotNumberButton);
		
		// Add all matches and all robots checkboxes
		JSplitPane allSelectionPane = new JSplitPane();
		allSelectionPane.setResizeWeight(0.5);
		allSelectionPane.setBorder(null);
		allSelectionPane.setDividerSize(0);
		dataPane.add(allSelectionPane, JSplitPane.BOTTOM);
		
		allData = new JCheckBox();
		allData.setText("All data");
		allData.setHorizontalAlignment(JCheckBox.CENTER);
		allData.addItemListener(this);
		allSelectionPane.add(allData, JSplitPane.RIGHT);
		
		allMatches = new JCheckBox();
		allMatches.setSelected(true);
		allMatches.setText("All matches");
		allMatches.setHorizontalAlignment(JCheckBox.CENTER);
		allMatches.addItemListener(this);
		allSelectionPane.add(allMatches, JSplitPane.LEFT);
		
		dataPanel = new JPanel();
		rightPane.setViewportView(dataPanel);
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		

		
		resetRobotBoxes();
		setVisible(true);
		setOptions();
	}

	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		if(itemEvent.getSource() == null){
//			loadingNewRobot = true;
//			
//			//Old robot that was previously selected
//			if(itemEvent.getStateChange() == ItemEvent.DESELECTED) return;
//			//The same robot
//			if((Integer) robotSelector.getSelectedItem() == selectedRobot) return;
//			//Setup checkboxes
//			getSelectedData();
//			setOptions();
//			
//			loadingNewRobot = false;
		} else if(itemEvent.getSource() == allMatches){
			if(itemEvent.getStateChange() == ItemEvent.DESELECTED){
				for(Component c : matchPanel.getComponents()){
					if(!(c instanceof JCheckBox)) continue;

					JCheckBox box = ((JCheckBox) c);
					
					box.setSelected(false);
				}
			}else{
				for(Component c : matchPanel.getComponents()){
					if(!(c instanceof JCheckBox)) continue;

					JCheckBox box = ((JCheckBox) c);
					
					box.setSelected(true);
				}
			}

			
		} else if(itemEvent.getSource() == allData){
			if(itemEvent.getStateChange() == ItemEvent.DESELECTED){
				for(Component c : dataPanel.getComponents()){
					if(!(c instanceof JCheckBox)) continue;

					JCheckBox box = ((JCheckBox) c);
					
					box.setSelected(false);
				}
			}else{
				for(Component c : dataPanel.getComponents()){
					if(!(c instanceof JCheckBox)) continue;

					JCheckBox box = ((JCheckBox) c);
					
					box.setSelected(true);
				}
			}

			
		} else if(itemEvent.getSource() instanceof JCheckBox && !loadingNewRobot){ //Do not reset if changing robots
			if(itemEvent.getStateChange() == ItemEvent.DESELECTED){
				if(matchBoxes.contains(itemEvent.getSource())) {
					allMatches.removeItemListener(this);
					allMatches.setSelected(false);
					allMatches.addItemListener(this);
				}else if(dataBoxes.contains(itemEvent.getSource())) {
					allData.removeItemListener(this);
					allData.setSelected(false);
					allData.addItemListener(this);
				}
			}
			
			getSelectedData(( (JCheckBox) itemEvent.getSource()).getText() );
		}
	}
	
	/**
	 * Setup the check boxes 
	 */
	
	void setOptions(){
//		selectedRobot = (Integer) robotSelector.getSelectedItem();
		
		//TODO: Replace selected robot with newer system
		System.out.println(selectedRobot);
		
		Robot robot = main.getRobotByNumber(selectedRobot);
		
		if(robot == null) return;
		
		//Delete all existing checkboxes
		matchPanel.removeAll();
		dataPanel.removeAll();
		
		//Create match checkboxes
		matchPanel.removeAll();
		for(String robotNum: selectedRobots){
			
			robot = main.getRobotByNumber(Integer.parseInt(robotNum.replace("Robot: ", "")));
			JLabel robotLabel = new JLabel();
			robotLabel.setHorizontalTextPosition(JLabel.LEFT);
			robotLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			robotLabel.setText(robotNum);
			matchPanel.add(robotLabel);
			
			for(Match m : robot.matches){
				System.out.println(m.number);
				JCheckBox checkBox = new JCheckBox("Match: " + m.number);
				if(allMatches.isSelected()) checkBox.setSelected(true);
				checkBox.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 0));
				matchPanel.add(checkBox);	
				checkBox.addItemListener(this);
				matchBoxes.add(checkBox);
			}
		}
		
		//Create data checkboxes
		JCheckBox box;
		System.out.println(selectedLabels.size());
		System.out.println("seperator");
		for(String s : robot.matches.get(0).floats.labels){
			box = new JCheckBox(s);
			box.addItemListener(this);
			// If box is in list of selected boxes, select it
			if(selectedLabels.contains(s)) box.setSelected(true);
			dataPanel.add(box);	
			dataBoxes.add(box);
		}
		
		for(String s : robot.matches.get(0).booleans.labels){
			box = new JCheckBox(s);
			box.addItemListener(this);
			// If box is in list of selected boxes, select it
			if(selectedLabels.contains(s)) box.setSelected(true);
			dataPanel.add(box);	
			dataBoxes.add(box);
		}
		
		for(String s : robot.matches.get(0).strings.labels){
			box = new JCheckBox(s);
			box.addItemListener(this);
			// If box is in list of selected boxes, select it
			if(selectedLabels.contains(s)) box.setSelected(true);
			dataPanel.add(box);	
			dataBoxes.add(box);
		}
		
		//Refresh page
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	void getSelectedData(String checkBoxLabel){
		selectedMatches = new ArrayList<Integer>();
		
		for(Component c : matchPanel.getComponents()){
			if(! (c instanceof JCheckBox)) continue;

			JCheckBox box = ((JCheckBox) c);
			
			if(box.isSelected()){
				System.out.println(Integer.parseInt(box.getText().replace("Match: ", "")));
				selectedMatches.add(Integer.parseInt(box.getText().replace("Match: ", "")));
			}
		}
		
		selectedLabels = new ArrayList<String>();
		
		for(Component c : dataPanel.getComponents()){
			if(!(c instanceof JCheckBox)) continue;

			JCheckBox box = ((JCheckBox) c);
			
			if(box.isSelected()){
				System.out.println(box.getText());
				selectedLabels.add(box.getText());
			}
		}
		
		selectedRobots = new ArrayList<String>();
		
		for(Component c : robotNumberPanel.getComponents()){
			if(!(c instanceof JCheckBox)) continue;

			JCheckBox box = ((JCheckBox) c);
			
			if(box.isSelected()){
				System.out.println(box.getText());
				selectedRobots.add(box.getText());
			}
		}
		
		//Display data
		Robot r = main.getRobotByNumber(selectedRobot);
		for(RobotPanel robotPanel: main.frame.robotPanels){
			robotPanel.loadData(r, selectedMatches, selectedLabels);

		}
		
		if(checkBoxLabel.contains("Robot:")) setOptions(); //it must be a change of robots
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		String text = robotSearch.getText();
		
		if(e.getSource() == robotSearch && !text.equals("")){ 
			
			ArrayList<Integer> containedRobotNumbers = new ArrayList<>();
			
			for(Robot robot: main.robots){
				if((robot.number + "").startsWith(text)){
					containedRobotNumbers.add(robot.number);
				}

			}
			
			resetRobotBoxes();
			
			ArrayList<Component> componentsToRemove = new ArrayList<>();
			
			for(Component c : robotNumberPanel.getComponents()){
				if(!(c instanceof JCheckBox)) continue;

				JCheckBox box = ((JCheckBox) c);
				
				if(!containedRobotNumbers.contains(Integer.parseInt(box.getText().replace("Robot: ", "")))){
					componentsToRemove.add(box);
				}
			}
			
			for(Component component: componentsToRemove){
				robotNumberPanel.remove(component);
			}
			
			SwingUtilities.updateComponentTreeUI(this);
			
		}else if(e.getSource() == robotSearch && text.equals("")){
			resetRobotBoxes();
		}
	}

	
	private void resetRobotBoxes(){
		robotNumberPanel.removeAll();
		
		Integer[] robots = new Integer[main.robots.size()];
		
		for(int i=0;i<robots.length;i++){
			robots[i] = main.robots.get(i).number;
		}
		
		Arrays.sort(robots);
		
		for(int robot: robots){
			JCheckBox checkBox = new JCheckBox("Robot: " + robot);
			
			robotNumberPanel.add(checkBox);	
			checkBox.addItemListener(this);
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
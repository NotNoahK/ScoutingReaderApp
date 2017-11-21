package ca.lakeeffectrobotics.scoutingreader.gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ca.lakeeffectrobotics.scoutingreader.Main;
import ca.lakeeffectrobotics.scoutingreader.Match;
import ca.lakeeffectrobotics.scoutingreader.Robot;

public class DataSelection extends JFrame implements ItemListener, MouseListener{
	
	JPanel contentPane;
	
	int selectedRobot = 0;
	JComboBox<Integer> robotSelector;
	
	static JFrame frame;
	
	JPanel matchPanel;
	JPanel dataPanel;
	
	JCheckBox allMatches;
	JCheckBox allData;
	JButton clearDataButton;
	
	Main main;
	ArrayList<Integer> selectedMatches = new ArrayList<>();
	ArrayList<String> selectedLabels = new ArrayList<>();
	
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(null);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		

		bottomPanel.setLayout(new BorderLayout(0,0));
		
		allMatches = new JCheckBox("All Matches");
		bottomPanel.add(allMatches, BorderLayout.WEST);
		allMatches.addItemListener(this);
		
		allData = new JCheckBox("All Data");
		bottomPanel.add(allData, BorderLayout.EAST);
		allData.addItemListener(this);
		
		
		clearDataButton = new JButton("Clear Data");
		bottomPanel.add(clearDataButton, BorderLayout.CENTER);

		
		JSplitPane matchPane = new JSplitPane();
		matchPane.setBorder(null);
		matchPane.setDividerSize(0);
		matchPane.setResizeWeight(0.1);
		contentPane.add(matchPane, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Robot:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		matchPane.setLeftComponent(lblNewLabel);
		
		robotSelector = new JComboBox<Integer>();
		
		Integer[] robots = new Integer[main.robots.size()];
		
		for(int i=0;i<robots.length;i++){
			robots[i] = main.robots.get(i).number;
		}
		Arrays.sort(robots);
		
		robotSelector.setModel(new DefaultComboBoxModel<Integer>(robots));
		robotSelector.setToolTipText("");
		matchPane.setRightComponent(robotSelector);

		
		JSplitPane buttonPane = new JSplitPane();
		buttonPane.setResizeWeight(0.5);
		buttonPane.setBorder(null);
		buttonPane.setDividerSize(0);
		contentPane.add(buttonPane, BorderLayout.CENTER);
		
		JScrollPane leftPane = new JScrollPane();
		leftPane.getVerticalScrollBar().setUnitIncrement(20);
		buttonPane.setLeftComponent(leftPane);
		
		matchPanel = new JPanel();
		leftPane.setViewportView(matchPanel);
		matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.Y_AXIS));
		
		JLabel lblMatch = new JLabel("Match");
		lblMatch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMatch.setHorizontalAlignment(SwingConstants.CENTER);
		matchPanel.add(lblMatch);
		
		
		JScrollPane rightPane = new JScrollPane();
		rightPane.getVerticalScrollBar().setUnitIncrement(20);
		buttonPane.setRightComponent(rightPane);
				dataPanel = new JPanel();
		rightPane.setViewportView(dataPanel);
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		
		JLabel lblData = new JLabel("Data");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblData.setAlignmentX(0.5f);
		dataPanel.add(lblData);
		
		robotSelector.addItemListener(this);
		clearDataButton.addMouseListener(this);
		
		setVisible(true);
		setOptions();
	}

	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		if(itemEvent.getSource() == robotSelector){
			loadingNewRobot = true;
			
			//Old robot that was previously selected
			if(itemEvent.getStateChange() == ItemEvent.DESELECTED) return;
			//The same robot
			if((Integer) robotSelector.getSelectedItem() == selectedRobot) return;
			//Setup checkboxes
			getSelectedData();
			setOptions();
			
			loadingNewRobot = false;
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
			
			getSelectedData();
		}
	}
	
	/**
	 * Setup the check boxes 
	 */
	
	void setOptions(){
		selectedRobot = (Integer) robotSelector.getSelectedItem();
		
		System.out.println(selectedRobot);
		
		Robot robot = main.getRobotByNumber(selectedRobot);
		
		if(robot == null) return;
		
		//Delete all existing checkboxes
		matchPanel.removeAll();
		dataPanel.removeAll();
		
		//Create match checkboxes
		for(Match m : robot.matches){
			System.out.println(m.number);
			JCheckBox checkBox = new JCheckBox("Match: " + m.number);
			matchPanel.add(checkBox);	
			checkBox.addItemListener(this);
			matchBoxes.add(checkBox);
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
	
	void getSelectedData(){
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
		
		//Display data
		Robot r = main.getRobotByNumber(selectedRobot);
		main.frame.robotPanel.loadData(r, selectedMatches, selectedLabels);
	}
	
	public void clearBoxes(){
		for(Component c : matchPanel.getComponents()){
			if(!(c instanceof JCheckBox)) continue;

			JCheckBox box = ((JCheckBox) c);
			
			box.setSelected(false);
		}
		for(Component c : dataPanel.getComponents()){
			if(!(c instanceof JCheckBox)) continue;

			JCheckBox box = ((JCheckBox) c);
			
			box.setSelected(false);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getComponent() == clearDataButton){
			System.out.println("Clear Data");
			clearBoxes();
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
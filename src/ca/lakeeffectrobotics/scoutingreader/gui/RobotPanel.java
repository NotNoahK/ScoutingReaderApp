package ca.lakeeffectrobotics.scoutingreader.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import ca.lakeeffectrobotics.scoutingreader.Main;
import ca.lakeeffectrobotics.scoutingreader.Match;
import ca.lakeeffectrobotics.scoutingreader.Robot;

public class RobotPanel extends JPanel implements MouseListener {

//	Robot robot;
	
	Main main;
	
	JTextPane dataText = new JTextPane();

	
	/**
	 * Create the panel.
	 */
	public RobotPanel(Main main) {
		this.main = main;
		
		setBackground(new Color(52, 52, 52));
//		robot = new Robot("Test Team", 1111, "Sample Sample");
		setBounds(100, 100, 800, 500);
		setLayout(new BorderLayout(0, 0));
		
		
		JLabel title = new JLabel(main.robot.number + " | " + main.robot.name + "  | " + main.robot.hint);
		title.setForeground(Color.WHITE);
		title.setBorder(BorderFactory.createLineBorder(new Color(74, 42, 124), 5));
		title.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		try {
			title.setFont(Font.createFont(Font.TRUETYPE_FONT, RobotPanel.class.getResourceAsStream("/TitilliumWeb-Regular.ttf")).deriveFont(40f));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		add(title, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(52, 52, 52));
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel image = new JLabel("");
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		Image icon = new ImageIcon(RobotPanel.class.getResource("/ca/lakeeffectrobotics/scoutingreader/1111.png")).getImage();
		icon = icon.getScaledInstance(320, 240, java.awt.Image.SCALE_SMOOTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);
		image.setIcon(new ImageIcon(icon));
		panel.add(image);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1);
		
		dataText.setText("Placeholder");
		dataText.setEditable(false);
//		SimpleAttributeSet aSet = new SimpleAttributeSet();
//		StyleConstants.setLineSpacing(aSet, 50);
//		pane.setParagraphAttributes(0, pane.getLength(), attr, replace);    TODO add paragraph spacing
		
		try {
			dataText.setFont(Font.createFont(Font.TRUETYPE_FONT, RobotPanel.class.getResourceAsStream("/TitilliumWeb-Regular.ttf")).deriveFont(20f));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		String paneText = "";
		
//		pane.setText("test\nhelo\nasdf");
		
		Match match = main.robot.getMatch(10);
		
		for(int i=0; i < match.strings.data.size(); i++){
			paneText += match.strings.labels.get(i) + ": " + match.strings.data.get(i) + "\n";
		}
		for(int i=0; i < match.floats.data.size(); i++){
			float number = match.floats.data.get(i);
			String numString = "";
			if((number+1) % (int) (number+1) == 0 || number == 0.0){
				numString = (int) number + "";
			}else{
				numString = number + "";
			}
			paneText += match.floats.labels.get(i) + ": " + numString + "\n";
		}
		for(int i=0; i < match.booleans.data.size(); i++){
			paneText += match.booleans.labels.get(i) + ": " + match.booleans.data.get(i) + "\n";
		}
		
		dataText.setText(paneText);
		
		dataText.setBackground(new Color(52, 52, 52));
		dataText.setForeground(Color.WHITE);
		panel.add(dataText);
		
		
		JSplitPane buttonsPane = new JSplitPane();
		buttonsPane.setBorder(null);
		buttonsPane.setDividerSize(0);
		buttonsPane.setResizeWeight(0.95);
		add(buttonsPane, BorderLayout.SOUTH);
		
		JButton chooseData = new JButton("Choose Data");
		chooseData.setForeground(Color.WHITE);
		chooseData.setBackground(new Color(150, 150, 150));
		chooseData.setOpaque(true);
		chooseData.addMouseListener(this);
//		chooseData.setIcon((Icon) icon);
		buttonsPane.setLeftComponent(chooseData);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Average Data");
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(new Color(150, 150, 150));
		chckbxNewCheckBox.setOpaque(true);
//		chckbxNewCheckBox.setIcon((Icon) icon);
		buttonsPane.setRightComponent(chckbxNewCheckBox);
		
		
	}
	
	public void loadData(Robot robot, ArrayList<Integer> matchNums, ArrayList<String> labels){
		String paneText = "";
		
		for(int matchNum: matchNums){
			paneText += "Match " + matchNum + ":\n\n";
			
			for(String label: labels){
				Match match = robot.getMatchByNumber(matchNum);
				
				String data = match.getAllDataByLabel(label);
				String labelOut = "";
//				if(label.contains("auto")) labelOut += "Auto ";
//				if(label.contains("tele")) labelOut += "Tele ";
//				if(label.contains("High")) labelOut += "high goals ";
//				if(label.contains("Low")) labelOut += "low goals ";
//				if(label.contains("Gear")) labelOut += "gears ";
//				if(label.contains("Baseline")) labelOut += "baseline reached";
//				if(label.contains("Did Climb")) labelOut += "Climed";
//				if(label.contains("died")) labelOut += "Died on field";
//				if(label.contains("Drive Rating")) labelOut += "Drive rating";
//				if(label.contains("defense")) labelOut += "Played defense";
//				if(label.contains("rotors")) labelOut += "Rotors turning";
//				if(label.contains("Comments")) labelOut += "Comments";
//				if(label.contains("Scout")) labelOut += "Scout";
//				if(label.contains("Complete")) labelOut += "scored";
//				if(label.contains("Missed")) labelOut += "failed";
//				if(label.contains("Dropped")) labelOut += "dropped";
				labelOut = label;
				paneText += labelOut + ": " + data + "\n";
			}
			
			paneText += "\n";
			
		}
		
		dataText.setText(paneText);
	}
	
	public String formatData(){
		String out = "";
		
		return out;
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
	public void mouseReleased(MouseEvent e) {
		new DataSelection(main);
	}
}

class SmoothLabel extends JLabel {

    public SmoothLabel(String text) {
        super(text);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        super.paintComponent(g2d);
    }
}

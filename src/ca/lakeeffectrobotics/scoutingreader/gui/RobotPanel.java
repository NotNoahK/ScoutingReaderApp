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
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ca.lakeeffectrobotics.scoutingreader.Robot;
import javax.swing.JTextPane;

public class RobotPanel extends JPanel {

	Robot robot;
	
	/**
	 * Create the panel.
	 */
	public RobotPanel() {
		setBackground(new Color(52, 52, 52));
		robot = new Robot("Test Team", 1111, "This is NOT a test");
		setBounds(100, 100, 800, 500);
		setLayout(new BorderLayout(0, 0));
		
		
		JLabel title = new JLabel(robot.number + " | " + robot.name + "  | " + robot.hint);
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
		
		JTextPane pane = new JTextPane();
		pane.setText("test\nhelo\nasdf");
		pane.setBackground(new Color(52, 52, 52));
		pane.setForeground(Color.WHITE);
		panel.add(pane);
		
		
		JButton chooseData = new JButton("Choose Data");
		chooseData.setForeground(Color.WHITE);
		chooseData.setBackground(new Color(150, 150, 150));
		chooseData.setOpaque(true);
		add(chooseData, BorderLayout.SOUTH);
		
		
	}
	
	public String formatData(){
		String out = "";
		
		return out;
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

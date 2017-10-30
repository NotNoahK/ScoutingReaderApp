package ca.lakeeffectrobotics.scoutingreader.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ScrollPaneConstants;

public class RobotView extends JPanel {

	/**
	 * Create the panel.
	 */
	public RobotView() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("2708 | Lake Effect Robotics");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblNewLabel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel Image = new JLabel("");
		Image.setHorizontalAlignment(SwingConstants.CENTER);
		Image.setIcon(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg"));
		panel.add(Image);
		
			
		JButton btnNewButton = new JButton("Button");
		add(btnNewButton, BorderLayout.SOUTH);
		
		
	}
}

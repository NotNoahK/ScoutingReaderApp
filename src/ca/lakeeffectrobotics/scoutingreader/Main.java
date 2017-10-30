package ca.lakeeffectrobotics.scoutingreader;

import java.io.File;
import java.util.ArrayList;

import ca.lakeeffectrobotics.scoutingreader.gui.MainFrame;

public class Main {

	String dir = "";
	
	MainFrame frame;
	
	ArrayList<Robot> robots;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		frame = new MainFrame();
		frame.setVisible(true);
	}
	
	public void loadData(String dir){
		File f = new File(dir);
		
	}
	
	public Robot getRobotByNumber(int robotNumber){
		for(Robot robot: robots){
			if(robot.number == robotNumber){
				return robot;
			}
		}
		
		return null;
	}

}



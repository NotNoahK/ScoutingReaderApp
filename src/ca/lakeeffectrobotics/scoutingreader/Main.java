package ca.lakeeffectrobotics.scoutingreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		try {
	
			File[] files = new File(dir).listFiles();
			
	        if(files == null){
	        	//no files in the directory
	        	System.err.println("NO ROBOT DATA IN DIRECTORY");
	        	return;
	        }
	
	        ArrayList<String> data = new ArrayList<>();
	
	        for(int i=0;i<files.length;i++){
	            BufferedReader br;
				br = new BufferedReader(new FileReader(files[i]));
				
	            String line;
	
	            int lineNum = 0;
	            while ((line = br.readLine()) != null) {
	                if(lineNum > 0) {
	                    data.add(line);
	                }
	                lineNum ++;
	            }
	            br.close();
	        }
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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



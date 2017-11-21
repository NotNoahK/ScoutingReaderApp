<<<<<<< HEAD
package ca.lakeeffectrobotics.scoutingreader;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ca.lakeeffectrobotics.scoutingreader.gui.MainFrame;

public class Main {

	String dir = "";
	
	public MainFrame frame;
	
	public ArrayList<Robot> robots;
	
	public Robot robot;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		robots = loadData("src/data/");
		
		
		robot = robots.get(28);
		
		
		
		System.out.println("Main Function");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame(Main.this);
					System.out.println("Frame variable set");
					
					frame.setVisible(true);
					System.out.println("Frame visible");
				}
				
				catch (Exception e) {
				e.printStackTrace();
				}
				
			}
		});
	}
	
	public ArrayList<Robot> loadData(String dir){
        ArrayList<Robot> robots = new ArrayList<>();
		
		try {
	
			File[] files = new File(dir).listFiles();
			
			//no files in the directory
	        if(files == null){
	        	System.err.println("NO ROBOT DATA IN DIRECTORY");
	        	return null;
	        }
	
	        for(int i=0;i<files.length;i++){
	            BufferedReader br;
				br = new BufferedReader(new FileReader(files[i]));
				
				System.out.print(files[i]);
				
				String line;
	            String file = "";
	
	            while ((line = br.readLine()) != null) {
                	file += line + "\n";
	            }
	            
	            br.close();
	            
	            //Set text on the top
	            Robot robot = new Robot("NAME", Integer.parseInt(files[i].getName().replace(".csv", "")), "This is where a note would go if we had anything to tell you");
				robot.setMatchData(file);
	            robots.add(robot);
	        }
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return robots;
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


=======
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


>>>>>>> 9d27cad64a3d481f8744330f844b8c012bfd57f5

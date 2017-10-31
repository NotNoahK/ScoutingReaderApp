package ca.lakeeffectrobotics.scoutingreader;
import java.util.Arrays;
import java.util.List;


public class Robot {

	public int number;
	public String name;
	public String hint;
	
	public String path;
	
	public Dictionary<String> matchStrings;
	public Dictionary<Float> matchFloats;
	public Dictionary<Boolean> matchBooleans;

	public Dictionary<String> pitStrings;
	public Dictionary<Float> pitFloats;
	public Dictionary<Boolean> pitBooleans;
	
	public Robot(int number, String filePath){
		this.number = number;
		this.path = filePath;
		
		//TODO load file from path and call setMatchData
		
	}
	
	//temp
	/**
	 * Used to add fake data for layout testing
	 */
	public Robot(String name, int number, String hint){
		this.number = number;
		this.name = name;
		this.hint = hint;
	}
	
	/**
	 * Takes match data and loads it in the class
	 * @param data
	 */
	public void setMatchData(String matchData){
		splitData(matchData, matchStrings, matchFloats, matchBooleans);
	}
	
	/**
	 * Takes pit data and loads it in the class
	 * @param data
	 */
	public void setPitData(String pitData){
		splitData(pitData, pitStrings, pitFloats, pitBooleans);
	}
	
	
	/**
	 * 
	 * Splits the data and adds to the input dictionaries
	 * 
	 * @param fullData
	 * @param strings
	 * @param floats
	 * @param booleans
	 */
	public static void splitData(String fullData, Dictionary<String> strings, Dictionary<Float> floats, Dictionary<Boolean> booleans){
		List<String> lines = Arrays.asList(fullData.split("\n"));
		
		String[] labels = lines.get(0).split(",");
		lines.remove(0);
		
		for(String line: lines){
			String[] data = line.split(",");
			
			for(int i=0;i<data.length;i++){
				
				//check what type it is
				try{
					floats.add(labels[i], Float.parseFloat(data[i]));
					break;
				}catch(NumberFormatException e){
					//not an int, check for bool
					
					if(data[i].toLowerCase().contains("true")){
						booleans.add(labels[i], true);
						break;
					}
					if(data[i].toLowerCase().contains("false")){
						booleans.add(labels[i], false);
						break;
					}
					
					//must be string
					strings.add(labels[i], data[i]);
				}
			}
			
		}
		
	}

}

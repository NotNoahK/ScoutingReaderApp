package ca.lakeeffectrobotics.scoutingreader;
import java.util.Arrays;
import java.util.List;


public class Robot {

	public int number;
//	public String name; Maybe later
	
	public Dictionary<String> matchStrings;
	public Dictionary<Integer> matchInts;
	public Dictionary<Boolean> matchBooleans;

	public Dictionary<String> pitStrings;
	public Dictionary<Integer> pitInts;
	public Dictionary<Boolean> pitBooleans;
	
	public Robot(int number){
		
	}
	
	/**
	 * Takes match data and loads it in the class
	 * @param data
	 */
	public void setMatchData(String matchData){
		List<String> lines = Arrays.asList(matchData.split("\n"));
		
		String[] labels = lines.get(0).split(",");
		lines.remove(0);
		
		for(String line: lines){
			String[] data = line.split(",");
			
			for(int i=0;i<data.length;i++){
				
				//check what type it is
				
				try{
					matchInts.add(labels[i], Integer.parseInt(data[i]));
				}catch(NumberFormatException e){
					//not an int
					
					//check for bool
					Boolean.parseBoolean("as");
				}
			}
			
		}
	}

}

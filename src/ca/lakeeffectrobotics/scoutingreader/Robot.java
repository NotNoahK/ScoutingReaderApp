<<<<<<< HEAD
package ca.lakeeffectrobotics.scoutingreader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Robot {

	public int number;
	public String name;
	public String hint;
	
	public String path;
	
	public ArrayList<Match> matches = new ArrayList<Match>();

	public Dictionary<String> pitStrings = new Dictionary<>();
	public Dictionary<Float> pitFloats = new Dictionary<>();
	public Dictionary<Boolean> pitBooleans = new Dictionary<>();
	
	/**
	 * Used to add fake data for layout testing
	 */
	
	public Robot(String name, int number, String hint){
		this.number = number;
		this.name = name;
		this.hint = hint;
	}
	
	public Match getMatchByNumber(int matchNumber){
		for(Match match: matches){
			if(match.number == matchNumber){
				return match;
			}
		}
		return null;
	}
	
	/**
	 * Takes match data and loads it in the class
	 * @param data
	 */
	
	public void setMatchData(String matchData){
		splitData(matchData);
	}
	
	/**
	 * Takes pit data and loads it in the class
	 * @param data
	 */
	
	public void setPitData(String pitData){
		splitPitData(pitData, pitStrings, pitFloats, pitBooleans); //TODO DO THIS, different function
	}
	
	/**
	 * Splits the data and adds to the input dictionaries based on the round
	 * 
	 * @param fullData
	 */
	
	public void splitData(String fullData){
		
		Dictionary<String> strings = new Dictionary<>();
		Dictionary<Float> floats = new Dictionary<>();
		Dictionary<Boolean> booleans = new Dictionary<>();
		
		List<String> lines = new ArrayList<>(Arrays.asList(fullData.split("\n")));
		
		String[] labels = lines.get(0).split(",");
		lines.remove(0);
		
		for(String line: lines){
			String[] data = line.split(",");
			
			for(int i=0;i<data.length;i++){
				
				if(labels[i].equals("autoBaselineGroup") || labels[i].equals("autoGear") || labels[i].equals("Did Climb") || labels[i].equals("died") || labels[i].equals("defense")){
					booleans.add(labels[i], Boolean.parseBoolean(data[i]));
					continue;
				}
				
				//check what type it is
				System.out.println(number);
				try{
					switch(labels[i]){
					case "Round":
						continue;
					case "Date and Time Of Match":
						continue;
					case "UUID":
						continue;
					case "end":
						continue;
					}
					
					floats.add(labels[i], Float.parseFloat(data[i]));
					continue;
				} catch(NumberFormatException e){
					//not an int, check for bool
					
					if(data[i].toLowerCase().contains("true")){
						booleans.add(labels[i], true);
						continue;
					}
					if(data[i].toLowerCase().contains("false")){
						booleans.add(labels[i], false);
						continue;
					}
					
					//must be string
					data[i] = "\n\t"+data[i];
					data[i] = data[i].replace("|c", ",").replace("|n", "\n\t").replace("||", "|").replace("|q", "\"").replace("|;", ":");
					strings.add(labels[i], data[i]);
				}
			}

			int matchNum = getMatchNum(line, labels);
			
			Match r = new Match(number, matchNum, "TIME");
			r.setData(strings, floats, booleans);
			matches.add(r);
			
			strings = new Dictionary<>();
			floats = new Dictionary<>();
			booleans = new Dictionary<>();
		}
	}
	
	/**
	 * Gets the match num out of a line of data
	 * 
	 * @param line
	 * @param labels
	 * @return matchNum
	 */
	
	public static int getMatchNum(String line, String[] labels){
		
		int robotNumIndex = 0;
		
		for(int i=0;i<labels.length;i++){
			if(labels[i].equals("Round")){
				robotNumIndex = i;
				break;
			}
		}
		
		return Integer.parseInt(line.split(",")[robotNumIndex]);
	}
	
	/**
	 * Splits the data and adds to the input dictionaries (for pit data)
	 * 
	 * @param fullData
	 * @param strings
	 * @param floats
	 * @param booleans
	 */
	
	public static void splitPitData(String fullData, Dictionary<String> strings, Dictionary<Float> floats ,Dictionary<Boolean> booleans){
		
		List<String> lines = new ArrayList<>(Arrays.asList(fullData.split("\n")));
		
		String[] labels = lines.get(0).split(",");
		lines.remove(0);
		
		for(String line: lines){
			
			String[] data = line.split(",");
			
			for(int i=0;i<data.length;i++){
				
				//check what type it is
				try{
					floats.add(labels[i], Float.parseFloat(data[i]));
					continue;
				}catch(NumberFormatException e){
					//not an int, check for bool
					
					if(data[i].toLowerCase().contains("true")){
						booleans.add(labels[i], true);
						continue;
					}
					if(data[i].toLowerCase().contains("false")){
						booleans.add(labels[i], false);
						continue;
					}
					
					//must be string
					data[i] = data[i].replace("|c", ",").replace("|n", "\n\t").replace("||", "|").replace("|q", "\"").replace("|;", ":");
					strings.add(labels[i], data[i]);
				}
			}
		}
	}
	
	/**
	 * Get match per match number
	 * 
	 * @param matchNum
	 * @return 	Match
	 */
	
	public Match getMatch(int matchNum){
		for(Match match : matches){
			if(match.number == matchNum){
				return match;
			}
		}
		return null;
	}
	
	/**
	 * Gets numbers for all matches played
	 * 
	 * @return A list of matches played
	 */
	
	public int[] getMatchNumbers(){
		int[] numbers = new int[matches.size()];
		for(int i = 0; i < matches.size(); i++){
			numbers[i] = matches.get(i).number;
		}
		return numbers;
	}
}
=======
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
>>>>>>> 9d27cad64a3d481f8744330f844b8c012bfd57f5

package ca.lakeeffectrobotics.scoutingreader;

public class Match {
	
	public int number;
	public int robotNum;
	public String time;
	
	public Dictionary<String> strings = new Dictionary<>();
	public Dictionary<Float> floats = new Dictionary<>();
	public Dictionary<Boolean> booleans = new Dictionary<>();
	
	public Match(int robotNum, int matchNum, String time){
		this.robotNum = robotNum;
		this.number = matchNum;
		this.time = time;
	}
	
	public void setData(Dictionary<String> strings, Dictionary<Float> floats ,Dictionary<Boolean> booleans){
		this.strings = strings;
		this.floats = floats;
		this.booleans = booleans;
	}
	
	public String getAllDataByLabel(String label){
		String string = strings.getData(label);
		if(string != null){
			return string;
		}
		
		Float number = floats.getData(label);
		if(number != null){
			String numString = "";
			float num = number;
			if((num+1) % (int) (num+1) == 0 || num == 0.0){
				numString = (int) num + "";
			}else{
				numString = num + "";
			}
			return numString;
		}
		
		Boolean bool = booleans.getData(label);
		if(bool != null){
			if(bool) return "Yes";
			else return "No";
		}
		return null;
	}
}

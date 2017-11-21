package ca.lakeeffectrobotics.scoutingreader;
import java.util.ArrayList;

public class Dictionary<T> {
	public ArrayList<String> labels = new ArrayList<>();
	public ArrayList<T> data = new ArrayList<>();
	
	public void add(String label, T data){
		labels.add(label);
		this.data.add(data);
	}
	
	public String getLabel(int i){
		return labels.get(i);
	}
	
	public T getData(int i){
		return data.get(i);
	}
	
	public T getData(String label){
		for(String l: labels){
			if(l.equals(label)){
				return data.get(labels.indexOf(l));
			}
		}
		return null;
	}
	
}

package com.dentist.pedodontics.data.models.response.dentition;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CorrectingAnamolyItem{

	@SerializedName("timing")
	private List<String> timing;

	@SerializedName("anamoly")
	private String anamoly;

	public void setTiming(List<String> timing){
		this.timing = timing;
	}

	public List<String> getTiming(){
		return timing;
	}

	public void setAnamoly(String anamoly){
		this.anamoly = anamoly;
	}

	public String getAnamoly(){
		return anamoly;
	}

	@Override
 	public String toString(){
		return 
			"CorrectingAnamolyItem{" + 
			"timing = '" + timing + '\'' + 
			",anamoly = '" + anamoly + '\'' + 
			"}";
		}
}
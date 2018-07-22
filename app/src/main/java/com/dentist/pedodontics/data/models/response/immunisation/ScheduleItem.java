package com.dentist.pedodontics.data.models.response.immunisation;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ScheduleItem{

	@SerializedName("route")
	private String route;

	@SerializedName("immunisation")
	private List<String> immunisation;

	public void setRoute(String route){
		this.route = route;
	}

	public String getRoute(){
		return route;
	}

	public void setImmunisation(List<String> immunisation){
		this.immunisation = immunisation;
	}

	public List<String> getImmunisation(){
		return immunisation;
	}

	@Override
 	public String toString(){
		return 
			"ScheduleItem{" + 
			"route = '" + route + '\'' + 
			",immunisation = '" + immunisation + '\'' + 
			"}";
		}
}
package com.dentist.pedodontics.data.models.response.immunisation;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator") public class ImmunisationData {

  @SerializedName("schedule") private List<ScheduleItem> schedule;

  @SerializedName("age") private String age;

  public void setSchedule(List<ScheduleItem> schedule) {
    this.schedule = schedule;
  }

  public List<ScheduleItem> getSchedule() {
    return schedule;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getAge() {
    return age;
  }

  @Override public String toString() {
    return "ImmunisationData{" + "schedule = '" + schedule + '\'' + ",age = '" + age + '\'' + "}";
  }
}
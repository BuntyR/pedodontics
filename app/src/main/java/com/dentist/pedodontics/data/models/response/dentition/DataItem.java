package com.dentist.pedodontics.data.models.response.dentition;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator") public class DataItem {

  @SerializedName("correctingAnamoly") private List<CorrectingAnamolyItem> correctingAnamoly;

  @SerializedName("developmentPeriod") private String developmentPeriod;

  public void setCorrectingAnamoly(List<CorrectingAnamolyItem> correctingAnamoly) {
    this.correctingAnamoly = correctingAnamoly;
  }

  public List<CorrectingAnamolyItem> getCorrectingAnamoly() {
    return correctingAnamoly;
  }

  public void setDevelopmentPeriod(String developmentPeriod) {
    this.developmentPeriod = developmentPeriod;
  }

  public String getDevelopmentPeriod() {
    return developmentPeriod;
  }

  @Override public String toString() {
    return "DataItem{"
        + "correctingAnamoly = '"
        + correctingAnamoly
        + '\''
        + ",developmentPeriod = '"
        + developmentPeriod
        + '\''
        + "}";
  }
}
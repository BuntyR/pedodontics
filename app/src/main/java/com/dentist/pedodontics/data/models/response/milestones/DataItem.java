package com.dentist.pedodontics.data.models.response.milestones;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator") public class DataItem {

  public DataItem() {
  }

  @SerializedName("expressive") private List<String> expressive;

  @SerializedName("gross") private List<String> gross;

  @SerializedName("fine") private List<String> fine;

  @SerializedName("social") private List<String> social;

  @SerializedName("age") private String age;

  public void setExpressive(List<String> expressive) {
    this.expressive = expressive;
  }

  public List<String> getExpressive() {
    return expressive;
  }

  public void setGross(List<String> gross) {
    this.gross = gross;
  }

  public List<String> getGross() {
    return gross;
  }

  public void setFine(List<String> fine) {
    this.fine = fine;
  }

  public List<String> getFine() {
    return fine;
  }

  public void setSocial(List<String> social) {
    this.social = social;
  }

  public List<String> getSocial() {
    return social;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getAge() {
    return age;
  }

  @Override public String toString() {
    return "DataItem{"
        + "expressive = '"
        + expressive
        + '\''
        + ",gross = '"
        + gross
        + '\''
        + ",fine = '"
        + fine
        + '\''
        + ",social = '"
        + social
        + '\''
        + ",age = '"
        + age
        + '\''
        + "}";
  }
}
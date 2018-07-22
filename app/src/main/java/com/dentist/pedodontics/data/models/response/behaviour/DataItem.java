package com.dentist.pedodontics.data.models.response.behaviour;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator") public class DataItem {

  @SerializedName("parentPresence") private String parentPresence;

  @SerializedName("developmentalStage") private String developmentalStage;

  @SerializedName("managementTechnique") private String managementTechnique;

  @SerializedName("age") private String age;

  public void setParentPresence(String parentPresence) {
    this.parentPresence = parentPresence;
  }

  public String getParentPresence() {
    return parentPresence;
  }

  public void setDevelopmentalStage(String developmentalStage) {
    this.developmentalStage = developmentalStage;
  }

  public String getDevelopmentalStage() {
    return developmentalStage;
  }

  public void setManagementTechnique(String managementTechnique) {
    this.managementTechnique = managementTechnique;
  }

  public String getManagementTechnique() {
    return managementTechnique;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getAge() {
    return age;
  }

  @Override public String toString() {
    return "DataItem{"
        + "parentPresence = '"
        + parentPresence
        + '\''
        + ",developmentalStage = '"
        + developmentalStage
        + '\''
        + ",managementTechnique = '"
        + managementTechnique
        + '\''
        + ",age = '"
        + age
        + '\''
        + "}";
  }
}
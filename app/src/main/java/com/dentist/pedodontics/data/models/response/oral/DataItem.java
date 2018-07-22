package com.dentist.pedodontics.data.models.response.oral;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator") public class DataItem {

  @SerializedName("oralHygieneMethod") private String oralHygieneMethod;

  @SerializedName("toothBrushingFrequency") private String toothBrushingFrequency;

  @SerializedName("developmentalStage") private String developmentalStage;

  @SerializedName("typeOfToothBrush") private String typeOfToothBrush;

  @SerializedName("typeOfToothPaste") private String typeOfToothPaste;

  @SerializedName("toothBrushingMethod") private String toothBrushingMethod;

  @SerializedName("age") private String age;

  @SerializedName("toothPasteQuantity") private String toothPasteQuantity;

  public void setOralHygieneMethod(String oralHygieneMethod) {
    this.oralHygieneMethod = oralHygieneMethod;
  }

  public String getOralHygieneMethod() {
    return oralHygieneMethod;
  }

  public void setToothBrushingFrequency(String toothBrushingFrequency) {
    this.toothBrushingFrequency = toothBrushingFrequency;
  }

  public String getToothBrushingFrequency() {
    return toothBrushingFrequency;
  }

  public void setDevelopmentalStage(String developmentalStage) {
    this.developmentalStage = developmentalStage;
  }

  public String getDevelopmentalStage() {
    return developmentalStage;
  }

  public void setTypeOfToothBrush(String typeOfToothBrush) {
    this.typeOfToothBrush = typeOfToothBrush;
  }

  public String getTypeOfToothBrush() {
    return typeOfToothBrush;
  }

  public void setTypeOfToothPaste(String typeOfToothPaste) {
    this.typeOfToothPaste = typeOfToothPaste;
  }

  public String getTypeOfToothPaste() {
    return typeOfToothPaste;
  }

  public void setToothBrushingMethod(String toothBrushingMethod) {
    this.toothBrushingMethod = toothBrushingMethod;
  }

  public String getToothBrushingMethod() {
    return toothBrushingMethod;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getAge() {
    return age;
  }

  public void setToothPasteQuantity(String toothPasteQuantity) {
    this.toothPasteQuantity = toothPasteQuantity;
  }

  public String getToothPasteQuantity() {
    return toothPasteQuantity;
  }

  @Override public String toString() {
    return "DataItem{"
        + "oral hygiene method = '"
        + oralHygieneMethod
        + '\''
        + ",tooth brushing frequency = '"
        + toothBrushingFrequency
        + '\''
        + ",developmental stage = '"
        + developmentalStage
        + '\''
        + ",type of tooth brush = '"
        + typeOfToothBrush
        + '\''
        + ",type of tooth paste = '"
        + typeOfToothPaste
        + '\''
        + ",tooth brushing method = '"
        + toothBrushingMethod
        + '\''
        + ",age = '"
        + age
        + '\''
        + ",tooth paste quantity = '"
        + toothPasteQuantity
        + '\''
        + "}";
  }
}
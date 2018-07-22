package com.dentist.pedodontics.data.models.response.oral;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator") public class OralHygieneData {

  @SerializedName("data") public List<DataItem> data;

  public void setData(List<DataItem> data) {
    this.data = data;
  }

  public List<DataItem> getData() {
    return data;
  }

  @Override public String toString() {
    return "OralHygieneData{" + "data = '" + data + '\'' + "}";
  }
}
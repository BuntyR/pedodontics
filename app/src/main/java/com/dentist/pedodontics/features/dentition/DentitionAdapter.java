package com.dentist.pedodontics.features.dentition;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dentist.pedodontics.R;
import com.dentist.pedodontics.data.models.response.dentition.DataItem;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import javax.inject.Inject;

public class DentitionAdapter extends RecyclerView.Adapter<DentitionAdapter.DentitionViewHolder> {

  ArrayList<DataItem> mData;

  @Inject public DentitionAdapter() {
  }

  @NonNull @Override
  public DentitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new DentitionViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.rv_item_immunisation, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull DentitionViewHolder holder, int position) {
    holder.bind(mData.get(position));
  }

  public void setData(ArrayList<DataItem> mData) {
    Logger.d("Data in DentitionAdapter : " + new Gson().toJson(mData));
    this.mData = mData;
    notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    return mData != null ? mData.size() : 0;
  }

  public class DentitionViewHolder extends RecyclerView.ViewHolder {

    TextView age;

    public DentitionViewHolder(View itemView) {
      super(itemView);
      age = itemView.findViewById(R.id.age);
    }

    public void bind(DataItem dataItem) {
      age.setText(dataItem.getDevelopmentPeriod());
    }
  }
}

package com.dentist.pedodontics.features.behaviour;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dentist.pedodontics.R;
import com.dentist.pedodontics.data.models.response.behaviour.DataItem;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import javax.inject.Inject;

public class BehaviourAdapter extends RecyclerView.Adapter<BehaviourAdapter.BehaviourViewHolder> {

  ArrayList<DataItem> mData;

  @Inject public BehaviourAdapter() {
  }

  @NonNull @Override
  public BehaviourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new BehaviourViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.rv_item_immunisation, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull BehaviourViewHolder holder, int position) {
    holder.bind(mData.get(position));
  }

  @Override public int getItemCount() {
    return mData != null ? mData.size() : 0;
  }

  public void setData(ArrayList<DataItem> mData) {
    Logger.d("Data in MileStone Adapter : " + new Gson().toJson(mData));
    this.mData = mData;
    notifyDataSetChanged();
  }

  public class BehaviourViewHolder extends RecyclerView.ViewHolder {

    TextView age;

    public BehaviourViewHolder(View itemView) {
      super(itemView);
      age = itemView.findViewById(R.id.age);
    }

    public void bind(DataItem dataItem) {
      age.setText(dataItem.getDevelopmentalStage());
    }
  }
}

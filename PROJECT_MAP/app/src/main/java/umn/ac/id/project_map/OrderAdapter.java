package umn.ac.id.project_map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    //________create number of objects according to your need
    private ArrayList<String> titleDataList;
    private ArrayList<String> messageDataList;

    //________create  constructor with required parameter
    public OrderAdapter(ArrayList<String> titleDataList, ArrayList<String> messageDataList) {

        //________initialize
        this.titleDataList = titleDataList;
        this.messageDataList = messageDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        //________show data in the horizontal listing
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_view, parent, false);


        //________return child view
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //________set data to the objects of each row widget
        holder.tvTitle.setText(titleDataList.get(position));
        holder.tvMessage.setText(messageDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //________create objects of each row widget
        public TextView tvTitle, tvMessage;
        public ViewHolder(View view) {
            super(view);

            //________initialize objects of each row widget
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        }
    }
}

package umn.ac.id.project_map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

    Context context;
    ArrayList<CouponModel> couponArrayList;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    public CouponAdapter(Context context, ArrayList<CouponModel> couponArrayList){
        this.context = context;
        this.couponArrayList = couponArrayList;
    }

    @NonNull
    @Override
    public CouponAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.coupon_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponAdapter.ViewHolder holder, int position){

        CouponModel coupon = couponArrayList.get(position);

        holder.name.setText(coupon.name);
        holder.date.setText(format.format(coupon.date));
    }
// 08.57
    @Override
    public int getItemCount(){
        return couponArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView date, desc, name;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tvTitle);
            date = itemView.findViewById(R.id.tvMessage);

        }

    }

    //________create number of objects according to your need
    private ArrayList<String> titleDataList;
    private ArrayList<String> messageDataList;

    //________create  constructor with required parameter
    public CouponAdapter(ArrayList<String> titleDataList, ArrayList<String> messageDataList) {

        //________initialize
        this.titleDataList = titleDataList;
        this.messageDataList = messageDataList;
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView;
//
//        //________show data in the horizontal listing
//        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_view, parent, false);
//
//
//        //________return child view
//        return new ViewHolder(itemView);
//    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        //________set data to the objects of each row widget
//        holder.tvTitle.setText(titleDataList.get(position));
//        holder.tvMessage.setText(messageDataList.get(position));
//    }

//    @Override
//    public int getItemCount() {
//        return titleDataList.size();
//    }

//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        //________create objects of each row widget
//        public TextView tvTitle, tvMessage;
//        public ViewHolder(View view) {
//            super(view);
//
//            //________initialize objects of each row widget
//            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//            tvMessage = (TextView) view.findViewById(R.id.tvMessage);
//        }
//    }
}

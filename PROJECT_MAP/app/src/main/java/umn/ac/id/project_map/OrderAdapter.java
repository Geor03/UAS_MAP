package umn.ac.id.project_map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    //________create number of objects according to your need
    private ArrayList<String> statusDataList;
    private ArrayList<String> orderidDataList;
    private ArrayList<String> dateOrderDataList;
    private ArrayList<String> priceOrderDataList;

    //________create  constructor with required parameter
    public OrderAdapter(ArrayList<String> statusDataList, ArrayList<String> orderidDataList, ArrayList<String>dateOrderDataList, ArrayList<String>priceOrderDataList) {

        //________initialize
        this.statusDataList = statusDataList;
        this.orderidDataList = orderidDataList;
        this.dateOrderDataList = dateOrderDataList;
        this.priceOrderDataList = priceOrderDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        //________show data in the horizontal listing
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_view, parent, false);


        //________return child view
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //________set data to the objects of each row widget
        holder.status.setText(statusDataList.get(position));
        holder.orderid.setText(orderidDataList.get(position));
        holder.dateOrder.setText(dateOrderDataList.get(position));
        holder.priceOrder.setText(priceOrderDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return statusDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //________create objects of each row widget
        public TextView status, orderid, dateOrder, priceOrder;
        public ViewHolder(View view) {
            super(view);

            //________initialize objects of each row widget
            status = (TextView) view.findViewById(R.id.status);
            orderid = (TextView) view.findViewById(R.id.orderId);
            dateOrder = (TextView) view.findViewById(R.id.dateFirstOrder);
            priceOrder = (TextView) view.findViewById(R.id.priceOrders);
        }
    }
}

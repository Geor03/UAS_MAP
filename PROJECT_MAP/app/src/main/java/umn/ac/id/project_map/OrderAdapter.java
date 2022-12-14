package umn.ac.id.project_map;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    ArrayList<OrderModel>orderArrayList;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public OrderAdapter(ArrayList<OrderModel> orderArrayList, Context context) {
        this.context = context;
        this.orderArrayList = orderArrayList;
    }


    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        //________show data in the horizontal listing
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_view, parent, false);

        //________return child view
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        OrderModel order = orderArrayList.get(position);
        //________set data to the objects of each row widget
        holder.status.setText(order.status);
        holder.address.setText(order.address);
        holder.dateOrder.setText(format.format(order.date));
        holder.priceOrder.setText(String.valueOf(order.total_price));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(order.status.equals("Ongoing")){
                    Intent intentToTrackOrder = new Intent(context, Track_Order.class);
                    intentToTrackOrder.putExtra("order", order.docId);
                    Log.d("Isi order id", order.docId);
                    context.startActivity(intentToTrackOrder);
                }
                else if(order.status.equals("Finished")){
                    Intent intentToTrackOrder = new Intent(context, Receipt.class);
                    intentToTrackOrder.putExtra("order", order.docId);
                    Log.d("Isi order id", order.docId);
                    context.startActivity(intentToTrackOrder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //________create objects of each row widget
        public TextView status, address, dateOrder, priceOrder;
        public ViewHolder(View view) {
            super(view);

            //________initialize objects of each row widget
            status = (TextView) view.findViewById(R.id.status);
            address = (TextView) view.findViewById(R.id.address);
            dateOrder = (TextView) view.findViewById(R.id.dateFirstOrder);
            priceOrder = (TextView) view.findViewById(R.id.priceOrders);
        }
    }
}

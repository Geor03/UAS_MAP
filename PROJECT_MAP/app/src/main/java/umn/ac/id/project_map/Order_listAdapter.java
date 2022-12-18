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

public class Order_listAdapter extends RecyclerView.Adapter<Order_listAdapter.ViewHolder> {
    private Context context;
    private ArrayList<OrderModel> order_ListArrayList;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    public Order_listAdapter(Context context, ArrayList<OrderModel> order_ListArrayList) {
        this.context = context;
        this.order_ListArrayList = order_ListArrayList;
    }

    @NonNull
    @Override
    public Order_listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_card_layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Order_listAdapter.ViewHolder holder, int position) {
        OrderModel order_ListModel = order_ListArrayList.get(position);
        holder.tvOrderListStatus.setText(order_ListModel.status);
        holder.tvOrderListOrderID.setText(order_ListModel.address);
        holder.tvOrderListDate.setText(format.format(order_ListModel.date));
        holder.tvOrderListPrice.setText(String.valueOf(order_ListModel.total_price));
    }

    @Override
    public int getItemCount() {
        return order_ListArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderListStatus;
        private TextView tvOrderListOrderID;
        private TextView tvOrderListDate;
        private TextView tvOrderListPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderListStatus = itemView.findViewById(R.id.tvOrderListStatus);
            tvOrderListOrderID = itemView.findViewById(R.id.address);
            tvOrderListDate = itemView.findViewById(R.id.tvOrderListDate);
            tvOrderListPrice = itemView.findViewById(R.id.tvOrderListPrice);
        }
    }
}

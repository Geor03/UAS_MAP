package umn.ac.id.project_map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Order_listAdapter extends RecyclerView.Adapter<Order_listAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order_listMOdel> order_ListArrayList;

    public Order_listAdapter(Context context, ArrayList<Order_listMOdel> order_ListArrayList) {
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
        Order_listMOdel order_ListModel = order_ListArrayList.get(position);
        holder.tvOrderListStatus.setText(order_ListModel.getTvOrderListStatus());
        holder.tvOrderListOrderID.setText(order_ListModel.getTvOrderListOrderID());
        holder.tvOrderListDate.setText(order_ListModel.getTvOrderListDate());
        holder.tvOrderListPrice.setText(order_ListModel.getTvOrderListPrice());
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
            tvOrderListOrderID = itemView.findViewById(R.id.tvOrderListOrderID);
            tvOrderListDate = itemView.findViewById(R.id.tvOrderListDate);
            tvOrderListPrice = itemView.findViewById(R.id.tvOrderListPrice);
        }
    }
}

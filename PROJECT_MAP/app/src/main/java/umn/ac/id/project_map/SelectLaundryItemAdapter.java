package umn.ac.id.project_map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectLaundryItemAdapter extends RecyclerView.Adapter<SelectLaundryItemAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<SelectLaundryItemModel> laundryModelArrayList;

    // Constructor
    public SelectLaundryItemAdapter(Context context, ArrayList<SelectLaundryItemModel> laundryModelArrayList) {
        this.context = context;
        this.laundryModelArrayList = laundryModelArrayList;
    }

    @NonNull
    @Override
    public SelectLaundryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_laundry_item_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectLaundryItemAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        SelectLaundryItemModel model = laundryModelArrayList.get(position);

        holder.laundryNameTV.setText(model.item_name);
//        holder.laundryIV.setImageResource(model.gv);
        holder.qty.setText(String.valueOf(model.qty));
        holder.add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                model.addToQuantity();
                holder.qty.setText(String.valueOf(model.qty));
                notifyDataSetChanged();
            }
        });
        holder.min.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                model.removeFromQuantity();
                holder.qty.setText(String.valueOf(model.qty));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return laundryModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView laundryIV;
        private final TextView laundryNameTV;
        private final TextView qty;
        private final Button add;
        private final Button min;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            laundryIV = itemView.findViewById(R.id.idIVSelectLaundryItemImage);
            laundryNameTV = itemView.findViewById(R.id.item_name);
            qty = itemView.findViewById(R.id.qty);
            add = (Button) itemView.findViewById(R.id.button_plus);
            min = (Button) itemView.findViewById(R.id.button_minus);

        }
    }
}

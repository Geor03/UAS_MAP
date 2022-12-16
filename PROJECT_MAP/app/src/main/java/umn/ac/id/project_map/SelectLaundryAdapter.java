package umn.ac.id.project_map;

//public class LaundryAdapter {
//
//}

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectLaundryAdapter extends RecyclerView.Adapter<SelectLaundryAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<SelectLaundryModel> laundryModelArrayList;

    // Constructor
    public SelectLaundryAdapter(Context context, ArrayList<SelectLaundryModel> laundryModelArrayList) {
        this.context = context;
        this.laundryModelArrayList = laundryModelArrayList;
    }

    @NonNull
    @Override
    public SelectLaundryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_laundry_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectLaundryAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        SelectLaundryModel model = laundryModelArrayList.get(position);
        holder.selectlaundryNameTV.setText(model.getLaundry_name());
//        holder.LaundryRatingTV.setText("" + model.getLaundry_rating());
        holder.selectlaundryIV.setImageResource(model.getLaundry_image());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentToSelectOutlet = new Intent(view.getContext(), SelectLaundryOutlet.class);
                intentToSelectOutlet.putExtra("laundry_type", model.getLaundry_name());
                view.getContext().startActivity(intentToSelectOutlet);
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
        public ImageView selectlaundryIV;
        public TextView selectlaundryNameTV;
        //private final TextView laundryRatingTV;

        public ViewHolder(@NonNull View view) {
            super(view);
            selectlaundryIV = view.findViewById(R.id.idIVSelectLaundryImage);
            selectlaundryNameTV = view.findViewById(R.id.idTVSelectLaundryName);
            //laundryRatingTV = itemView.findViewById(R.id.idTVLaundryRating);
        }
    }
}

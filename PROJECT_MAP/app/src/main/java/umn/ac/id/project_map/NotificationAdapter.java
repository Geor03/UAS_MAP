package umn.ac.id.project_map;

//public class LaundryAdapter {
//
//}

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<NotificationModel> laundryModelArrayList;

    // Constructor
    public NotificationAdapter(Context context, ArrayList<NotificationModel> laundryModelArrayList) {
        this.context = context;
        this.laundryModelArrayList = laundryModelArrayList;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        NotificationModel model = laundryModelArrayList.get(position);
        holder.laundryNameTV.setText(model.getLaundry_name());
//        holder.LaundryRatingTV.setText("" + model.getLaundry_rating());
        holder.laundryIV.setText(model.getLaundry_message());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return laundryModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView laundryIV;
        private final TextView laundryNameTV;
        //private final TextView laundryRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            laundryIV = itemView.findViewById(R.id.idIVLaundryImage);
            laundryNameTV = itemView.findViewById(R.id.idTVLaundryName);
            //laundryRatingTV = itemView.findViewById(R.id.idTVLaundryRating);
        }
    }
}

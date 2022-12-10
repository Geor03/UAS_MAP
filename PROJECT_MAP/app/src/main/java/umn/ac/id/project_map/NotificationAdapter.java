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
    private final ArrayList<NotificationModel> notificationModelArrayList;

    // Constructor
    public NotificationAdapter(Context context, ArrayList<NotificationModel> notificationModelArrayList) {
        this.context = context;
        this.notificationModelArrayList = notificationModelArrayList;
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

        NotificationModel model = notificationModelArrayList.get(position);
        holder.notificationNameTV.setText(model.getNotification_title());
//        holder.LaundryRatingTV.setText("" + model.getLaundry_rating());
        holder.notificationMessageTV.setText(model.getNotification_message());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return notificationModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView notificationMessageTV;
        private final TextView notificationNameTV;
        //private final TextView laundryRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationMessageTV = itemView.findViewById(R.id.idTVMessage);
            notificationNameTV = itemView.findViewById(R.id.idTVTitle);
            //laundryRatingTV = itemView.findViewById(R.id.idTVLaundryRating);
        }
    }
}

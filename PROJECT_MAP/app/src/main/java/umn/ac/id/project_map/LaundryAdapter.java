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
        import java.util.ArrayList;

public class LaundryAdapter extends RecyclerView.Adapter<LaundryAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<LaundryModel> laundryModelArrayList;

    // Constructor
    public LaundryAdapter(Context context, ArrayList<LaundryModel> laundryModelArrayList) {
        this.context = context;
        this.laundryModelArrayList = laundryModelArrayList;
    }

    @NonNull
    @Override
    public LaundryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaundryAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        LaundryModel model = laundryModelArrayList.get(position);
        holder.laundryNameTV.setText(model.getLaundry_name());
//        holder.LaundryRatingTV.setText("" + model.getLaundry_rating());
        holder.laundryIV.setImageResource(model.getLaundry_image());
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
        //private final TextView laundryRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            laundryIV = itemView.findViewById(R.id.idIVLaundryImage);
            laundryNameTV = itemView.findViewById(R.id.idTVLaundryName);
            //laundryRatingTV = itemView.findViewById(R.id.idTVLaundryRating);
        }
    }
}

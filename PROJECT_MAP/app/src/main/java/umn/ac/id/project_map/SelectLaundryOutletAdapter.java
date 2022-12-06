package umn.ac.id.project_map;

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

public class SelectLaundryOutletAdapter extends RecyclerView.Adapter<SelectLaundryOutletAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<SelectLaundryOutletModel> laundryModelArrayList;

    // Constructor
    public SelectLaundryOutletAdapter(Context context, ArrayList<SelectLaundryOutletModel> laundryModelArrayList) {
        this.context = context;
        this.laundryModelArrayList = laundryModelArrayList;
    }

    @NonNull
    @Override
    public SelectLaundryOutletAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_laundry_outlet_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectLaundryOutletAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        SelectLaundryOutletModel model = laundryModelArrayList.get(position);

        holder.laundryNameTV.setText(model.outlet_name);
        holder.laundryDesc.setText(model.tagline);
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return laundryModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView laundryIV;
        public TextView laundryNameTV;
        public TextView laundryDesc;
        //private final TextView laundryRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            laundryIV = itemView.findViewById(R.id.idIVLaundryImage);
            laundryNameTV = (TextView) itemView.findViewById(R.id.idTVLaundryName);
            laundryDesc = (TextView) itemView.findViewById(R.id.idTVSelectOutletDesc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentToSelectOutlet = new Intent(view.getContext(), SelectLaundryItem.class);
                    view.getContext().startActivity(intentToSelectOutlet);
                }
            });
            //laundryRatingTV = itemView.findViewById(R.id.idTVLaundryRating);
        }
    }
}
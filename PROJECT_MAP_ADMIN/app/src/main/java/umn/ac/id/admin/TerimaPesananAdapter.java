package umn.ac.id.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TerimaPesananAdapter extends RecyclerView.Adapter<TerimaPesananAdapter.TerimaPesananViewHolder>{

    Context context;
    ArrayList<TerimaPesananModel> TerimaPesananModelArrayList;


    public TerimaPesananAdapter(Context context, ArrayList<TerimaPesananModel> TerimaPesananModelArrayList){
        this.context = context;
        this.TerimaPesananModelArrayList = TerimaPesananModelArrayList;
    }



    @NonNull
    @Override
    public TerimaPesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_terimapesanan, parent, false);

        return new TerimaPesananViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TerimaPesananViewHolder holder, int position) {

        TerimaPesananModel terimaPesananModel = TerimaPesananModelArrayList.get(position);
        holder.tvNamaPelanggan.setText(terimaPesananModel.tvNamaPelanggan);
        holder.tvTotalItem.setText(terimaPesananModel.tvTotalItem);

    }

    @Override
    public int getItemCount() {
        return TerimaPesananModelArrayList.size();
    }

    public static class TerimaPesananViewHolder extends RecyclerView.ViewHolder{

        TextView tvNamaPelanggan;
        TextView tvTotalItem;
        Button btnTerimaPesanan;

        public TerimaPesananViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaPelanggan = itemView.findViewById(R.id.tvNamaPelanggan);
            tvTotalItem = itemView.findViewById(R.id.tvTotalItem);
            btnTerimaPesanan = itemView.findViewById(R.id.btnTerimaPesanan);
        }
    }
}

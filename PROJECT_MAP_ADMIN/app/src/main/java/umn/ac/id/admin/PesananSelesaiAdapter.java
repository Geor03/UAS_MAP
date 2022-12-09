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

public class PesananSelesaiAdapter extends RecyclerView.Adapter<PesananSelesaiAdapter.PesananSelesaiHolder> {
    Context context;
    ArrayList<PesananSelesaiModel> PesananSelesaiArrayList;

    public PesananSelesaiAdapter(Context context, ArrayList<PesananSelesaiModel> pesananSelesaiArrayList) {
        this.context = context;
        this.PesananSelesaiArrayList = pesananSelesaiArrayList;
    }

    @NonNull
    @Override
    public PesananSelesaiAdapter.PesananSelesaiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_pesananselesai, parent, false);

        return new PesananSelesaiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananSelesaiHolder holder, int position) {
        PesananSelesaiModel pesananSelesaiModel = PesananSelesaiArrayList.get(position);
        holder.tvNamaPelanggan.setText(pesananSelesaiModel.tvNamaPelanggan);
        holder.tvTotalItem.setText(pesananSelesaiModel.tvTotalItem);
    }

    @Override
    public int getItemCount() {
        return PesananSelesaiArrayList.size();
    }

    public static class PesananSelesaiHolder extends RecyclerView.ViewHolder {

        TextView tvNamaPelanggan;
        TextView tvTotalItem;
        Button btnPesananSelesai;

        public PesananSelesaiHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPelanggan = itemView.findViewById(R.id.tvNamaPelanggan);
            tvTotalItem = itemView.findViewById(R.id.tvTotalItem);
            btnPesananSelesai = itemView.findViewById(R.id.btnPesananSelesai);
        }
    }
}

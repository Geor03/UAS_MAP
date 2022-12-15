package umn.ac.id.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PesananSelesaiAdapter extends RecyclerView.Adapter<PesananSelesaiAdapter.PesananSelesaiHolder> {
    Context context;
    ArrayList<PesananModel> PesananSelesaiArrayList;
    public FirebaseFirestore fStore = FirebaseFirestore.getInstance();


    public PesananSelesaiAdapter(Context context, ArrayList<PesananModel> pesananSelesaiArrayList) {
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
        PesananModel pesananSelesaiModel = PesananSelesaiArrayList.get(position);
        DocumentReference documentReference = fStore.collection("users").document(pesananSelesaiModel.customerId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    holder.tvNamaPelanggan.setText(documentSnapshot.getString("fName"));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        holder.tvTotalItem.setText(String.valueOf(pesananSelesaiModel.total_price));
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

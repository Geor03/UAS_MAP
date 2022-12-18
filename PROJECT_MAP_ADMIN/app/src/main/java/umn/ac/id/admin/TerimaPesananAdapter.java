package umn.ac.id.admin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TerimaPesananAdapter extends RecyclerView.Adapter<TerimaPesananAdapter.TerimaPesananViewHolder>{

    Context context;
    ArrayList<PesananModel> TerimaPesananModelArrayList;
    public FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String outletID = firebaseAuth.getCurrentUser().getUid();

    public TerimaPesananAdapter(Context context, ArrayList<PesananModel> TerimaPesananModelArrayList){
        this.context = context;
        this.TerimaPesananModelArrayList = TerimaPesananModelArrayList;
    }

    @NonNull
    @Override
    public TerimaPesananAdapter.TerimaPesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_terimapesanan, parent, false);

        return new TerimaPesananViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TerimaPesananViewHolder holder, int position) {
        PesananModel pesananSelesaiModel = TerimaPesananModelArrayList.get(position);
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
        holder.btnTerimaPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docref = fStore.collection("outlets").document(outletID).collection("orders").document(pesananSelesaiModel.docID);
                docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            docref.update("status", "Ongoing");
                            notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Fail on updating data", "Data gagal diupdate. TerimaPesananAdapter:80");
                    }
                });
            }
        });
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

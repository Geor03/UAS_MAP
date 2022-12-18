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

public class PesananBerlangsungAdapter extends RecyclerView.Adapter<PesananBerlangsungAdapter.PesananBerlangsungHolder> {
    Context context;
    ArrayList<PesananModel> PesananBerlangsungModelArrayList;
    public FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String outletID = firebaseAuth.getCurrentUser().getUid();

    public PesananBerlangsungAdapter(Context context, ArrayList<PesananModel> PesananBerlangsungModelArrayList){
        this.context = context;
        this.PesananBerlangsungModelArrayList = PesananBerlangsungModelArrayList;
    }

    @NonNull
    @Override
    public PesananBerlangsungHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_berlangsungpesanan, parent, false);

        return new PesananBerlangsungHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananBerlangsungHolder holder, int position) {
        PesananModel pesananberlangsungmodel = PesananBerlangsungModelArrayList.get(position);

        DocumentReference documentReference = fStore.collection("users").document(pesananberlangsungmodel.customerId);
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
                Log.d("Query Error", "Failed to get user name on. PesananBerlangsungAdapter:57");
            }
        });
        holder.tvTotalItem.setText(String.valueOf(pesananberlangsungmodel.total_price));
        holder.btnPesananBerlangsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docref = fStore.collection("outlets").document(outletID).collection("orders").document(pesananberlangsungmodel.docID);
                DocumentReference documentReference = fStore.collection("users").document(pesananberlangsungmodel.customerId).collection("orders").document(pesananberlangsungmodel.docID);
                docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            docref.update("status", "Finished");
                            notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Fail on updating data", "Data gagal diupdate. TerimaPesananAdapter:78");
                    }
                });
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            documentReference.update("status", "Finished");
                            notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Fail on updating data", "Data gagal diupdate. TerimaPesananAdapter:92");
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return PesananBerlangsungModelArrayList.size();
    }

    public static class PesananBerlangsungHolder extends RecyclerView.ViewHolder{

        TextView tvNamaPelanggan;
        TextView tvTotalItem;
        Button btnPesananBerlangsung;

        public PesananBerlangsungHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPelanggan = itemView.findViewById(R.id.tvNamaPelanggan);
            tvTotalItem = itemView.findViewById(R.id.tvTotalItem);
            btnPesananBerlangsung = itemView.findViewById(R.id.btnPesananBerlangsung);
        }
    }
}

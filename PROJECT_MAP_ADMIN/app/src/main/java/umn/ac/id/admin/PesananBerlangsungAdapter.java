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

public class PesananBerlangsungAdapter extends RecyclerView.Adapter<PesananBerlangsungAdapter.PesananBerlangsungHolder> {
    Context context;
    ArrayList<PesananBerlangsungModel> PesananBerlangsungModelArrayList;
    public FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public PesananBerlangsungAdapter(Context context, ArrayList<PesananBerlangsungModel> PesananBerlangsungModelArrayList){
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
        PesananBerlangsungModel pesananberlangsungmodel = PesananBerlangsungModelArrayList.get(position);

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

            }
        });
        holder.tvTotalItem.setText(String.valueOf(pesananberlangsungmodel.total_price));
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

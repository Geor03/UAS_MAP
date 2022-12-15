package umn.ac.id.admin;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BerlangsungPesananFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerlangsungPesananFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<PesananBerlangsungModel> PesananBerlangsungArraylist;
    private RecyclerView recyclerviewPesananBerlangsung;
    private PesananBerlangsungAdapter PesananBerlangsungAdapter;
    public FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private String outletId;


    public BerlangsungPesananFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BerlangsungPesananFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BerlangsungPesananFragment newInstance(String param1, String param2) {
        BerlangsungPesananFragment fragment = new BerlangsungPesananFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        fetchData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_berlangsung_pesanan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerviewPesananBerlangsung = view.findViewById(R.id.rvPesananBerlangsung);
        PesananBerlangsungArraylist = new ArrayList<PesananBerlangsungModel>();
        PesananBerlangsungAdapter = new PesananBerlangsungAdapter(getContext(), PesananBerlangsungArraylist);

        recyclerviewPesananBerlangsung.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewPesananBerlangsung.setHasFixedSize(true);
        recyclerviewPesananBerlangsung.setAdapter(PesananBerlangsungAdapter);
        fetchData();
    }

    public void fetchData() {
        outletId = fAuth.getCurrentUser().getUid();
        fStore.collection("outlets").document(outletId).collection("orders").orderBy("address", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        PesananBerlangsungArraylist.add(dc.getDocument().toObject(PesananBerlangsungModel.class));
                    }

                    PesananBerlangsungAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
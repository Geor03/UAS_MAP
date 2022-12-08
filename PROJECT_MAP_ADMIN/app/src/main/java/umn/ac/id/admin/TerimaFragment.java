package umn.ac.id.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TerimaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TerimaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<TerimaPesananModel> TerimaPesananModelArrayList;
    private String[] detailNamaPelanggan;
    private String[] detailTotalItem;
    private RecyclerView recyclerviewTerimaPesanan;

    public TerimaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TerimaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TerimaFragment newInstance(String param1, String param2) {
        TerimaFragment fragment = new TerimaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terima, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerviewTerimaPesanan = view.findViewById(R.id.rvTerimaPesanan);
        recyclerviewTerimaPesanan.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewTerimaPesanan.setHasFixedSize(true);
        TerimaPesananAdapter TerimaPesananAdapter = new TerimaPesananAdapter(getContext(),TerimaPesananModelArrayList);
        recyclerviewTerimaPesanan.setAdapter(TerimaPesananAdapter);
        TerimaPesananAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        TerimaPesananModelArrayList = new ArrayList<>();

        detailNamaPelanggan = new String[]{
                getString(R.string.nama1),
                getString(R.string.nama2)
        };
        detailTotalItem = new String[]{
                getString(R.string.item1),
                getString(R.string.item2),
        };

        for(int i = 0; i < detailNamaPelanggan.length; i++){
            TerimaPesananModel terimaPesananModel = new TerimaPesananModel(detailNamaPelanggan[i], detailTotalItem[i]);
            TerimaPesananModelArrayList.add(terimaPesananModel);
        }
    }
}
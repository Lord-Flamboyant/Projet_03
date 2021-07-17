package com.openclassrooms.entrevoisins.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.FragmentNeighbourListBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.view.adapter.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.utils.DeleteNeighbour;

import java.util.List;


public class NeighbourFragment extends Fragment implements DeleteNeighbour {

    private FragmentNeighbourListBinding mBinding;
    private NeighbourApiService mApiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentNeighbourListBinding.inflate(inflater, container, false);
        View view = mBinding.getRoot();

        Context context = view.getContext();
        mBinding.listNeighbours.setLayoutManager(new LinearLayoutManager(context));
        mBinding.listNeighbours.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        List<Neighbour> neighbours = mApiService.getNeighbours();
        mBinding.listNeighbours.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbours, this));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //----- delete neighbour -----//
    @Override
    public void clickOnTrash(Neighbour neighbour) {
        Toast.makeText(getContext(), getResources().getString(R.string.delete), Toast.LENGTH_SHORT).show();
        DI.getNeighbourApiService().deleteNeighbour(neighbour);
        initList();
    }
}
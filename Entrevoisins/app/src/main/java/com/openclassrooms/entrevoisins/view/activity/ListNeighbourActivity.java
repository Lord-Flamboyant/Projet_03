package com.openclassrooms.entrevoisins.view.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.ActivityListNeighbourBinding;
import com.openclassrooms.entrevoisins.view.fragment.NeighbourFragment;
import com.openclassrooms.entrevoisins.view.fragment.NeighbourFragmentFav;

public class ListNeighbourActivity extends AppCompatActivity {

    private ActivityListNeighbourBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListNeighbourBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        setSupportActionBar(mBinding.toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NeighbourFragment()).commit();
        mBinding.tabs.setOnItemSelectedListener(navLister);
        goToAddActivity();
    }

    public void goToAddActivity() {
        mBinding.addNeighbour.setOnClickListener(v -> AddNeighbourActivity.navigate(this));
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnItemSelectedListener navLister =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.neighbours_tabs:
                        selectedFragment = new NeighbourFragment();
                        break;
                    case R.id.favorite_tab:
                        selectedFragment = new NeighbourFragmentFav();
                        break;
                }
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                        selectedFragment).commit();
                return true;
            };
}
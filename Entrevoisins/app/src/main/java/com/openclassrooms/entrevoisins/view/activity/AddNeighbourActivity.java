package com.openclassrooms.entrevoisins.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.ActivityAddNeighbourBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

public class AddNeighbourActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private ActivityAddNeighbourBinding mBinding;
    private String mNeighbourImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddNeighbourBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();
        init();
        createNeighbour();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this)
                .load(mNeighbourImage)
                .placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(mBinding.avatar);
        mBinding.nameLyt.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                mBinding.create.setEnabled(s.length() > 0);
            }
        });

    }

    private void createNeighbour() {
        mBinding.create.setOnClickListener(v -> {
            Neighbour neighbour = new Neighbour(
                    System.currentTimeMillis(),
                    mBinding.nameLyt.getEditText().getText().toString(),
                    mNeighbourImage,
                    mBinding.addressLyt.getEditText().getText().toString(),
                    mBinding.phoneNumberLyt.getEditText().getText().toString(),
                    mBinding.aboutMeLyt.getEditText().getText().toString(),
                    false
            );
            mApiService.createNeighbour(neighbour);
            finish();
        });
    }

    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }


    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}

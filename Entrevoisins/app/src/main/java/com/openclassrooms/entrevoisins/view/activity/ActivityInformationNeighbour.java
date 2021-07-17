package com.openclassrooms.entrevoisins.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.ActivityInformationNeighbourBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;


public class ActivityInformationNeighbour extends AppCompatActivity {

    private ActivityInformationNeighbourBinding mBinding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityInformationNeighbourBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("infoNeighbour");
        int position = intent.getIntExtra("position", 0);

        uploadNeighbourInformation(neighbour);
        updateFavoriteOrNot(neighbour, position);
        clickOnBack();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();

    }

    //----- upload neighbour information -----//
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void uploadNeighbourInformation(Neighbour neighbour) {
        mBinding.titleNameNeighbourView.setText(neighbour.getName());
        mBinding.nameNeightbourView.setText(neighbour.getName());
        Glide.with(mBinding.imageNeighbourView.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(mBinding.imageNeighbourView);
        mBinding.adressNeighbourView.setText(neighbour.getAddress());
        mBinding.numberPhoneNeighbourView.setText(neighbour.getPhoneNumber());
        mBinding.adressNetView.setText("Facebook.fr/" + neighbour.getName());
        mBinding.detailView.setText(neighbour.getAboutMe());

        uploadStar(neighbour);
    }

    //----- Upload star favorite -----//
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void uploadStar(Neighbour neighbour) {
        if (neighbour.getFav()) {
            mBinding.addFavView.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            mBinding.addFavView.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
        mBinding.addFavView.setImageTintList(ColorStateList.valueOf(getColor(R.color.yellow)));
    }

    //----- Add neighbour to favorite or delete -----//
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updateFavoriteOrNot(Neighbour neighbour, int position) {
        mBinding.addFavView.setOnClickListener(v -> {
            if (!neighbour.getFav()) {
                DI.getNeighbourApiService().addFavNeighbour(neighbour);
                mBinding.addFavView.setImageResource(R.drawable.ic_star_white_24dp);
                Toast.makeText(this, "Neighbour add to favorites list", Toast.LENGTH_SHORT).show();
            } else  {
                DI.getNeighbourApiService().deleteFavNeighbour(neighbour);
                mBinding.addFavView.setImageResource(R.drawable.ic_star_border_white_24dp);
                Toast.makeText(this, "Neighbour delete to favorites list", Toast.LENGTH_SHORT).show();
            }
            mBinding.addFavView.setImageTintList(ColorStateList.valueOf(getColor(R.color.yellow)));
            DI.getNeighbourApiService().getNeighbours().set(position, neighbour);

        });
    }

    //----- Back button -----//
    private void clickOnBack() {
        mBinding.BackButtonView.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}


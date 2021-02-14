package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;


public class ActivityInformationNeighbour extends AppCompatActivity {


    /***item declaration */
    public ImageButton mBackButton;
    public FloatingActionButton mAddFav;
    public ImageView mImageUser;
    public TextView mTittleName;
    public TextView mNameNeighbour;
    public TextView mAdressNeighbour;
    public TextView mNumberPhoneNeighbour;
    public TextView mDetailView;
    public TextView mAdressNet;
    public Boolean mFavNeighbour;
    public int mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_neighbour);

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("infoNeighbour");
        mPosition = intent.getIntExtra("position",0);


        mBackButton = findViewById(R.id.BackButton_view);
        mAddFav = findViewById(R.id.addFav_view);
        mImageUser = findViewById(R.id.imageNeighbour_view);
        mTittleName = findViewById(R.id.titleNameNeighbour_view);
        mNameNeighbour = findViewById(R.id.nameNeightbour_view);
        mAdressNeighbour = findViewById(R.id.adressNeighbour_view);
        mNumberPhoneNeighbour = findViewById(R.id.numberPhoneNeighbour_view);
        mDetailView = findViewById(R.id.detail_view);
        mAdressNet = findViewById(R.id.adressNet_view);

        /**Item new generation */
        mTittleName.setText(neighbour.getName());
        mNameNeighbour.setText(neighbour.getName());
        Glide.with(mImageUser.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(mImageUser);
        mAdressNeighbour.setText(neighbour.getAddress());
        mNumberPhoneNeighbour.setText(neighbour.getPhoneNumber());
        mAdressNet.setText("Facebook.fr/" + neighbour.getName());
        mDetailView.setText(neighbour.getAboutMe());
        mFavNeighbour = neighbour.getFav(true);


        /**Star fav true/false */
        if (mFavNeighbour) {
            mAddFav.setImageResource(R.drawable.ic_star_white_24dp);

        } else mAddFav.setImageResource(R.drawable.ic_star_border_white_24dp);


        /**Click on star for add fav or delete fav */
        mAddFav.setOnClickListener(v -> {
            if (!neighbour.Fav) {
                DI.getNeighbourApiService().addFavNeighbour(neighbour);
                DI.getNeighbourApiService().getNeighbours().set(mPosition,neighbour);
                mAddFav.setImageResource(R.drawable.ic_star_white_24dp);

            } else if (neighbour.Fav) {
                DI.getNeighbourApiService().deleteFavNeighbour(neighbour);
                DI.getNeighbourApiService().getNeighbours().set(mPosition, neighbour);
                mAddFav.setImageResource(R.drawable.ic_star_border_white_24dp);
            }
        });


        /**arrow for back */
        mBackButton.setOnClickListener(v -> {
            Intent backView = new Intent(ActivityInformationNeighbour.this, ListNeighbourActivity.class);
            startActivity(backView);
        });

    }
}


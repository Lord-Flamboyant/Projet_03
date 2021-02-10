package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;


public class ActivityInformationNeighbour extends AppCompatActivity {


    /***item declaration */
    public ImageButton mBackButton;
    public FloatingActionButton mAddFav;
    public ImageView mImageUser;
    public TextView mTittleName;
    public CardView mCardViewName;
    public TextView mNameNeighbour;
    public ImageView mMapView;
    public TextView mAdressNeighbour;
    public ImageView mPhoneView;
    public TextView mNumberPhoneNeighbour;
    public ImageView mIView;
    public CardView mCardViewInfo;
    public TextView mAbout;
    public TextView mDetailView;
    public TextView mAdressNet;
    public Boolean mFavNeighbour;
    public long mId;
    public int mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_neighbour);

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("infoNeighbour");
        mPosition = intent.getIntExtra("position",0);


        /*** Item view */
        mBackButton = findViewById(R.id.BackButton_view);
        mAddFav = findViewById(R.id.addFav_view);
        mImageUser = findViewById(R.id.imageNeighbour_view);
        mTittleName = findViewById(R.id.titleNameNeighbour_view);
        mCardViewName = findViewById(R.id.CartViewName);
        mNameNeighbour = findViewById(R.id.nameNeightbour_view);
        mMapView = findViewById(R.id.map_view);
        mAdressNeighbour = findViewById(R.id.adressNeighbour_view);
        mPhoneView = findViewById(R.id.phone_view);
        mNumberPhoneNeighbour = findViewById(R.id.numberPhoneNeighbour_view);
        mIView = findViewById(R.id.i_view);
        mCardViewInfo = findViewById(R.id.CartViewInfo);
        mAbout = findViewById(R.id.aPropos_view);
        mDetailView = findViewById(R.id.detail_view);
        mAdressNet = findViewById(R.id.adressNet_view);


        /*** item new generation*/
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
        mFavNeighbour = neighbour.getFav();
        mId = neighbour.getId();


        /***Star fav true/false */
        if (mFavNeighbour == true) {
            mAddFav.setImageResource(R.drawable.ic_star_white_24dp);

        } else mAddFav.setImageResource(R.drawable.ic_star_border_white_24dp);


//TODO: change favorite on appli

        /***Click on star for add fav or delete fav */
        mAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (neighbour.Fav == false) {
                    DI.getNeighbourApiService().addFavNeighbour(neighbour);
                    DI.getNeighbourApiService().getNeighbours().set(mPosition,neighbour);
                    mAddFav.setImageResource(R.drawable.ic_star_white_24dp);

                } else if (neighbour.Fav == true) {
                    DI.getNeighbourApiService().deleteFavNeighbour(neighbour);
                    DI.getNeighbourApiService().getNeighbours().set(mPosition, neighbour);
                    mAddFav.setImageResource(R.drawable.ic_star_border_white_24dp);
                }
            }
        });


        /***arrow for back */
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backView = new Intent(ActivityInformationNeighbour.this, ListNeighbourActivity.class);
                startActivity(backView);
            }
        });

    }
}


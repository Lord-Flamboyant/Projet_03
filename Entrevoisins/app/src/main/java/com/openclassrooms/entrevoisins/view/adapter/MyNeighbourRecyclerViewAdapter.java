package com.openclassrooms.entrevoisins.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.databinding.FragmentNeighbourBinding;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.utils.DeleteNeighbour;
import com.openclassrooms.entrevoisins.view.activity.ActivityInformationNeighbour;

import java.util.List;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {


    private final List<Neighbour> mNeighbours;
    private FragmentNeighbourBinding mBinding;
    private final DeleteNeighbour mDeleteNeighbour;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, DeleteNeighbour deleteNeighbour) {
        this.mNeighbours = items;
        this.mDeleteNeighbour = deleteNeighbour;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = FragmentNeighbourBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);

        holder.updateNeighbour(neighbour);

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, ActivityInformationNeighbour.class);
            intent.putExtra("infoNeighbour",mNeighbours.get(position));
            intent.putExtra("position",position);
            Log.e(getClass().getSimpleName(),mNeighbours.get(position).getName());
            context.startActivity(intent);
        });

        mBinding.itemListDeleteButton.setOnClickListener(v ->
            mDeleteNeighbour.clickOnTrash(neighbour));
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentNeighbourBinding mBinding;

        public ViewHolder(FragmentNeighbourBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        private void updateNeighbour(Neighbour neighbour) {

            Glide.with(mBinding.itemListAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mBinding.itemListAvatar);
            mBinding.itemListName.setText(neighbour.getName());

        }
    }
}

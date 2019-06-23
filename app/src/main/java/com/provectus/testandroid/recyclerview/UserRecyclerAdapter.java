

package com.provectus.testandroid.recyclerview;



import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import com.provectus.testandroid.R;

import com.provectus.testandroid.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder> {

    private List<Result> mlist = Collections.emptyList();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_recycler, parent, false);
        return new UserViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {


        holder.bind(mlist.get(position));


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<Result> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvName;

        public UserViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.person_photo);
            tvName = itemView.findViewById(R.id.person_name);

        }

        public void bind(Result result) {


            //String r = result.getPicture().getMedium();

            tvName.setText(result.getName().getFirst());
            Picasso.get().load(result.getPicture().getLarge()).into(ivAvatar);
        }
    }
}
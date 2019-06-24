

package com.provectus.testandroid.recyclerview;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.provectus.testandroid.R;

import com.provectus.testandroid.myinterface.OnItemClickListener;
import com.provectus.testandroid.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private List<Result> mlist = Collections.emptyList();
    public Context mContext;
    private OnItemClickListener itemClickListener;


    public UserRecyclerAdapter(List<Result> listItems, Context mContext, OnItemClickListener itemClickListener) {
        this.itemClickListener =itemClickListener;
        this.mlist = mlist;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_recycler, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


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

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvName;

         ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.person_photo);
            tvName = itemView.findViewById(R.id.person_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = ViewHolder.super.getAdapterPosition();
                    itemClickListener.onItemClick(v,pos);

                }
            });

        }

        public void bind(Result result) {


            //String r = result.getPicture().getMedium();

            tvName.setText(result.getName().getFirst());
            Picasso.get().load(result.getPicture().getLarge()).into(ivAvatar);
        }
    }
}
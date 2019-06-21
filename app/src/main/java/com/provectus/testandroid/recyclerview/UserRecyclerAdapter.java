

package com.provectus.testandroid.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.provectus.testandroid.R;
import com.provectus.testandroid.pojo.User;
import com.provectus.testandroid.retrofit.NetworkService;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {
    private List<User> listItems;
    private Context mContext;


       public UserRecyclerAdapter(List<User> listItems,Context mContext) {
           this.listItems = listItems;
           this.mContext = mContext;
       }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final User itemList = listItems.get(position);
       // holder.imageView.setImageDrawable(itemList.getPhoto());
        holder.nameUser.setText(itemList.getName());


        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(1)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        User post = response.body();


                       // Picasso.with(mContext).load(new File()).into(holder.imageView);
                        //holder.imageView.append(post.getPicture() + "\n");
                        holder.nameUser.append(post.getName() + "\n");

                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                        t.printStackTrace();
                    }
                });

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameUser;


        ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.person_photo);
            nameUser = (TextView) itemView.findViewById(R.id.person_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = ViewHolder.super.getAdapterPosition();
                    //itemClickListener.onItemClick(v, pos);

                }
            });
        }
    }

}
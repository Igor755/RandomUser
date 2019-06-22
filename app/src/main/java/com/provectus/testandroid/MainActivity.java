package com.provectus.testandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.provectus.testandroid.pojo.Result;
import com.provectus.testandroid.pojo.User;
import com.provectus.testandroid.recyclerview.UserRecyclerAdapter;
import com.provectus.testandroid.retrofit.JSONPlaceHolder;
import com.provectus.testandroid.retrofit.NetworkService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvUserList;
    private UserRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        rvUserList = findViewById(R.id.my_recycler_view);

        final UserRecyclerAdapter adapter = new UserRecyclerAdapter();
        rvUserList.setLayoutManager(new LinearLayoutManager(this));

    }
        public void initial(){


            JSONPlaceHolder client = NetworkService.getClient().create(JSONPlaceHolder.class);
            client.fetchUsers(10).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    List<Result> mlist = response.body().getResults();
                    adapter = new UserRecyclerAdapter();
                    adapter.setData(mlist);
                    rvUserList.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Main", t.toString());
                }
            });
        }
        }



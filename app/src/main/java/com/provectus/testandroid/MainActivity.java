package com.provectus.testandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.provectus.testandroid.myinterface.OnItemClickListener;
import com.provectus.testandroid.pojo.Result;
import com.provectus.testandroid.pojo.User;
import com.provectus.testandroid.recyclerview.UserRecyclerAdapter;
import com.provectus.testandroid.retrofit.JSONPlaceHolder;
import com.provectus.testandroid.retrofit.NetworkService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView rvUserList;
    private UserRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(VISIBLE);

        initial();

        rvUserList = (RecyclerView) findViewById(R.id.my_recycler_view);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));

    }
        public void initial(){


            JSONPlaceHolder client = NetworkService.getClient().create(JSONPlaceHolder.class);
            client.getMyJson(5000).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    final List<Result> mlist = response.body().getResults();

                    progressBar.setVisibility(View.INVISIBLE);
                    adapter = new UserRecyclerAdapter(mlist, getBaseContext(), new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                            Result m = mlist.get(position);
                            intent.putExtra("myclass", m);
                            startActivity(intent);




                        }
                    });
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



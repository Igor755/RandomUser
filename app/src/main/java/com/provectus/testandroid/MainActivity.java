package com.provectus.testandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvUserList;
    private UserRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        rvUserList = (RecyclerView) findViewById(R.id.my_recycler_view);

        //final UserRecyclerAdapter adapter = new UserRecyclerAdapter();
        rvUserList.setLayoutManager(new LinearLayoutManager(this));

    }
        public void initial(){


            JSONPlaceHolder client = NetworkService.getClient().create(JSONPlaceHolder.class);
            client.getMyJson(5000).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    final List<Result> mlist = response.body().getResults();
                    adapter = new UserRecyclerAdapter(mlist, getBaseContext(), new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                            Result m = mlist.get(position);
                            System.out.println(m);


                            intent.putExtra("myclass", m);

                            /*m.getPicture().getLarge();
                            m.getCell();
                            m.getDob();
                            m.getEmail();
                            m.getGender();
                            m.getId();
*/

                            startActivity(intent);




                        }
                    });
                    adapter.setData(mlist);
                    rvUserList.setAdapter(adapter);

                   // rvUserList.setOnClickListener(new );
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Main", t.toString());
                }
            });
        }
        }



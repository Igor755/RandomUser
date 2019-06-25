package com.provectus.testandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.provectus.testandroid.pojo.Result;
import com.squareup.picasso.Picasso;

public class UserInfoActivity extends AppCompatActivity {


    private EditText edit_cell;
    private EditText edit_dob;
    private EditText edit_email;
    private EditText edit_gender;
    private EditText edit_id;
    private EditText edit_location;
    private EditText edit_login;
    private EditText edit_name;
    private EditText edit_nat;
    private EditText edit_phone;
    private EditText edit_registered;
    private ImageView imageView;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);

        edit_cell = (EditText)findViewById(R.id.edit_cell);
        edit_dob = (EditText)findViewById(R.id.edit_dob);
        edit_email = (EditText)findViewById(R.id.edit_email);
        edit_gender = (EditText)findViewById(R.id.edit_gender);
        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_location = (EditText)findViewById(R.id.edit_location);
        edit_login = (EditText)findViewById(R.id.edit_login);
        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_nat = (EditText)findViewById(R.id.edit_nat);
        edit_phone = (EditText)findViewById(R.id.edit_phone);
        edit_registered = (EditText)findViewById(R.id.edit_registered);
        imageView = (ImageView)findViewById(R.id.imageView);

        Intent i = getIntent();
        Result result = (Result) i.getSerializableExtra("myclass");

        Uri imgUri=Uri.parse(result.getPicture().getLarge());

        Picasso.get().load(imgUri).into(imageView);


        edit_cell.setText(result.getCell());

        edit_dob.setText(getBaseContext().getResources().getString(R.string.date) + " " +
                result.getDob().getDate() + " " +
                getBaseContext().getResources().getString(R.string.age) + " " +
                result.getDob().getAge());

        edit_email.setText(result.getEmail());
        edit_gender.setText(result.getGender());
        edit_id.setText(result.getId().getValue());

        edit_location.setText(getBaseContext().getResources().getString(R.string.city) + " " +
                result.getLocation().getCity() + " " +
                getBaseContext().getResources().getString(R.string.state) + " " +
                result.getLocation().getState());
        edit_login.setText(result.getLogin().getUsername());

        edit_name.setText(getBaseContext().getResources().getString(R.string.title) + " " +
                result.getName().getTitle() + " " +
                getBaseContext().getResources().getString(R.string.first) + " " +
                result.getName().getFirst() + " " +
                getBaseContext().getResources().getString(R.string.last) + " " +
                result.getName().getLast());

        edit_nat.setText(result.getNat());
        edit_phone.setText(result.getPhone());
        edit_registered.setText(result.getRegistered().getDate());

        edit_cell.setFocusable(false);
        edit_dob.setFocusable(false);
        edit_email.setFocusable(false);
        edit_gender.setFocusable(false);
        edit_id.setFocusable(false);
        edit_location.setFocusable(false);
        edit_login.setFocusable(false);
        edit_name.setFocusable(false);
        edit_nat.setFocusable(false);
        edit_phone.setFocusable(false);
        edit_registered.setFocusable(false);

    }
}

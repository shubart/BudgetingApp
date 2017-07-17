package com.example.dell.myapplicationbudgetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Papers extends AppCompatActivity implements View.OnClickListener{


    ////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);
    }
    /////////////////////////////////////////////////////////////



    @Override
    public void onClick(View v) {

    }
    public void onPapersClick(View v) {
        switch (v.getId())
        {
            case R.id.selectPBackbtn:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.categoriesBackbtn:
                setContentView(R.layout.activity_papers);
                break;



        }
    }
    ////////////////////////////////////////////////
}

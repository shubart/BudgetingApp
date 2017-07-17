package com.example.dell.myapplicationbudgetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    /////////////////////////////////////////////////////////////
    //what happens when activity has just been created or opened

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    ////////////////////////////////////////


/////////////////////////////////////////////////////////////
    //what happens when buttons on are clicked

    @Override
    public void onClick(View v) {

    }
    public void onMainClick(View v) {
        switch (v.getId())
        {
            case R.id.NewShoppingListbtn:
                startActivity(new Intent(this,NewShoppingList.class));
                break;
            case R.id.GoShoppingbtn:
                startActivity(new Intent(this,GoShopping.class));
                break;
            case R.id.nbudgetbtn:
                startActivity(new Intent(this,NewBudget.class));
                break;

            case R.id.papersbtn:
                startActivity(new Intent(this,Papers.class));
                break;

            case R.id.sbudgetbtn:
                startActivity(new Intent(this, SavedBudget.class));
                break;

            case R.id.aboutusbtn:
                setContentView(R.layout.aboutus);
            break;

            case R.id.setttingsbtn:
                startActivity(new Intent(this, Settings.class));
                break;

            case R.id.helpbtn:
                setContentView(R.layout.help);
                break;

        }
    }
    //////////////////////////////////////


}////////////THE END//////////////////////

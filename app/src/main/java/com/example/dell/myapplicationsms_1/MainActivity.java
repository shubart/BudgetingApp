package com.example.dell.myapplicationsms_1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

        /////////////////////////////////////////////////////////////
        //what happens when activity has just been created or opened

        ////////////////////////////////////////
private Button btn1;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.nbudgetbtn);



    }


/////////////////////////////////////////////////////////////
        //what happens when buttons on are clicked

        @Override
        public void onClick(View v) {

        }
        public void onMainClick(View v) {
            switch (v.getId())
            {
                case R.id.NewShoppingListbtn:
                    startActivity(new Intent(this,CreateShoppingList.class));
                    break;
                case R.id.sbudgetbtn:
                   startActivity(new Intent(this,SavedBudgets.class));
                    break;



            }
        }
        //////////////////////////////////////



    }


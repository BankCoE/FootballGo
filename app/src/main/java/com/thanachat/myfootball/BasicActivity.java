package com.thanachat.myfootball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.thanachat.myfootball.R;

public class BasicActivity extends AppCompatActivity {

    int status1 = 0;
    int status2 = 0;
    int status3 = 0;
    int status4 = 0;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
    }

    public void backBasic(View v){
        finish();
    }

    public  void enter1(View v){
        LinearLayout lay1 = (LinearLayout)findViewById(R.id.info_1);
        if(status1 == 0){
            lay1.setVisibility(View.VISIBLE);
            status1 = 1;
        }
        else{
            lay1.setVisibility(View.GONE);
            status1 = 0;
        }
    }

    public  void enter2(View v){
        LinearLayout lay2 = (LinearLayout)findViewById(R.id.info_2);
        if(status2 == 0){
            lay2.setVisibility(View.VISIBLE);
            status2 = 1;
        }
        else{
            lay2.setVisibility(View.GONE);
            status2 = 0;
        }
    }

    public  void enter3(View v){
        LinearLayout lay3 = (LinearLayout)findViewById(R.id.info_3);
        if(status3 == 0){
            lay3.setVisibility(View.VISIBLE);
            status3 = 1;
        }
        else{
            lay3.setVisibility(View.GONE);
            status3 = 0;
        }
    }

    public  void enter4(View v){
        LinearLayout lay4 = (LinearLayout)findViewById(R.id.info_4);
        if(status4 == 0){
            lay4.setVisibility(View.VISIBLE);
            status4 = 1;
        }
        else{
            lay4.setVisibility(View.GONE);
            status4 = 0;
        }
    }

    public void enterBasic1 (View v){
        check = 1;
        Intent goBasic1 = new Intent(this, VideoActivity.class);
        goBasic1.putExtra("chk", check);
        startActivity(goBasic1);
    }

    public void enterBasic2 (View v){
        check = 2;
        Intent goBasic1 = new Intent(this, VideoActivity.class);
        goBasic1.putExtra("chk", check);
        startActivity(goBasic1);
    }

    public void enterBasic3 (View v){
        check = 3;
        Intent goBasic1 = new Intent(this, VideoActivity.class);
        goBasic1.putExtra("chk", check);
        startActivity(goBasic1);
    }

    public void enterBasic4 (View v){
        check = 4;
        Intent goBasic1 = new Intent(this, VideoActivity.class);
        goBasic1.putExtra("chk", check);
        startActivity(goBasic1);
    }

}

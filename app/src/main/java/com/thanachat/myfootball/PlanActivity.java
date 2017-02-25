package com.thanachat.myfootball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thanachat.myfootball.R;

public class PlanActivity extends AppCompatActivity {

    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

    }

    public void enterAP1 (View v){
        check = 11;
        Intent goAP1 = new Intent(this, APlan1Activity.class);
        goAP1.putExtra("chk", check);
        startActivity(goAP1);

    }
    public void enterAP2 (View v){
        check = 12;
        Intent goAP1 = new Intent(this, APlan1Activity.class);
        goAP1.putExtra("chk", check);
        startActivity(goAP1);
    }

    public void enterBP1 (View v){
        check = 21;
        Intent goAP1 = new Intent(this, APlan1Activity.class);
        goAP1.putExtra("chk", check);
        startActivity(goAP1);
    }

    public void enterBP2 (View v){
        check = 22;
        Intent goAP1 = new Intent(this, APlan1Activity.class);
        goAP1.putExtra("chk", check);
        startActivity(goAP1);
    }

    public void enterDP1 (View v){
        check = 31;
        Intent goAP1 = new Intent(this, APlan1Activity.class);
        goAP1.putExtra("chk", check);
        startActivity(goAP1);
    }

    public void enterDP2 (View v){
        check = 32;
        Intent goAP1 = new Intent(this, APlan1Activity.class);
        goAP1.putExtra("chk", check);
        startActivity(goAP1);
    }

    public void backPlan (View view){
        finish();
    }
}

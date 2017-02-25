package com.thanachat.myfootball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.thanachat.myfootball.R;

public class MainActivity extends AppCompatActivity {

    int menu1 = 0;
    int menu2 = 0;
    int menu3 = 0;
    int menu4 = 0;
    int menu5 = 0;
    int menu6 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Menu style GONE
    public  void openmbasic(View v){
        ImageButton img1 = (ImageButton)findViewById(R.id.menu_1);
        ImageButton cimg1 = (ImageButton)findViewById(R.id.mbasic);
        if(menu1 == 0){
            cimg1.setImageResource(R.drawable.nmbasicc);
            img1.setVisibility(View.VISIBLE);
            menu1 = 1;
        }
        else{
            cimg1.setImageResource(R.drawable.nmbasico);
            img1.setVisibility(View.GONE);
            menu1 = 0;
        }
    }
    public  void openmrules(View v){
        ImageButton img2 = (ImageButton)findViewById(R.id.menu_2);
        ImageButton cimg2 = (ImageButton)findViewById(R.id.mrules);
        if(menu2 == 0){
            cimg2.setImageResource(R.drawable.nmrulesc);
            img2.setVisibility(View.VISIBLE);
            menu2 = 1;
        }
        else{
            cimg2.setImageResource(R.drawable.nmruleso);
            img2.setVisibility(View.GONE);
            menu2 = 0;
        }
    }

    public  void openmplan(View v){
        ImageButton img3 = (ImageButton)findViewById(R.id.menu_3);
        ImageButton cimg3 = (ImageButton)findViewById(R.id.mplan);
        if(menu3 == 0){
            cimg3.setImageResource(R.drawable.nmplanc);
            img3.setVisibility(View.VISIBLE);
            menu3 = 1;
        }
        else{
            cimg3.setImageResource(R.drawable.nmplano);
            img3.setVisibility(View.GONE);
            menu3 = 0;
        }
    }

    public  void openmmap(View v){
        ImageButton img4 = (ImageButton)findViewById(R.id.menu_4);
        ImageButton cimg4 = (ImageButton)findViewById(R.id.mmap);
        if(menu4 == 0){
            cimg4.setImageResource(R.drawable.nmmapc);
            img4.setVisibility(View.VISIBLE);
            menu4 = 1;
        }
        else{
            cimg4.setImageResource(R.drawable.nmmapo);
            img4.setVisibility(View.GONE);
            menu4 = 0;
        }
    }

    public  void openmtrain(View v){
        ImageButton img5 = (ImageButton)findViewById(R.id.menu_5);
        ImageButton cimg5 = (ImageButton)findViewById(R.id.mtrain);
        if(menu5 == 0){
            cimg5.setImageResource(R.drawable.nmtrainc);
            img5.setVisibility(View.VISIBLE);
            menu5 = 1;
        }
        else{
            cimg5.setImageResource(R.drawable.nmtraino);
            img5.setVisibility(View.GONE);
            menu5 = 0;
        }
    }

    public  void openmcommit(View v){
        ImageButton img6 = (ImageButton)findViewById(R.id.menu_6);
        ImageButton cimg6 = (ImageButton)findViewById(R.id.mcommit);
        if(menu6 == 0){
            cimg6.setImageResource(R.drawable.nmcommitc);
            img6.setVisibility(View.VISIBLE);
            menu6 = 1;
        }
        else{
            cimg6.setImageResource(R.drawable.nmcommito);
            img6.setVisibility(View.GONE);
            menu6 = 0;
        }
    }

    //==========================================================================================

    public void enterBasic (View v){
        Intent goBasic = new Intent(this, BasicActivity.class);
        startActivity(goBasic);
    }

    public void enterRules (View view){
        Intent goRules = new Intent(this, RulesActivity.class);
        startActivity(goRules);
    }

    public void enterPlan (View v){
        Intent goPlan = new Intent(this, PlanActivity.class);
        startActivity(goPlan);
    }

    public void enterMap (View v){
        Intent goMap = new Intent(this, MapsActivity.class);
        startActivity(goMap);
    }

    public void enterTrain (View v){
        Intent goTrain = new Intent(this, TrainActivity.class);
        startActivity(goTrain);
    }

    public void enterCommit (View v){
        Intent goCommit = new Intent(this, CommitActivity.class);
        startActivity(goCommit);
    }


}

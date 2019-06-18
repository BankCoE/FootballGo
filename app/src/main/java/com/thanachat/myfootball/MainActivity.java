package com.thanachat.myfootball;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.thanachat.myfootball.R;

public class MainActivity extends AppCompatActivity {

    int menu1 = 0;
    int menu2 = 0;
    int menu3 = 0;
    int menu4 = 0;
    int menu5 = 0;
    int menu6 = 0;
    int menu7 = 0;
    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

    public  void openls(View v){
        ImageButton img7 = (ImageButton)findViewById(R.id.menu_7);
        ImageButton cimg7 = (ImageButton)findViewById(R.id.mls);
        ImageButton img71 = (ImageButton)findViewById(R.id.menu_7_1);
        if(menu7 == 0){
            cimg7.setImageResource(R.drawable.nmlsc);
            img7.setVisibility(View.VISIBLE);
            img71.setVisibility(View.VISIBLE);
            menu7 = 1;
        }
        else{
            cimg7.setImageResource(R.drawable.nmlso);
            img7.setVisibility(View.GONE);
            img71.setVisibility(View.GONE);
            menu7 = 0;
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
        Intent goCommit = new Intent(this, ComActivity.class);
        startActivity(goCommit);
    }

    public void enterGuide (View v){
        Intent goGuide = new Intent(this, GuideActivity.class);
        startActivity(goGuide);
    }

    public void enterLS (View v){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            Toast.makeText(MainActivity.this,
                    "Connect!", Toast.LENGTH_SHORT).show();
            connected = true;
            Intent goLS = new Intent(this, LSActivity.class);
            startActivity(goLS);
        }
        else{
            connected = false;
            Toast.makeText(MainActivity.this,
                    "Disconnect!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }

    }

    public void enterSCB (View v){
        Intent goSCB = new Intent(this, ScoreboardActivity.class);
        startActivity(goSCB);
    }


}

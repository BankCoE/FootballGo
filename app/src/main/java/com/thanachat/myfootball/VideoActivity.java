package com.thanachat.myfootball;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thanachat.myfootball.R;

public class VideoActivity extends AppCompatActivity {

    int req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //getExtra and Check Even
        Intent goBasic1 = getIntent();
        req = goBasic1.getIntExtra("chk", 0);

        if(req == 1){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tTkFBcFa72I")));
        }
        else if(req == 2){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1dF7PXH3pgI")));
        }
        else if(req == 3){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VB7Kcx-ij0k")));
        }
        else if(req == 4){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Dgs6NGsEBHw")));
        }
    }

    public void backVideo (View v){
        finish();
    }

    public void reVideo (View v){
        recreate();
    }
}

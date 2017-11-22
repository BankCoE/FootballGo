package com.thanachat.myfootball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GuideActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }


    public void guideBasic(View v){
        ((TextView)findViewById(R.id.topic_guide)).setText(R.string.basictopic);
        ((ImageView)findViewById(R.id.info_guide)).setImageResource(R.drawable.basic_info);
    }

    public void guideRules(View v){
        ((TextView)findViewById(R.id.topic_guide)).setText(R.string.rulestopic);
        ((ImageView)findViewById(R.id.info_guide)).setImageResource(R.drawable.rules_info);
    }
}

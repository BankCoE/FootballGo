package com.thanachat.myfootball;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.thanachat.myfootball.R;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class CommitActivity extends Activity {

    CountDownTimer cdt;
    TextView tvTimer;
    ToggleButton btnCount;
    TextView homescore, awayscore, hname, aname;
    ImageButton setting;
    LinearLayout listbar, listcard, timebar;
    EditText editcardhome, editcardaway;

    int setstatus = 0;
    int csstatus = 0;
    int time = 0;
    int hnum, anum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);

        tvTimer = (TextView)findViewById(R.id.tvTimer);

        timebar = (LinearLayout)findViewById(R.id.timebar);
        listbar = (LinearLayout)findViewById(R.id.listbar);

        //yellow card home
//        TextView listyhome = new TextView(this);
//        listyhome.setText("New text");

        btnCount = (ToggleButton)findViewById(R.id.btnCount);
        btnCount.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton view
                    , boolean isChecked) {
                TextView chpop = (TextView)findViewById(R.id.chpopup);
                if(isChecked) {
                    setting = (ImageButton)findViewById(R.id.btn_set);
                    setting.setEnabled(false);
                    csstatus = 1;
                    chpop.setVisibility(View.GONE);
                    cdt = new CountDownTimer(time, 1000) {
                        public void onTick(long millisUntilFinished) {
                            String strTime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                                    , (double)millisUntilFinished / 1000);
                            tvTimer.setText(String.valueOf(strTime));
                        }

                        public void onFinish() {
                            tvTimer.setText("00:00:00");
                            btnCount.setChecked(false);
                        }
                    }.start();
                } else {
                    cdt.cancel();
                    homescore = (TextView)findViewById(R.id.homescore);
                    awayscore = (TextView)findViewById(R.id.awayscore);
                    setting = (ImageButton)findViewById(R.id.btn_set);
                    ScrollView homeview = (ScrollView) findViewById(R.id.htview);
                    ScrollView awayview = (ScrollView) findViewById(R.id.atview);
                    hname = (TextView)findViewById(R.id.hname);
                    aname = (TextView)findViewById(R.id.aname);
                    timebar = (LinearLayout)findViewById(R.id.timebar);
                    listbar = (LinearLayout)findViewById(R.id.listbar);
                    listcard = (LinearLayout)findViewById(R.id.listcard);

                    tvTimer.setText("00:00:00");
                    csstatus = 0;
                    homescore.setText("0");
                    awayscore.setText("0");
                    setting.setEnabled(true);
                    homeview.setVisibility(View.GONE);
                    homeview.scrollTo(0, 0);
                    awayview.setVisibility(View.GONE);
                    awayview.scrollTo(0, 0);
                    hname.setVisibility(View.GONE);
                    aname.setVisibility(View.GONE);
                    timebar.setVisibility(View.GONE);
                    listbar.setVisibility(View.GONE);
                    listbar.scrollTo(0, 0);
                    listcard.removeAllViewsInLayout();
                }
            }
        });
    }

    public void backCommit (View v){
        finish();
    }

    public void setting (View v){
        LinearLayout settingbar = (LinearLayout)findViewById(R.id.settingwindows);
        if (setstatus == 0){
            settingbar.setVisibility(View.VISIBLE);
            setstatus = 1;
        }
        else if(setstatus == 1){
            settingbar.setVisibility(View.GONE);
            setstatus = 0;
        }

        }

    public void save (View v){
        EditText timeinput = (EditText)findViewById(R.id.ts);
        EditText homeinput = (EditText)findViewById(R.id.homename);
        EditText awayinput = (EditText)findViewById(R.id.awayname);

        tvTimer = (TextView)findViewById(R.id.tvTimer);
        TextView hname = (TextView)findViewById(R.id.hname);
        TextView aname = (TextView)findViewById(R.id.aname);

        ScrollView homeview = (ScrollView) findViewById(R.id.htview);
        ScrollView awayview = (ScrollView) findViewById(R.id.atview);

        LinearLayout settingbar = (LinearLayout)findViewById(R.id.settingwindows);
        LinearLayout timebar = (LinearLayout)findViewById(R.id.timebar);
        listbar = (LinearLayout)findViewById(R.id.listbar);
        listcard = (LinearLayout)findViewById(R.id.listcard);

        TextView nocard = new TextView(this);
        nocard.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        nocard.setGravity(Gravity.CENTER);
        nocard.setId(R.id.nocard);
        nocard.setText("... No card ...");
        listcard.addView(nocard);


        if (timeinput.getText().toString().length() > 0) {
            if (homeinput.getText().toString().length() > 0){
                if (awayinput.getText().toString().length() > 0){
                    //time
                    time = Integer.parseInt(timeinput.getText().toString());
                    time = time*60000;
                    setstatus = 0;
                    int showtime;
                    showtime = time/60000;
                    tvTimer.setText(showtime + " Min");
                    //home name
                    String homename;
                    homename = homeinput.getText().toString();
                    hname.setText("(" + homename + ")");
                    //Away name
                    String awayname;
                    awayname = awayinput.getText().toString();
                    aname.setText("(" + awayname + ")");
                    //Layout
                    settingbar.setVisibility(View.GONE);
                    homeview.setVisibility(View.VISIBLE);
                    awayview.setVisibility(View.VISIBLE);
                    timebar.setVisibility(View.VISIBLE);
                    listbar.setVisibility(View.VISIBLE);
                    hname.setVisibility(View.VISIBLE);
                    aname.setVisibility(View.VISIBLE);
                    //Clear
                    timeinput.setText(null);
                    homeinput.setText(null);
                    awayinput.setText(null);
                }
                else{
                    awayinput.setError("กรุณากรอกชื่อทีม");
                    return;
                }
            }
            else{
                homeinput.setError("กรุณากรอกชื่อทีม");
                return;
            }

        }
        else
        {
            timeinput.setError("กรุณากรอกเวลา");
            return;
        }
    }
    //add and sub home score
    public void homeaddscore(View v){
        TextView homechaddpop = (TextView)findViewById(R.id.chpopup);
        homescore = (TextView)findViewById(R.id.homescore);
        int hscore = Integer.parseInt(homescore.getText().toString());
        if(csstatus == 1){
            hscore = hscore+1;
            homescore.setText("" + hscore);
        }
        else{
            homechaddpop.setVisibility(View.VISIBLE);
        }
    }
    public void homesubscore(View v){
        TextView homechsubpop = (TextView)findViewById(R.id.chpopup);
        homescore = (TextView)findViewById(R.id.homescore);
        int hscore = Integer.parseInt(homescore.getText().toString());
        if(csstatus == 1){
            if(hscore > 0){
                hscore = hscore-1;
                homescore.setText("" + hscore);
            }
            else{
                homescore.setText("" + hscore);
            }
        }
        else{
            homechsubpop.setVisibility(View.VISIBLE);
        }
    }

    //add and sub away score
    public void awayaddscore(View v){
        TextView awaychaddpop = (TextView)findViewById(R.id.chpopup);
        awayscore = (TextView)findViewById(R.id.awayscore);
        int ascore = Integer.parseInt(awayscore.getText().toString());
        if(csstatus == 1){
            ascore = ascore+1;
            awayscore.setText("" + ascore);
        }
        else{
            awaychaddpop.setVisibility(View.VISIBLE);
        }
    }
    public void awaysubscore(View v){
        TextView awaychsubpop = (TextView)findViewById(R.id.chpopup);
        awayscore = (TextView)findViewById(R.id.awayscore);
        int ascore = Integer.parseInt(awayscore.getText().toString());
        if(csstatus == 1){
            if(ascore > 0){
                ascore = ascore-1;
                awayscore.setText("" + ascore);
            }
            else{
                awayscore.setText("" + ascore);
            }
        }
        else{
            awaychsubpop.setVisibility(View.VISIBLE);
        }
    }

    //yellow and red card of home team
    //yellow home
    public void yellowaddhome (View v){
        EditText homeinput = (EditText)findViewById(R.id.homenum);
        TextView awaychsubpop = (TextView)findViewById(R.id.chpopup);

        listcard = (LinearLayout)findViewById(R.id.listcard);
        editcardhome = (EditText)findViewById(R.id.homenum);


        if(csstatus == 1){
            if(homeinput.getText().toString().length() > 0){
                hnum = Integer.parseInt(editcardhome.getText().toString());
                listcard = (LinearLayout)findViewById(R.id.listcard);
                TextView nocard = (TextView)findViewById(R.id.nocard);

                LinearLayout detial = new LinearLayout(this);
                TextView name = new TextView(this);
                ImageView colorcard = new ImageView(this);

                detial.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
                name.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
                colorcard.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

                detial.setOrientation(LinearLayout.HORIZONTAL);
                name.setText("Number: " + hnum);
                name.setGravity(Gravity.CENTER);
                colorcard.setImageResource(R.drawable.ycard);
                detial.addView(name);
                detial.addView(colorcard);
                listcard.addView(detial);
                listcard.removeView(nocard);

                editcardhome.setText(null);

            }
            else{
                homeinput.setError("กรุณากรอกหมายเลข");
                return;
            }
        }
        else{
            awaychsubpop.setVisibility(View.VISIBLE);
        }
    }


    //red home
    public void redaddhome (View v){
        EditText homeinput = (EditText)findViewById(R.id.homenum);
        TextView awaychsubpop = (TextView)findViewById(R.id.chpopup);
        TextView nocard = (TextView)findViewById(R.id.nocard);

        listcard = (LinearLayout)findViewById(R.id.listcard);
        editcardhome = (EditText)findViewById(R.id.homenum);

        if(csstatus == 1){
            if(homeinput.getText().toString().length() > 0){
                hnum = Integer.parseInt(editcardhome.getText().toString());
                listcard = (LinearLayout)findViewById(R.id.listcard);

                LinearLayout detial = new LinearLayout(this);
                TextView name = new TextView(this);
                ImageView colorcard = new ImageView(this);

                detial.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
                name.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
                colorcard.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

                detial.setOrientation(LinearLayout.HORIZONTAL);
                name.setText("Number: " + hnum);
                name.setGravity(Gravity.CENTER);
                colorcard.setImageResource(R.drawable.rcard);
                detial.addView(name);
                detial.addView(colorcard);
                listcard.addView(detial);
                listcard.removeView(nocard);

                editcardhome.setText(null);
            }
            else{
                homeinput.setError("กรุณากรอกหมายเลข");
                return;
            }
        }
        else{
            awaychsubpop.setVisibility(View.VISIBLE);
        }
    }

    //yellow and red card of away team
    //yellow away
    public void yellowaddaway (View v){
        EditText awayinput = (EditText)findViewById(R.id.awaynum);
        TextView awaychsubpop = (TextView)findViewById(R.id.chpopup);
        TextView nocard = (TextView)findViewById(R.id.nocard);

        listcard = (LinearLayout)findViewById(R.id.listcard);
        editcardaway = (EditText)findViewById(R.id.awaynum);

        if(csstatus == 1){
            if(awayinput.getText().toString().length() > 0){
                anum = Integer.parseInt(editcardaway.getText().toString());
                listcard = (LinearLayout)findViewById(R.id.listcard);

                LinearLayout detial = new LinearLayout(this);
                TextView name = new TextView(this);
                ImageView colorcard = new ImageView(this);

                detial.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
                name.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
                colorcard.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

                detial.setOrientation(LinearLayout.HORIZONTAL);
                name.setText("Number: " + anum);
                name.setGravity(Gravity.CENTER);
                colorcard.setImageResource(R.drawable.ycard);
                detial.addView(name);
                detial.addView(colorcard);
                detial.setGravity(Gravity.RIGHT);
                listcard.addView(detial);
                listcard.removeView(nocard);

                editcardaway.setText(null);
            }
            else{
                awayinput.setError("กรุณากรอกหมายเลข");
                return;
            }
        }
        else{
            awaychsubpop.setVisibility(View.VISIBLE);
        }
    }
    //red away
    public void redaddaway (View v){
        EditText awayinput = (EditText)findViewById(R.id.awaynum);
        TextView awaychsubpop = (TextView)findViewById(R.id.chpopup);
        TextView nocard = (TextView)findViewById(R.id.nocard);

        listcard = (LinearLayout)findViewById(R.id.listcard);
        editcardaway = (EditText)findViewById(R.id.awaynum);

        if(csstatus == 1){
            if(awayinput.getText().toString().length() > 0){
                anum = Integer.parseInt(editcardaway.getText().toString());
                listcard = (LinearLayout)findViewById(R.id.listcard);

                LinearLayout detial = new LinearLayout(this);
                TextView name = new TextView(this);
                ImageView colorcard = new ImageView(this);

                detial.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
                name.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
                colorcard.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

                detial.setOrientation(LinearLayout.HORIZONTAL);
                name.setText("Number: " + anum);
                name.setGravity(Gravity.CENTER);
                colorcard.setImageResource(R.drawable.rcard);
                detial.addView(name);
                detial.addView(colorcard);
                detial.setGravity(Gravity.RIGHT);
                listcard.addView(detial);
                listcard.removeView(nocard);

                editcardaway.setText(null);
            }
            else{
                awayinput.setError("กรุณากรอกหมายเลข");
                return;
            }
        }
        else{
            awaychsubpop.setVisibility(View.VISIBLE);
        }
    }
}


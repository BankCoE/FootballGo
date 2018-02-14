package com.thanachat.myfootball;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComActivity extends AppCompatActivity {

    public DatabaseReference myRef;
    public DatabaseReference match;
    public DatabaseReference matchname;
    public DatabaseReference matchdetail;
    public DatabaseReference livesc;

    String hname;
    String aname;
    String keys;
    String livekeys;

    int homesc;
    int awaysc;
    int n;

    private TextView homesname;
    private TextView awaysname;
    private TextView homescore;
    private TextView awayscore;
    private ListView listmatch;
    private Button btnsave;
    private EditText homeinput;
    LinearLayout boardshow;
    LinearLayout endshow;
    LinearLayout settingbar;

    Notification.Builder notification;
    final int id=11;

    //Array List
//    private ArrayList<String> arrayList = new ArrayList<>( );
//    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);


        homesname = (TextView) findViewById(R.id.hsname);
        awaysname = (TextView) findViewById(R.id.asname);
        homescore = (TextView) findViewById(R.id.homescore);
        awayscore = (TextView) findViewById(R.id.awayscore);
        homeinput = (EditText) findViewById(R.id.home_name);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");
        //matchdetail = match.child(keys);

//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);


        btnsave = (Button) findViewById(R.id.save_set);

//        listmatch.setAdapter(adapter);

//        btnsave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                match.push().setValue(homeinput.getText().toString());
//            }
//        });

//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String string = dataSnapshot.getValue(String.class);
//                arrayList.add(string);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }


    // Save Button
    public void save(View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");
        matchname = match.child("matchname");

        boardshow = (LinearLayout)findViewById(R.id.boardshow);
        endshow = (LinearLayout)findViewById(R.id.controlbar);
        settingbar = (LinearLayout)findViewById(R.id.settingbar);

        EditText homeinput = (EditText)findViewById(R.id.home_name);
        EditText awayinput = (EditText)findViewById(R.id.away_name);

        final TextView hsname = (TextView)findViewById(R.id.hsname);

        if (homeinput.getText().toString().length() > 0) {
            if(awayinput.getText().toString().length() > 0) {

                //Notification
                notification = new Notification.Builder(ComActivity.this);
                notification.setSmallIcon(R.drawable.basic4pic);
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Football Go");
                notification.setTicker("มีแจ้งเตือนใหม่");
                notification.setContentText("มีการแข่งขันฟุตบอลนัดใหม่");
                Intent intent = new Intent(this, ComActivity.class);
                PendingIntent panding = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(panding);
                NotificationManager nm = (NotificationManager) getSystemService(
                        NOTIFICATION_SERVICE
                );
                nm.notify(id, notification.build());

                hname = homeinput.getText().toString();
                aname = awayinput.getText().toString();

                homeinput.setText("");
                awayinput.setText("");

                //matchname.push().setValue(hname + " : " + homesc + " - " + aname);

                livekeys = matchname.push().getKey();
                matchname.child(livekeys).setValue(hname + " : " + homesc + " - " + awaysc + " : " + aname);

                keys = match.push().getKey();
                matchdetail = match.child(keys);
//                // Home Team updateDB
//                match.child("homename").setValue(hname);
//
//                // Away Team updateDB
//                match.child("awayname").setValue(aname);
                //Add match detail

                match.child(keys).child("homename").setValue(hname);
                match.child(keys).child("awayname").setValue(aname);
                match.child(keys).child("homescore").setValue(0);
                match.child(keys).child("awayscore").setValue(0);

                boardshow.setVisibility(View.VISIBLE);
                endshow.setVisibility(View.VISIBLE);
                settingbar.setVisibility(View.GONE);

                //Read Home name
                matchdetail.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map map = (Map)dataSnapshot.getValue();
                        String hnteam = String.valueOf(map.get("homename"));
                        homesname.setText(hnteam);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //Read Away name
                matchdetail.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map map = (Map)dataSnapshot.getValue();
                        String anteam = String.valueOf(map.get("awayname"));
                        awaysname.setText(anteam);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //Read Home Score
                matchdetail.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map map = (Map)dataSnapshot.getValue();
                        String hsc = String.valueOf(map.get("homescore"));
                        homescore.setText(hsc);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //Read Away Score
                matchdetail.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map map = (Map)dataSnapshot.getValue();
                        String asc = String.valueOf(map.get("awayscore"));
                        awayscore.setText(asc);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            else {
                awayinput.setError("กรุณากรอกชื่อทีม");
                return;
            }
        }
        else {
            homeinput.setError("กรุณากรอกชื่อทีม");
            return;
        }
    }
    // Add Home Score
    public void addhome(View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");
        matchdetail = match.child(keys);
        livesc = matchname.child(livekeys);

        homescore = (TextView)findViewById(R.id.homescore);
        String text = homescore.getText().toString();
        n = Integer.parseInt(text);
        n = n+1;
        matchdetail.child("homescore").setValue(n);
        homesc = n;
        livesc.setValue(hname + " : " + homesc + " - " + awaysc + " : " + aname);


    }
    // Del Home Score
    public void delhome (View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");
        matchdetail = match.child(keys);
        livesc = matchname.child(livekeys);

        homescore = (TextView)findViewById(R.id.homescore);
        String text = homescore.getText().toString();
        n = Integer.parseInt(text);
        if(n > 0){
            n = n-1;
            matchdetail.child("homescore").setValue(n);
            homesc = n;
            livesc.setValue(hname + " : " + homesc + " - " + awaysc + " : " + aname);
        }
        else return;
    }
    // Add Away Score
    public void addaway(View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");
        matchdetail = match.child(keys);
        livesc = matchname.child(livekeys);

        awayscore = (TextView)findViewById(R.id.awayscore);
        String text = awayscore.getText().toString();
        n = Integer.parseInt(text);
        n = n+1;
        matchdetail.child("awayscore").setValue(n);
        awaysc = n;
        livesc.setValue(hname + " : " + homesc + " - " + awaysc + " : " + aname);
    }
    // Del Away Score
    public void delaway (View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");
        matchdetail = match.child(keys);
        livesc = matchname.child(livekeys);

        awayscore = (TextView)findViewById(R.id.awayscore);
        String text = awayscore.getText().toString();
        n = Integer.parseInt(text);
        if(n > 0){
            n = n-1;
            matchdetail.child("awayscore").setValue(n);
            awaysc = n;
            livesc.setValue(hname + " : " + homesc + " - " + awaysc + " : " + aname);
        }
        else return;
    }

    //End match
    public void endmatch (View v){
        boardshow = (LinearLayout)findViewById(R.id.boardshow);
        endshow = (LinearLayout)findViewById(R.id.controlbar);
        settingbar = (LinearLayout)findViewById(R.id.settingbar);

        boardshow.setVisibility(View.GONE);
        settingbar.setVisibility(View.VISIBLE);
        endshow.setVisibility(View.GONE);
    }
}

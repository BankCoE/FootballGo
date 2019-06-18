package com.thanachat.myfootball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;

public class ScoreboardActivity extends ComActivity {

    public DatabaseReference myRef;
    public DatabaseReference match;
    public DatabaseReference matchdetail;

    TextView homename;
    TextView awayname;
    TextView schome;
    TextView scaway;

    String scbkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        homename = (TextView)findViewById(R.id.homename);
        awayname = (TextView)findViewById(R.id.awayname);
        schome = (TextView)findViewById(R.id.homescore);
        scaway = (TextView)findViewById(R.id.awayscore);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");
        scbkey = ComActivity.keys;
        matchdetail = match.child(scbkey);

        //read home name
        matchdetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String hnteam = String.valueOf(map.get("homename"));
                homename.setText(hnteam);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //read away name
        matchdetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String anteam = String.valueOf(map.get("awayname"));
                awayname.setText(anteam);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //read home scaore
        matchdetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String homesc = String.valueOf(map.get("homescore"));
                schome.setText(homesc);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //read away scaore
        matchdetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String homesc = String.valueOf(map.get("awayscore"));
                scaway.setText(homesc);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Log.d("xxxxx",ComActivity.keys);


    }
}

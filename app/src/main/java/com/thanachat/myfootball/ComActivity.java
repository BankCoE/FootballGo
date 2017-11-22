package com.thanachat.myfootball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

    String hname;
    String aname;
    int n;



    private TextView homesname;
    private TextView awaysname;
    private TextView homescore;
    private TextView awayscore;
    private ListView listmatch;
    private Button btnsave;
    private EditText homeinput;

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

        //Read Home Name
        match.addValueEventListener(new ValueEventListener() {
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


        //Read Away Name
        match.addValueEventListener(new ValueEventListener() {
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
        match = myRef.child("match");

        match.addValueEventListener(new ValueEventListener() {
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
        match.addValueEventListener(new ValueEventListener() {
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


    // Save Button
    public void save(View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");

        EditText homeinput = (EditText)findViewById(R.id.home_name);
        EditText awayinput = (EditText)findViewById(R.id.away_name);

        final TextView hsname = (TextView)findViewById(R.id.hsname);

        if (homeinput.getText().toString().length() > 0) {
            if(awayinput.getText().toString().length() > 0) {
                hname = homeinput.getText().toString();
                aname = awayinput.getText().toString();

                homeinput.setText("");
                awayinput.setText("");

                // Home Team updateDB
                match.child("homename").setValue(hname);
//                Map<String, Object> homevalue = new HashMap<String, Object>();
//                homevalue.put("id-1", hname);
//                myRef.updateChildren(homevalue);

                // Away Team updateDB
                match.child("awayname").setValue(aname);
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

        homescore = (TextView)findViewById(R.id.homescore);
        String text = homescore.getText().toString();
        n = Integer.parseInt(text);
        n = n+1;
        match.child("homescore").setValue(n);
    }
    // Del Home Score
    public void delhome (View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");

        homescore = (TextView)findViewById(R.id.homescore);
        String text = homescore.getText().toString();
        n = Integer.parseInt(text);
        if(n > 0){
            n = n-1;
            match.child("homescore").setValue(n);
        }
        else return;
    }
    // Add Away Score
    public void addaway(View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");

        awayscore = (TextView)findViewById(R.id.awayscore);
        String text = awayscore.getText().toString();
        n = Integer.parseInt(text);
        n = n+1;
        match.child("awayscore").setValue(n);
    }
    // Del Away Score
    public void delaway (View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match = myRef.child("match");

        awayscore = (TextView)findViewById(R.id.awayscore);
        String text = awayscore.getText().toString();
        n = Integer.parseInt(text);
        if(n > 0){
            n = n-1;
            match.child("awayscore").setValue(n);
        }
        else return;
    }
}

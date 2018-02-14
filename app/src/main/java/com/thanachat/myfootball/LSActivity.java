package com.thanachat.myfootball;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.FirebaseError;
import com.firebase.client.annotations.Nullable;
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

public class LSActivity extends AppCompatActivity {

    int match1 = 0;
    private LinearLayout match;
    private LinearLayout matchd;

    public DatabaseReference myRef;
    public DatabaseReference match2;
    public DatabaseReference matchname;
    public DatabaseReference livesc;

    ListView listview;
    private TextView hname;
    private TextView aname;
    private TextView hscore;
    private TextView ascore;

    String keys;

    ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> mKeys=  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls);

        listview=(ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, list);
        listview.setAdapter(adapter);

//        hname = (TextView)findViewById(R.id.homename);
//        aname = (TextView)findViewById(R.id.awayname);
//        hscore = (TextView)findViewById(R.id.homescore);
//        ascore = (TextView)findViewById(R.id.awayscore);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        match2 = myRef.child("match");
        matchname = match2.child("matchname");

//      Firebase Change Value
        matchname.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                list.add(value);
                //list.add(dataSnapshot.getValue().toString());
                String  key = dataSnapshot.getKey();
                mKeys.add(key);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                int index = mKeys.indexOf(key);
                list.set(index, value);

                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.remove(dataSnapshot.getValue().toString());
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

//        //Read Home Name
//        match2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map map = (Map)dataSnapshot.getValue();
//                String hnteam = String.valueOf(map.get("homename"));
//                hname.setText(hnteam + " :");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        //Read Away Name
//        match2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map map = (Map)dataSnapshot.getValue();
//                String anteam = String.valueOf(map.get("awayname"));
//                aname.setText(": " + anteam);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        //Read Home Score
//        match2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map map = (Map)dataSnapshot.getValue();
//                String hsc = String.valueOf(map.get("homescore"));
//                hscore.setText(hsc);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        //Read Away Score
//        match2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map map = (Map)dataSnapshot.getValue();
//                String asc = String.valueOf(map.get("awayscore"));
//                ascore.setText(asc);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    public void backLS(View view){
        finish();
    }

}



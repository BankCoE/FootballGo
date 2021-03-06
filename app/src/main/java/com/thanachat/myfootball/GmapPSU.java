package com.thanachat.myfootball;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thanachat.myfootball.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GmapPSU extends FragmentActivity {

    private GyroscopeObserver gyroscopeObserver;

    public DatabaseReference myRef;
    public DatabaseReference comment;
    public DatabaseReference psument;

    String discmt;
    String cmtname;
    String cmtcmt;
    String cmtkey;

    ListView listview;

    Button btncmt;
    Button cmtback;
    Button cmt;
    LinearLayout laydetail;
    LinearLayout layoutcmt;

    ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmap_psu);

        listview = (ListView)findViewById(R.id.listpsument);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        listview.setAdapter(adapter);

        layoutcmt = (LinearLayout)findViewById(R.id.laypsucmt);
        laydetail = (LinearLayout)findViewById(R.id.laypsudetail);
        btncmt = (Button) findViewById(R.id.btn_cmt);
        cmtback = (Button)findViewById(R.id.cmtback);
        cmt = (Button)findViewById(R.id.cmt);

        //firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        comment = myRef.child("comment");
        psument = comment.child("psu");

        //firebase change
        psument.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                String value = dataSnapshot.getValue(String.class);
//                list.add(value);
                list.add(dataSnapshot.getValue().toString());
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

        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI/3);

        PanoramaImageView panoramaImageView = (PanoramaImageView)findViewById(R.id.panorama_image_view);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);

        //comment show
        btncmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutcmt.setVisibility(View.VISIBLE);
                laydetail.setVisibility(View.GONE);
            }
        });

        //write comment
        cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //back comment
        cmtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutcmt.setVisibility(View.GONE);
                laydetail.setVisibility(View.VISIBLE);
            }
        });

        //comment
        cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GmapPSU.this);
                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog_custom, null);
                builder.setView(view);

                final EditText name = (EditText)view.findViewById(R.id.name);
                final EditText cmt = (EditText)view.findViewById(R.id.cmt);

                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cmtname = name.getText().toString();
                        cmtcmt = cmt.getText().toString();
                        discmt = cmtname + " : " + cmtcmt;

                        cmtkey = psument.push().getKey();
                        psument.child(cmtkey).setValue(discmt);
                    }
                });

                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_gmap_moo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        return true;
    }

    //return super.onOptionsItemSelected(item);

}

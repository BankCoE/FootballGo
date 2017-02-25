package com.thanachat.myfootball;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thanachat.myfootball.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> MyArrList;

    private static final String PREFS_NAME = "APP_BANK_THANACHAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        // listView1
        final ListView lisView1 = (ListView) findViewById(R.id.listv1);


        MyArrList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;

        /*** Rows 1 ***/
        map = new HashMap<String, String>();
        map.put("ID", "1");
        map.put("Code", "Running");
        map.put("Country", "30 minutes");
        MyArrList.add(map);

        /*** Rows 2 ***/
        map = new HashMap<String, String>();
        map.put("ID", "2");
        map.put("Code", "Passing");
        map.put("Country", "50 Balls");
        MyArrList.add(map);

        /*** Rows 3 ***/
        map = new HashMap<String, String>();
        map.put("ID", "3");
        map.put("Code", "Drible");
        map.put("Country", "10 minutes");
        MyArrList.add(map);

        /*** Rows 4 ***/
        map = new HashMap<String, String>();
        map.put("ID", "4");
        map.put("Code", "Free kick");
        map.put("Country", "30 Balls");
        MyArrList.add(map);

        /*** Rows 5 ***/
        map = new HashMap<String, String>();
        map.put("ID", "5");
        map.put("Code", "Penalty");
        map.put("Country", "30 Balls");
        MyArrList.add(map);


        lisView1.setAdapter(new CountryAdapter(this));


        // Check All
        Button btnCheckAll = (Button) findViewById(R.id.btnCheckAll);
        btnCheckAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count = lisView1.getAdapter().getCount();
                for (int i = 0; i < count; i++) {
                    LinearLayout itemLayout = (LinearLayout) lisView1.getChildAt(i); // Find by under LinearLayout
                    CheckBox checkbox = (CheckBox) itemLayout.findViewById(R.id.ColChk);
                    checkbox.setChecked(true);


                }



            }
        });

        // Clear All
        Button btnClearAll = (Button) findViewById(R.id.btnClearAll);
        btnClearAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count = lisView1.getAdapter().getCount();
                for (int i = 0; i < count; i++) {
                    LinearLayout itemLayout = (LinearLayout) lisView1.getChildAt(i); // Find by under LinearLayout
                    CheckBox checkbox = (CheckBox) itemLayout.findViewById(R.id.ColChk);
                    checkbox.setChecked(false);
                }

            }
        });


        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_train_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count = lisView1.getAdapter().getCount();

                SharedPreferences Settings = getBaseContext().getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = Settings.edit();


                for (int i = 0; i < count; i++) {
                    LinearLayout itemLayout = (LinearLayout) lisView1.getChildAt(i); // Find by under LinearLayout
                    CheckBox checkbox = (CheckBox) itemLayout.findViewById(R.id.ColChk);

                    if (checkbox.isChecked()) {
                        Log.d("Item " + String.valueOf(i), "hhh " + checkbox.getTag().toString());

                        editor.putInt(Integer.toString(i), 1);
                        editor.commit();
                    }
                    else
                    {
                        Log.d("Item " + String.valueOf(i), "BBB " + checkbox.getTag().toString());
                        editor.putInt(Integer.toString(i), 2);
                        editor.commit();
                    }
                }

                Toast.makeText(TrainActivity.this,
                        "Saved", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        final ListView lisView1 = (ListView) findViewById(R.id.listv1);
        int count = lisView1.getAdapter().getCount();

        SharedPreferences Settings = getBaseContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = Settings.edit();


        for (int i = 0; i < count; i++) {
            LinearLayout itemLayout = (LinearLayout) lisView1.getChildAt(i); // Find by under LinearLayout
            CheckBox checkbox = (CheckBox) itemLayout.findViewById(R.id.ColChk);

            if (checkbox.isChecked()) {
                Log.d("Item " + String.valueOf(i), "hhh " + checkbox.getTag().toString());

                editor.putInt(Integer.toString(i), 1);
                editor.commit();
            }
            else
            {
                Log.d("Item " + String.valueOf(i), "BBB " + checkbox.getTag().toString());
                editor.putInt(Integer.toString(i), 2);
                editor.commit();
            }
        }

        Toast.makeText(TrainActivity.this,
                "Saved", Toast.LENGTH_LONG).show();

        finish();
    }

    public class CountryAdapter extends BaseAdapter {
        private Context context;


        public CountryAdapter(Context c) {
            //super( c, R.layout.activity_column, R.id.rowTextView, );
            // TODO Auto-generated method stub
            context = c;
        }


        public int getCount() {
            // TODO Auto-generated method stub
            return MyArrList.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_column, null);

            }

            // ColID
            TextView txtID = (TextView) convertView.findViewById(R.id.ColID);
            txtID.setText(MyArrList.get(position).get("ID") + ".");

            // ColCode
            TextView txtCode = (TextView) convertView.findViewById(R.id.ColCode);
            txtCode.setText(MyArrList.get(position).get("Code"));

            // ColCountry
            TextView txtCountry = (TextView) convertView.findViewById(R.id.ColCountry);
            txtCountry.setText(MyArrList.get(position).get("Country"));

            // ColChk
            CheckBox Chk = (CheckBox) convertView.findViewById(R.id.ColChk);
            Chk.setTag(MyArrList.get(position).get("Code"));



            SharedPreferences Settings = getBaseContext().getSharedPreferences(PREFS_NAME, 0);

            if(Chk.getTag().toString() == "Running")
            {
                int dataCheck = Settings.getInt(Integer.toString(0), 0);
                if (dataCheck == 1) {
                    Log.d("vbb","JJJ");
                    Chk.setChecked(true);
                } else if (dataCheck==2){
                    Log.d("vbb","CCC");
                    Chk.setChecked(false);
                }

            }
            else if(Chk.getTag().toString() == "Passing")
            {
                int dataCheck = Settings.getInt(Integer.toString(1), 0);
                if (dataCheck == 1) {
                    Log.d("vbb","JJJ");
                    Chk.setChecked(true);
                } else if (dataCheck==2){
                    Log.d("vbb","CCC");
                    Chk.setChecked(false);
                }
            }
            else if(Chk.getTag().toString() == "Drible")
            {
                int dataCheck = Settings.getInt(Integer.toString(2), 0);
                if (dataCheck == 1) {
                    Log.d("vbb","JJJ");
                    Chk.setChecked(true);
                } else if (dataCheck==2){
                    Log.d("vbb","CCC");
                    Chk.setChecked(false);
                }
            }

            else if(Chk.getTag().toString() == "Free kick")
            {
                int dataCheck = Settings.getInt(Integer.toString(3), 0);
                if (dataCheck == 1) {
                    Log.d("vbb","JJJ");
                    Chk.setChecked(true);
                } else if (dataCheck==2){
                    Log.d("vbb","CCC");
                    Chk.setChecked(false);
                }
            }

            else if(Chk.getTag().toString() == "Penalty")
            {
                int dataCheck = Settings.getInt(Integer.toString(4), 0);
                if (dataCheck == 1) {
                    Log.d("vbb","JJJ");
                    Chk.setChecked(true);
                } else if (dataCheck==2){
                    Log.d("vbb","CCC");
                    Chk.setChecked(false);
                }
            }

            return convertView;

        }


    }

}


package com.thanachat.myfootball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thanachat.myfootball.R;

public class APlan1Activity extends AppCompatActivity {

    int req;

    private Canvas canvas;
    public Path path = new Path();
    public Path path1 = new Path();
    MyView mv;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplan1);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_aplan1) ;
        final LinearLayout mplan = (LinearLayout)findViewById(R.id.planmenu);

        TextView gag = (TextView)findViewById(R.id.tester2);
        ImageButton back = (ImageButton)findViewById(R.id.backPlan);
        ImageButton pman = (ImageButton)findViewById(R.id.pman);
        ImageButton pball = (ImageButton)findViewById(R.id.pball) ;
        ImageButton pre = (ImageButton)findViewById(R.id.pre);

        // Button transpent
        back.setAlpha(0.7f);
        pball.setAlpha(0.3f);
        pre.setAlpha(0.7f);

        //getExtra and Check Even
        Intent goAP1 = getIntent();
        req = goAP1.getIntExtra("chk", 0);

        mv = new MyView(this);
        //layout.addView(mv);
        mplan.addView(mv);

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                path.reset();
                mv.invalidate();

            }
        });


        pman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((ImageButton)findViewById(R.id.pman)).setAlpha(0.7f);
                ((ImageButton)findViewById(R.id.pball)).setAlpha(0.3f);
                mv.paint.setColor(Color.WHITE);
                mv.paint1.setColor(Color.RED);
            }
        });

        pball.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((ImageButton)findViewById(R.id.pman)).setAlpha(0.3f);
                ((ImageButton)findViewById(R.id.pball)).setAlpha(0.7f);
                mv.paint.setColor(Color.WHITE);
                mv.paint1.setColor(Color.RED);

            }
        });


    }


    public void backPlan (View v){
        finish();
    }

    //Draw Plan
    public class MyView extends View {

        public Paint paint = new Paint();
        public Path path = new Path();
        public Paint paint1 = new Paint();
        public Path path1 = new Path();
        public Canvas Canvas = new Canvas();
//        private ArrayList<Path> paths = new ArrayList<Path>();

        @Override

        protected void onDraw(Canvas canvas){
            Rect dest = new Rect(0, 0, getWidth(), getHeight());
            Bitmap b;
            if(req == 11) {
                  b = BitmapFactory.decodeResource(getResources(), R.drawable.ga_plan1);
//                canvas.drawBitmap(b, null, dest, paint);
//                canvas.drawPath(path, paint);
            }
            else if(req == 12){
                 b = BitmapFactory.decodeResource(getResources(), R.drawable.ga_plan2);
//                canvas.drawBitmap(b, null, dest, paint);
//                canvas.drawPath(path, paint);
            }
            else if(req == 21){
                 b = BitmapFactory.decodeResource(getResources(), R.drawable.gb_plan1);
//                canvas.drawBitmap(b, null, dest, paint);
//                canvas.drawPath(path, paint);
            }
            else if(req == 22){
                 b = BitmapFactory.decodeResource(getResources(), R.drawable.gb_plan2);
//                canvas.drawBitmap(b, null, dest, paint);
//                canvas.drawPath(path, paint);
            }
            else if(req == 31){
                 b = BitmapFactory.decodeResource(getResources(), R.drawable.gd_plan1);
//                canvas.drawBitmap(b, null, dest, paint);
//                canvas.drawPath(path, paint);
            }
            else if(req == 32){
                 b = BitmapFactory.decodeResource(getResources(), R.drawable.gd_plan2);
//                canvas.drawBitmap(b, null, dest, paint);
//                canvas.drawPath(path, paint);
            }
            else{
                 b = BitmapFactory.decodeResource(getResources(), R.drawable.g_plan);
//                canvas.drawBitmap(b, null, dest, paint);
//                canvas.drawPath(path, paint);
            }



            canvas.drawBitmap(b, null, dest, paint);

            paint.setColor(Color.WHITE);
            canvas.drawPath(path, paint);
            paint1.setColor(Color.CYAN);
            canvas.drawPath(path1, paint1);

        }

        public MyView(Context context ){
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            path =((APlan1Activity) context).path;
            paint.setAntiAlias(true);
            paint.setStrokeWidth(6f);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);

            path1 =((APlan1Activity) context).path1;
            paint1.setAntiAlias(true);
            paint1.setStrokeWidth(6f);
            paint1.setColor(Color.BLUE);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setStrokeJoin(Paint.Join.ROUND);
            //paths.add(path);
//            path1.moveTo(100,100);
//            path1.lineTo(200,200);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x, y);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    return false;
            }

            invalidate();
            return true;

        }


    }

}

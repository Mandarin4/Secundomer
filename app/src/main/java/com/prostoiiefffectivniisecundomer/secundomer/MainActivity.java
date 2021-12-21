package com.prostoiiefffectivniisecundomer.secundomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.prostoiiefffectivniisecundomer.secundomer.db.MyDBManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView del_BTN, lap_BTN, stop_BTN, start_BTN;
    private MyDBManager myDBManager;
    private Chronometer mChronometer;
    private RecyclerView rcView;
    private Adapter adapter;
    private boolean running = true;
    String time1, time2 = "00:00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChronometer = (Chronometer) findViewById(R.id.chronometer);

        start_BTN = (TextView) findViewById(R.id.start_BTN);
        stop_BTN = (TextView) findViewById(R.id.stop_BTN);
        lap_BTN = (TextView) findViewById(R.id.lap_BTN);
        del_BTN = (TextView) findViewById(R.id.del_BTN);
        rcView =  findViewById(R.id.recycler_view);

        myDBManager = new MyDBManager(this);
        myDBManager.openDb();

        dbMan();
        if(adapter.getItemCount()!=0){
            del_BTN.setVisibility(View.VISIBLE);
            rcView.setVisibility(View.VISIBLE);
        }
        mChronometer.setText("00:00:00");
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
//                long elapsedMillis = SystemClock.elapsedRealtime()
//                        - mChronometer.getBase();
                CharSequence text = mChronometer.getText();
                if (text.length()==5){
                    mChronometer.setText("00:"+text);
                }else if (text.length()==7){
                    mChronometer.setText("0:"+text);
                }

            }
        });

        start_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_BTN.setVisibility(View.GONE);
                stop_BTN.setVisibility(View.VISIBLE);
                lap_BTN.setVisibility(View.VISIBLE);
                if(adapter.getItemCount()!=0){
                    del_BTN.setVisibility(View.VISIBLE);
                }
                onStartClick();
            }
        });

        stop_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop_BTN.setVisibility(View.GONE);
                start_BTN.setVisibility(View.VISIBLE);
                lap_BTN.setVisibility(View.GONE);
                if(adapter.getItemCount()!=0){
                    del_BTN.setVisibility(View.VISIBLE);
                }
                onStopClick();
                time2 = "00:00:00";
            }
        });
        lap_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1 = mChronometer.getText().toString();
                onClickSave(time1,razTime(time1, time2));
                time2 = mChronometer.getText().toString();
                del_BTN.setVisibility(View.VISIBLE);
                dbMan();
                rcView.setVisibility(View.VISIBLE);
            }
        });
        del_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDBManager.delete();
                dbMan();
                del_BTN.setVisibility(View.GONE);
                rcView.setVisibility(View.GONE);
            }
        });
    }
    public void onStartClick() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    public void onStopClick() {
        mChronometer.stop();
    }

    public void onClickSave(String time1, String time2){

        myDBManager.insertToDb(time1, time2);

    }
    public void dbMan(){
        rcView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new Adapter(MainActivity.this,myDBManager.getFromDb());
        rcView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public String razTime(String time1, String  time2){
        DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss");
        Date date1 = null;
        try {
            date1 = df.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = df.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = (date1.getTime() - date2.getTime()) / 1000;
        long hours = diff / 3600;
        long minutes = (diff - (3600 * hours)) / 60;
        long seconds = (diff - (3600 * hours)) - minutes * 60;
        String str;
        if (minutes<10 && seconds<10) str = "0"+hours + ":0"+ (minutes) + ":0" + (seconds);
        else if(minutes<10 && seconds>10) str = "0"+hours + ":0"+ (minutes) + ":" + (seconds);
        else if(minutes>=10 && seconds<10) str = "0"+hours +":"+ (minutes) + ":0" + (seconds);
        else str = "0"+ hours +":"+ (minutes) + ":" + (seconds);

        return str;
    }
}
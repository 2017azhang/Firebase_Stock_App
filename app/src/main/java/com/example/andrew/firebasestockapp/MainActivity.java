package com.example.andrew.firebasestockapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import android.graphics.Color;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends AppCompatActivity {
    Firebase ref;
    private String[] dayzerovalues = new String[391];
    private int indexOfDayZero = -1;
    private String[] dayonevalues = new String[391];
    private int indexOfDayOne = -1;
    private String[] daytwovalues = new String[391];
    private int indexOfDayTwo = -1;
    GraphView graph;
    LineGraphSeries<DataPoint> seriesDayZero;
    LineGraphSeries<DataPoint> seriesDayOne;
    LineGraphSeries<DataPoint> seriesDayTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://stock-apptj.firebaseio.com/");

        graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(391);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(200);
        graph.getViewport().setMaxY(215);

        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.getKey().equals("0")) {//Day Number
                        for (DataSnapshot postSnapshot2 : postSnapshot.getChildren()) {
                            for (DataSnapshot postSnapshot3 : postSnapshot2.getChildren()) {
                                if (postSnapshot3.getKey().equals("1")) {//1 minute intervals
                                    for (DataSnapshot postSnapshot4 : postSnapshot3.getChildren()) {
                                        System.out.println("" + postSnapshot4.getKey());
                                        for (DataSnapshot postSnapshot5 : postSnapshot4.getChildren()) {
                                            if (postSnapshot5.getKey().equals("c")) {
                                                indexOfDayZero++;
                                                String value = "" + postSnapshot5.getValue();
                                                dayzerovalues[indexOfDayZero] = value;
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                seriesDayZero = new LineGraphSeries<>();
                for (int i = 0; i < dayzerovalues.length; i++) {
                    System.out.println("Minute " + i + ": " + dayzerovalues[i]);
                    DataPoint point = new DataPoint((double) i, Double.parseDouble(dayzerovalues[i]));
                    seriesDayZero.appendData(point, true, dayzerovalues.length);
                }
                seriesDayZero.setColor(Color.BLUE);
            }

            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("" + firebaseError.getMessage());
            }
        });

        Button retrieveData = (Button) findViewById(R.id.retrievedata);
        retrieveData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                graph.addSeries(seriesDayZero);
            }
        });


        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.getKey().equals("1")) {//Day Number
                        for (DataSnapshot postSnapshot2 : postSnapshot.getChildren()) {
                            for (DataSnapshot postSnapshot3 : postSnapshot2.getChildren()) {
                                if (postSnapshot3.getKey().equals("1")) {//1 minute intervals
                                    for (DataSnapshot postSnapshot4 : postSnapshot3.getChildren()) {
                                        System.out.println("" + postSnapshot4.getKey());
                                        for (DataSnapshot postSnapshot5 : postSnapshot4.getChildren()) {
                                            if (postSnapshot5.getKey().equals("c")) {
                                                indexOfDayOne++;
                                                String value = "" + postSnapshot5.getValue();
                                                dayonevalues[indexOfDayOne] = value;
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                seriesDayOne = new LineGraphSeries<>();
                for (int i = 0; i < dayonevalues.length; i++) {
                    System.out.println("Minute " + i + ": " + dayonevalues[i]);
                    DataPoint point = new DataPoint((double) i, Double.parseDouble(dayonevalues[i]));
                    System.out.println("" + point);
                    seriesDayOne.appendData(point, true, dayonevalues.length);
                }
                seriesDayOne.setColor(Color.RED);
            }

            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("" + firebaseError.getMessage());
            }
        });

        Button addDayOneData = (Button) findViewById(R.id.adddata);
        addDayOneData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                graph.addSeries(seriesDayOne);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.getKey().equals("2")) {//Day Number
                        for (DataSnapshot postSnapshot2 : postSnapshot.getChildren()) {
                            for (DataSnapshot postSnapshot3 : postSnapshot2.getChildren()) {
                                if (postSnapshot3.getKey().equals("1")) {//1 minute intervals
                                    for (DataSnapshot postSnapshot4 : postSnapshot3.getChildren()) {
                                        System.out.println("" + postSnapshot4.getKey());
                                        for (DataSnapshot postSnapshot5 : postSnapshot4.getChildren()) {
                                            if (postSnapshot5.getKey().equals("c")) {
                                                indexOfDayTwo++;
                                                String value = "" + postSnapshot5.getValue();
                                                daytwovalues[indexOfDayTwo] = value;
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                seriesDayTwo = new LineGraphSeries<>();
                for (int i = 0; i < daytwovalues.length; i++) {
                    System.out.println("Minute " + i + ": " + daytwovalues[i]);
                    DataPoint point = new DataPoint((double) i, Double.parseDouble(daytwovalues[i]));
                    System.out.println("" + point);
                    seriesDayTwo.appendData(point, true, daytwovalues.length);
                }
                seriesDayTwo.setColor(Color.YELLOW);
            }

            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("" + firebaseError.getMessage());
            }
        });

        Button addDayTwoData = (Button) findViewById(R.id.adddata2);
        addDayTwoData.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                graph.addSeries(seriesDayTwo);
            }
        });

        Button removeAllData = (Button) findViewById(R.id.removedata);
        removeAllData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                graph.removeAllSeries();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

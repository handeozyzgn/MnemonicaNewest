package com.example.Mnemonica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Bengu on 28.3.2017.
 */

public class deldeldel extends AppCompatActivity {
    private Button addActivity;
    private Button sendEvt;
    private Button strtAlarm;
    private Button attandanceCheck;
    private Button preExamWarning;
    private EditText actName;
    private EditText dest;
    private TimePicker tp;
    private DatePicker dp;
    String actN;
    String uid;
    static int num = 0;
    static int numberOfActivity=0;
    int hour;
    int minute;
    int day;
    int month;
    int year;
    String dayStr;
    String dayStr2;
    String dayStr3;
    String dayStr4;
    String monthStr;
    String yearStr;
    String hourStr;
    String minuteStr;
    String numOfAct;
    String activityID;
    String destination;
    private EditText attandanceLimitTxt;
    String attandanceLimitS;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String activityType;
    String limit;
    ImageButton backArrow;
    int day2;
    int day3;
    int day4;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_layout);
        Firebase.setAndroidContext(this);

        init();
        spinner = (Spinner) findViewById(R.id.spinnerActType);
        adapter = ArrayAdapter.createFromResource(this, R.array.activity_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), activityType, Toast.LENGTH_SHORT);
                activityType = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
        uid = user2.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        addActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                actN = actName.getText().toString();
                hour = tp.getCurrentHour();
                minute = tp.getCurrentMinute();
                day = dp.getDayOfMonth();
                month = dp.getMonth();
                year = dp.getYear();
                destination = dest.getText().toString();
                day2 = day +7;
                day3 = day +14;
                day4 = day +21;


                if (TextUtils.isEmpty(actN)) {
                    Toast.makeText(getApplicationContext(), "Enter Title of Activity!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Adding name of activity to database
                hourStr = String.valueOf(hour);
                minuteStr = String.valueOf(minute);
                dayStr = String.valueOf(day);
                dayStr2 = String.valueOf(day2);
                dayStr3 = String.valueOf(day3);
                dayStr4 = String.valueOf(day4);

                monthStr = String.valueOf(month);
                yearStr = String.valueOf(year);
                num++;
                activityID = String.valueOf(num);

                DatabaseReference newRef = myRef.child("Users").child(uid).child("activities").push();
                newRef.child("Activity Name").setValue(actN);
                newRef.child("Activity Hour").setValue(hourStr);
                newRef.child("Activity Minute").setValue(minuteStr);
                newRef.child("Activity Day").setValue(dayStr);
                newRef.child("Activity Month").setValue(monthStr);
                newRef.child("Activity Year").setValue(yearStr);
                newRef.child("Activity Destination").setValue(destination);
                numberOfActivity++;

                strtAlarm.setVisibility(View.VISIBLE);
                addActivity.setVisibility(View.GONE);
                backArrow.setVisibility(View.VISIBLE);


                myRef.child("Users").child(uid).child("Number of Activities").setValue(numberOfActivity);
                //Adding location information of activity to database
                if(activityType.equals("Lesson")){
                    DatabaseReference newRef2 = myRef.child("Users").child(uid).child("Lessons").push();
                    newRef2.child("Activity Name").setValue(actN);
                    newRef2.child("Activity Hour").setValue(hourStr);
                    newRef2.child("Activity Minute").setValue(minuteStr);
                    newRef2.child("Activity Day").setValue(dayStr);
                    newRef2.child("Activity Month").setValue(monthStr);
                    newRef2.child("Activity Year").setValue(yearStr);
                    newRef2.child("Activity Destination").setValue(destination);
                    newRef2.child("Attendance Limit").setValue(limit);
                    newRef2.child("Absence").setValue("0");

                    //2.Lesson Activity
                    DatabaseReference newRef12 = myRef.child("Users").child(uid).child("activities").push();
                    newRef12.child("Activity Name").setValue(actN);
                    newRef12.child("Activity Hour").setValue(hourStr);
                    newRef12.child("Activity Minute").setValue(minuteStr);
                    newRef12.child("Activity Day").setValue(dayStr2);
                    newRef12.child("Activity Month").setValue(monthStr);
                    newRef12.child("Activity Year").setValue(yearStr);
                    newRef12.child("Activity Destination").setValue(destination);
                    numberOfActivity++;

                    //3.Lesson Activity
                    DatabaseReference newRef13 = myRef.child("Users").child(uid).child("activities").push();
                    newRef13.child("Activity Name").setValue(actN);
                    newRef13.child("Activity Hour").setValue(hourStr);
                    newRef13.child("Activity Minute").setValue(minuteStr);
                    newRef13.child("Activity Day").setValue(dayStr3);
                    newRef13.child("Activity Month").setValue(monthStr);
                    newRef13.child("Activity Year").setValue(yearStr);
                    newRef13.child("Activity Destination").setValue(destination);
                    numberOfActivity++;

                    //4.Lesson Activity
                    DatabaseReference newRef14 = myRef.child("Users").child(uid).child("activities").push();
                    newRef14.child("Activity Name").setValue(actN);
                    newRef14.child("Activity Hour").setValue(hourStr);
                    newRef14.child("Activity Minute").setValue(minuteStr);
                    newRef14.child("Activity Day").setValue(dayStr4);
                    newRef14.child("Activity Month").setValue(monthStr);
                    newRef14.child("Activity Year").setValue(yearStr);
                    newRef14.child("Activity Destination").setValue(destination);
                    numberOfActivity++;

                    attandanceCheck.setVisibility(View.VISIBLE);
                }
                if(activityType.equals("Exam")){
                    DatabaseReference newRef2 =
                            myRef.child("Users").child(uid).child("Exams").push();
                    newRef2.child("Activity Name").setValue(actN);
                    newRef2.child("Activity Hour").setValue(hourStr);
                    newRef2.child("Activity Minute").setValue(minuteStr);
                    newRef2.child("Activity Day").setValue(dayStr);
                    newRef2.child("Activity Month").setValue(monthStr);
                    newRef2.child("Activity Year").setValue(yearStr);
                    newRef2.child("Activity Destination").setValue(destination);

                    preExamWarning.setVisibility(View.VISIBLE);
                    backArrow.setVisibility(View.VISIBLE);
                }

            }
        });


        sendEvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(deldeldel.this,SendEvent.class);
                intent.putExtra("dest", destination);
                intent.putExtra("name", actN);
                intent.putExtra("hour", hour);
                intent.putExtra("minute", minute);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });

        strtAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(deldeldel.this,AddActToSchedule.class);
                startActivity(intent);
            }
        });

        attandanceCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        preExamWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActivity.setVisibility(View.VISIBLE);
                preExamWarning.setVisibility(View.GONE);
                attandanceCheck.setVisibility(View.GONE);
                strtAlarm.setVisibility(View.GONE);
                backArrow.setVisibility(View.GONE);
            }
        });

    }

    private void init(){
        addActivity = (Button) findViewById(R.id.addActivity);
        sendEvt = (Button) findViewById(R.id.sendEvent);

        strtAlarm = (Button) findViewById(R.id.strtAlarm);
        strtAlarm.setVisibility(View.GONE);

        actName = (EditText) findViewById(R.id.actName);
        dp = (DatePicker)findViewById(R.id.eventDate);
        tp = (TimePicker)findViewById(R.id.eventTime);
        dest = (EditText) findViewById(R.id.dest);

        attandanceCheck = (Button) findViewById(R.id.attandanceCheck);
        attandanceCheck.setVisibility(View.GONE);

        preExamWarning = (Button) findViewById(R.id.preExamWarning);
        preExamWarning.setVisibility(View.GONE);

        backArrow = (ImageButton) findViewById(R.id.backArrow);
        backArrow.setVisibility(View.GONE);

        limit = "10";
    }
}
package com.example.Mnemonica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Mnemonica.TimeBetweenLocations.MapsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Bengu on 16.3.2017.
 */


public class Menu extends Activity {
    Button scheduleBtn;
    Button editAccount;
    Button alarm;
    Button dataTest;
    Button addAct;
    Button btnlocation, findAddressLocation, findDistTime, tryButton, pushNotification;
    Button addFriend;
    Button showFriends;
    private FirebaseAuth mAuth;
    static String LoggedIn_User_Email;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
        uid = user2.getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        Intent intent = this.getIntent();
        String userName = intent.getStringExtra("userName");
        myRef.child("Users").child(uid).child("Name").setValue(userName);

        myRef.child("Users").child(uid).child("Mail").setValue(user2.getEmail());

        pushNotification = (Button) findViewById(R.id.pushNotification);
        //OneSignal.startInit(this).init();

        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        // OneSignal.syncHashedEmail(userEmail);

        //////////////////Setting the tags for Current User./////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        pushNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,CreateEvent.class);
                startActivity(intent);
            }
        });


        final TextView txt = (TextView) findViewById(R.id.justTryDelete);


        btnlocation=(Button) findViewById(R.id.location);
        btnlocation.setVisibility(View.VISIBLE);
        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,AndroidGPSTrackingActivity.class);
                startActivity(intent);

            }
        });

        findDistTime = (Button) findViewById(R.id.findDistTime);
        findDistTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        Button btnShowToken = (Button)findViewById(R.id.button_show_token);
        btnShowToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,Try.class);
                startActivity(intent);
                /*
                //Get the token
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, "Token: " + token);
                Toast.makeText(Menu.this, token, Toast.LENGTH_LONG).show();

                //setContentView(R.layout.justtrydelete);
                //txt.setText(token);
                MyFirebaseMessagingService obj = new MyFirebaseMessagingService();
                obj.sendNotification("hans");
                */


            }
        });


        findAddressLocation=(Button) findViewById(R.id.findAdressLocation);
        findAddressLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,AddressLocation.class);
                startActivity(intent);
            }
        });

     /*   tryButton=(Button) findViewById(R.id.tryButton);
        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,AlarmManager.class);
                startActivity(intent);
            }
        });*/


        Init();
        //openPickerDialog(false);
       /* scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,Schedule.class);
                startActivity(intent);
            }
        });*/

        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,MainActivity.class);
                startActivity(intent);
            }
        });

       /* alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,Alarm.class);
                startActivity(intent);
            }
        });*/
        dataTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,LessonSchedule.class);
                startActivity(intent);
            }
        });
        addAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,CreateAct.class);
                startActivity(intent);
            }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,AddFriend.class);
                startActivity(intent);
            }
        });
        showFriends.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ShowFriends.class);
                startActivity(intent);
            }
        });


    }

    private void Init() {

        //scheduleBtn = (Button)findViewById(R.id.scheduleBtn);
        editAccount = (Button)findViewById(R.id.editAccount);
        //alarm = (Button) findViewById(R.id.alarm);
        dataTest = (Button) findViewById(R.id.dataTest);
        addAct = (Button) findViewById(R.id.addAct);
        addFriend = (Button) findViewById(R.id.addFriend);
        showFriends = (Button) findViewById(R.id.showFriends);

    }
}

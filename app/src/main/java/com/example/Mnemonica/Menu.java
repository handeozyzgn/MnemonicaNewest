package com.example.Mnemonica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

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
    Button viewAw;
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


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Init();
        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,MainActivity.class);
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

        viewAw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ViewAwards.class);
                startActivity(intent);
            }
        });
    }

    private void Init() {
        editAccount = (Button)findViewById(R.id.editAccount);
        addAct = (Button) findViewById(R.id.addAct);
        addFriend = (Button) findViewById(R.id.addFriend);
        showFriends = (Button) findViewById(R.id.showFriends);
        viewAw = (Button) findViewById(R.id.viewAw);
    }
}

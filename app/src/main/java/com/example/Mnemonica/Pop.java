package com.example.Mnemonica;

/**
 * Created by Bengu on 16.3.2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pop extends Activity {
    boolean isClicked = true;
    PopupWindow popUpWindow;
    LayoutParams layoutParams;
    LinearLayout mainLayout;
    Button btnClickHere;
    LinearLayout containerLayout;
    String str;
    String key;
    String len;
    TextView tvMsg;
    TextView tevet;
    Ringtone ringtone;
    Schedule s = new Schedule();
    private FirebaseAuth mAuth;


    private DatabaseReference dataRef;

    private FirebaseDatabase myRef;
    private String userID;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.schedule);

        tevet = (TextView)findViewById(R.id.eventTitle);
        Intent intent = this.getIntent();
        str = intent.getStringExtra("str");
        key = intent.getStringExtra("key");
        len = intent.getStringExtra("len");


        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        myRef =FirebaseDatabase.getInstance();
        dataRef = myRef.getReference("Users");


        AlertDialog.Builder builder = new AlertDialog.Builder(Pop.this);
        builder.setTitle("Mnemonica");
        builder.setMessage(str+" "+key+" "+len);
        builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {

                //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak
                //ringtone.stop();

            }
        });


        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Tamam butonuna basılınca yapılacaklar
                dataRef.child(userID).child("activities").child(key).removeValue();
                dataRef.child(userID).child("Number of Activities").setValue(len);
                Intent intent = new Intent(Pop.this,Menu.class);
                startActivity(intent);


            }
        });


        builder.show();
        addNotification();

    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.m_ic)
                        .setContentTitle("Mnemonica")
                        .setContentText(str+" "+key);

        Intent notificationIntent = new Intent(this, Pop.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());



     /*   Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(Pop.this, alarmUri);
        ringtone.play();*/

    }

}

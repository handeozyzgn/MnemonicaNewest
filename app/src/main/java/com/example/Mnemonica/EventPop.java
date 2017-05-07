package com.example.Mnemonica;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by handeozyazgan on 26/04/17.
 */

public class EventPop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        String eventN = intent.getStringExtra("eventN");

        AlertDialog.Builder builder = new AlertDialog.Builder(EventPop.this);
        builder.setTitle("Mnemonica");
        builder.setMessage(eventN + " join");
        builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {

                //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak
                //ringtone.stop();

            }
        });


        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Tamam butonuna basılınca yapılacaklar



            }
        });


        builder.show();
    }
}

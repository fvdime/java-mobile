package com.example.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsActivity extends AppCompatActivity {
    EditText editTextPhone, editTextMessage;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editTextPhone = findViewById(R.id.phoneNumber);
        editTextMessage = findViewById(R.id.message);
        buttonSend = findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(SmsActivity.this, android.Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else {
                    ActivityCompat.requestPermissions(SmsActivity.this,new String[]{Manifest.permission.SEND_SMS}, 100);
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if(requestCode == 100 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else{
            Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS(){
        //get value
        String phone = editTextPhone.getText().toString();
        String message = editTextMessage.getText().toString();

        //check condition if string is empty or not
        if (!phone.isEmpty() && !message.isEmpty()){
            //initialize sms manager
            SmsManager smsManager = SmsManager.getDefault();
            //send msg
            smsManager.sendTextMessage(phone, null, message, null, null);
            //display toast msg
            Toast.makeText(this, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
        }else {
            //when string empty
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();

        }
    }
}

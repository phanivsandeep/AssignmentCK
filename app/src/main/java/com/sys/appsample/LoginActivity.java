package com.sys.appsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText et=findViewById(R.id.phno);
        Button bt= (Button) findViewById(R.id.reqotp);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number= et.getText().toString().trim();
                if(number.isEmpty() || number.length()<10){
                    et.setError("Invalid Number");
                    et.requestFocus();
                    return;
                }
                String phonenum="+91"+number;
                Intent intent= new Intent(LoginActivity.this, otpVerification.class);
                intent.putExtra("phonenumber",phonenum);
                startActivity(intent);


            }
        });
    }
  //  @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//            Intent intent = new Intent(this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//            startActivity(intent);
//        }
//    }
}

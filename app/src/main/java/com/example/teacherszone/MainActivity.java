package com.example.teacherszone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText temail,tpass;
    Button tlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temail = findViewById(R.id.temail);
        tpass = findViewById(R.id.tpassword);
        tlogin = findViewById(R.id.tlogin);
        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Temail  = temail.getText().toString();
                String Tpass = tpass.getText().toString();

                if(TextUtils.isEmpty(Temail))
                {
                    temail.setError("Field Is Required");
                }
                else if (TextUtils.isEmpty(Tpass)){tpass.setError("Field Is Required");
                }
                else
                {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(Temail, Tpass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this,Home.class);
                                        startActivity(intent);

                                        
                                    } else {
                                        Toast.makeText(MainActivity.this, "Invaild Caredentials", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }



            }
        });
    }


    public void registration(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }
}
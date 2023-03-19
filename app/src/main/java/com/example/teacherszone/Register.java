package com.example.teacherszone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText remail,rname,rschool,rpass;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        remail = findViewById(R.id.remail);
        rname = findViewById(R.id.rname);
        rschool = findViewById(R.id.rschool);
        rpass = findViewById(R.id.rpassword);
        register = findViewById(R.id.rregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = remail.getText().toString().trim();
                String Tname = rname.getText().toString().trim();
                String Tschool = rschool.getText().toString().trim();
                String Tpass = rpass.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
               if(TextUtils.isEmpty(Email))
               {
                   remail.setError("Field Is Required");
               }
               else if (TextUtils.isEmpty(Tname)){rname.setError("Field Is Required");
                }
               else if(TextUtils.isEmpty(Tschool)){
                    rschool.setError("Field Is Required");
                }
               else if(TextUtils.isEmpty(Tpass)){
                    rpass.setError("Field Is Required");
                }
               else if(!isValidEmailId(Email))
               {
                   remail.setError("Invalid Email");
               }
               else if(!isValidPassword(Tpass)){
                   rpass.setError("Password Must have  8 characters at least 1 UpperCase, 1 Number and 1 Special Character ");

               }

               else {

                   Toast.makeText(Register.this, "Please Wait", Toast.LENGTH_SHORT).show();


                   FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                   FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                   firebaseAuth.createUserWithEmailAndPassword(Email, Tpass)
                           .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                                       String Email = remail.getText().toString().trim();
                                       String Tname = rname.getText().toString().trim();
                                       String Tschool = rschool.getText().toString().trim();
                                       String Tpass = rpass.getText().toString();
                                       String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                       teacherdata info = new teacherdata(Email,Tname,Tpass,Tschool,id);
                                       FirebaseDatabase.getInstance().getReference("teachers").child(id).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull @NotNull Task<Void> task) {
                                               Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                               startActivity(new Intent(Register.this,MainActivity.class));
                                           }
                                       });



                                   } else {
                                       Toast.makeText(Register.this, "Error To Register", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });










               }
            }
        });
    }
    private boolean isValidEmailId(String Email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(Email).matches();
    }
    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }

}
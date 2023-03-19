package com.example.teacherszone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Students extends AppCompatActivity {
    EditText sname,sclass,sdiv,sroll;
    Button add;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);


        sname = findViewById(R.id.sname);
        sclass = findViewById(R.id.sclass);
        sdiv =findViewById(R.id.sdiv);
        sdiv.setFilters(new InputFilter[] {
                new InputFilter.AllCaps() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("[a-zA-Z ]+")){
                            return cs;
                        }
                        return "";
                    }
                }});
        sroll = findViewById(R.id.sroll);

        add = findViewById(R.id.sadd);






        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle p = getIntent().getExtras();
                String auth = p.getString("key");

                String Sname = sname.getText().toString();
                String Sclass = sclass.getText().toString();
                String Sdiv = sdiv.getText().toString();
                String Sroll = sroll.getText().toString();

                sdiv.setFilters(new InputFilter[]{new InputFilter.AllCaps()});



                if(TextUtils.isEmpty(Sname))
                {
                    sname.setError("Field Is Required");
                }
                else if (TextUtils.isEmpty(Sclass)){sclass.setError("Field Is Required");
                }
                else if(TextUtils.isEmpty(Sdiv)){
                    sdiv.setError("Field Is Required");
                }
                else if(TextUtils.isEmpty(Sroll)){
                    sroll.setError("Field Is Required");
                }
                else if(auth == null){
                    Toast.makeText(Students.this, "Student Not Added Due To Invailid Key", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    studentmodel data = new studentmodel(Sname,Sroll,Sclass,Sdiv);
                    DatabaseReference student = FirebaseDatabase.getInstance().getReference();
                    String key = student.push().getKey();
                    DatabaseReference child = student.child("teachers").child(auth);
                    child.child("students").child("class").child(Sclass).child("div").child(Sdiv).child(Sroll).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            Toast.makeText(Students.this, "Student Added successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });






    }
    public static InputFilter getEditTextFilter() {
        return new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                boolean keepOriginal = true;
                StringBuilder sb = new StringBuilder(end - start);
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (isCharAllowed(c)) // put your condition here
                        sb.append(c);
                    else
                        keepOriginal = false;
                }
                if (keepOriginal)
                    return null;
                else {
                    if (source instanceof Spanned) {
                        SpannableString sp = new SpannableString(sb);
                        TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                        return sp;
                    } else {
                        return sb;
                    }
                }
            }

            private boolean isCharAllowed(char c) {
                Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
                Matcher ms = ps.matcher(String.valueOf(c));
                return ms.matches();
            }
        };
    }
}
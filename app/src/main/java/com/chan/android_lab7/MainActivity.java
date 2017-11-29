package com.chan.android_lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int MODE = MODE_PRIVATE;
    public static String PREFERENCE_NAME = "SavePassword";
    SharedPreferences SP;
    Button OK_Btn;
    Button Clear_Btn;
    EditText Edit1;
    EditText Edit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SP = getSharedPreferences(PREFERENCE_NAME, MODE);
        if(SP.getString("Password", "").equals("")){
            setContentView(R.layout.activity_main);
            onFirstStart();
        }else{
            setContentView(R.layout.activity_main2);
            on2Start();
        }
    }

    void onFirstStart(){
        OK_Btn = findViewById(R.id.OK_Btn);
        Clear_Btn = findViewById(R.id.Clear_Btn);
        Edit1 = findViewById(R.id.NPW_EditText);
        Edit2 = findViewById(R.id.CPW_EditText);

        OK_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NPW = Edit1.getText().toString();
                String CPW = Edit2.getText().toString();

                if(NPW.equals("")){
                    Toast.makeText(MainActivity.this, "Password cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if(!NPW.equals(CPW)){
                    Toast.makeText(MainActivity.this, "Password Mismatch",Toast.LENGTH_LONG).show();
                }
                else{
                    SharedPreferences.Editor SPEdit = SP.edit();
                    SPEdit.putString("Password", NPW);
                    SPEdit.commit();
                    Toast.makeText(MainActivity.this, "Password accepted",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, FileEdit.class);
                    startActivityForResult(intent, 1);
                }
            }
        });// end OK_Btn Listener

        Clear_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit1.setText("");
                Edit2.setText("");
            }
        });// end Clear_Btn Listener
    }

    void on2Start(){
        OK_Btn = findViewById(R.id.OK_Btn);
        Clear_Btn = findViewById(R.id.Clear_Btn);
        Edit1 = findViewById(R.id.PW_EditText);

        OK_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PW = Edit1.getText().toString();
                String CorrectPW = SP.getString("Password", "");

                if(PW.equals("")){
                    Toast.makeText(MainActivity.this, "Password cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if(!PW.equals(CorrectPW)){
                    Toast.makeText(MainActivity.this, "Password Mismatch",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Password accepted",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, FileEdit.class);
                    startActivityForResult(intent,1);
                }
            }
        });// end OK_Btn Listener

        Clear_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit1.setText("");
            }
        });// end Clear_Btn Listener
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        finish();
    }
}

package com.chan.android_lab7;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileEdit extends AppCompatActivity {
    EditText Name_EditText;
    EditText Content_EditText;
    Button Save_Btn;
    Button Load_Btn;
    Button Claer_Btn;
    Button Delete_Btn;
    public static int MODE = MODE_PRIVATE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_edit);

        Name_EditText = findViewById(R.id.Name_EditText);
        Content_EditText = findViewById(R.id.Content_EditText);
        Save_Btn = findViewById(R.id.Save_Btn);
        Load_Btn = findViewById(R.id.Load_Btn);
        Claer_Btn = findViewById(R.id.Clear_Btn);
        Delete_Btn = findViewById(R.id.Delete_Btn);

        Save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try(FileOutputStream FO = openFileOutput(Name_EditText.getText().toString(),MODE)){
                    String info = Content_EditText.getText().toString();
                    FO.write(info.getBytes());
                    Toast.makeText(FileEdit.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                    Log.i("TAG", "Successfully Saved File.");
                }catch (IOException e){
                    Toast.makeText(FileEdit.this, "Fail Successfully", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "Fail to Save File.");
                }
            }
        });// end Save_Btn listener

        Load_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try(FileInputStream FI = openFileInput(Name_EditText.getText().toString())){
                    byte[] conts = new byte[FI.available()];
                    FI.read(conts);
                    String contents = new String(conts);
                    Content_EditText.setText(contents);
                    Toast.makeText(FileEdit.this, "Load Successfully", Toast.LENGTH_LONG).show();
                    Log.i("TAG", "Successfully Read File.");
                }catch (IOException e){
                    Toast.makeText(FileEdit.this, "Fail to Load File", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "Fail to Save File.");
                }
            }
        });//end Load_Btn Listener

        Claer_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content_EditText.setText("");
            }
        });//end Clear_Btn Listener

        Delete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FileEdit.this.getApplicationContext().deleteFile(Name_EditText.getText().toString())) {
                    Toast.makeText(FileEdit.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(FileEdit.this, "Fail to Delete File", Toast.LENGTH_LONG).show();
                }
            }
        });//end Delete_Btn Listener
    }// end onCreate
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(FileEdit.this, MainActivity.class);
        FileEdit.this.setResult(RESULT_OK, intent);
        finish();
    }
}

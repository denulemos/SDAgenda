package com.example.sdagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText title, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
    }

    public void save (View view){
        String titlex = title.getText().toString();
        String notes = note.getText().toString();

        try{
            File sd = Environment.getExternalStorageDirectory();

            File ruta = new File(sd.getPath() , titlex);
            OutputStreamWriter create = new OutputStreamWriter(openFileOutput(titlex, Activity.MODE_PRIVATE));
            create.write(notes);
            create.flush();
            create.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            title.setText("");
            note.setText("");
        }
        catch (Exception ex){
            Toast.makeText(this , "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void Search(View view){
        String namex = title.getText().toString();

        try{
            File sd = Environment.getExternalStorageDirectory();
            File ruta = new File(sd.getPath() , namex);
            InputStreamReader open = new InputStreamReader(openFileInput(namex));
            BufferedReader read = new BufferedReader(open);
            String line = read.readLine();
            String all = "";
            while(line != null){
                all = all + line + "\n";
                line = read.readLine();
            }
            read.close();
            open.close();

            note.setText(all);
        }
        catch (Exception ex){
            Toast.makeText(this, "Error on reading", Toast.LENGTH_SHORT).show();
        }
    }
}
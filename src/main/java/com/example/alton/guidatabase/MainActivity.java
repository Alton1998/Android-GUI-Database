package com.example.alton.guidatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText e1 = (EditText) findViewById(R.id.editText);
        final EditText e2 = (EditText) findViewById(R.id.editText2);

        Button b = (Button) findViewById(R.id.button);
        Button b2=(Button) findViewById(R.id.button2);
        Button b3=(Button) findViewById(R.id.button3);
        Button b4=(Button) findViewById(R.id.button4);
        Button b5=(Button) findViewById(R.id.button5);
        db = this.openOrCreateDatabase("student", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS data (ROLL VARCHAR,NAME VARCHAR)");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //db.execSQL("INSERT INTO data (ROLL) VALUES('"+e1.getText()+'");");
                db.execSQL("INSERT INTO data VALUES('" + e1.getText().toString() +"','"+e2.getText().toString()+ "');");
                showMessage("Success", "Record added");


            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c= db.rawQuery("SELECT * FROM data",null);
                c.moveToFirst();
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }

                StringBuffer buffer= new StringBuffer();
                while (c.moveToNext())
                {
                    buffer.append("Roll number:"+c.getString(0)+"\n");
                    buffer.append("Name:"+c.getString(1)+"\n");

                }
                showMessage("ROll no",buffer.toString());
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c= db.rawQuery("SELECT * FROM data",null);
                c.moveToFirst();
                if(e1.getText().toString().equals(c.getString(0)))
                {
                    Log.i(e1.getText().toString(),"Found");
                    Log.i(e1.getText().toString(),"Updating");
                    db.execSQL("UPDATE data SET NAME='"+e2.getText().toString()+ "' where ROLL='"+e1.getText().toString()+"'");
                    Log.i(e1.getText().toString(),"Updated");
                    showMessage(e1.getText().toString(),"Updated name");
                }
                else
                {
                    showMessage(e1.getText().toString(),"Not Found");
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c= db.rawQuery("SELECT * FROM data",null);
                c.moveToFirst();
                if(e1.getText().toString().equals(c.getString(0)))
                {
                    Log.i(e1.getText().toString(),"Found");
                    Log.i(e1.getText().toString(),"Deleting");
                    db.execSQL("DELETE FROM data WHERE ROLL = '"+e1.getText().toString()+"'");
                    Log.i(e1.getText().toString(),"Deleted");
                    showMessage(e1.getText().toString(),"Deleted");
                }
                else
                {
                    showMessage(e1.getText().toString(),"Not found");
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c= db.rawQuery("SELECT * FROM data",null);
                c.moveToFirst();
                if(e2.getText().toString().equals(c.getString(1)))
                {
                    Log.i(e2.getText().toString(),"Found");
                    Log.i(e2.getText().toString(),"Updating");
                    db.execSQL("UPDATE data SET ROLL='"+e1.getText().toString()+ "' where NAME='"+e2.getText().toString()+"'");
                    Log.i(e2.getText().toString(),"Updated");
                    showMessage(e2.getText().toString(),"Updated ROll");
                }
                else
                {
                    showMessage(e2.getText().toString(),"Not Found");
                }
            }
        });
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

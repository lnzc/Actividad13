package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tareasqlite.database.DBAdapter;
import com.example.tareasqlite.database.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        final DBAdapter helper =new DBAdapter (this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Button btnInsert=(Button) findViewById(R.id.btnInsertar);
        Button btnmostrar=(Button) findViewById(R.id.btnMostrar);

        btnInsert.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView cod=(TextView) findViewById(R.id.txtCodigo);
                TextView cat=(TextView) findViewById(R.id.txtCat);
                TextView sum=(TextView) findViewById(R.id.txtRes);
                TextView des=(TextView) findViewById(R.id.txtDes);
                String codigo=String.valueOf(cod.getText());
                String categoria=String.valueOf(cat.getText());
                String resumen=String.valueOf(sum.getText());
                String desc=String.valueOf(des.getText());
                if(db != null) {
                    helper.open();
                    helper.createTodo(categoria, resumen, desc);
                    Toast msg = Toast.makeText(getApplicationContext(), "Se inserto en la bd ", Toast.LENGTH_LONG);
                    msg.show();
                }else{
                    Toast msg = Toast.makeText(getApplicationContext(), "No ha ingresado datos ", Toast.LENGTH_LONG);
                    msg.show();
                }
            }
        });

            //BOTON MOSTRAR
        btnmostrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity_mostrar.class);
                startActivity(i);
            }
        });
    }
}


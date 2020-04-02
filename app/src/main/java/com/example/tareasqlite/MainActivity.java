package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tareasqlite.database.DBAdapter;
import com.example.tareasqlite.database.DataBaseHelper;

public class MainActivity extends AppCompatActivity {
    EditText cod,cat, sum, des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        final DBAdapter helper =new DBAdapter (this).open();
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Button btnInsert=(Button) findViewById(R.id.btnInsertar);
        Button btnmostrar=(Button) findViewById(R.id.btnMostrar);

        btnInsert.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                 cod=(EditText) findViewById(R.id.txtCodigo);
                 cat=(EditText) findViewById(R.id.txtCategoria);
                 sum=(EditText) findViewById(R.id.txtResumen);
                 des=(EditText) findViewById(R.id.txtDescripcion);

                if(db != null) {
                    helper.createTodo(cat.getText().toString(), sum.getText().toString(), des.getText().toString());
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


package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;
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
        final DBAdapter helper =new DBAdapter (this);

        Button btnInsert=(Button) findViewById(R.id.btnInsertar);
        Button btneliminar=(Button) findViewById(R.id.btnEliminar);
        Button btnmostrar=(Button) findViewById(R.id.btnMostrar);
        Button btneditar=(Button) findViewById(R.id.btnActualizar);

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
                helper.open();
                helper.createTodo(categoria,resumen,desc);
                Toast msg= Toast.makeText(getApplicationContext(),"Se inserto en la bd "+cod.getText(), Toast.LENGTH_LONG);
                msg.show();
            }
        });
        //BOTON ELIMINAR

        btneliminar.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Main_Activity_eliminar.class);
                startActivity(i);
            }
            });

            //BOTON MOSTRAR

        btnmostrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity_mostrar.class);
                startActivity(i);
            }
        });
        //BOTON EDITAR

        btneditar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity_editar.class);
                startActivity(i);
            }
        });
    }
}


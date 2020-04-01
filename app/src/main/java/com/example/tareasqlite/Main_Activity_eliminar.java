package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tareasqlite.database.DBAdapter;

public class Main_Activity_eliminar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__eliminar);

        final DBAdapter helper=new DBAdapter(this);

        Button btneliminar=(Button) findViewById(R.id.btnEliminar);
        btneliminar.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {

                TextView cod=(TextView)findViewById(R.id.txtcodeliminar);
                int cod2=Integer.parseInt(String.valueOf(cod.getText()));
                helper.open();
                helper.deleteTodo(cod2);
                helper.close();
                cod.setText("");
                Toast msg=Toast.makeText(getApplicationContext(),"Se Elimino el Registro "+cod2,Toast.LENGTH_LONG);
                msg.show();
            }

        });
    }
}

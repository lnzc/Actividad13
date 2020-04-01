package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tareasqlite.database.DBAdapter;

public class MainActivity_editar extends AppCompatActivity {
long cod2;
String cat2,res2,des2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_editar);
        final DBAdapter helper =new DBAdapter (this);

        final TextView cod=(TextView) findViewById(R.id.txtCodigo);
        final TextView cat=(TextView) findViewById(R.id.txtCategoria);
        final TextView res=(TextView) findViewById(R.id.txtResumen);
        final TextView des=(TextView) findViewById(R.id.txtDescripcion);
        cod2 = Long.parseLong(cod.getText().toString());
        cat2=cat.getText().toString();
        res2=res.getText().toString();
        des2=des.getText().toString();
        helper.open();
        helper.updateTodo(cod2,cat2,res2,des2);
        Toast msg=Toast.makeText(getApplicationContext(),"Se actualizo",Toast.LENGTH_LONG);
        msg.show();
    }
}

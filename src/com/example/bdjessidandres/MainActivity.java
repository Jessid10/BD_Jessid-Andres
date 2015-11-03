package com.example.bdjessidandres;

import com.example.bdJA.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void agregar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String documento = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromaterias = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("documento", documento);
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromaterias", nromaterias);
        bd.insert("estudiantes", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se registró el estudiante correctamente.",
                Toast.LENGTH_SHORT).show();
    }

    public void buscar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String documento = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,colegio,nromaterias  from estudiantes where documento=" + documento, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe un estudiante con dicho documento",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

 
    public void modificar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String documento = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromaterias = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromaterias", nromaterias);
        int cant = bd.update("estudiantes", registro, "documento=" + documento, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos del estudiante", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un estudiante con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }
    
    public void eliminar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String documento = et1.getText().toString();
        int cant = bd.delete("estudiantes", "documento=" + documento, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se ha borrado el estudiante correctamente",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un estudiante con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }

}

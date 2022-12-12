package com.example.wikivan.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wikivan.Controladores.DBAccess;
import com.example.wikivan.R;

public class CrearCuenta extends AppCompatActivity {

    Button bNewUser;
    Button bCancelNewUser;
    TextView txtNewUser;
    TextView txtNewPass;
    DBAccess dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        //Se enlaza los elementos creados con los elementos del layout
        bNewUser = (Button) findViewById(R.id.bCreateNewUser);
        bCancelNewUser = (Button) findViewById(R.id.bCancelNewUser);

        txtNewUser = (TextView) findViewById(R.id.txtNewUser);
        txtNewPass = (TextView) findViewById(R.id.txtNewPass);

        //Se crea el acceso a la base de datos
        dba = new DBAccess(this);

        //Cuando pulsamos a crear usuario...
        bNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se almacena en unos strings la contraseña  y el nombre de usuario
                String nom = txtNewUser.getText().toString();
                String pass = txtNewPass.getText().toString();

                //Se comprueba de que hay texto en ellos
                if(!nom.equals("") || !pass.equals("")){
                    //Se checkea si existe el nombre de usuario
                    if(!dba.checkUser(nom)){
                        //Se inserta el usuario
                        if(dba.insert(nom, pass) != -1){
                            Toast.makeText(CrearCuenta.this, "Se creo el usuario", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(CrearCuenta.this, "No se pudo crear el usuario", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(CrearCuenta.this, "El usuario ya existe", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CrearCuenta.this, "Debe introducir datos en el nombre y la contraseña", Toast.LENGTH_LONG).show();
                }
            }
        });

        //El boton de cancelar va hacia atras
        bCancelNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CrearCuenta.this, MainActivity.class);

                startActivity(i);
            }
        });
    }
}

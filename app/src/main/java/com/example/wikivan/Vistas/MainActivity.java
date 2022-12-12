package com.example.wikivan.Vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wikivan.Controladores.DBAccess;
import com.example.wikivan.R;

public class MainActivity extends AppCompatActivity {
    
    Button bInicioSesion;
    Button bCrearCuenta;
    TextView txtIniNombre;
    TextView txtIniPass;

    DBAccess dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Boton para iniciar sesión
        bInicioSesion = (Button) findViewById(R.id.bInicioSesion);
        //Boton para ir a la pantalla de crear cuenta
        bCrearCuenta = (Button) findViewById(R.id.bCrearCuenta);
        //Texto de nombre de usuario
        txtIniNombre = (TextView) findViewById(R.id.txtIniNombre);
        //Texto de la contraseña
        txtIniPass = (TextView) findViewById(R.id.txtIniPass);
        //Se crea la base de datos
        dba = new DBAccess(this);


        //Listener de inicio de sesión
        bInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = txtIniNombre.getText().toString();
                String pass = txtIniPass.getText().toString();
                //Se comprueba si se puede iniciar sesión o no
                if(dba.checkPassword(nom, pass)){
                    Intent i = new Intent(MainActivity.this, MenuActivity.class);

                    startActivity(i);
                }
            }
        });
        //Listener para crear una pantalla de creacion de usuario
        bCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CrearCuenta.class);

                startActivity(i);
            }
        });

    }
}
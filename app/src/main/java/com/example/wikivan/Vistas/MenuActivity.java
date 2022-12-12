package com.example.wikivan.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wikivan.Modelos.Carta;
import com.example.wikivan.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    Button bListaCards;
    Button bMenuExit;

    ArrayList<ArrayList<Carta>> mazos = new ArrayList<>();
    ArrayList <Carta> cartasMazo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Se enlazan los elementos creados en esta clase con los elementos del layout
        bListaCards = (Button) findViewById(R.id.bListaCards);
        bMenuExit = (Button) findViewById(R.id.bMenuExit);

        //Si pulsamos el boton bListaCards entonces...
        bListaCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se accede a la lista de cartas
                Intent i = new Intent(MenuActivity.this, ListaCartas.class);
                startActivity(i);
            }
        });

        //Al pulsar el siguiente boton...
        bMenuExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sale del activity
                MenuActivity.this.finish();
            }
        });

    }
}

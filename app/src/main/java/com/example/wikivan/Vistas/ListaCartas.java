package com.example.wikivan.Vistas;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikivan.Controladores.RecyclerAdapter;
import com.example.wikivan.Modelos.Carta;
import com.example.wikivan.R;
import com.example.wikivan.io.HttpConnectCartas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaCartas extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recAdapter;
    //Lista que se utilizara para almacenar los datos sacados del JSON
    private ArrayList <Carta> listCards = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_cartas);

        //Conectamos con el JSON añadiendole el endpoint, que muestra todos los datos
        //de las cartas que sean de tipo monstruo
        new taskConnections().execute("GET", "/cardinfo.php?type=Normal%20Monster");

        //Se enlaza el recyclerview con su layout
        recyclerView = (RecyclerView) findViewById(R.id.recylerCardList);

        mostrarDatos();

    }

    //Este metodo se utiliza para mostrar los datos en el layout
    private void mostrarDatos(){
        //Se crea el recyclerAdapter y se le añade la lista donde estara almacenado los datos del JSON procesados
        recAdapter = new RecyclerAdapter(listCards);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    //Se crea el metodo asincrono que nos conectara con el JSON
    private class taskConnections extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            switch (strings[0]){
                case "GET":
                    result = HttpConnectCartas.getRequest(strings[1]);
                    break;
                case "POST":
                    result = Integer.toString(HttpConnectCartas.postRequest(strings[1],strings[2]));
                    break;
            }

            return result;
        }


        @Override
        protected void onPostExecute(String s) {
            try {
                //Si el JSON no es nulo entonces...
                if(s != null){
                    //Se indica al usuario que se estan añadiendo las cartas
                    Toast.makeText(ListaCartas.this, "Añadiendo cartas", Toast.LENGTH_SHORT).show();

                    //Se pilla el JSON completo
                    JSONObject jsonObject = new JSONObject(s);
                    //Lo procesa el JSON Completo desde la palabra data
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    //Se crean variables auxiliares para la facilidad de manejo de datos
                    String name = " ";
                    String atk = "";
                    String def = "";
                    String nivel = "";
                    String raza = "";
                    String aux = "";
                    String img = "";

                    //Seguira almacenando datos hasta el final de estos o hasta 1000 elementos
                    for(int i=0; i<jsonArray.length() && i<1000; i++){

                        //Se procesa cada información
                        name = jsonArray.getJSONObject(i).getString("name");
                        atk = jsonArray.getJSONObject(i).getString("atk");
                        def = jsonArray.getJSONObject(i).getString("def");
                        nivel = jsonArray.getJSONObject(i).getString("level");
                        raza = jsonArray.getJSONObject(i).getString("race");

                        //Al tener dos padres el link de la imagen hacemos esto para poder procesar
                        //el dato faltante
                        aux = jsonArray.getJSONObject(i).getString("card_images");
                        JSONObject sJSON = new JSONObject("{\"card_images\":" + aux + "}");

                        JSONArray sArray = sJSON.getJSONArray("card_images");

                        //Se almacena la url de la imagen que era el dato faltante
                        img = sArray.getJSONObject(0).getString("image_url");
                        listCards.add(new Carta(name, atk, def, nivel, raza, img));
                    }

                    //Se muestran los datos por pantalla
                    mostrarDatos();

                }else{
                    Toast.makeText(ListaCartas.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

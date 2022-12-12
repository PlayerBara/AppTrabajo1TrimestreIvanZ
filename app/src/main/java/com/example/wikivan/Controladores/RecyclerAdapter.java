package com.example.wikivan.Controladores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.wikivan.Modelos.Carta;
import com.example.wikivan.R;
import com.example.wikivan.Vistas.MostrarImagen;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{

    //La lista se utilizara como auxiliar
    List<Carta> listCard;
    Context context;
    private CircularProgressDrawable progressDrawable;

    public RecyclerAdapter(List<Carta> listCard) {
        this.listCard = listCard;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_list_of_card,parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);
        context = parent.getContext();
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int posicion) {
        Carta carta = listCard.get(posicion);

        //Se indica como se introduciria los datos en cada elemento de la lista
        holder.txtCardName.setText(carta.getNom());
        holder.txtCardAttack.setText(String.valueOf("ATK/ " + carta.getAtaque()));
        holder.txtCardDefends.setText(String.valueOf("DEF/ " + carta.getDefensa()));
        holder.txtCardLevel.setText(String.valueOf("Level/ " + carta.getNivel()));
        holder.txtCardRaza.setText("Raza/ " + carta.getRaza());

        //Este codigo se utiliza para indicar que se esta cargando las imagenes
        progressDrawable = new CircularProgressDrawable(this.context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setStyle(CircularProgressDrawable.LARGE);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        //Se utiliza para cargar las imagenes de internet
        Glide.with(holder.imgCardMin)
                .load(carta.getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgCardMin);

        //Si se pulsa este boton...
        holder.bVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se ve la imagen del elemento seleccionado y sale un aviso del nombre del seleccionado
                Toast.makeText(view.getContext(), holder.txtCardName.getText(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), MostrarImagen.class);
                i.putExtra("img", carta.getImg());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        ImageView imgCardMin;
        TextView txtCardName;
        TextView txtCardAttack;
        TextView txtCardDefends;
        TextView txtCardLevel;
        TextView txtCardRaza;

        Button bVer;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            //Se enlaza los elementos del layout
            imgCardMin  = (ImageView) itemView.findViewById(R.id.imgCardMin);
            txtCardName = (TextView)  itemView.findViewById(R.id.txtCardName);
            txtCardAttack  = (TextView)  itemView.findViewById(R.id.txtCardAttack);
            txtCardDefends = (TextView) itemView.findViewById(R.id.txtCardDefends);
            txtCardLevel = (TextView) itemView.findViewById(R.id.txtCardLevel);
            txtCardRaza = (TextView) itemView.findViewById(R.id.txtCardRaza);

            bVer = (Button) itemView.findViewById(R.id.bRecyclerVer);

        }
    }
}

package com.example.wikivan.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.wikivan.Controladores.RecyclerAdapter;
import com.example.wikivan.R;

public class MostrarImagen extends AppCompatActivity {

    ImageView imgCardBig;
    private CircularProgressDrawable progressDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_en_grande);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imgCardBig = (ImageView) findViewById(R.id.imgCardBig);

        Intent i = getIntent();
        String idImagen = i.getExtras().getString("img");

        progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setStyle(CircularProgressDrawable.LARGE);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        Glide.with(this)
                .load(idImagen)
                .error(R.mipmap.ic_launcher)
                .into(imgCardBig);
    }


}

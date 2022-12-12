package com.example.wikivan.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.wikivan.Modelos.Usuario;

import java.util.ArrayList;

public class DBAccess extends SQLiteOpenHelper {

    //Nombre de la base de datos
    private static final String DB_NAME = "WikiVan_DB";
    //Nombre de la tabla de la base de datos
    private static final String DB_TABLE_NAME = "Usuarios";
    //Version de la base de datos
    private static final int DB_VERSION = 1;
    //Columnas de la tabla
    private static final String USER_COLUMN = "Nombre";
    private static final String PASSWORD_COLUMN = "Password";
    //Contexto de la aplicación
    private Context mContext;

    public DBAccess(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        mContext = context;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //Se los comandos para crear una tabla en sqlite
        String CREATE_USER_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "("
                + USER_COLUMN + " TEXT PRIMARY KEY, "
                + PASSWORD_COLUMN + " TEXT NOT NULL)";
        //Se ejecuta el comando antes realizado en CREATE_USER_TABLE
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionVieja, int versionNueva) {
        //Actualizar

    }

    public long insert(String nombre, String password){
        //Iniciamos SQLite y lo hacemos para que podamos escribir y leer
        SQLiteDatabase db = this.getWritableDatabase();
        //Creamos el result -1 para que si no se puede añadir salga un valor negativo
        long result = -1;

        //Creamos un contenedor donde almacenaremos las columnas y sus nuevos valores
        ContentValues values = new ContentValues();

        //Añadimos valores al contenedor
        values.put(USER_COLUMN, nombre);
        values.put(PASSWORD_COLUMN, password);

        //Insertamos ese contenedor dentro del log result
        result = db.insert(DB_TABLE_NAME, null, values);

        //Cerramos la base de datos
        db.close();

        return result;
    }

    public boolean checkUser (String user){

        boolean existe = false;
        SQLiteDatabase db = this.getReadableDatabase();

        //Creamos un cursor que nos dara si funciona correctamente o 1 o ningun registro de la tabla
        Cursor c = db.rawQuery("SELECT * FROM " + DB_TABLE_NAME  +
                " WHERE " + USER_COLUMN + " = '" + user + "'", null);

        //Si se mueve al principio entonces significa que existe ese registro
        if(c.moveToFirst()){
            existe = true;
        }

        c.close();
        db.close();
        return existe;
    }

    public boolean checkPassword (String user, String pass){
        boolean existe = false;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            //Se almacena el registro si existe
            Cursor c = db.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " " +
                    "WHERE " + USER_COLUMN + " = '" + user +
                    "' AND " + PASSWORD_COLUMN + " = '" + pass + "'", null);

            //Se comprueba si existe ese dato
            if(c.moveToFirst()){
                existe = true;
            }

            c.close();
        } catch (SQLException ex){ }

        db.close();
        return existe;
    }

}

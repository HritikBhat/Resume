package com.hritik.resume;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;

public class MyHelper extends SQLiteOpenHelper
{
    public MyHelper(Context context){
        super(context,"res.db",null,1);
    }
    public void onCreate(SQLiteDatabase db){
        try {
            db.execSQL("create table ach(ach_desc text) ");
            db.execSQL("create table dec(dec_desc text)");
            db.execSQL("create table obj(obj_desc text)");
            db.execSQL("create table sk(sk_desc text)");
            db.execSQL("create table prg(name text,tech text,prg_desc text)");
            db.execSQL("create table edu(name text,city text,degree text,tfrom text,tto text,type text,grade text)");
            db.execSQL("create table intr(name text,city text,tfrom text,tto text,intr_desc text)");
            db.execSQL("create table exp(name text,city text,post text,tfrom text,tto text,exp_desc text)");
            db.execSQL("create table psl(name text,addr text,phone text,email text,dob text)");


            //db.execSQL("CREATE UNIQUE INDEX idx_category ON callmg(category);");
        } catch (SQLiteException e) {
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }}
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("drop table if exists ach");
        db.execSQL("drop table if exists dec");
        db.execSQL("drop table if exists obj");
        db.execSQL("drop table if exists sk");
        db.execSQL("drop table if exists prg");
        db.execSQL("drop table if exists edu");
        db.execSQL("drop table if exists intr");
        db.execSQL("drop table if exists exp");
        db.execSQL("drop table if exists psl");
        onCreate(db);
    }
    public Cursor getAllData(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+table;
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getCategoryNames(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT cname FROM catnm";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }

    public int getRows(){
        String countQuery = "SELECT  * FROM callmg";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public void onDeleteOne(String table,String col,String con){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+table+" WHERE "+col+" = '" + con + "'";
        db.execSQL(query);
    }
    public void onDeleteAll(String tb){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+tb;
        db.execSQL(query);
        db.close();
    }
    public void onDeleteAllTables(){
        ArrayList<String> tables = new ArrayList<String>();
        tables.add("ach");
        tables.add("dec");
        tables.add("obj");
        tables.add("sk");
        tables.add("prg");
        tables.add("edu");
        tables.add("intr");
        tables.add("exp");
        tables.add("psl");

        for(int i=0;i<tables.size();i++) {
            SQLiteDatabase db = this.getWritableDatabase();
            try {
                String query = "DELETE FROM " + tables.get(i);
                db.execSQL(query);
            }
            catch (Exception e){e.printStackTrace();}
            db.close();
        }

    }

}



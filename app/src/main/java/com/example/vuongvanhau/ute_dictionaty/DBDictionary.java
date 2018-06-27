package com.example.vuongvanhau.ute_dictionaty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBDictionary extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DBDictionary";
    private static final String ten_table = "ds_wordlist";
    // danh sách các field của table Dictionary
    private static final String KEY_ID = "_id";
    private static final String KEY_WORD = "word";
    private static final String KEY_NGHIA = "nghia";
    private static final String KEY_DONGNGHIA = "dnghia";
    private static final String KEY_IMAGE = "image";

    public DBDictionary(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // tạo table
        String SQL_String = "CREATE TABLE " + ten_table + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_WORD + " TEXT," + KEY_NGHIA + " TEXT,"
                + KEY_DONGNGHIA + " TEXT," + KEY_IMAGE + " TEXT" +")";
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ten_table);
        // Create tables again
        onCreate(db);
    }
    // tự xây dựng các phương thức thao tác
    public void them_word(Dictionary dictionary) {
        SQLiteDatabase db = this.getWritableDatabase();
        String nullColumnHack=null;
        ContentValues values = new ContentValues();
        values.put(KEY_WORD,dictionary.getWord());
        values.put(KEY_NGHIA, dictionary.getNghia());
        values.put(KEY_DONGNGHIA, dictionary.getDnghia());
        values.put(KEY_IMAGE, dictionary.getImage());
        db.insert(ten_table, nullColumnHack, values);
        //String Insert_Data= "INSERT INTO "+ten_table+ " VALUES("+dictionary.getWord()+","+dictionary.getNghia()+","+dictionary.getDnghia()+","+dictionary.getTnghia()+","+dictionary.getImage()+")";
        //db.execSQL(Insert_Data);
        db.close();
    }
    public ArrayList<Dictionary> getWord(String  word) {
        ArrayList<Dictionary> dictionaryList = new ArrayList<Dictionary>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + ten_table + " WHERE " +KEY_WORD + " LIKE '"+word+"%'";
        String[] ds_dieukien_loc=null;
        Cursor cursor = db.rawQuery(selectQuery, ds_dieukien_loc);

        if (cursor.moveToFirst()) {
            do {
                Dictionary dictionary = new Dictionary();
                dictionary.setWord(cursor.getString(1));
                dictionary.setNghia(cursor.getString(2));
                dictionary.setDnghia(cursor.getString(3));
                dictionary.setImage(cursor.getString(4));
                dictionaryList.add(dictionary);
            } while (cursor.moveToNext());
        }
        return dictionaryList;
    }

    public ArrayList<Dictionary> getAllWord() {
        ArrayList<Dictionary> dictionaryList = new ArrayList<Dictionary>();
        String[] ds_dieukien_loc=null;
        String selectQuery = "SELECT * FROM " + ten_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, ds_dieukien_loc);
        if (cursor.moveToFirst()) {
            do {
                Dictionary dictionary = new Dictionary();
                dictionary.setWord(cursor.getString(1));
                dictionary.setNghia(cursor.getString(2));
                dictionary.setDnghia(cursor.getString(3));
                dictionary.setImage(cursor.getString(4));

                dictionaryList.add(dictionary);
            } while (cursor.moveToNext());
        }
        return dictionaryList;
    }

    public int soluong_Word() {
        int sl=0;
        String[] ds_dieukien_loc=null;
        String selectQuery = "SELECT * FROM " + ten_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, ds_dieukien_loc);
        if (cursor.moveToFirst()) {
            do {
                sl++;
            } while (cursor.moveToNext());
        }
        return sl;
    }

    public int updateWord(Dictionary dictionary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NGHIA, dictionary.getNghia());
        values.put(KEY_DONGNGHIA, dictionary.getDnghia());
        values.put(KEY_IMAGE, dictionary.getImage());
        String whereClause=KEY_WORD + "=?";
        String[] whereArgs={String.valueOf(dictionary.getWord())};
        return db.update(ten_table, values, whereClause, whereArgs);
    }
    public void deleteWord(Dictionary dictionary) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause="word=?";
        String[] whereArgs={String.valueOf(dictionary.getWord())};
        db.delete(ten_table, whereClause, whereArgs);
    }
}

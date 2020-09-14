package com.anjali.internship_challenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "ToDo";
    private static final int DB_VER = 1;
    private static final String DB_TABLE = "Task";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TASK = "Task_Name";
    private static final String COLUMN_TIME = "Date";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query = "CREATE TABLE " + DB_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + " TEXT, " +
                COLUMN_TIME + " TEXT);";

        DB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(DB);

    }
    void addBook(String task,String date){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TASK, task);
        cv.put(COLUMN_TIME, date);
        long result=DB.insert(DB_TABLE,null,cv);
        if(result < 2){
            Toast.makeText(context, "Application failed", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Inserted succesfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query="SELECT * FROM " + DB_TABLE;
        SQLiteDatabase DB=this.getReadableDatabase();

        Cursor cursor=null;
        if (DB != null ){
            cursor = DB.rawQuery(query,null);
        }
        return cursor;
    }

    public void updateData(String row_id, String task,String date){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TASK, task);
        cv.put(COLUMN_TIME, date);

        long result=DB.update(DB_TABLE,cv," id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Application failed to update", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Updated succesfully", Toast.LENGTH_SHORT).show();
        }
    }

    public int removePlace(String Id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE, "id=?",new String[]{String.valueOf(Id)});
    }
}

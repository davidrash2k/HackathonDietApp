package comet.hackathonfoodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Zehcnas on 2/22/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "notebook";

    public DatabaseOpenHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // if schema does not exist, onCreate is called
        //in onCreate, create tables
        //CREATE TABLE note(_id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT, note TEXT,
        /*String sql = "CREATE TABLE " + Note.TABLE_NAME + " (" + Note.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Note.COLUMN_TITLE + " TEXT," + Note.COLUMN_NOTE + " TEXT);";*/

       /* String sql = "CREATE TABLE " + Note.TABLE_NAME + " (" + Note.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Note.COLUMN_TITLE + " TEXT," + Note.COLUMN_NOTE + " TEXT);"; */

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        //drop tables if they already exist, then call onCreate

       // String sql = "DROP TABLE IF EXISTS " + Note.TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }


    public long insertNote(Note n) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Note.COLUMN_TITLE, n.getTitle());
        contentValues.put(Note.COLUMN_NOTE, n.getNote());
        long id = db.insert(Note.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }


    //query note
    public Note queryNote(int id) {
        Note note = null;
        SQLiteDatabase db = getReadableDatabase();
        // null == *
        Cursor cursor = db.query(Note.TABLE_NAME, null, Note.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            note = new Note();
            note.setTitle(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE)));
            note.setNote(cursor.getString(cursor.getColumnIndex((Note.COLUMN_NOTE))));
            note.setId(id);
        }

        cursor.close();
        return note;
    }


    //query all notes
   /* public ArrayList<Note> queryAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Note.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setTitle(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE)));
                note.setNote(cursor.getString(cursor.getColumnIndex((Note.COLUMN_NOTE))));
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                notes.add(note);

            } while (cursor.moveToNext());
        }
            return notes;
        }*/


    //query all notes
    public Cursor queryAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Note.TABLE_NAME, null, null, null, null, null, null);

        return cursor;

        //DO NOT CLOSE cursor or db
    }


    public int updateNote(Note n){
        //updateNote contains the ORIGINAL_ID
        //updatedNote contains the NEW title and note
        //return the number of rows affected
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Note.COLUMN_TITLE, n.getTitle());
        contentValues.put(Note.COLUMN_NOTE, n.getNote());

        return db.update(Note.TABLE_NAME, contentValues, Note.COLUMN_ID + " = ?", new String[]{String.valueOf(n.getId())});
        }

    public int deleteNote(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});

    }

    }









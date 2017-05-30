package tr.com.berkaytutal.beslenmedanismani.Utils;

/**
 * Created by berka on 29.05.2017.
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import org.apache.commons.lang3.SerializationUtils;

public class DBHelper extends SQLiteOpenHelper {

    //TODO buralar hep yapılacak, delete all var, bir de if not exist ile create etsin yoksa sıkıntı hep

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String PROGRAMS_TABLE_NAME = "programs";
    public static final String PROGRAMS_COLUMN_ID = "id";
    public static final String PROGRAMS_COLUMN_DATA = "data";
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_DATA = "data";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table IF NOT EXISTS " + PROGRAMS_TABLE_NAME +
                        "(" + PROGRAMS_COLUMN_ID + " integer primary key, " + PROGRAMS_COLUMN_DATA + " blob)"
        );
        db.execSQL(
                "create table IF NOT EXISTS " + USER_TABLE_NAME +
                        "(" + USER_COLUMN_ID + " integer primary key, " + USER_COLUMN_DATA + " blob)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + PROGRAMS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertProgram(ProgramPOJO programPOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] data = SerializationUtils.serialize(programPOJO);

        contentValues.put(PROGRAMS_COLUMN_ID, programPOJO.getProgramID());
        contentValues.put(PROGRAMS_COLUMN_DATA, data);

        db.insert(PROGRAMS_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertUser(UserDataPOJO userDataPOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] data = SerializationUtils.serialize(userDataPOJO);

        contentValues.put(USER_COLUMN_ID, userDataPOJO.getUser_ID());
        contentValues.put(USER_COLUMN_DATA, data);

        db.insert(USER_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int programID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PROGRAMS_TABLE_NAME + " where " + PROGRAMS_COLUMN_ID + "=" + programID + "", null);
        return res;
    }

    public ProgramPOJO getProgram(int programID) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PROGRAMS_TABLE_NAME + " where " + PROGRAMS_COLUMN_ID + "=" + programID + "", null);


        byte[] data = res.getBlob(res.getColumnIndex(PROGRAMS_COLUMN_ID));
        ProgramPOJO program = (ProgramPOJO) SerializationUtils.deserialize(data);
        return program;

    }

    public UserDataPOJO getUser(int userID) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + USER_TABLE_NAME + " where " + USER_COLUMN_ID + "=" + userID + "", null);


        byte[] data = res.getBlob(res.getColumnIndex(PROGRAMS_COLUMN_ID));
        UserDataPOJO user = (UserDataPOJO) SerializationUtils.deserialize(data);
        return user;

    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PROGRAMS_TABLE_NAME);
        return numRows;
    }

    public boolean updateProgram(ProgramPOJO programPOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] data = SerializationUtils.serialize(programPOJO);
        contentValues.put(PROGRAMS_COLUMN_DATA, data);

        db.update(PROGRAMS_TABLE_NAME, contentValues, PROGRAMS_COLUMN_ID + " = ? ", new String[]{Integer.toString(programPOJO.getProgramID())});
        return true;
    }

    public boolean updateUser(UserDataPOJO userDataPOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] data = SerializationUtils.serialize(userDataPOJO);
        contentValues.put(USER_COLUMN_DATA, data);

        db.update(USER_TABLE_NAME, contentValues, USER_COLUMN_ID + " = ? ", new String[]{Integer.toString(userDataPOJO.getUser_ID())});
        return true;
    }

    public Integer deleteProgram(Integer programID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PROGRAMS_TABLE_NAME,
                PROGRAMS_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(programID)});
    }

    public Integer deleteUser(Integer userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME,
                USER_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(userID)});
    }

    public boolean deleteAllPrograms() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + PROGRAMS_TABLE_NAME);

        return true;
    }

    public ArrayList<ProgramPOJO> getAllPrograms() {
        ArrayList<ProgramPOJO> array_list = new ArrayList();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PROGRAMS_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {


            byte[] data = res.getBlob(res.getColumnIndex(PROGRAMS_COLUMN_ID));
            ProgramPOJO program = (ProgramPOJO) SerializationUtils.deserialize(data);
            array_list.add(program);
            res.moveToNext();
        }
        return array_list;
    }
}
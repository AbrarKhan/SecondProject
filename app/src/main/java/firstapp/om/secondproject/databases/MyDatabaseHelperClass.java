package firstapp.om.secondproject.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="gulf_college_database.db";
    public static final String TABLE_NAME="student_table";
    public static final String ID="_id";
    public static final String NAME="name";
    public static final String USERID="user_id";
    public static final String PASSWORD="password";
    public static final int VERSION=5;
    private Context context;
    private  SQLiteDatabase database;

    private static final String SQL="create table "+TABLE_NAME+"("+ID+" integer " +
        "primary key autoincrement,"+NAME+" text not null,"+USERID+" text not null,"+PASSWORD+" " +
        "text not null"+");";
    public MyDatabaseHelperClass(@Nullable Context context) {
        super(context,DATABASE_NAME,null,VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
           sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
           onCreate(sqLiteDatabase);
    }
    public long insertStudent(Student student){
        database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,student.getName());
        values.put(USERID,student.getUser_id());
        values.put(PASSWORD,student.getPassword());
        long l=database.insert(TABLE_NAME,null,values);
        return l;

    }
    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> studentArrayList=new ArrayList<>();
        Student student=null;
        database=getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from "+TABLE_NAME ,null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex(NAME));
                String user_id = cursor.getString(cursor.getColumnIndex(USERID));
                String pass = cursor.getString(cursor.getColumnIndex(PASSWORD));
                student = new Student();
                student.setName(name);
                student.setUser_id(user_id);
                student.setPassword(pass);
                studentArrayList.add(student);
            }while(cursor.moveToNext());

        }
        return studentArrayList;
    }


}

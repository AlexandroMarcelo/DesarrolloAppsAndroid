package csf.itesm.sqlite_complemento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CRM.db";
    public static final String TABLE_NAME = "tabla_cliente";
    public static final String COL_1 = "INE";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDOS";
    public static final String COL_4 = "VEHICULOS";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME +
                "(INE INT PRIMARY KEY AUTOINCREMENT, "+
                "NOMBRE TEXT, " +
                "APELLIDOS TEXT, " +
                "VEHICULOS INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarDatos(String nombre,String apellidos,String vehiculos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellidos);
        contentValues.put(COL_4,vehiculos);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor obtieneTodosLosDatos() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean actualizaDatos(String ine,String nombre,String apellidos,String vehiculos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ine);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellidos);
        contentValues.put(COL_4,vehiculos);
        db.update(TABLE_NAME, contentValues, "INE = ?",new String[] { ine });
        return true;
    }

    public Integer borraDatos (String ine) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "INE = ?",new String[] {ine});
    }
}

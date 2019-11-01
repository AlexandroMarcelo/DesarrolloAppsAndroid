package csf.itesm.esqueleto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdaptadorDB {

    //Del cliente
    static final String LLAVE_ID_CLIENTE = "cliente_id";
    static final String  LLAVE_NOMBRE_CLIENTE = "nombre_cliente";
    static final String  LLAVE_CORREO_CLIENTE = "correo_cliente";
    static final String  LLAVE_CONTRASENIA_CLIENTE = "contrasenia_cliente";

    //Del automovil
    static final String LLAVE_ID_AUTO = "auto_id";
    static final String  LLAVE_MARCA_AUTO = "marca_auto";
    static final String  LLAVE_NOMBRE_AUTO = "nombre_auto";
    static final String  LLAVE_PRECIO_AUTO = "precio_auto";

    //Del cliente_compra_autos
    static final String  LLAVE_ID_COMPRA_AUTO = "compra_id";
    static final String  LLAVE_FOREIGN_KEY_ClIENTE = "cliente_id_fk";
    static final String  LLAVE_FOREIGN_KEY_AUTO = "auto_id_fk";


    static final String ETIQUETA = "AdaptadorDB";

    static final String NOMBRE_BD = "CRM";
    static final String CLIENTES_TABLA = "clientes";
    static final String CLIENTES_COMPRA_AUTOS_TABLA = "clientes_compra_autos";
    static final String AUTOS_TABLA = "autos";
    static final int VERSION_DB = 2;

    static final String CREAR_TABLE_CLIENTES =
            "create table clientes (cliente_id integer primary key autoincrement, "
                    + "nombre_cliente text not null, correo_cliente text not null, contrasenia_cliente text not null);";
    static final String CREAR_TABLE_AUTOS =
            "create table autos (auto_id integer primary key autoincrement, "
                    + "marca_auto text not null, nombre_auto text not null, precio_auto text not null);";
    static final String CREAR_TABLE_CLIENTE_COMPRA_AUTOS =
            "create table clientes_compra_autos (compra_id integer primary key autoincrement, "
                    + "cliente_id_fk integer not null, auto_id_fk integer not null," +
                    "foreign key(cliente_id_fk) references clientes(cliente_id), foreign key(auto_id_fk) references autos(auto_id));";

    final Context contexto;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public AdaptadorDB(Context ctx)
    {
        this.contexto = ctx;
        DBHelper = new DatabaseHelper(contexto);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, NOMBRE_BD, null, VERSION_DB);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(CREAR_TABLE_CLIENTES);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                db.execSQL(CREAR_TABLE_AUTOS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                db.execSQL(CREAR_TABLE_CLIENTE_COMPRA_AUTOS);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(ETIQUETA, "Actualizando la version de la Base de Datos de " + oldVersion + " a "
                    + newVersion + ", este proceso eliminarÃ¡ los registros de la versiÃ³n anterior");
            db.execSQL("DROP TABLE IF EXISTS clientes");
            onCreate(db);
        }
    }

    //--- Abrimos la BD ---
    public AdaptadorDB open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //--- Cerramos la BD ---
    public void close()
    {
        DBHelper.close();
    }

    //--- Insertamos registros a la tabla clientes ---
    public long insertarClientes(String nombre, String email, String contrasenia)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(LLAVE_NOMBRE_CLIENTE, nombre);
        initialValues.put(LLAVE_CORREO_CLIENTE, email);
        initialValues.put(LLAVE_CONTRASENIA_CLIENTE, contrasenia);
        return db.insert(CLIENTES_TABLA, null, initialValues);
    }

    //--- Isertamos automoviles en la tabla autos --
    public long insertarAutos(String marca, String nombre, String precio)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(LLAVE_MARCA_AUTO, marca);
        initialValues.put(LLAVE_NOMBRE_AUTO, nombre);
        initialValues.put(LLAVE_PRECIO_AUTO, precio);
        return db.insert(AUTOS_TABLA, null, initialValues);
    }

    //--- Insertamos registros a la tabla clientes_compra_autos ---
    public long insertarClientesCompraAutos(Integer id_cliente, Integer id_auto)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(LLAVE_FOREIGN_KEY_ClIENTE, id_cliente);
        initialValues.put(LLAVE_FOREIGN_KEY_AUTO, id_auto);
        return db.insert(CLIENTES_COMPRA_AUTOS_TABLA, null, initialValues);
    }



    //--- Borra un cliente en particular ---
    public boolean borrarCliente(long idFila)
    {
        return db.delete(CLIENTES_TABLA, LLAVE_ID_CLIENTE + "=" + idFila, null) > 0;
    }

    //--- Borra un auto en particular ---
    public boolean borrarAuto(long idFila)
    {
        return db.delete(AUTOS_TABLA, LLAVE_ID_AUTO + "=" + idFila, null) > 0;
    }

    //--- Borra un auto en particular ---
    public boolean borrarClientesCompraAutos(long idFila)
    {
        return db.delete(CLIENTES_COMPRA_AUTOS_TABLA, LLAVE_ID_COMPRA_AUTO + "=" + idFila, null) > 0;
    }



    //--- Recuperamos todos los registros de la tabla clientes ---
    public Cursor obtenerTodosLosClientes()
    {
        return db.query(CLIENTES_TABLA, new String[] {LLAVE_ID_CLIENTE, LLAVE_NOMBRE_CLIENTE,
                LLAVE_CORREO_CLIENTE, LLAVE_CONTRASENIA_CLIENTE}, null, null, null, null, null);
    }

    //--- Recuperamos todos los registros de la tabla AUTOS ---
    public Cursor obtenerTodosLosAutos()
    {
        return db.query(AUTOS_TABLA, new String[] {LLAVE_ID_AUTO, LLAVE_MARCA_AUTO,
                LLAVE_NOMBRE_AUTO, LLAVE_PRECIO_AUTO}, null, null, null, null, null);
    }

    //--- Recuperamos todos los registros de la tabla clientes_compra_autos ---
    public Cursor obtenerTodosLosClientesCompraAutos()
    {
        return db.query(CLIENTES_COMPRA_AUTOS_TABLA, new String[] {LLAVE_ID_COMPRA_AUTO, LLAVE_FOREIGN_KEY_ClIENTE,
                LLAVE_FOREIGN_KEY_AUTO}, null, null, null, null, null);
    }




    //--- Recuperamos un registro de una compra en particular ---
    public Cursor obtenerUnaCompra(long idFila) throws SQLException
    {
        Cursor mCursor =
                db.query(true, CLIENTES_COMPRA_AUTOS_TABLA, new String[] {LLAVE_ID_COMPRA_AUTO,
                                LLAVE_FOREIGN_KEY_ClIENTE, LLAVE_FOREIGN_KEY_AUTO}, LLAVE_ID_COMPRA_AUTO + "=" + idFila, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



    //--- Actualizamos un registro de los clientes ---
    public boolean actualizarCliente(long idFila, String nombre, String email, String contrasenia)
    {
        ContentValues args = new ContentValues();
        args.put(LLAVE_NOMBRE_CLIENTE, nombre);
        args.put(LLAVE_CORREO_CLIENTE, email);
        args.put(LLAVE_CONTRASENIA_CLIENTE, contrasenia);
        return db.update(CLIENTES_TABLA, args, LLAVE_ID_CLIENTE + "=" + idFila, null) > 0;
    }


}

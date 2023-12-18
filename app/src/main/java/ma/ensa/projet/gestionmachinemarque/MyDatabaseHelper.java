package ma.ensa.projet.gestionmachinemarque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "machineMarqueDB";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "machine";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_REF = "machine_ref";
    private static final String COLUMN_PRIX = "machine_prix";
    private static final String COLUMN_MARQUE = "machine_marque";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_REF + " TEXT, " + COLUMN_PRIX + " INTEGER, " + COLUMN_MARQUE + " TEXT);";
        String query2 = "create Table users(email TEXT primary key, password TEXT)";
        db.execSQL(query);
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);

    }

    void addBook(String reference, int prix, String marque){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REF, reference );
        cv.put(COLUMN_PRIX, prix);
        cv.put(COLUMN_MARQUE, marque);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Machine ajoutee", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null ) {
           cursor =  db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String reference, String marque, String prix){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_REF, reference);
        cv.put(COLUMN_MARQUE, marque);
        cv.put(COLUMN_PRIX, prix);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to update.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated.", Toast.LENGTH_SHORT).show();
        }


    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

}

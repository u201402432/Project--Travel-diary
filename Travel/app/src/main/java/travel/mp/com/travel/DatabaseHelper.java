package travel.mp.com.travel;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String ROOT_DIR = "/sdcard/databases/";  //로컬db 저장
    private static final String DATABASE_NAME = "Travel.db"; //로컬db명
    private static final int SCHEMA_VERSION = 1; //로컬db 버전
    private SQLiteDatabase db ;
    private static DatabaseHelper mInstance ;

    public DatabaseHelper(Context context)    {

        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        setDB(context); // setDB에 context 부여

        db = SQLiteDatabase.openDatabase("/sdcard/databases/Travel.db", null,SQLiteDatabase.OPEN_READWRITE) ;
        Log.e(db.getPath(),"DB");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private String getCreateCategoryTableString(){
        return "CREATE TABLE "+ "list" +" ( "
                + "_ID"   						+	 " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "folder"			+	 " VARCHAR(50), "
                + "title"      	+	 " VARCHAR(50), "
                + "date"         	+	 " VARCHAR(50), "
                + "region"      	+	 " VARCHAR(50), "
                + "add_title1"      	+	 " VARCHAR(50), "
                + "add_contents1"      	+	 " VARCHAR(500), "
                + "add_title2"      	+	 " VARCHAR(50), "
                + "add_contents2"      	+	 " VARCHAR(500), "
                + "add_title3"      	+	 " VARCHAR(50), "
                + "add_contents3"      	+	 " VARCHAR(500), "
                + "add_title4"      	+	 " VARCHAR(50), "
                + "add_contents4"      	+	 " VARCHAR(500), "
                + "add_title5"      	+	 " VARCHAR(50), "
                + "add_contents5"      	+	 " VARCHAR(500), "
                + "tag1"      	+	 " VARCHAR(20), "
                + "tag2"      	+	 " VARCHAR(20), "
                + "tag3"      	+	 " VARCHAR(20), "
                + "photo"      	+	 " BLOB, "
                + "video"      	+	 " BLOB, "
                + "text"			    +	 " TEXT NOT NULL	) ; ";
    }

    public static void setDB(Context ctx) {
        File folder = new File(ROOT_DIR);
        if(folder.exists()) {
        } else {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets(); //ctx가 없으면 assets폴더를 찾지 못한다.
        File outfile = new File(ROOT_DIR+"Travel.db");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open("Travel.db", AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            if (outfile.length() <= 0) {
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } else {}
        } catch (IOException e) {
            Log.e("mkFile",e.getMessage()) ;
        }
    }

    // db 사용 메소드

    private static final String CLASSNAME = DatabaseHelper.class.getSimpleName() ;
    private static final String KEY_COLUMN = "PrimaryKey" ;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version) ;
        Log.v(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + "Create of Open database : " + name) ;
    }

    public Cursor get(String table, String[] columns) {
        return db.query(table, columns, null,null, null, null, null) ;
    }

    public Cursor get(String sql) {
        return db.rawQuery(sql, null) ;
    }

    public long insert(String table, ContentValues values){
        return db.insert(table, null, values) ;
    }

    public int update(String table, ContentValues values, long id){
        return db.update(table, values, KEY_COLUMN+"="+id , null ) ;
    }

    public int update(String table, ContentValues values, String whereClause) {
        return db.update(table, values, whereClause, null) ;
    }

    public int delete(String table, String whereClause){
        return db.delete(table, whereClause, null) ;
    }

    public int delete(String table, long id){
        return db.delete(table, KEY_COLUMN + "="+ id, null) ;
    }

    public void exec(String sql){
        db.execSQL(sql) ;
    }

    public void logCursorInfo(Cursor c){
        Log.i(Constants.LOG_TAG, "*** Cursor Begin ***" + "Results : "+ c.getCount() + "Colmns +" + c.getColumnCount() ) ;

        String rowHeaders = " || " ;
        for(int i=0 ; i<c.getColumnCount() ; i++ ){
            rowHeaders = rowHeaders.concat(c.getColumnName(i) + " || " ) ;
        }

        Log.i(Constants.LOG_TAG, "Columns " + rowHeaders) ;

        c.moveToFirst() ;
        while(c.isAfterLast() == false) {
            String rowResults = " || " ;
            for(int i=0 ; i< c.getColumnCount() ; i++) {
                rowResults = rowResults.concat(c.getString(i)+ " || ") ;
            }

            Log.i(Constants.LOG_TAG,"Row"+c.getPosition() + ": "+ rowResults) ;
            c.moveToNext() ;
        }
        Log.i(Constants.LOG_TAG, "*** Cursor End ***") ;
    }

}

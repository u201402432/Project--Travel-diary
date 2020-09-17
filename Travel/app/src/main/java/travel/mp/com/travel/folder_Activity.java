package travel.mp.com.travel;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by PSH on 2015-06-17.
 */
public class folder_Activity extends Activity {
    SQLiteDatabase db;
    DatabaseHelper helper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void Back_write(View view){
        helper = new DatabaseHelper(this,"Travel.db",null,1);
        EditText editText = (EditText)findViewById(R.id.folder_edit);
        String ed = editText.getText().toString();
        db = helper.getWritableDatabase();
        db.insert("folder", ed, null);
        finish();
    }


}
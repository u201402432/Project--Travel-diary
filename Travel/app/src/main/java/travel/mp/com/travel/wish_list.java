package travel.mp.com.travel;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

public class wish_list extends Activity {
    private Toast toast;
    private DatabaseHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        db = new DatabaseHelper(this) ;
    }

    TempPress_wish tpw;

    public void temp_wish(View v) {
        EditText t[] = new EditText[13];

        String str[] = {"title", "date", "region", "content", "preparation", "add_title2", "add_content2", "add_title3", "add_content3", "add_title4", "add_content4", "add_title5", "add_content5" };
        for (int i = 0; i < str.length ; i++) {
            String add = "W"+str[i] ;
            int lid = this.getResources().getIdentifier(add, "id", this.getPackageName());
            t[i] = (EditText) findViewById(lid);
            if (t[i].getText().toString() == null)
                t[i].setText(" ");
        }

        Cursor c = db.get("SELECT PrimaryKey FROM list");
        c.moveToLast();
        int pk = c.getInt(0);
        pk++;
        db.exec("insert into list VALUES( " + pk + ",'wishTemp','" + t[0].getText().toString() + "','" + t[1].getText().toString() + "','"
                        + t[2].getText().toString() + " ',' " + t[3].getText().toString() + " ',' " + "준비물" + " ',' " + t[4].getText().toString() + " ',' " + t[5].getText().toString() + " ',' "
                        + t[6].getText().toString() + " ',' " + t[7].getText().toString() + " ',' " + t[8].getText().toString() + " ',' " + t[9].getText().toString() + " ',' "
                        + t[10].getText().toString() + " ',' " + t[11].getText().toString() + " ',' " + t[12].getText().toString() + " ',' " + "tag1" + " ',' "
                        + "tag2" + " ',' " + "tag3" + " ' , NULL , NULL , NULL ) ; "
        );
        db.close();
        c.close();
        this.finish();

    }


    int count = 0;

    public void plus(View v){
        count++;
        if(count==1){
            GridLayout layout2 = (GridLayout) findViewById(R.id.WAdd2);
            layout2.setVisibility(View.VISIBLE);
        }
        else if(count==2){
            GridLayout layout3 = (GridLayout) findViewById(R.id.WAdd3);
            layout3.setVisibility(View.VISIBLE);
        }
        else if(count==3){
            GridLayout layout4 = (GridLayout) findViewById(R.id.WAdd4);
            layout4.setVisibility(View.VISIBLE);
        }
        else if(count==4){
            GridLayout layout5 = (GridLayout) findViewById(R.id.WAdd5);
            layout5.setVisibility(View.VISIBLE);
        }
        else {
            toast = Toast.makeText(this,
                    "더이상 추가가 불가능 합니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // Gets the data repository in write mode
    public void save(View v) {

        EditText t[] = new EditText[13];

        String str[] = {"title", "date", "region", "content", "preparation", "add_title2", "add_content2", "add_title3", "add_content3", "add_title4", "add_content4", "add_title5", "add_content5" };
        for (int i = 0; i < str.length ; i++) {
            String add = "W"+str[i] ;
            int lid = this.getResources().getIdentifier(add, "id", this.getPackageName());
            t[i] = (EditText) findViewById(lid);
            if (t[i].getText().toString() == null)
                t[i].setText(" ");
        }

        Cursor c = db.get("SELECT PrimaryKey FROM list");
        c.moveToLast();
        int pk = c.getInt(0);
        pk++;
        db.exec("insert into list VALUES( " + pk + ",'wish','" + t[0].getText().toString() + "','" + t[1].getText().toString() + "','"
                        + t[2].getText().toString() + " ',' " + t[3].getText().toString() + " ',' " + "준비물" + " ',' " + t[4].getText().toString() + " ',' " + t[5].getText().toString() + " ',' "
                        + t[6].getText().toString() + " ',' " + t[7].getText().toString() + " ',' " + t[8].getText().toString() + " ',' " + t[9].getText().toString() + " ',' "
                        + t[10].getText().toString() + " ',' " + t[11].getText().toString() + " ',' " + t[12].getText().toString() + " ',' " + "tag1" + " ',' "
                        + "tag2" + " ',' " + "tag3" + " ' , NULL , NULL , NULL ) ; "
        );
        db.close();
        c.close();
        this.finish();

    }

} //class
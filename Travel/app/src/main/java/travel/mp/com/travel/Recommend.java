package travel.mp.com.travel;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Recommend extends Activity {
    int[] rank = new int[21];
    String[] tag = {"꽃", "쇼핑", "축제", "해돋이", "물가", "유원지", "레저스포츠", "촬영지", "낚시", "체험", "농사", "산", "바다", "야경", "섬", "소풍", "전통적", "자전거", "졸맛", "문화유적", "야영장"};
    DatabaseHelper db;
    Cursor c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_activity);

        db = new DatabaseHelper(this);

        c = db.get("SELECT tag1, tag2, tag3 FROM list");
        if (c == null) {
            Toast toast = Toast.makeText(this,
                    "저장된 테그가 없습니다.", Toast.LENGTH_SHORT);
            toast.show();

            finish();
        }

        for (c.moveToFirst(); ; c.moveToNext()) {
            for (int i = 0; i < 21; i++) {
                if (c.getString(0).toString().contains(tag[i]))
                    rank[i] += 3;
                if (c.getString(1).toString().contains(tag[i]))
                    rank[i] += 2;
                if (c.getString(2).toString().contains(tag[i]))
                    rank[i] += 1;
            }
            if (c.isLast())
                break;
        }

        int r;
        for (int i = 0; i < 21; i++) {
            r = 1;

            for (int j = 0; j < 21; j++)
                if (rank[i] < rank[j])
                    r++;


            if (r == 1) {
                TextView t = (TextView) findViewById(R.id.Recommend1);
                c = db.get("SELECT * FROM travel where tag1='" + tag[i] + "' ");
                c.moveToFirst();
                try {
                    t.setText("\n1. "+c.getString(0).toString()+"\n       ("+c.getString(1).toString()+","+c.getString(2).toString()+","+c.getString(3).toString()+")");
                } catch (Exception e) {
                    t.setText("태그가 부족합니다.");
                }

            }

            if (r == 2) {
                TextView t = (TextView) findViewById(R.id.Recommend2);
                c = db.get("SELECT * FROM travel where tag2= '" + tag[i] + "' ");
                c.moveToFirst();

                try {
                    t.setText("\n2. "+c.getString(0).toString()+"\n       ("+c.getString(1).toString()+","+c.getString(2).toString()+","+c.getString(3).toString()+")");
                } catch (Exception e) {
                    t.setText("\n2. 태그가 부족합니다.");
                }

            }

            if (r == 3) {
                TextView t = (TextView) findViewById(R.id.Recommend3);
                c = db.get("SELECT * FROM travel where tag3='" + tag[i] + "' ");
                if (c != null) {
                    c.moveToFirst();
                    try {
                        t.setText("\n3. "+c.getString(0).toString()+"\n       ("+c.getString(1).toString()+","+c.getString(2).toString()+","+c.getString(3).toString()+")");
                    } catch (Exception e) {
                        t.setText("\n3. 태그가 부족합니다.");
                    }
                }
            }

        }
    }
}
package travel.mp.com.travel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by PSH on 2015-06-17.
 */
public class view_list extends Activity {

    private List list[];
    private DatabaseHelper db;
    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;
    private Cursor c;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {

        db = new DatabaseHelper(this);
        this.list = new List[500];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);

        // Android에서 제공하는 string 문자열 하나를 출력 가능한 layout으로 어댑터 생성
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.travel_list);
        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                TextView t =(TextView)view ;
                getData(t.getText().toString());
                setContentView(R.layout.view_selected_list);
            }
        });

        // ListView에 아이템 추가
        c = db.get("SELECT title FROM list");
        for (c.moveToFirst(); ; c.moveToNext()) {
            m_Adapter.add(c.getString(0));
            if (c.isLast())
                break;
        }

    }



    public void getData(String title) {

        Cursor c = db.get("SELECT * FROM list where title='" + title + "'");
        c.moveToFirst();
        //String str_[] = new String[20];

        try {
            intent = new Intent(this,View_Select_Activity.class);
            intent.putExtra("title",title);

		/*int lid = this.getResources().getIdentifier(str[i], "id", this.getPackageName());
             	TextView t = (TextView) findViewById(lid);
		str_[i]=c.getString(i+2);
		Intent intent = new Intent(this, view_Select_Activity.class);
		intent.putExtra(str[i-2],t.getText().toString());
		startActivity(intent);*/

        } catch (Exception e) {
        }


        startActivity(intent);

    }


}


package travel.mp.com.travel;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class show_list_Activity extends Activity{

    private DatabaseHelper db ;
    private Cursor c ;
    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);

        db = new DatabaseHelper(this) ;

        View.OnClickListener b = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_click(v) ;
            }
        } ;
        b.onClick(this.getCurrentFocus()) ;

        def() ;


    }

    public void def(){

        String sity[][] = {{"서울","seoul"},{"경기도","geonggi"},{"대전","daejeon"},{"대구","daegu"},{"울산","ulsan"},{"부산","busan"},{"광주","goang"},{"강원도","gangwon"},{"충청도","chung"},{"전라도","jeonla"},{"경상도","geongsang"},{"제주도","jejoo"} } ;
        int num ;
        int co = 1 ;
        int temp ;
        for(int i=0 ; i<sity.length ; i++){
            num = 0 ;
            int lid = this.getResources().getIdentifier(sity[i][1]+"_persent", "id", this.getPackageName());
            TextView m_TextView = (TextView)findViewById(lid) ;
            c = db.get("SELECT region FROM list");
            for(c.moveToFirst() ; ; c.moveToNext()) {
                if (c.getString(0).toString().contains(sity[i][0]))
                    num++;
                if (c.isLast()) {
                    co = c.getCount();
                    break;
                }

            }

            if(co!=1)
                temp = (int) (((num*1.0)/(co-1))*100) ;
            else
                temp = 0 ;

            m_TextView.setText(temp+ "%");


        }

    }


    public void mk_persent( String sity[], int num[], String name ){

        int lid = this.getResources().getIdentifier(name+"_persent", "id", this.getPackageName());

        TextView m_TextView = (TextView) findViewById(lid);
        c = db.get("SELECT * FROM list");
        c.moveToLast();
        int co = c.getInt(0);
        int sum = 0 ;
        for (int i = 0; i < sity.length; i++)
            sum += num[i] ;

        int temp = (int) (((sum*1.0)/(co-1))*100) ;
        m_TextView.setText(temp+ "%");

    }

    public void total_click(View v){

        String sity[] = {"서울","경기도","대전","대구","울산","부산","광주","강원도","충청도","전라도","경상도","제주도"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }

        int lid = this.getResources().getIdentifier("total_persent", "id", this.getPackageName());
        TextView m_TextView = (TextView) findViewById(lid);
        c = db.get("SELECT * FROM list");
        c.moveToLast();
        int co = c.getInt(0);
        int sum = 0 ;
        for (int i = 0; i < sity.length; i++)
            sum += num[i] ;

        m_TextView.setText(sum + " 건");
    }


    public void seoul_click(View v){

        String sity[] = {"서울강서구","서울구로구","서울금천구","서울노원구","서울동작구","서울서대문구","서울성동구","서울양천구","서울중랑구"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }

        mk_persent(sity, num, "seoul") ;

    }

    public void geonggi_click(View v){

        String sity[] = {"경기도구리시","경기도성남시","경기도가평시","경기도고양시","경기도과천시","경기도광명시","경기도광주시","경기도군포시","경기도남양주시","경기도동두천시","경기도부천시","경기도수원시","경기도시흥시","경기도안산시","경기도안성시","경기도안양시","경기도양주시","경기도양평시","경기도여주시","경기도오산시","경기도용인시","경기도의왕시","경기도의정부시","경기도이천시","경기도인천시","경기도파주시","경기도평택시","경기도하남시","경기도화성시","경기도포천시","경기도연천군"} ;
        int num[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }

        mk_persent(sity,num,"geonggi") ;

    }

    public void daejeon_click(View v){

        String sity[] = {"대전동구","대전중구","대전서구","대전유성구","대전대덕구"} ;
        int num[] = { 0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }

        mk_persent(sity, num, "daejeon") ;

    }
    public void daegu_click(View v){

        String sity[] = {"대구중구","대구동구","대구서구","대구남구","대구달서구","대구달성군","대구북구","대구수성군"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }

        mk_persent(sity, num, "daegu") ;

    }
    public void ulsan_click(View v){

        String sity[] = {"울산중구","울산동구","울산울주군","울산남구","울산북구"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }
        mk_persent(sity, num, "ulsan") ;

    }
    public void busan_click(View v){

        String sity[] = {"부산동구","부산영도구","부산부산진구","부산동래구","부산서구","부산남구","부산북구","부산해운대구","부산금정구","부산강서구","부산연제구","부산수영구","부산사상구", "기장군","부산중구","부산사하구"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        // xml Visible ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }

        mk_persent(sity, num, "busan") ;
    }



    public void gangwon_click(View v){

        String sity[] = {"강원도횡성군","강원도영월군","강원도평창군","강원도정선군","강원도철원군","강원도화천군","강원도양구군","강원도인제군","강원도원주시","강원도고성군","강원도강릉시","강원도태백시","강원도속초시","강원도삼척시","강원도홍천군","강원도춘천시","강원도양양군","강원도동해시"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        // xml Visible ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break;
        }
        mk_persent(sity, num, "gangwon") ;

    }
    public void chung_click(View v){

        String sity[] = {"충청도괴산군","충청도제천시","충청도음성군","충청도단양군","충청도옥천군","충청도영동군","충청도청주시","충청도충주시","충청도증평군","충청도진천군","충청도보은군","충청도세종시","충청도공주시","충청도천안시","충청도청양군","충청도홍성군","충청도논산시","충청도예산군","충청도계룡시","충청도태안군","충청도금산군","충청도부여군","충청도보령시","충청도서천군","충청도아산시","충청도서산시","충청도당진시"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        // xml Visible ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }
        mk_persent(sity, num, "chung") ;

    }
    public void jeonla_click(View v){

        String sity[] = {"전라도정읍시","전라도순창군","전라도남원시","전라도고창군","전라도김제시","전라도완주군","전라도부안군","전라도전주시","전라도진안군","전라도익산시","전라도장수군","전라도임실군","전라도군산시","전라도무주군","전라도목포시","전라도여수시","전라도장성군","전라도장흥군","전라도완도군","전라도광양시","전라도강진군","전라도담양군","전라도진도군","전라도곡성군","전라도해남군","전라도신안군","전라도영암군","전라도구례군","전라도고흥군","전라도무안군","전라도순천시","전라도보성군","전라도함평군","전라도화순군","전라도영광군","전라도나주시"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        // xml Visible ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext()) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }
        mk_persent(sity, num, "jeonla") ;

    }
    public void geongsang_click(View v){

        String sity[] = {"경상도함안군","경상도진주시","경상도창녕군","경상도통영시","경상도사천시","경상도고성군","경상도김해시","경상도산청군","경상도밀양시","경상도남해군","경상도하동군","경상도거제시","경상도창원시","경상도양산시","경상도의령군","경상도합천군","경상도함양군","경상도거창군","경상도안동시","경상도구미시","경상도포항시","경상도영천시","경상도영주시","경상도경주시","경상도김천시","경상도청도군","경상도고령군","경상도성주군","경상도경산시","경상도칠곡군","경상도군위군","경상도예천군","경상도의성군","경상도봉화군","경상도울진군","경상도상주시","경상도울릉군","경상도영덕군","경상도문경시","경상도청송군","경상도영양군"} ;
        int num[] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        // xml Visible ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }
        mk_persent(sity, num, "geongsang") ;

    }
    public void jejoo_click(View v){

        String sity[] = {"제주도"};
        int num[] = { 0 };
        c = db.get("SELECT region FROM list") ;

        // xml Visible ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }
        mk_persent(sity, num, "jejoo") ;

    }

    public void goang_click(View v){

        String sity[] = {"광주동구","광주서구","광주남구","광주북구","광주광산구"} ;
        int num[] = { 0,0,0,0,0 };
        c = db.get("SELECT region FROM list") ;

        // xml Visible ;
        if(c==null)
            return ;

        for ( c.moveToFirst() ; ; c.moveToNext() ) {

            for (int j = 0; j < sity.length; j++)
                if (c.getString(0).contains(sity[j]) )
                    num[j]++;

            // xml Visible ;
            m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) ;

            for(int i=0 ; i<sity.length ; i++){

                // Xml에서 추가한 ListView 연결
                m_ListView = (ListView) findViewById(R.id.sity_listView) ;
                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);

                // ListView에 아이템 추가
                String add = "  "+sity[i]+"        " ;
                for(int j = 0 ; j<7-sity[i].length() ; j++)
                    add += "    " ;
                add += num[i]+"건" ;
                m_Adapter.add(add) ;

            }
            if(c.isLast())
                break ;
        }
        mk_persent(sity, num, "goang") ;
    }




}
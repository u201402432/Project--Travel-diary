package travel.mp.com.travel;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class write_list_Activity extends Activity {

    int count = 0;
    private DatabaseHelper db ;
    String autoText[] = {"서울","경기도","대전","대구","울산","부산","광주","강원도","충청도","전라도","경상도","제주도","서울강서구","서울구로구","서울금천구","서울노원구","서울동작구","서울서대문구","서울성동구","서울양천구","서울중랑구",
            "경기도구리시","경기도성남시","경기도가평시","경기도고양시","경기도과천시","경기도광명시","경기도광주시","경기도군포시","경기도남양주시","경기도동두천시","경기도부천시","경기도수원시","경기도시흥시","경기도안산시","경기도안성시","경기도안양시","경기도양주시","경기도양평시","경기도여주시","경기도오산시","경기도용인시","경기도의왕시","경기도의정부시","경기도이천시","경기도인천시","경기도파주시","경기도평택시","경기도하남시","경기도화성시","경기도포천시","경기도연천군",
            "대전동구","대전중구","대전서구","대전유성구","대전대덕구", "대구중구","대구동구","대구서구","대구남구","대구달서구","대구달성군","대구북구","대구수성군",
            "울산중구","울산동구","울산울주군","울산남구","울산북구", "부산동구","부산영도구","부산부산진구","부산동래구","부산서구","부산남구","부산북구","부산해운대구","부산금정구","부산강서구","부산연제구","부산수영구","부산사상구", "기장군","부산중구","부산사하구",
            "강원도횡성군","강원도영월군","강원도평창군","강원도정선군","강원도철원군","강원도화천군","강원도양구군","강원도인제군","강원도원주시","강원도고성군","강원도강릉시","강원도태백시","강원도속초시","강원도삼척시","강원도홍천군","강원도춘천시","강원도양양군","강원도동해시",
            "충청도괴산군","충청도제천시","충청도음성군","충청도단양군","충청도옥천군","충청도영동군","충청도청주시","충청도충주시","충청도증평군","충청도진천군","충청도보은군","충청도세종시","충청도공주시","충청도천안시","충청도청양군","충청도홍성군","충청도논산시","충청도예산군","충청도계룡시","충청도태안군","충청도금산군","충청도부여군","충청도보령시","충청도서천군","충청도아산시","충청도서산시","충청도당진시",
            "전라도정읍시","전라도순창군","전라도남원시","전라도고창군","전라도김제시","전라도완주군","전라도부안군","전라도전주시","전라도진안군","전라도익산시","전라도장수군","전라도임실군","전라도군산시","전라도무주군","전라도목포시","전라도여수시","전라도장성군","전라도장흥군","전라도완도군","전라도광양시","전라도강진군","전라도담양군","전라도진도군","전라도곡성군","전라도해남군","전라도신안군","전라도영암군","전라도구례군","전라도고흥군","전라도무안군","전라도순천시","전라도보성군","전라도함평군","전라도화순군","전라도영광군","전라도나주시",
            "경상도함안군","경상도진주시","경상도창녕군","경상도통영시","경상도사천시","경상도고성군","경상도김해시","경상도산청군","경상도밀양시","경상도남해군","경상도하동군","경상도거제시","경상도창원시","경상도양산시","경상도의령군","경상도합천군","경상도함양군","경상도거창군","경상도안동시","경상도구미시","경상도포항시","경상도영천시","경상도영주시","경상도경주시","경상도김천시","경상도청도군","경상도고령군","경상도성주군","경상도경산시","경상도칠곡군","경상도군위군","경상도예천군","경상도의성군","경상도봉화군","경상도울진군","경상도상주시","경상도울릉군","경상도영덕군","경상도문경시","경상도청송군","경상도영양군",
            "제주도"  } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_list_layout) ;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        backPressCloseHandler = new BackPressCloseHandler(this);
        //setButtons();
        //setMediaLayout() ;

        db = new DatabaseHelper(this) ;

        AutoCompleteTextView text = (AutoCompleteTextView)findViewById(R.id.region) ;
        text.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,autoText)) ;

    }

    public void plus(View v) {
        count++;

        if (count == 1) {
            GridLayout layout = (GridLayout) findViewById(R.id.Add1);
            layout.setVisibility(View.VISIBLE);
        } else if (count == 2) {
            GridLayout layout2 = (GridLayout) findViewById(R.id.Add2);
            layout2.setVisibility(View.VISIBLE);
        } else if (count == 3) {
            GridLayout layout3 = (GridLayout) findViewById(R.id.Add3);
            layout3.setVisibility(View.VISIBLE);
        } else if (count == 4) {
            GridLayout layout4 = (GridLayout) findViewById(R.id.Add4);
            layout4.setVisibility(View.VISIBLE);
        } else if (count == 5) {
            GridLayout layout5 = (GridLayout) findViewById(R.id.Add5);
            layout5.setVisibility(View.VISIBLE);
        } else {
            Toast toast = Toast.makeText(this,
                    "더이상 추가가 불가능 합니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void wish(View v) {
        Intent intent = new Intent(this, wish_list.class);
        startActivity(intent);
        finish() ;
    }

    BackPressCloseHandler backPressCloseHandler;
    TempPress tp;
    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    public void temp_write(View v) {

        EditText t[] = new EditText[17] ;
        String str[] = {"title", "date", "region","content","add_title","add_content","add_title2","add_content2","add_title3","add_content3","add_title4","add_content4","add_title5","add_content5","Tag","Tag2","Tag3"} ;
        for(int i=0 ; i<17 ; i++){
            int lid = this.getResources().getIdentifier(str[i],"id",this.getPackageName()) ;
            t[i] = (EditText) findViewById(lid) ;
            if(t[i].getText().toString()==null)
                t[i].setText(" ") ;
        }

        Cursor c = db.get("SELECT PrimaryKey FROM list");
        c.moveToLast() ;
        int pk = c.getInt(0) ;
        pk++ ;
        db.exec("insert into list VALUES( " + pk + ",'temp','" + t[0].getText().toString() + "','" + t[1].getText().toString() + "','"
                        + t[2].getText().toString() + " ',' " + t[3].getText().toString() + " ',' " + t[4].getText().toString() + " ',' " + t[5].getText().toString() + " ',' " + t[6].getText().toString() + " ',' "
                        + t[7].getText().toString() + " ',' " + t[8].getText().toString() + " ',' " + t[9].getText().toString() + " ',' " + t[10].getText().toString() + " ',' "
                        + t[11].getText().toString() + " ',' " + t[12].getText().toString() + " ',' " + t[13].getText().toString()+" ',' "+t[14].getText().toString()+" ',' "
                        +t[15].getText().toString()+" ',' "+t[16].getText().toString()+" ' , NULL , NULL , NULL ) ; "
        ) ;
        db.close() ;
        c.close() ;
        this.finish() ;

    }

    public void photo(View v) {
        doSelectImage();
    }

    public void video(View v) {
        doSelectMovie();
    }

    private final int SELECT_IMAGE = 1;
    private final int SELECT_MOVIE = 2;
    // 이미지 선택
    private void doSelectImage() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivityForResult(i, SELECT_IMAGE);

        } catch (android.content.ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 동영상선택
    private void doSelectMovie() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivityForResult(i, SELECT_MOVIE);
        } catch (android.content.ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String ImagePath = "NULL" ;
    private String ImageName = "NULL" ;
    private String VideoPath = "NULL" ;
    private String VideoName = "NULL" ;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                Uri uri = intent.getData();
                String path = getPath(uri);
                ImagePath = getPath(uri) ;
                String name = getName(uri);
                ImageName = getName(uri) ;
                String uriId = getUriId(uri);
                Log.e("###", "실제경로 : " + path + "\n파일명 : " + name + "\nuri : " + uri.toString() + "\nuri id : " + uriId);
            } else if (requestCode == SELECT_MOVIE) {
                Uri uri = intent.getData();
                String path = getPath(uri);
                VideoPath = getPath(uri) ;
                String name = getName(uri);
                VideoName = getName(uri) ;
                String uriId = getUriId(uri);
                Log.e("###", "실제경로 : " + path + "\n파일명 : " + name + "\nuri : " + uri.toString() + "\nuri id : " + uriId);
            }
        }
    }

    // 실제 경로 찾기
    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // 파일명 찾기
    private String getName(Uri uri) {
        String[] projection = {MediaStore.Images.ImageColumns.DISPLAY_NAME};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // uri 아이디 찾기
    private String getUriId(Uri uri) {
        String[] projection = {MediaStore.Images.ImageColumns._ID};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void folder(View v) {
        //  Intent intent =  new Intent(this, folder_Activity.class);   // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
        //   startActivity(intent);
    }

    public void photoSave(){

        String ROOT_DIR = "/sdcard/databases/Image/" ;
        File folder = new File(ROOT_DIR) ;
        if(folder.exists()) {
        } else {
            folder.mkdirs();
        }
        File outfile = new File(ROOT_DIR+ImageName);
        FileInputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = new FileInputStream(ImagePath) ;
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
    public void videoSave(){

        String ROOT_DIR = "/sdcard/databases/Image/" ;
        File folder = new File(ROOT_DIR) ;
        if(folder.exists()) {
        } else {
            folder.mkdirs();
        }
        File outfile = new File(ROOT_DIR+VideoName);
        FileInputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = new FileInputStream(VideoPath) ;
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

    public void saveBt(View v){

        EditText t[] = new EditText[17] ;
        String str[] = {"title", "date", "region","content","add_title","add_content","add_title2","add_content2","add_title3","add_content3","add_title4","add_content4","add_title5","add_content5","Tag","Tag2","Tag3"} ;
        for(int i=0 ; i<17 ; i++){
            int lid = this.getResources().getIdentifier(str[i],"id",this.getPackageName()) ;
            t[i] = (EditText) findViewById(lid) ;
            if(t[i].getText().toString()==null)
                t[i].setText(" ") ;
        }



        Cursor c = db.get("SELECT PrimaryKey FROM list");
        c.moveToLast() ;
        int pk = c.getInt(0) ;
        pk++ ;
        db.exec("insert into list VALUES( " + pk + ",'0','" + t[0].getText().toString() + "','" + t[1].getText().toString() + "','"
                        + t[2].getText().toString() + " ',' " + t[3].getText().toString() + " ',' " + t[4].getText().toString() + " ',' " + t[5].getText().toString() + " ',' " + t[6].getText().toString() + " ',' "
                        + t[7].getText().toString() + " ',' " + t[8].getText().toString() + " ',' " + t[9].getText().toString() + " ',' " + t[10].getText().toString() + " ',' "
                        + t[11].getText().toString() + " ',' " + t[12].getText().toString() + " ',' " + t[13].getText().toString()+" ',' "+t[14].getText().toString()+" ',' "
                        +t[15].getText().toString()+" ',' "+t[16].getText().toString()+" ' , '"+ ImageName+"' , '"+VideoName+"' , NULL ) ; "
        ) ;

        if(VideoName!="NULL")
            videoSave() ;
        if(ImageName!="NULL")
            photoSave();

        db.close() ;
        c.close() ;
        this.finish() ;

    }


}

package travel.mp.com.travel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class View_Select_Activity extends Activity {
    String str[] = {"title", "date", "region", "content", "add_title", "add_content", "add_title2", "add_content2", "add_title3", "add_content3", "add_title4", "add_content4", "add_title5", "add_content5", "Tag", "Tag2", "Tag3", "photo", "video"};
    String video;
    String photo;
    TextView t;
    ImageView blobImg;
    /**
     * Called when the activity is first created.
     */

    // String temp = intent.getExtras().get(str[i]).toString();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onResume();
        setContentView(R.layout.view_selected_list);
        DatabaseHelper db = new DatabaseHelper(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Cursor c = db.get("SELECT * FROM list where title='" + title + "'");
        c.moveToFirst();
        for (int i = 0; i < 18; i++) {
            String temp = c.getString(i + 2);
            int lid = this.getResources().getIdentifier(str[i], "id", this.getPackageName());

            try {
                t = (TextView) findViewById(lid);
                t.setText(temp);
            } catch (NullPointerException null_e) {
            }
        }
        video = c.getString(20);
        photo = c.getString(19);
    }



    public void photoShow(View v){

        if(photo==null){
            Toast toast = Toast.makeText(this,
                    "저장하신 사진이 없습니다", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setVisibility(View.VISIBLE);

            File ImageFile = new File("/sdcard/databases/Image/" + photo);
            Uri ImageUri = Uri.fromFile(ImageFile);
            img.setImageURI(ImageUri);
        }
    }

    public void VideoPlay(View v) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        File videoFile = new File("/sdcard/databases/Image/"+ video);
        Uri uriFromVideoFile = Uri.fromFile(videoFile);
        intent.setDataAndType(uriFromVideoFile, "video/*");
        startActivity(intent);


    }



}
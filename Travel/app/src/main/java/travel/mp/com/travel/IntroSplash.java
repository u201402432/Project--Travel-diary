package travel.mp.com.travel;

        import android.app.Activity;
        import android.os.Bundle;
        import android.os.Handler;

public class IntroSplash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();       // 3 초후 이미지를 닫아버림
            }
        }, 3000);

//      Handler handler = new Handler() {
//          public void handleMessage(android.os.Message msg) {
//              finish();
//          }
//      };
//      handler.sendEmptyMessageDelayed(0, 3000);
    }
}
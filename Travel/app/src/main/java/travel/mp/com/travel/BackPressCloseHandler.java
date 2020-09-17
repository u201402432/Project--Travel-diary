package travel.mp.com.travel;

/**
 * Created by kims on 2015-06-15.
 */

        import android.app.Activity;
        import android.widget.Toast;

public class BackPressCloseHandler extends Activity{

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();

            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 Main페이지로 돌아갑니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

}




/**
 * Created by kims on 2015-06-15.
 */
/*
import android.app.Activity;
import android.widget.Toast;

public class BackPressCloseHandler extends Activity{

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();

            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 Main페이지로 돌아갑니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

}

*/

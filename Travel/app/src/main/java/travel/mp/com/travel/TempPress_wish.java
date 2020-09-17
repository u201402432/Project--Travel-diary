package travel.mp.com.travel;

/**
 * Created by admin on 2015-06-17.
 */
import android.app.Activity;
import android.widget.Toast;

/**
 * Created by 김가윤 on 2015-06-17.
 */


public class TempPress_wish {
    private Toast toast;
    private Activity activity;


    public void on_temp_wish() {
        //new prefActivity_wish();
        showGuide();
        return;
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'임시\'저장이 됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}

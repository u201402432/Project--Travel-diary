package travel.mp.com.travel;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    ImageView iv;
    public static int index = 1 ;
    PhotoViewAttacher mAttacher ;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Bitmap map ;
    public Canvas canvas ;
    public ImageView view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this,IntroSplash.class));

        view  = (ImageView)findViewById(R.id.map_total) ;

        // 확대 축소소
        mAttacher = new PhotoViewAttacher(view);
        mAttacher.setMinimumScale(1/2);
        mAttacher.setMaximumScale(5) ;

        Bitmap theMap = BitmapFactory.decodeResource(getResources(), R.drawable.map_total) ;
        map = Bitmap.createBitmap(theMap.getWidth(), theMap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(map) ;
        canvas.drawBitmap(theMap,0,0,null) ;
        view.setImageDrawable(new BitmapDrawable(getResources(), map));


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        View.OnClickListener b = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetClick(v);
            }
        } ;
        b.onClick(this.getCurrentFocus()) ;
        b = null ;


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        int commit = fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();

    }

    public void onSectionAttached(int number) {

        Intent intent = null;

        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                intent = new Intent(this , show_list_Activity.class) ;
                startActivity(intent) ;
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                intent = new Intent(this , write_list_Activity.class) ;
                startActivity(intent) ;
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                intent = new Intent(this, view_list.class) ;
                startActivity(intent) ;
                break;
            case 5:
                mTitle = getString(R.string.title_section5) ;
                intent = new Intent(this , Recommend.class) ;
                startActivity(intent) ;
                break ;
            case 6 :
                mTitle = getString(R.string.title_section6) ;
                intent = new Intent(this, setting.class) ;
                startActivity(intent) ;
                break ;
        }
    }

    public void restoreActionBar() {

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void resetClick(View v) {

        Toast toast = Toast.makeText(this,
                "지도 생성중", Toast.LENGTH_SHORT);
        toast.show();


        String regions[] = {"강릉시", "강진군", "거제시", "거창군", "안양군", "과천시", "광명시", "구리시", "군포시", "김포시", "동두천시", "성남시", "안성시", "오산시", "의정부", "인천시", "평택시", "포천군",
                "하남시", "화성시", "서울강서구", "서울구로구", "서울금천구", "서울노원구", "서울동작구", "서울서대문구", "서울성동구", "서울양천구", "서울중랑구", "경기도가평군", "경기도고양시", "경기도광주시", "경기도남양주시",
                "경기도부천시", "경기도안산시", "경기도안양시", "경기도양주시", "경기도양평군",
                "경기도여주시",
                "경기도연천군",
                "경기도용인시",
                "경기도의왕시",
                "경기도이천시",
                "경기도파주시",
                "경기도포천시",
                "경산시",
                "경주시",
                "계룡시",
                "고령군",
                "고성군",
                "고창군",
                "고흥군",
                "곡성군",
                "공주시",
                "광양시",
                "광주광산구",
                "광주남구",
                "광주동구",
                "광주북구",
                "광주서구",
                "괴산군",
                "구례군",
                "구미시",
                "군위군",
                "금산군",
                "김제시",
                "김천시",
                "김해시",
                "나주시",
                "남원시",
                "남해군",
                "논산시",
                "단양군",
                "담양군",
                "당진시",
                "대구남구",
                "대구달서구",
                "대구달성군",
                "대구동구",
                "대구북구",
                "대구서구",
                "대구수성구",
                "대구중구",
                "대전대덕구",
                "대전동구",
                "대전서구",
                "대전유성구",
                "대전중구",
                "동해시",
                "무안군",
                "무주군",
                "문경시",
                "밀양시",
                "보령시",
                "보성군",
                "보은군",
                "봉화군",
                "부산강서구",
                "부산금정구",
                "부산기장군",
                "부산남구",
                "부산동구",
                "부산동래구",
                "부산부산진구",
                "부산북구",
                "부산사상구",
                "부산사하구",
                "부산서구",
                "부산수영구",
                "부산연제구",
                "부산영도구",
                "부산중구",
                "부산해운대구",
                "부안군",
                "부여군",
                "사천시",
                "산청군",
                "삼척시",
                "상주시",
                "서산시",
                "서울강남구",
                "서울강동구",
                "서울강북구",
                "서울관악구",
                "서울광진구",
                "서울동대문구",
                "서울마포구",
                "서울서초구",
                "서울성북구",
                "서울송파구",
                "서울영등포구",
                "서울용산구",
                "서울은평구",
                "서울종로구",
                "서울중랑구",
                "서천군",
                "성주군",
                "속초시",
                "순창군",
                "순천시",
                "아산시",
                "안동시",
                "양구군",
                "양산시",
                "양양군",
                "여수시",
                "영광군",
                "영덕군",
                "영동군",
                "영암군",
                "영양군",
                "영월군",
                "영주시",
                "예산군",
                "예천군",
                "옥천군",
                "완도군",
                "완주군",
                "울릉군",
                "울진군",
                "원주시",
                "음성군",
                "의령군",
                "의성군",
                "익산시",
                "인제군",
                "임실군",
                "장성군",
                "장수군",
                "장흥군",
                "목포시",
                "군산시",
                "전주시",
                "정선군",
                "정읍시",
                "제천시",
                "증평군",
                "진도군",
                "진안군",
                "진주시",
                "진천군",
                "창녕군",
                "창원시",
                "천안시",
                "철원군",
                "청도군",
                "청송군",
                "청양군",
                "청주시",
                "춘천시",
                "충주시",
                "칠곡군",
                "태백시",
                "태안군",
                "통영시",
                "평창군",
                "포항시",
                "하동군",
                "함안군",
                "함양군",
                "함평군",
                "합천군",
                "해남군",
                "홍성군",
                "홍천군",
                "화순군",
                "화천군",
                "횡성군",
                "영천시",
                "울산남구",
                "울산동구",
                "울산북구",
                "울산중구",
                "울산울주군",
                "독도",
                "연기군",
                "신안군",
                "제주도",
                "수원",
                "시흥"};
        int i, j;

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor c = db.get("SELECT region FROM list");
        view  = (ImageView)findViewById(R.id.map_total) ;
        Bitmap theMap = BitmapFactory.decodeResource(getResources(), R.drawable.map_total) ;
        map = Bitmap.createBitmap(theMap.getWidth(), theMap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(map) ;
        canvas.drawBitmap(theMap,0,0,null) ;
        int lid ;
        Paint paint = new Paint() ;
        Bitmap addMap ;
        String address ;
        Cursor where ;
        for (c.moveToFirst(); ; c.moveToNext()) {

            for (i = 0; i < regions.length; i++)
                if (c.getString(0).toString().contains(regions[i]))
                    break;

            if (i != regions.length) {

                where = db.get("SELECT region FROM list where region ='" + regions[i] + "'");
                try {

                    if (i != regions.length) {

                        address = "m" + (i + 1);
                        lid = this.getResources().getIdentifier(address, "drawable", this.getPackageName());

                        view  = (ImageView)findViewById(R.id.map_total) ;
                        addMap = BitmapFactory.decodeResource(getResources(),lid ) ;
                        paint.setAlpha(60) ;
                        canvas.drawBitmap(addMap,0,0, paint) ;

                    }
                } catch (Exception e) {
                    Log.e("Error : ", e.getMessage());
                }

            }

            if (c.isLast())
                break;
        }

        view.setImageDrawable(new BitmapDrawable(getResources(), map));

        db = null ;
        paint = null ;
        addMap = null ;
        c.close() ;


    } // saveBt


} // class

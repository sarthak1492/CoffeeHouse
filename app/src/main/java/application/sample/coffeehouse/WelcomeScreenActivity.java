package application.sample.coffeehouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import Adapters.ImageSliderAdapter;
import me.relex.circleindicator.CircleIndicator;

public class WelcomeScreenActivity extends AppCompatActivity {

    ViewPager viewPagerImageSlider;
    ImageSliderAdapter imageSliderAdapter;
    //ArrayList<ImageSliderItems> arrSlidingImages = new ArrayList<>();
    ArrayList<String> arrSlidingImagesTesting;

    private static int currentPage = 0;
    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#CC000000"));
            window.setFeatureDrawableAlpha(1,1);
        }

        getSupportActionBar().hide();

        viewPagerImageSlider = (ViewPager) findViewById(R.id.viewPagerImageSlider);


        indicator = (CircleIndicator) findViewById(R.id.indicator);

        new GetSlidingImagesTask().execute();
    }

    public void fncGetStartedClick(View view){
        if (isNetworkAvailable()) {
            Intent intent = new Intent(WelcomeScreenActivity.this, MenuScreenActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(this, NoConnectionActivity.class);
            startActivity(intent);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class GetSlidingImagesTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            arrSlidingImagesTesting = new ArrayList<>(Arrays.asList("https://cbmcinc.com/wp-content/uploads/2015/01/grocery-454296875-1030x684.jpg",
                    "https://http2.mlstatic.com/como-armar-pedidos-en-walmart-y-celsur-D_NQ_NP_311221-MLA20724651225_052016-F.jpg",
                    "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BBxAcYJ.img?h=416&w=624&m=6&q=60&u=t&o=f&l=f&x=1386&y=860",
                    "http://www.romerobarriosphotographs.com/wp-content/uploads/galleries/post-2223/Mini_Fruit_Baskets.jpg"));

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            imageSliderAdapter = new ImageSliderAdapter(WelcomeScreenActivity.this, arrSlidingImagesTesting);
            viewPagerImageSlider.setAdapter(imageSliderAdapter);

            indicator.setViewPager(viewPagerImageSlider);

            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == arrSlidingImagesTesting.size()) {
                        currentPage = 0;
                    }
                    viewPagerImageSlider.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);

            // Pager listener over indicator
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });

        }
    }



}

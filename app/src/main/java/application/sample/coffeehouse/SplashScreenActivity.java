package application.sample.coffeehouse;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashScreenActivity extends AppCompatActivity {

    String strImageBg;
    ImageView imgSplashBackgound;
    Animation animation;
    ImageView imgSplashLogo;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private AWSCredentialsProvider credentialsProvider;
    private AWSConfiguration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.i("YourMainActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();

        preferences = getSharedPreferences(getApplicationContext().getString(R.string.sharedPrefName), Context.MODE_PRIVATE);
        editor = preferences.edit();

        imgSplashLogo = (ImageView) findViewById(R.id.imgSplashLogo);


        imgSplashBackgound = (ImageView) findViewById(R.id.imgSplashBackground);
        new GetSplashBackgroundImageTask().execute();


    }

    public class GetSplashBackgroundImageTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            strImageBg = "http://hdwallpapersdl.net/wp-content/uploads/2018/02/Strawberry-Fruits-1080x1920.jpg";
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Picasso.get().load(strImageBg).into(imgSplashBackgound);
            animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.splash_text_animation);
            imgSplashLogo.startAnimation(animation);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isNetworkAvailable()){
                        AlertDialog alertDialog = new AlertDialog.Builder(SplashScreenActivity.this).create();
                        alertDialog.setTitle(getApplicationContext().getString(R.string.connectionErrorString));
                        alertDialog.setMessage(getApplicationContext().getString(R.string.connectionErrorMessage));
                        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(SplashScreenActivity.this, NoConnectionActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        alertDialog.show();
                    }else {

                        if (preferences.getString("FirstUserName", null) == null || preferences.getString("FirstMobileNumber", null) == null){
                            Intent intent = new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent = new Intent(SplashScreenActivity.this, MenuScreenActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                }
            }, 5000);


        }
    }

    @Override
    public void onBackPressed() {

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

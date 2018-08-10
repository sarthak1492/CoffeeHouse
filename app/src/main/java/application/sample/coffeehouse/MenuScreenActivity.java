package application.sample.coffeehouse;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import Adapters.MenuScreenAdapter;
import DataModel.MenuItems;

public class MenuScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    List<MenuItems> menuItems = new ArrayList<>();
    RecyclerView recyclerViewMenu;
    MenuScreenAdapter menuScreenAdapter;

    ArrayList<String> arrCategoryBackgroundImages;

    ArrayList<String> arrCategoryNames;

    ArrayList<String> arrCategoryHeadlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FF807E7E"));
            window.setFeatureDrawableAlpha(1,1);
        }

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu_icon);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        toggle.syncState();

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView lblGreetingText = (TextView) view.findViewById(R.id.lblGreetingText);
        TextView lblSDName = (TextView) view.findViewById(R.id.lblSDName);
        if(timeOfDay >= 0 && timeOfDay < 12){
            lblGreetingText.setText("Good Morning, ");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            lblGreetingText.setText("Good Afternoon, ");
        }else if(timeOfDay >= 16 && timeOfDay < 22){
            lblGreetingText.setText("Good Evening, ");
        }else if(timeOfDay >= 22 && timeOfDay < 24){
            lblGreetingText.setText("Good Night, ");
        }

        new GetMenuItemsTask().execute();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_my_address) {

        } else if (id == R.id.nav_my_subscriptions) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class GetMenuItemsTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            arrCategoryBackgroundImages = new ArrayList<>(Arrays.asList("https://images.pexels.com/photos/2065/restaurant-beans-coffee-morning.jpg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
                    "https://www.hdwallpaperstop.in/wp-content/uploads/2018/03/Golden-Coffee-Beans-Wallpaper-1280x1080.jpg",
                    "http://www.theplaidzebra.com/wp-content/uploads/2016/04/2_restaurant-dedicated-to-stoners.jpg",
                    "http://www.wallpapers4u.org/wp-content/uploads/restaurant_table_interior_modern_style_39288_1920x1080.jpg",
                    "https://assets3.thrillist.com/v1/image/1196274/size/tmg-slideshow_l.jpg"));

            arrCategoryNames = new ArrayList<>(Arrays.asList("Coffee", "Breakfast", "Munchies", "Sandwiches",
                    "Specialty Drinks"));

            arrCategoryHeadlines = new ArrayList<>(Arrays.asList("Freshly brewed coffee",
                    "Perfectly baked and served warm", "Perfectly baked and served warm", "Fresh, healthy and tasty",
                    "Special drinks for every taste"));

            for (int i = 0; i < arrCategoryBackgroundImages.size(); i++){
                MenuItems items = new MenuItems();
                items.setStrMainCategory(arrCategoryNames.get(i));
                items.setStrHeadline(arrCategoryHeadlines.get(i));
                items.setStrImageUrls(arrCategoryBackgroundImages.get(i));

                menuItems.add(items);
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            recyclerViewMenu = (RecyclerView) findViewById(R.id.recyclerViewMenu);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(MenuScreenActivity.this);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewMenu.setLayoutManager(mLayoutManager);
            menuScreenAdapter = new MenuScreenAdapter(MenuScreenActivity.this, menuItems);

            recyclerViewMenu.setAdapter(menuScreenAdapter);

        }
    }
}

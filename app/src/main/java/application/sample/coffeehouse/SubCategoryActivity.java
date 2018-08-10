package application.sample.coffeehouse;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.MenuScreenAdapter;
import Adapters.SubCategoryAdapter;
import DataModel.MenuItems;
import DataModel.SubCategoryItems;

public class SubCategoryActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imgBackground;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    List<SubCategoryItems> subCategoryItems = new ArrayList<>();
    RecyclerView recyclerViewSubCategories;
    SubCategoryAdapter subCategoryAdapter;

    ArrayList<String> arrSubCategoryImageURLs;
    ArrayList<String> arrSubCategoryNames;
    ArrayList<String> arrSubCategoryDescriptions;
    ArrayList<String> arrSubCategoryPrices;
    String strCategoryName;
    String strHeadingImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FFE4B5"));
        }

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        imgBackground = (ImageView) findViewById(R.id.imgBackground);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (preferences.getString("MainCategoryName", null) != null){
            strCategoryName = preferences.getString("MainCategoryName", null);
        }

        new SubCategoryTask().execute();

    }

    public class SubCategoryTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            if (strCategoryName.equals("Coffee")) {

                arrSubCategoryImageURLs = new ArrayList<>(Arrays.asList("http://www.delonghi.com/Global/artOfEspresso/images/espresso-long-big.png",
                        "https://mcdonalds.co.nz/sites/mcdonalds.co.nz/files/hero_pdt_chocolate_frappe.png",
                        "https://a1coffee.net/media/catalog/product/t/o/tofffrappe.png",
                        "https://mcdonalds.co.nz/sites/mcdonalds.co.nz/files/hero_pdt_coffee_frappe.png",
                        "https://www.pngarts.com/files/1/Cappuccino-PNG-Free-Download.png",
                        "https://www.ahorradoras.com/wp-content/uploads/2013/05/cafe1.png",
                        "https://www.teisseire.com/media/1100/_0073_amaretto-macchiato-png.png",
                        "https://mcdonalds.com.au/sites/mcdonalds.com.au/files/Product_thumb_McSpider-Coke-Medium.png"));

                arrSubCategoryNames = new ArrayList<>(Arrays.asList("Espresso", "Choco Frappe", "Caramel Frappe", "Kick Frappe",
                        "Americano", "Café Bombón", "Café au lait", "Caffé Corretto"));

                arrSubCategoryDescriptions = new ArrayList<>(Arrays.asList("Blue Ridge Blend", "Locally Roasted",
                        "Decaf Columbia", "Decaf Columbia", "Caffè Americano", "Coffee percolator", "Vacuum coffee maker", "Locally Roasted"));

                arrSubCategoryPrices = new ArrayList<>(Arrays.asList("245", "110", "226", "159", "108", "220", "226", "300"));
            } else if (strCategoryName.equals("Breakfast")){

                arrSubCategoryImageURLs = new ArrayList<>(Arrays.asList(
                        "http://www.mcdonalds.ie/content/iehome/food/more_food/deserts_treats/double_chocolate_muffin/_jcr_content/genericpagecontent/columncontrol/columncontrol%20column2/everything/image.img.png/1409676699598.png",
                        "http://www.pngmart.com/files/5/Bagels-PNG-Image.png",
                        "http://www.garlospies.com.au/upload/products/lean_beef_3.png",
                        "https://cdn.monpanierdachat.com/538/manipulated/83dc68cf82388af5e721c3459cb24da15d1ce3fd_680X680cropresize.png",
                        "http://pngimg.com/uploads/pancake/pancake_PNG114.png",
                        "http://pluspng.com/img-png/bacon-and-eggs-png-classic-bacon-eggs-700.png",
                        "http://cdn.shopify.com/s/files/1/1465/3370/products/Goji-Agave-Granola-1_grande.png?v=1481916510",
                        "http://www.pngmart.com/files/5/French-Toast-PNG-HD.png"));

                arrSubCategoryNames = new ArrayList<>(Arrays.asList("Chocolate Muffin", "Classic Muffin", "Chicken & Veggie Pie", "Choco Cookies",
                        "Strawberry Pancakes", "Classic Menu", "Granola", "French Toast"));

                arrSubCategoryDescriptions = new ArrayList<>(Arrays.asList("Blue Ridge Blend", "Locally Roasted",
                        "Decaf Columbia", "Decaf Columbia", "Caffè Americano", "Coffee percolator", "Vacuum coffee maker", "Locally Roasted"));

                arrSubCategoryPrices = new ArrayList<>(Arrays.asList("880", "1100", "1123", "875", "408", "430", "549", "750"));

            } else if (strCategoryName.equals("Munchies")){
                arrSubCategoryImageURLs = new ArrayList<>(Arrays.asList(
                        "https://theterraceathobblecreek.com/wp-content/uploads/menu-sweet_potato_fries.png",
                        "http://yogurtmountain.com/wp-content/uploads/hof-detail-very-berry-parfait.png",
                        "http://pluspng.com/img-png/oat-png-download-419.png",
                        "http://bk-emea-prd.s3.amazonaws.com/sites/burgerking.fi/files/Detail_10pc_Chicken_Nuggets.png",
                        "https://carolinapride.com/wp-content/uploads/2015/08/SMOKEHOUSE-1024x1024.png"));

                arrSubCategoryNames = new ArrayList<>(Arrays.asList("Crispy Sweet Potato Fries",
                        "Yogurt", "Oat meal", "Vegetarian Chicken Nuggets",
                        "Wrapped Hot Dog"));

                arrSubCategoryDescriptions = new ArrayList<>(Arrays.asList("Blue Ridge Blend", "Locally Roasted",
                        "Decaf Columbia", "Decaf Columbia", "Caffè Americano"));

                arrSubCategoryPrices = new ArrayList<>(Arrays.asList("545", "490", "776", "670", "734"));
            }

            for (int i = 0; i < arrSubCategoryImageURLs.size(); i++) {
                SubCategoryItems items = new SubCategoryItems();
                items.setStrSubCategoryHeader(arrSubCategoryNames.get(i));
                items.setGetStrSubCategoryDescription(arrSubCategoryDescriptions.get(i));
                items.setGetStrSubCategoryImageURL(arrSubCategoryImageURLs.get(i));
                items.setStrSubCategoryPrice(arrSubCategoryPrices.get(i));

                subCategoryItems.add(items);
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            collapsingToolbarLayout.setTitle(strCategoryName);
            if (strCategoryName.equals("Coffee")){
                strHeadingImageUrl = "https://cdn.allwallpaper.in/wallpapers/1280x720/6174/fresh-cup-of-coffee-1280x720-wallpaper.jpg";
            } else if (strCategoryName.equals("Breakfast")){
                strHeadingImageUrl = "http://cn-larinascente-wordpress.s3-eu-west-1.amazonaws.com/larinascente/avdm/wp-content/uploads/2017/06/16095144/cover13-1280x720.jpg";
            } else if (strCategoryName.equals("Munchies")){
                strHeadingImageUrl = "https://s3.amazonaws.com/media.asp/31254_munchies_greek_12616_bauerleffler_0002f.jpg";
            }

            Picasso.get().load(strHeadingImageUrl).into(imgBackground);

            recyclerViewSubCategories = (RecyclerView) findViewById(R.id.recyclerViewSubCategories);
            recyclerViewSubCategories.setLayoutManager(new GridLayoutManager(SubCategoryActivity.this, 2));
            subCategoryAdapter = new SubCategoryAdapter(subCategoryItems, SubCategoryActivity.this);

            recyclerViewSubCategories.setAdapter(subCategoryAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.remove("MainCategoryName");

    }
}

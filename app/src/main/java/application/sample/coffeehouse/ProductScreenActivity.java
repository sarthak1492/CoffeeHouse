package application.sample.coffeehouse;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductScreenActivity extends AppCompatActivity {

    TextView lblSubCategoryHeading, lblProductName, lblSubCategoryProductPrice, lblProductDescription;
    ImageView imgProductImage;
    Spinner spQuantity, spSize;
    String strSubCategoryHeading, strSubCategoryImage, strSubCategoryPrice;
    RelativeLayout layoutSize, layoutQuantity;
    View view2;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#D2B48C"));
        }
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        lblSubCategoryHeading = (TextView) findViewById(R.id.lblSubCategoryHeading);
        lblProductName = (TextView) findViewById(R.id.lblProductName);
        lblSubCategoryProductPrice = (TextView) findViewById(R.id.lblSubCategoryProductPrice);
        lblProductDescription = (TextView) findViewById(R.id.lblProductDescription);
        imgProductImage = (ImageView) findViewById(R.id.imgProductImage);
        layoutSize = (RelativeLayout) findViewById(R.id.layoutSize);
        layoutQuantity = (RelativeLayout) findViewById(R.id.layoutQuantity);
        view2 = (View) findViewById(R.id.view2);

        if (preferences.getString("MainCategoryName", null).equals("Breakfast") ||
                preferences.getString("MainCategoryName", null).equals("Munchies")){
            layoutSize.setVisibility(View.GONE);
            view2.setVisibility(View.GONE);
            layoutQuantity.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }

        spQuantity = (Spinner) findViewById(R.id.spQuantity);
        spSize = (Spinner) findViewById(R.id.spSize);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            strSubCategoryHeading = bundle.getString("SubCategoryName");
            strSubCategoryImage = bundle.getString("SubCategoryImage");
            strSubCategoryPrice = bundle.getString("SubCategoryPrice");

            lblSubCategoryHeading.setText(strSubCategoryHeading);
            lblProductName.setText(strSubCategoryHeading);
            lblSubCategoryProductPrice.setText("\u20B9 " + strSubCategoryPrice);
            Picasso.get().load(strSubCategoryImage).into(imgProductImage);
        }

        final List<String> arrQuantity = new ArrayList<>();
        arrQuantity.add("Quantity");
        arrQuantity.add("1");
        arrQuantity.add("2");
        arrQuantity.add("3");
        arrQuantity.add("4");
        arrQuantity.add("5");

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrQuantity);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(arrayAdapter);

        spQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final List<String> arrSize = new ArrayList<>();
        arrSize.add("Size");
        arrSize.add("Small");
        arrSize.add("Medium");
        arrSize.add("Large");

        ArrayAdapter sizeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrSize);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSize.setAdapter(sizeAdapter);

        spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (arrSize.get(i).equals("Medium")){
                    int iPrice = Integer.parseInt(strSubCategoryPrice);
                    int iNewPrice = iPrice * 2;
                    String strNewPrice = String.valueOf(iNewPrice);
                    lblSubCategoryProductPrice.setText("\u20B9 " + strNewPrice);
                }

                if (arrSize.get(i).equals("Large")){
                    int iPrice = Integer.parseInt(strSubCategoryPrice);
                    int iNewPrice = iPrice * 3;
                    String strNewPrice = String.valueOf(iNewPrice);
                    lblSubCategoryProductPrice.setText("\u20B9 " + strNewPrice);
                }

                if (arrSize.get(i).equals("Small")){
                    lblSubCategoryProductPrice.setText("\u20B9 " + strSubCategoryPrice);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}

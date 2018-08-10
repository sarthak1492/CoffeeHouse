package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import DataModel.MenuItems;
import application.sample.coffeehouse.MenuScreenActivity;
import application.sample.coffeehouse.R;
import application.sample.coffeehouse.SubCategoryActivity;

public class MenuScreenAdapter extends RecyclerView.Adapter<MenuScreenAdapter.MyViewHolder> {

    List<MenuItems> menuItems;
    Activity activity;
    View itemView;


    public MenuScreenAdapter(Activity activity, List<MenuItems> menuItems) {
        this.menuItems = menuItems;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        MenuItems items = menuItems.get(position);
        holder.lblCategoryName.setText(items.getStrMainCategory());
        holder.lblCategoryHeadline.setText(items.getStrHeadline());
        Picasso.get().load(items.getStrImageUrls()).into(holder.imgCategoryBackground);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(activity, menuItems.get(position).getStrMainCategory(), Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                SharedPreferences.Editor editor = preferences.edit();
                if (menuItems.get(position).getStrMainCategory().equals("Coffee") ||
                        menuItems.get(position).getStrMainCategory().equals("Breakfast")||
                        menuItems.get(position).getStrMainCategory().equals("Munchies")){
                    Intent intent = new Intent(activity.getApplicationContext(), SubCategoryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    editor.putString("MainCategoryName", menuItems.get(position).getStrMainCategory());
                    editor.apply();
                    activity.startActivity(intent);
                }else {
                    Toast.makeText(activity, menuItems.get(position).getStrMainCategory(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView lblCategoryName, lblCategoryHeadline;
        ImageView imgCategoryBackground;

        public MyViewHolder(final View itemView) {
            super(itemView);

            lblCategoryName = (TextView) itemView.findViewById(R.id.lblCategoryName);
            lblCategoryHeadline = (TextView) itemView.findViewById(R.id.lblCategoryHeadline);
            imgCategoryBackground = (ImageView) itemView.findViewById(R.id.imgCategoryBackground);



        }
    }

}

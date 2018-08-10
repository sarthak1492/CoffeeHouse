package Adapters;

import android.app.Activity;
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


import com.squareup.picasso.Picasso;

import java.util.List;
import DataModel.SubCategoryItems;
import application.sample.coffeehouse.ProductScreenActivity;
import application.sample.coffeehouse.R;

/**
 * Created by Sarthak.Sharma on 19-07-2018.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    View itemView;
    List<SubCategoryItems> subCategoryItems;
    Activity activity;

    public SubCategoryAdapter(List<SubCategoryItems> subCategoryItems, Activity activity) {
        this.subCategoryItems = subCategoryItems;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_items, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        final SharedPreferences.Editor editor = preferences.edit();

        SubCategoryItems items = subCategoryItems.get(position);
        if (items.getStrSubCategoryHeader() != null || !items.getStrSubCategoryHeader().isEmpty()) {
            holder.lblSubCategoryName.setText(items.getStrSubCategoryHeader());
        } else {
            holder.lblSubCategoryName.setVisibility(View.GONE);
        }

        if (items.getGetStrSubCategoryDescription() != null || !items.getGetStrSubCategoryDescription().isEmpty()) {
            holder.lblSubCategoryDescription.setText(items.getGetStrSubCategoryDescription());
        } else {
            holder.lblSubCategoryDescription.setVisibility(View.GONE);
        }

        if (items.getStrSubCategoryPrice() != null || !items.getStrSubCategoryPrice().isEmpty()) {
            holder.lblSubCategoryProductPrice.setText("\u20B9 " + items.getStrSubCategoryPrice());
        } else {
            holder.lblSubCategoryProductPrice.setVisibility(View.GONE);
        }

        if (items.getGetStrSubCategoryImageURL() != null || !items.getGetStrSubCategoryImageURL().isEmpty()) {
            Picasso.get().load(items.getGetStrSubCategoryImageURL()).into(holder.imgSubCategoryProduct);
        } else {
            holder.imgSubCategoryProduct.setVisibility(View.GONE);
        }

//        SelectedPosition = preferences.getInt("SelectedPos", -1);
//        if (SelectedPosition == position){
//            holder.imgLike.setColorFilter(Color.rgb(255, 0, 0));
//        }

//        holder.imgLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (SelectedPosition == position){
//                    final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.like_button_bounce);
//                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//                    myAnim.setInterpolator(interpolator);
//                    holder.imgLike.startAnimation(myAnim);
//                    holder.imgLike.setColorFilter(Color.parseColor("#D3D3D3"));
//                    editor.remove("SelectedPos");
//                }else {
//
//                    final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.like_button_bounce);
//                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//                    myAnim.setInterpolator(interpolator);
//                    holder.imgLike.startAnimation(myAnim);
//                    holder.imgLike.setColorFilter(Color.rgb(255, 0, 0));
//                    editor.putInt("SelectedPos", position);
//                    editor.apply();
//                }
//            }
//        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strSubCategoryName = subCategoryItems.get(position).getStrSubCategoryHeader();
                String strSubCategoryImageUrl = subCategoryItems.get(position).getGetStrSubCategoryImageURL();
                String strSubCategoryPrice = subCategoryItems.get(position).getStrSubCategoryPrice();

                Intent intent = new Intent(activity, ProductScreenActivity.class);
                Bundle b = new Bundle();

                b.putString("SubCategoryName", strSubCategoryName);
                b.putString("SubCategoryImage", strSubCategoryImageUrl);
                b.putString("SubCategoryPrice", strSubCategoryPrice);

                intent.putExtras(b);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subCategoryItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lblSubCategoryName, lblSubCategoryDescription, lblSubCategoryProductPrice;
        ImageView imgSubCategoryProduct, imgLike;

        public MyViewHolder(final View itemView) {
            super(itemView);

            lblSubCategoryName = (TextView) itemView.findViewById(R.id.lblSubCategoryName);
            lblSubCategoryDescription = (TextView) itemView.findViewById(R.id.lblSubCategoryDescription);
            imgSubCategoryProduct = (ImageView) itemView.findViewById(R.id.imgSubCategoryProduct);
            lblSubCategoryProductPrice = (TextView) itemView.findViewById(R.id.lblSubCategoryProductPrice);
            //imgLike = (ImageView) itemView.findViewById(R.id.imgLike);
        }
    }
}

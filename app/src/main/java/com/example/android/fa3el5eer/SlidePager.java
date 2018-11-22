package com.example.android.fa3el5eer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SlidePager extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlidePager(Context context){
        this.context = context;
    }

    public int[] slide_image = {
            R.drawable.ic_about_one,
            R.drawable.ic_about_two
    };
    @Override
    public int getCount() {
        return slide_image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_slide,container,false);

        ImageView view1 = view.findViewById(R.id.imageSlide);
        view1.setImageResource(slide_image[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

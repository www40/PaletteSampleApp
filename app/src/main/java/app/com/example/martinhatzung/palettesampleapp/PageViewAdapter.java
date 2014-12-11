package app.com.example.martinhatzung.palettesampleapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by martinhatzung on 11/12/14.
 */
public class PageViewAdapter extends FragmentStatePagerAdapter {

    List<Data> list;
    public PageViewAdapter(FragmentManager fragmentManager, List<Data> list) {
        super(fragmentManager);
        this.list = list;
    }


    // Returns total number of pages
    @Override
    public int getCount() {
        return list.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(list.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
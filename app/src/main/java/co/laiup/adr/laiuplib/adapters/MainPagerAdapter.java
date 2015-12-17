package co.laiup.adr.laiuplib.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import co.laiup.adr.laiuplib.R;

/**
 * Project Laiup Lib
 * Created by Ha on 9/17/2015.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();
    private int[] iconIds = {
            R.drawable.ic_watch_black,
            R.drawable.ic_friend_black,
            R.drawable.ic_notification_black,
            R.drawable.ic_setting_black
    };

    private Context context;
    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addPage(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    public View getTabView(int position) {
        View tabView = LayoutInflater.from(context).inflate(R.layout.custom_main_tab, null);
        ImageView tabIcon = (ImageView) tabView.findViewById(R.id.tab_icon);
        View dot = tabView.findViewById(R.id.notification_dot);
        if(position == 2) {
            dot.setVisibility(View.VISIBLE);
        }
        tabIcon.setImageResource(iconIds[position]);
        return tabView;
    }
}

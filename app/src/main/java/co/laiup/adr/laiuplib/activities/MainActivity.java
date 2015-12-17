package co.laiup.adr.laiuplib.activities;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;
import co.laiup.adr.laiuplib.adapters.MainPagerAdapter;
import co.laiup.adr.laiuplib.extras.Utils;
import co.laiup.adr.laiuplib.fragments.FriendsFragment;
import co.laiup.adr.laiuplib.fragments.NewsFeedFragment;
import co.laiup.adr.laiuplib.fragments.NotificationFragment;
import co.laiup.adr.laiuplib.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private Context context;
    // layout components
    private TabLayout tabs;
    private ViewPager pager;
    private MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // init layout components
        tabs = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);

        // setup tabs and pager
        setupPager();
        tabs.setupWithViewPager(pager); // link tabs to pager
        setupTabs();
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition(), true);
                setTabSelectedState(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    // set alpha for tab selected
    private void setTabSelectedState(int position) {
        for(int i = 0; i < tabs.getTabCount(); i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            if(tab != null && tab.getCustomView() != null) {
                View v = tab.getCustomView();
                ImageView icon = (ImageView) v.findViewById(R.id.tab_icon);
                if(position == i) {
                    icon.setSelected(true);
                    icon.setAlpha(1.0f);
                } else {
                    icon.setSelected(false);
                    icon.setAlpha(0.6f);
                }
            }
        }

    }

    // setup icon for each tab
    private void setupTabs() {
        for (int i = 0; i < tabs.getTabCount(); i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mainPagerAdapter.getTabView(i));
            }
        }
        setTabSelectedState(0);
    }

    // init pager : put fragment into each page
    private void setupPager() {
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), context);
        mainPagerAdapter.addPage(new NewsFeedFragment(), "NEWS");
        mainPagerAdapter.addPage(new FriendsFragment(), "FRIEND");
        mainPagerAdapter.addPage(new NotificationFragment(), "NOTIFICATION");
        mainPagerAdapter.addPage(new SettingFragment(), "SETTING");
        pager.setAdapter(mainPagerAdapter);
    }
}

package com.pranavtharoor.servermanager;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;


public class AdminHome extends AppCompatActivity implements
    ServerTab.OnFragmentInteractionListener,
    RequestTab.OnFragmentInteractionListener,
    AccessTab.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        TabLayout tabLayout = findViewById(R.id.admin_tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Servers"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));
        tabLayout.addTab(tabLayout.newTab().setText("Accesses"));
        for(int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView custom_tab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_layout_text_view, null);
            custom_tab.setTextColor(getResources().getColor(R.color.colorText));
            custom_tab.setText(tabLayout.getTabAt(i).getText());
            tabLayout.getTabAt(i).setCustomView(custom_tab);
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.admin_view_pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

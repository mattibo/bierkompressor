package com.broeckel.bierkompressor;

import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private String[] menuItems;
    private ListView menuDrawerList;
    private CharSequence menuTitle;
    private CharSequence fragmentTitle;
    private DrawerLayout menuDrawerLayout;
    private ActionBarDrawerToggle menuDrawerToogle;
    private MenuInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuTitle = fragmentTitle = getTitle();

        menuItems = getResources().getStringArray(R.array.menu_array);
        menuDrawerList = (ListView) findViewById(R.id.left_drawer);

        menuDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, menuItems));

        menuDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        menuDrawerToogle = new ActionBarDrawerToggle(this, menuDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(menuTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(fragmentTitle);
                invalidateOptionsMenu();
            }
        };

        menuDrawerLayout.setDrawerListener(menuDrawerToogle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = menuDrawerLayout.isDrawerOpen(menuDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        menuDrawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        menuDrawerToogle.onConfigurationChanged(newConfig);
    }
}

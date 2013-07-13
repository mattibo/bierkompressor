package com.broeckel.bierkompressor;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.broeckel.bierkompressor.fragment.RandomFragment;
import com.broeckel.bierkompressor.fragment.StatusFragment;

public class MainActivity extends Activity {

    private String[] menuItems;
    private ListView menuDrawerList;
    private CharSequence menuTitle;
    private CharSequence fragmentTitle;
    private DrawerLayout menuDrawerLayout;
    private ActionBarDrawerToggle menuDrawerToogle;
    private MenuInflater inflater;
    private Fragment[] fragmentList;


    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuTitle = fragmentTitle = getTitle();

        fragmentList = new Fragment[7];
        fragmentList[0] = new RandomFragment();
        fragmentList[3] = new StatusFragment();

        menuItems = getResources().getStringArray(R.array.menu_array);
        menuDrawerList = (ListView) findViewById(R.id.left_drawer);
        menuDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        menuDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, menuItems));


        menuDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        menuDrawerToogle = new ActionBarDrawerToggle(this, menuDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(fragmentTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(menuTitle);
                invalidateOptionsMenu();
            }
        };

        menuDrawerLayout.setDrawerListener(menuDrawerToogle);

        selectFragment(0);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectFragment(position);
            Log.d(TAG, String.valueOf(position));
        }
    }

    private void selectFragment(int position) {

        Fragment fragment = fragmentList[position];

        FragmentManager fragmentManager = getFragmentManager();

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            setTitle(menuItems[position]);
        } else {
            String failToOpenFragment = getResources().getString(R.string.menu_not_available);

            Toast.makeText(this, String.format(failToOpenFragment, menuItems[position]), Toast.LENGTH_LONG).show();
        }

        menuDrawerList.setItemChecked(position, true);
        menuDrawerLayout.closeDrawer(menuDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        fragmentTitle = title;
        getActionBar().setTitle(fragmentTitle);
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

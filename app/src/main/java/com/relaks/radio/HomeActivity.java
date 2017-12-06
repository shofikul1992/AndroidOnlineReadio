package com.relaks.radio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import co.mobiwise.library.radio.RadioListener;
import co.mobiwise.library.radio.RadioManager;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RadioListener {

    private RadioManager mRadioManager;
    private final String[] RADIO_URL = {"http://rockfm.rockfm.com.tr:9450"};
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mContext=this;
        mRadioManager = RadioManager.with(getApplicationContext());
        mRadioManager.registerListener(this);
        mRadioManager.setLogging(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        InitUi();
    }

    public void InitUi() {

        findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mRadioManager.isPlaying())
                    mRadioManager.startRadio(RADIO_URL[0]);
                else
                    mRadioManager.stopRadio();
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /* Receive Broadcast Messages from RadioService */
    private class RadioUpdateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }


    public void SelectedMenu(int id) {
        FragmentManager fm = HomeActivity.this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        switch (id) {
            case 999:

//                fragment = new Radiofragment();
                break;
            case 100:

                break;

            default:
                break;
        }
        if (fragment != null) {
            ft.replace(R.id.content, fragment);
            ft.commit();

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mRadioManager.connect();
    }

    @Override
    public void onRadioLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                Toast.makeText(mContext,"RADIO STATE : LOADING...",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRadioConnected() {

    }

    @Override
    public void onRadioStarted() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                Toast.makeText(mContext,"RADIO STATE : PLAYING...",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onRadioStopped() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here
                Toast.makeText(mContext,"RADIO STATE : STOPPED.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMetaDataReceived(String s, String s1) {
        //TODO Check metadata values. Singer name, song name or whatever you have.
    }

    @Override
    public void onError() {

    }

}

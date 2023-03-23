package com.example.monpotager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

/**
 * Function used to manage Main Activity of the Application
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Toolbar displayed on top of the screen
     */
    private Toolbar toolbar;
    /**
     * DrawerLayout used to choose a fragment to display
     */
    private DrawerLayout drawerLayout;
    /**
     * NavigationView to navigate between fragments
     */
    private NavigationView navigationView;

    /**
     * Fragment displaying Home View
     */
    private Fragment fragmentHome;
    /**
     * Fragment displaying View to add an Action
     */
    private Fragment fragmentAdd;

    //Id of the two Fragments
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_ADD = 1;

    /**
     * Function launched when the MainActivity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        this.showFirstFragment();
    }

    /**
     * Function to configure the toolbar
     */
    private void configureToolBar () {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Function to configure the drawer
     */
    private void configureDrawerLayout () {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Function to configure NavigationView
     */
    private void configureNavigationView () {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Function to close the DrawerLayout
     */
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Function to display a Fragment when selected in the drawer
     * @param item The selected item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.activity_main_drawer_home:
                this.showFragment(FRAGMENT_HOME);
                break;
            case R.id.activity_main_drawer_add:
                this.showFragment(FRAGMENT_ADD);

                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    /**
     * Function to display HomeFragment by default when the app is opened
     */
    private void showFirstFragment(){
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null){
            this.showFragment(FRAGMENT_HOME);
            //Mark as selected the menu item corresponding to HomeFragment
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    /**
     * Function to show a fragment
     * @param fragmentIdentifier the Id of the fragment
     */
    private void showFragment(int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case FRAGMENT_HOME:
                this.showHomeFragment();
                break;
            case FRAGMENT_ADD:
                this.showAddFragment();
                break;
            default:
                break;
        }
    }

    /**
     * Function to create and show HomeFragment
     */
    private void showHomeFragment() {
        if (this.fragmentHome == null) this.fragmentHome = HomeFragment.newInstance();
        this.startTransactionFragment(this.fragmentHome);
    }

    /**
     * Function to create and show AddFragment
     */
    private void showAddFragment() {
        if (this.fragmentAdd == null) this.fragmentAdd = AddFragment.newInstance();
        this.startTransactionFragment(this.fragmentAdd);
    }

    /**
     * Function to replace and show a fragment inside the MainActivity Frame Layout
     */
    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }

}

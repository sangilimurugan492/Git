package com.example.sangilimurugn.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle abDrawerToggle;
        private DrawerLayout dlNavigationView;
        private View drawerLayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);

            Toolbar toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(false);
                actionBar.setTitle("Home");
            }
            dlNavigationView = (DrawerLayout) findViewById(R.id.dl_navigation_view);
            drawerLayout = findViewById(R.id.rlay_footer);

            abDrawerToggle = new ActionBarDrawerToggle(this, dlNavigationView, 0, 0) {

                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }
            };
            dlNavigationView.addDrawerListener(abDrawerToggle);
            HomePageFragment homePageFragment = new HomePageFragment();
            switchFragment(homePageFragment, "homePage", true);
            getSupportFragmentManager().addOnBackStackChangedListener(new OnBackStackChange());

        }

    private class OnBackStackChange implements FragmentManager.OnBackStackChangedListener {

        @Override
        public void onBackStackChanged() {
            int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
            if (backStackCount > 1) {
                abDrawerToggle.setDrawerIndicatorEnabled(true);
            } else {
                abDrawerToggle.setDrawerIndicatorEnabled(true);
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        abDrawerToggle.syncState();
    }

    public void switchFragment(Fragment fragment, String tag, boolean addToBackStack) {
        Fragment lastFragment = getSupportFragmentManager().findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (lastFragment == null) {
            updateIsActionBarTitleEnabled(true);
            hideLastFragment(fragmentTransaction);
            if (!addToBackStack) {
                fragmentTransaction.add(R.id.fllay_container, fragment, tag);
            } else {
                fragmentTransaction.add(R.id.fllay_container, fragment, tag).addToBackStack(tag);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void updateIsActionBarTitleEnabled(boolean isTitleEnabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(isTitleEnabled);
        }
    }

    public void hideLastFragment(FragmentTransaction fragmentTransaction) {
        final int backStackCount = getSupportFragmentManager().getBackStackEntryCount() - 1;
        if (backStackCount >= 0) {
            String fragmentName = getSupportFragmentManager().getBackStackEntryAt(backStackCount).getName();
            Fragment lastFragment = getSupportFragmentManager().findFragmentByTag(fragmentName);
            fragmentTransaction.hide(lastFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (abDrawerToggle.isDrawerIndicatorEnabled()) {
            return abDrawerToggle.onOptionsItemSelected(item);
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (dlNavigationView.isDrawerOpen(drawerLayout)) {
            dlNavigationView.closeDrawers();
        } else {
            int backStackCount = getSupportFragmentManager().getBackStackEntryCount();

            if (backStackCount > 1) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {
                moveTaskToBack(true);
            }
        }
    }

    }

package com.bengui.volleytest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.MainMenuNavigation{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Loads the Main Menu Fragment
        //we should not add this fragment to the back stack because it's the top fragment in the Activity.
        MainMenuFragment userFragment = new MainMenuFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, userFragment)
                .commit();
    }


    @Override
    public void navigateToUserData() {
        UserFragment userFragment = new UserFragment();
        navigateTo(userFragment);
    }

    @Override
    public void navigateToUserList() {
        UserListFragment userListFragment = new UserListFragment();
        navigateTo(userListFragment);
    }

    @Override
    public void navigateToImageScreen() {
        //TODO create the image screen fragment
    }

    /**
     * Navigates to the given fragment.
     * @param fragment
     */
    private void navigateTo(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

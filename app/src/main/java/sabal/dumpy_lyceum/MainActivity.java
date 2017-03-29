package sabal.dumpy_lyceum;

import android.content.Intent;
import android.net.Uri;
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
import android.view.MenuItem;

import sabal.dumpy_lyceum.Fragments.NewsFragment;
import sabal.dumpy_lyceum.Fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        initializeNavDriver(toolbar);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            NewsFragment fragment = new NewsFragment();
            transaction.add(R.id.main_container, fragment);
            transaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_news:
                fragmentClass = NewsFragment.class;
                break;
            case R.id.nav_weather:
                fragmentClass = WeatherFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = null;
                break;
            case R.id.href_cite:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL.HOST));
                startActivity(browserIntent);
                break;
            default:
                fragmentClass = NewsFragment.class;
                break;
        }

        setTitle(menuItem.getTitle());
        mDrawer.closeDrawer(GravityCompat.START);
        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mDrawer.closeDrawers();
        return true;
    }

    private void initializeNavDriver(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
    }

}

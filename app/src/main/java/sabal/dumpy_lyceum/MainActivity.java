package sabal.dumpy_lyceum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private List<New> news;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        initializeNavDriver(toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            NewsFragment fragment = new NewsFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (menuItem.getItemId()) {
            case R.id.nav_news:
                fragmentClass = NewsFragment.class;
                drawer.closeDrawer(GravityCompat.START);
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                //Toast.makeText(this, "news", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_weather:
                fragmentClass = WeatherFragment.class;
                drawer.closeDrawer(GravityCompat.START);
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                //Toast.makeText(this, "weather", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                fragmentClass = null;
                drawer.closeDrawer(GravityCompat.START);

                //Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.href_cite:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://lyceum.nstu.ru"));
                startActivity(browserIntent);
                break;
            default:
                fragmentClass = NewsFragment.class;
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                break;
        }
        try {
            //fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.sample_content_fragment, (android.support.v4.app.Fragment) fragmentClass.newInstance()).commit();
            //Toast.makeText(this, "replaced", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment


        // Highlight the selected item has been done by NavigationView

        // Close the navigation drawer
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
    }
}

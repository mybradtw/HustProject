package tw.brad.hustproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProductFragment productFragment;
    private WorkItemFragment workItemFragment;
    private Fragment[] fragments;
    private String[] titles = {"產品管理", "工作紀錄"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 新增項目
                int nowPage = viewPager.getCurrentItem();
                if (nowPage == 0){
                    Snackbar.make(view,
                            "新增產品項目",
                            Snackbar.LENGTH_LONG)
                            .setAction("新增", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.v("brad", "action0");
                                }
                            }).show();
                }else if (nowPage == 1){
                    Snackbar.make(view,
                            "新增工作紀錄",
                            Snackbar.LENGTH_LONG)
                            .setAction("新增", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.v("brad", "action1");
                                }
                            }).show();
                }


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    private void initView(){
        productFragment = new ProductFragment();
        workItemFragment = new WorkItemFragment();
        fragments = new Fragment[]{productFragment, workItemFragment};

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerListener(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);

                    }
                });

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private class MyPagerListener extends FragmentStatePagerAdapter {

        public MyPagerListener(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    @Override
    public void onBackPressed() {
        Log.v("brad", "Back");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 當按下Back, 會先判斷是否經開啟 Drawer, 若是, 則關閉 Drawer
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            Log.v("brad", "DrawerOpen");
        } else {
            super.onBackPressed();
            Log.v("brad", "DrawerClose");
        }
    }

    @Override
    public void finish() {
        super.finish();
        Log.v("brad", "finish");
    }

    // 設定 Menu 的版面
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // 按下 Menu 的 Option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 按下側邊 Option
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

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

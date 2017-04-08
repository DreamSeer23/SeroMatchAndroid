package seromatch.seromatchtest;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bottom Tabs
        TabLayout tabLayoutBottom = (TabLayout) findViewById(R.id.bottom_tab_layout);
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_community));
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_search_friends));
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_action_name));
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_account));
        tabLayoutBottom.setTabGravity(TabLayout.GRAVITY_FILL);;
        final ViewPager viewPagerBottom = (ViewPager) findViewById(R.id.bottom_pager);
        //Need to load all of the tabs correctly it is number of tabs+1
        viewPagerBottom.setOffscreenPageLimit(5);
        final BottomTabAdapter adapterBottom = new BottomTabAdapter
                (getSupportFragmentManager(), tabLayoutBottom.getTabCount());
        viewPagerBottom.setAdapter(adapterBottom);
        viewPagerBottom.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutBottom));
        viewPagerBottom.setCurrentItem(0);
        tabLayoutBottom.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabBottom)
            {
                viewPagerBottom.setCurrentItem(tabBottom.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/6/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Profile_Match_Settings_Tab extends Fragment
{
    private boolean activityStartup = true;
    android.widget.SearchView sV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v=inflater.inflate(R.layout.search_friends_tab, container, false);
        sV= (android.widget.SearchView) v.findViewById(R.id.search_bar);
        System.out.println(activityStartup);
        sV.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    if (activityStartup)
                    {
                        sV.clearFocus();
                        activityStartup = false;
                        // TODO: 4/10/2017 Send a bundle with 2 so it knows what tab it came from. Since right now it goes auto back to 1
                        Intent change = new Intent(getContext(), Search.class);
                        startActivity(change);
                    }
                    else
                    {
                        sV.clearFocus();
                        activityStartup = true;
                    }

                }
            }
        });
        //Search
        //Top Tabs
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("My Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Match"));
        tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        //Need to load all of the tabs correctly it is number of tabs+1
        viewPager.setOffscreenPageLimit(4);
        final TabAdapter adapter = new TabAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                System.out.println(tab.getText()+" "+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }
    public void searchBar(View v, boolean hasFocus)
    {
        if (hasFocus)
        {
            if (activityStartup)
            {
                sV.clearFocus();
                activityStartup = false;
            }
            else
            {
                Intent change = new Intent(getContext(), Search.class);
                startActivity(change);
            }

        }
    }
}

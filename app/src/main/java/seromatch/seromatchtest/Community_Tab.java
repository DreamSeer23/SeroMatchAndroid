package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/6/2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Community_Tab extends Fragment
{
    private boolean activityStartup;
    android.widget.SearchView sV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.community_tab, container, false);
        //Search
        sV= (android.widget.SearchView) v.findViewById(R.id.search_bar_community);
        activityStartup = false;
        sV.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    Log.d("Startup",String.valueOf(activityStartup)+"0");
                    if (activityStartup)
                    {
                        sV.clearFocus();
                        activityStartup = false;
                        // TODO: 4/10/2017 Send a bundle with 1 so it knows what tab it came from. Since right now it goes auto back to 1
                        Intent change = new Intent(getContext(), Search.class);
                        change.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        change.putExtra("Tab Number",0  );
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
        //Top Tabs
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout_community);
        tabLayout.addTab(tabLayout.newTab().setText("Feed"));
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager_community);
        //Need to load all of the tabs correctly it is number of tabs+1
        viewPager.setOffscreenPageLimit(4);
        final CommunityTabAdapter adapter = new CommunityTabAdapter
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
    @Override
    public void onResume()
    {
        super.onResume();
        activityStartup=false;
    }
}

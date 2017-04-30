package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/6/2017.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Messages_Tab extends Fragment
{
    //Main Messages Code here @ToDo
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.message_tab, container, false);
        //Top Tabs
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout_messages);
        tabLayout.addTab(tabLayout.newTab().setText("Inbox"));
        tabLayout.addTab(tabLayout.newTab().setText("Drafts"));
        tabLayout.addTab(tabLayout.newTab().setText("Sent"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager_messages);
        //Need to load all of the tabs correctly it is number of tabs+1
        viewPager.setOffscreenPageLimit(4);
        final Message_Tab_Adapter adapter2 = new Message_Tab_Adapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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
}
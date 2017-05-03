package seromatch.seromatchtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Map;

/**
 * Created by jason_000 on 4/6/2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter
{

        private int NumOfTabs;
        private Map<Integer,Fragment> mPageReferenceMap;
        public TabAdapter(FragmentManager fm, int NumTabs)
        {
            super(fm);
            NumOfTabs = NumTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                     Profile_Tab ptab = new Profile_Tab ();
                     return ptab;
                case 1:
                    SwipeView mtab = new SwipeView();
                    return mtab;
                case 2:
                    Settings_Tab tab3 = new Settings_Tab();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount()
        {
            return NumOfTabs;
        }
    }


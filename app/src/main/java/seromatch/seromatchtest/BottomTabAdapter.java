package seromatch.seromatchtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * Created by jason_000 on 4/6/2017.
 */

public class BottomTabAdapter extends FragmentStatePagerAdapter
{

    private int NumOfTabs;

    public BottomTabAdapter(FragmentManager fm, int NumTabs)
    {
        super(fm);
        NumOfTabs = NumTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Community_Tab tab4= new Community_Tab();
                return tab4;
            case 1:
                Profile_Match_Settings_Tab tab5 = new Profile_Match_Settings_Tab();
                return tab5;
            case 2:
                Messages tab6 = new Messages();
                return tab6;
            case 3:
                Accounts tab7 = new Accounts();
                return tab7;
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


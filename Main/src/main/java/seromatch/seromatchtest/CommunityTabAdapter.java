package seromatch.seromatchtest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * Created by jason_000 on 4/6/2017.
 */

public class CommunityTabAdapter extends FragmentStatePagerAdapter
{

    private int NumOfTabs;

    public CommunityTabAdapter(FragmentManager fm, int NumTabs)
    {
        super(fm);
        NumOfTabs = NumTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Feed_Tab ();
            case 1:
                return new Groups_Tab();
            case 2:
                return new Events_Tab();
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


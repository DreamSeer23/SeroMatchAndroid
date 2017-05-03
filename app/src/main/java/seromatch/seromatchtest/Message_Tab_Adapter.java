package seromatch.seromatchtest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * Created by jason_000 on 4/6/2017.
 */

public class Message_Tab_Adapter extends FragmentStatePagerAdapter
{

    private int NumOfTabs;

    public Message_Tab_Adapter(FragmentManager fm, int NumTabs)
    {
        super(fm);
        NumOfTabs = NumTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Inbox_Tab messagetab = new Inbox_Tab ();
                return messagetab;
            case 1:
                Draft_Tab dtab = new Draft_Tab();
                return dtab;
            case 2:
                Sent_Tab tab3 = new Sent_Tab();
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


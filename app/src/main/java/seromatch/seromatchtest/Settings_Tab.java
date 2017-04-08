package seromatch.seromatchtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Settings_Tab extends Fragment
{
    //Main Code for Settings here @ToDO
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.settings_tab, container, false);

        return v;
    }
}



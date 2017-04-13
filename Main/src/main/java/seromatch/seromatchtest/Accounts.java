package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/6/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Accounts extends Fragment
{
    //Main Account Code here @ToDo
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.account_tab, container, false);
        return v;
    }
}
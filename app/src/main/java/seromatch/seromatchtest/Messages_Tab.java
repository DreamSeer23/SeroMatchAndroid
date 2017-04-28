package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/6/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        /*ListView lv = (ListView) v.findViewById(R.id.message_list);
        String [] fromList= new String[3];
        String [] meatList= new String[3];
        final ArrayAdapter<String> adapter = new MySimpleArrayAdapter<>(v.getContext(),android.R.layout.simple_list_item_1,fromList,meatList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> myAdapter, View myView, int pos, long mylng)
            {

            }
        }
        );*/
        return v;

    }
}
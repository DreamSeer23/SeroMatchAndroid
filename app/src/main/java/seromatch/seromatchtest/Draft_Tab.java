package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/6/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Draft_Tab extends Fragment
{
    //Main Code for Match here @ToDO
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.inbox_tab, container, false);

        String [] drafts;
        //Change to loadDrafts
        List<Message> messages =Utils.loadDrafts(v.getContext());
        ListView lv2 = (ListView) v.findViewById(R.id.messagelist);
        if(messages.size()==0)
        {
            drafts=new String[2];
            drafts[0]="";
            drafts[1]="No Drafts";
            lv2.setDividerHeight(0);
        }
        else
        {
            drafts = new String[messages.size()];
            int i = 0;
            for (Message m : messages)
            {
                drafts[i] = m.getRecipient();
                i++;
            }
            lv2.setDividerHeight(1);
        }
        final ArrayAdapter<String> adapter = new MySimpleArrayAdapter<>(v.getContext(), R.layout.message, messages, drafts);
        lv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
            //Add On click here

        return v;
    }
}
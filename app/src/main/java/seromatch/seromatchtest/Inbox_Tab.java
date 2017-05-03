package seromatch.seromatchtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by jason_000 on 4/20/2017.
 */

public class Inbox_Tab extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.inbox_tab, container, false);

        String [] inbox;
        List<Message> messages =Utils.loadMessages(v.getContext());
        ListView lv2 = (ListView) v.findViewById(R.id.messagelist);
        if(messages.size()==0)
        {
            inbox=new String[2];
            inbox[0]="";
            inbox[1]="Inbox is Empty";
            lv2.setDividerHeight(0);
        }
        else
        {
            inbox = new String[messages.size()];
            int i = 0;
            for (Message m : messages)
            {
                inbox[i] = m.getRecipient();
                i++;
            }
            lv2.setDividerHeight(1);
        }
        final ArrayAdapter<String> adapter = new MySimpleArrayAdapter<>(v.getContext(), R.layout.message, messages,inbox);
        lv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //Add On click here
        return v;
    }
}
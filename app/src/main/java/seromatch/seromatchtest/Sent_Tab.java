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

public class Sent_Tab extends Fragment
{
    //Main Code for Match here @ToDO
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.inbox_tab, container, false);

        String [] sent;
        //Change to loadSent
        List<Message> messages =Utils.loadSent(v.getContext());
        ListView lv2 = (ListView) v.findViewById(R.id.messagelist);
        if(messages.size()==0)
        {
            sent=new String[2];
            sent[0]="";
            sent[1]="No Sent Mail";
            lv2.setDividerHeight(0);
        }
        else
        {
            sent = new String[messages.size()];
            int i = 0;
            for (Message m : messages)
            {
                sent[i] = m.getRecipient();
                i++;
            }
            lv2.setDividerHeight(1);
        }
        final ArrayAdapter<String> adapter = new MySimpleArrayAdapter<>(v.getContext(), R.layout.message, messages,sent);
        lv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //Add On click here
        return v;
    }
}
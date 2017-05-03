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

        String [] send;
        //Change to loadDrafts
        List<Message> messages =Utils.loadMessages(v.getContext());
        send=new String[messages.size()];
        int i=0;
        ListView lv2 = (ListView) v.findViewById(R.id.messagelist);
        for(Message m:messages)
        {
            send[i]=m.getSender();
            i++;
        }
        final ArrayAdapter<String> adapter = new MySimpleArrayAdapter<>(v.getContext(), R.layout.message, messages,send);
        lv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //Add On click here
        return v;
    }
}
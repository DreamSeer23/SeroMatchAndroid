package seromatch.seromatchtest;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by jason_000 on 4/20/2017.
 */

public class Messages extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.messages, container, false);
        String [] send;
        List<Message> messages =Utils.loadMessages(v.getContext());
        send=new String[messages.size()];
        int i=0;
        ListView lv2 = (ListView) v.findViewById(R.id.messagelist);
        for(Message m:messages)
        {
            send[i]="From: "+m.getSender();
            Log.d("Send", m.getSender());
            i++;
        }
        final ArrayAdapter<String> adapter = new MySimpleArrayAdapter<>(v.getContext(), android.R.layout.simple_list_item_1, messages,send);
        lv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;
    }
}
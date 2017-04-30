package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/20/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MySimpleArrayAdapter<S> extends ArrayAdapter<String> {
    private final Context context;
    private final List<Message> messsageList;
    private String [] send;

    //Sets up the adapter
    public MySimpleArrayAdapter(Context context, int test, List<Message> messages,String [] sender)
    {
        super(context, test,sender);
        this.context = context;
        messsageList = messages;
        send=sender;
    }
    /**
     * Puts the names in the center
     * @param position - where it's located
     * @param convertView - to convert the view
     * @param parent - the parent of the view
     * @return view - returns the value view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView myText = (TextView) view;
        String sub=messsageList.get(position).getSubject();
        String body=messsageList.get(position).getBody();
        myText.setTextColor(Color.BLACK);
        myText.append("\nSubject: "+sub+"\nBody: \n"+body);
        return view;
    }

}


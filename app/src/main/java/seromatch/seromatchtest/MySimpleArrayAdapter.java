package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/20/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
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
        if(messsageList.size()==0)
        {
            myText.setGravity(Gravity.CENTER_HORIZONTAL);
            myText.setTextColor(Color.BLACK);
            myText.setTextSize(20);
        }
        else
        {
            SpannableString message = new SpannableString(myText.getText().toString() + "\n" + messsageList.get(position).getSubject() + "\n\n" + messsageList.get(position).getBody());
            //End number of the Sender Name
            int send = myText.getText().toString().length() + 1;
            //End number of Subject
            int sub = send + messsageList.get(position).getSubject().length();
            //This sets the UI of the message Txt
            message.setSpan(new AbsoluteSizeSpan(100), 0, myText.getText().toString().length(), 0);
            message.setSpan(new StyleSpan(Typeface.BOLD), 0, myText.getText().toString().length(), 0);
            message.setSpan(new AbsoluteSizeSpan(70), send, sub, 0);
            message.setSpan(new AbsoluteSizeSpan(50), sub + 1, message.length(), 0);
            myText.setText(message);
            myText.setTextColor(Color.BLACK);
        }
        return view;
    }

}


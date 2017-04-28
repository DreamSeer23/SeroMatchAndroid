package seromatch.seromatchtest;

/**
 * Created by jason_000 on 4/20/2017.
 */

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class MySimpleArrayAdapter<S> extends ArrayAdapter<String> {
    private final Context context;
    private final String[] from;
    //Sets up the adapter
    public MySimpleArrayAdapter(Context context,int test, String[] from,String [] meat)
    {
        super(context, test, from);
        this.context = context;
        this.from = from;
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
        int i = 0;
        TextView myText = (TextView) view;
        myText.setGravity(Gravity.CENTER);
        return view;
    }

}


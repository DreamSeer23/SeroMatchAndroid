package seromatch.seromatchtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;


public class Settings_Tab extends Fragment implements OnValueChangeListener, AdapterView.OnItemSelectedListener
{
    ActualNumberPicker mPicker;
    private InterfaceDataCommunicator interfaceDataCommunicator;
    ActualNumberPicker mPicker2;
    private int maxDis;
    int minAge;
    int maxAge;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.settings_tab, container, false);
        mPicker = (ActualNumberPicker) v.findViewById(R.id.agePickerMin);
        mPicker.setListener(this);
        mPicker.setMaxValue(50);
        mPicker2= (ActualNumberPicker) v.findViewById(R.id.agePickerMax);
        mPicker2.setListener(this);
        mPicker2.setMinValue(51);
        Spinner distance=(Spinner) v.findViewById(R.id.Radiusdropdown);
        distance.setOnItemSelectedListener(this);
        minAge=18;
        maxAge=100;
        maxDis=20;
        return v;
    }
    public interface InterfaceDataCommunicator
    {
        void updateData(Bundle data);
    }
    @Override
    public void onValueChanged(int oldValue, int newValue)
    {
        minAge = mPicker.getValue();
        maxAge = mPicker2.getValue();
        if((!(maxAge <=mPicker.getMinValue()))&&mPicker2.getValue()!=mPicker.getMaxValue())
            mPicker.setMaxValue(maxAge);
        else if((!(minAge >=mPicker2.getMaxValue()))&&mPicker.getValue()!=mPicker2.getMinValue())
            mPicker2.setMinValue(minAge);
        //Rebuilds the matches
        Bundle b=new Bundle(4);
        b.putBoolean("restart",true);
        b.putInt("Min", minAge);
        b.putInt("Max", maxAge);
        b.putInt("MaxDis", maxDis);
        interfaceDataCommunicator.updateData(b);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        maxDis=Integer.parseInt(parent.getItemAtPosition(pos).toString().split(" ")[0]);
        Bundle b=new Bundle(4);
        b.putBoolean("restart",true);
        b.putInt("Min", minAge);
        b.putInt("Max", maxAge);
        b.putInt("MaxDis", maxDis);
        interfaceDataCommunicator.updateData(b);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    //Communication
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof InterfaceDataCommunicator) {
            interfaceDataCommunicator = (InterfaceDataCommunicator) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }
    @Override
    public void onDetach() {
        interfaceDataCommunicator = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }
}



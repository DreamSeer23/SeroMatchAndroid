package seromatch.seromatchtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;


public class Settings_Tab extends Fragment implements OnValueChangeListener
{
    ActualNumberPicker mPicker;
    private InterfaceDataCommunicator interfaceDataCommunicator;
    ActualNumberPicker mPicker2;
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

        return v;
    }
    public interface InterfaceDataCommunicator
    {
        void updateData(Bundle data);
    }
    @Override
    public void onValueChanged(int oldValue, int newValue)
    {
        int minAge = mPicker.getValue();
        int maxAge = mPicker2.getValue();
        if((!(maxAge <=mPicker.getMinValue()))&&mPicker2.getValue()!=mPicker.getMaxValue())
            mPicker.setMaxValue(maxAge);
        else if((!(minAge >=mPicker2.getMaxValue()))&&mPicker.getValue()!=mPicker2.getMinValue())
            mPicker2.setMinValue(minAge);
        //Rebuilds the matches
        Bundle b=new Bundle(3);
        b.putBoolean("restart",true);
        b.putInt("Min", minAge);
        b.putInt("Max", maxAge);
        interfaceDataCommunicator.updateData(b);
    }
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



package seromatch.seromatchtest;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

public class SwipeView extends Fragment implements FragmentCommunicator
{

    Context mContext;
    View view;
    SwipePlaceHolderView sv;
    protected int numOfMatches;
    private int minAge;
    private int maxAge;
   // Button b;
    private GestureDetectorCompat mDetector;
    private int range;
    private boolean reset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.swipetab, container, false);
        view=v;
        mContext = v.getContext();
        mDetector = new GestureDetectorCompat(mContext,new MyGestureListener());
        numOfMatches=0;
        //These would be gotten from the database
        minAge=18;
        maxAge=100;
        range=3;
        setupSV();
        v.setOnTouchListener(mOnListTouchListener);
        setMatches(false);
        return v;
    }

    public void setMatches(boolean restart)
    {
        if (restart)
        {
            removeMatches();
            Log.d("Test", "Removing: C2");
        }
        else
        {
            for (Profile profile : Utils.loadProfiles(mContext))
            {
                Log.d("Test", "Number: C2 " + numOfMatches+" "+profile.getMonths());
                if ((profile.getAge() >= minAge && profile.getAge() <= maxAge)&&profile.getMonths()<=range) {
                    numOfMatches++;
                    Log.d("Test", "Number: C " + numOfMatches+" "+profile.getMonths());
                    sv.addView(new MatchCard(mContext, profile, sv));
                }
            }
        }
    }

    private void removeMatches()
    {
        if(numOfMatches>0)
        {
            //Will change to add flag
            Log.d("Test", "Number: T" + numOfMatches);
            sv.doSwipe(false);
            //Change this to make it faster; Might not be able to do this
            new CountDownTimer((int) (2.25 * 200), 200) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish()
                {
                    numOfMatches--;
                   // Log.d("Test", "Number: F" + numOfMatches);
                    removeMatches();
                }
            }.start();
        }
        else
        {
            setupSV();
           // Log.d("Test","0: "+numOfMatches);
            setMatches(false);
        }

    }

    private void setupSV()
    {
        sv = (SwipePlaceHolderView) view.findViewById(R.id.swipeView);
        sv.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg));
        ImageButton r=(ImageButton) view.findViewById(R.id.rejectBtn);
        ImageButton a=(ImageButton) view.findViewById(R.id.acceptBtn);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.doSwipe(false);
                numOfMatches--;
               // Log.d("Test", "Number: R" + numOfMatches);
            }
        });
        r.bringToFront();
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.doSwipe(true);
                numOfMatches--;
               // Log.d("Test", "Number: A" + numOfMatches);
            }
        });
        a.bringToFront();
    }

    //Communicating
    @Override
    public void passDataToFragment(Bundle someValue)
    {


        reset=someValue.getBoolean("Reset",false);
        Log.d("Test", "The settings: "+minAge+" "+maxAge+" "+range);
        if(minAge!=someValue.getInt("Min",18)||maxAge!=someValue.getInt("Max",100)||range!=someValue.getInt("Months",3))
        {
            minAge=someValue.getInt("Min",18);
            maxAge=someValue.getInt("Max",100);
            range=someValue.getInt("Months",3);
            Log.d("Test", "The settings: "+minAge+" "+maxAge+" "+range);
            setMatches(reset);
        }
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        ((MainActivity)context).fragmentCommunicator = this;
    }
    //Touch Events
    View.OnTouchListener mOnListTouchListener = new  View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View view, MotionEvent event)
        {
            return mDetector.onTouchEvent(event);
        }
    };
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event)
        {
            Log.d(DEBUG_TAG,"onDown: ");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
        {
            if (event2.getY()-event1.getY() > 30)
            {
                Log.d("Test", "onFling:");
                setMatches(false);
                return true;
            }
            else return super.onFling(event1, event2, velocityX, velocityY);
        }
    }
}

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

import java.util.List;

public class SwipeView extends Fragment implements FragmentCommunicator
{

    Context mContext;
    View view;
    SwipePlaceHolderView sv;
    protected int numOfMatches;
    private int minAge;
    private int maxAge;
    private int maxDis;
   // Button b;
    private GestureDetectorCompat mDetector;
    private double myLat;
    private double myLng;
    private boolean changedLoc;
    private double oldLat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.swipetab, container, false);
        view=v;
        mContext = v.getContext();
        mDetector = new GestureDetectorCompat(mContext,new MyGestureListener());
        numOfMatches=0;
        minAge=18;
        maxAge=100;
        maxDis=20;
        setupSV();
        myLat=0;
        oldLat=0;
        v.setOnTouchListener(mOnListTouchListener);
        //setMatches(false);
        return v;
    }

    public void setMatches(boolean restart)
    {
        if (restart)
        {
            Log.d("Numbers", "Reseting " + numOfMatches);
            removeMatches();
        }
        else
        {
            Utils u=new Utils();
            List<Profile> test=u.loadProfiles(mContext);
            Log.d("Test","Lat/Lng "+myLat+"/"+myLng);
            for (int i=0;i<test.size();i++)
            {

                if (test.get(i).getAge() >= minAge && test.get(i).getAge() <= maxAge)
                {
                    if(myLat!=0)
                    {
                        oldLat=myLat;
                        List<Double> temp= u.distance(test.get(i).getLocation());
                        Log.d("Test","Got the array: "+temp.size());
                        double dis;
                        if(!(temp.get(0)==0))
                        {
                             dis= u.distanceTotal(temp, myLat, myLng, 0, 0);
                        }
                        else
                        {
                            dis = 100;
                        }
                        if (dis <= maxDis) {
                            Log.d("Test", "Pass: Dis: " + dis + " Max Dis: " + maxDis);
                            Log.d("Numbers", "Number: C1 " + numOfMatches);
                            numOfMatches++;
                            sv.addView(new MatchCard(mContext, test.get(i), sv));
                        } else
                            Log.d("Test", "Fail: Dis: " + dis + " Max Dis: " + maxDis);
                    }
                    else
                    {
                        Log.d("Test", "Fail:");
                        numOfMatches++;
                         Log.d("Numbers", "Number: C " + numOfMatches);
                        sv.addView(new MatchCard(mContext, test.get(i), sv));
                    }
                }
            }
        }
    }

    private void removeMatches()
    {
        if(numOfMatches>0)
        {
            //Will change to add flag so it wont remove the person from possible matches
            Log.d("Numbers", "Number: T" + numOfMatches);
            sv.doSwipe(false);
            new CountDownTimer((int) (2.25 * 200), 200) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish()
                {
                    numOfMatches--;
                    Log.d("Numbers", "Number: F" + numOfMatches);
                    removeMatches();
                }
            }.start();
        }
        else
        {
            setupSV();
            Log.d("Numbers","0: "+numOfMatches);
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
        if(someValue.containsKey("Settings"))
        {
            minAge = someValue.getBundle("Settings").getInt("Min");
            maxAge = someValue.getBundle("Settings").getInt("Max");
            maxDis = someValue.getBundle("Settings").getInt("MaxDis");
        }
        if(someValue.containsKey("Lat")&&myLat!=someValue.getDouble("Lat"))
        {
            myLat = someValue.getDouble("Lat");
            myLng = someValue.getDouble("Lng");
        }
        Log.d("Numbers", ""+minAge+" "+maxAge+" "+someValue.getBoolean("restart")+" D:"+maxDis+" Lat: "+myLat+" Lng: "+myLng);

        setMatches(true);
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

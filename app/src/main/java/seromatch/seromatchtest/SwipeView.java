package seromatch.seromatchtest;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import static seromatch.seromatchtest.MainActivity.maxAge;
import static seromatch.seromatchtest.MainActivity.minAge;

public class SwipeView extends Fragment implements View.OnClickListener
{

    Context mContext;
    View view;
    static SwipePlaceHolderView sv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.swipetab, container, false);
        view=v;
        mContext = v.getContext();
        //setMatches(mContext,v);
        Button b= (Button) v.findViewById(R.id.out);
        b.setOnClickListener(this);
       /* v.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
               mSwipeView.doSwipe(false);
          }
       });

        v.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });*/
        return v;
    }
    public void setMatches(Context c,View vi)
    {
        sv = (SwipePlaceHolderView) vi.findViewById(R.id.swipeView);
        sv.bringToFront();
        sv.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg));

        vi.bringToFront();
        //Log.d("Restart","test");
        for(Profile profile : Utils.loadProfiles(mContext))
        {

            if(profile.getAge()>=minAge&&profile.getAge()<=maxAge)
            {
                // Log.d("Restart",profile.getAge().toString());
                sv.addView(new MatchCard(mContext, profile, sv));
            }
        }
    }
    @Override
    public void onClick(View v)
    {
        setMatches(mContext,view);
    }
}

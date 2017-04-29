package seromatch.seromatchtest;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

public class SwipeView extends Fragment
{
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.swipetab, container, false);

        mSwipeView = (SwipePlaceHolderView)v.findViewById(R.id.swipeView);
        mContext = v.getContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg));

        for(Profile profile : Utils.loadProfiles(v.getContext())){
            mSwipeView.addView(new MatchCard(mContext, profile, mSwipeView));
        }

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
}

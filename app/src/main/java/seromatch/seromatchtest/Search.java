package seromatch.seromatchtest;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;

/**
 * Created by jason_000 on 4/9/2017.
 */

public class Search extends Activity
{
    private Intent intent;
    public Search()
    {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        intent = getIntent();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.search_bar_search);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        // Get the intent, verify the action and get the query
        Intent search = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = search.getStringExtra(SearchManager.QUERY);
        }

    }
    public void back(View v)
    {
        Intent back= new Intent(getApplicationContext(),MainActivity.class);
        //@// TODO: 4/10/2017 Send back a bundle with 1 or 2 so it knows what tab it came from. Since right now it goes auto to 1
        back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        back.putExtra("Tab Number", intent.getIntExtra("Tab Number",0));
        startActivity(back);
    }
}

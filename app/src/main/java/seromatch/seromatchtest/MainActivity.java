package seromatch.seromatchtest;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Settings_Tab.InterfaceDataCommunicator {
    public FragmentCommunicator fragmentCommunicator;
    private LocationManager locationManager;
    private Location currentLoc;
    private int maxRange;
    private int minAge;
    private int maxAge;
    private int miles;
    private boolean reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bottom Tabs
        TabLayout tabLayoutBottom = (TabLayout) findViewById(R.id.bottom_tab_layout);
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_community));
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_profile_match));
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_action_name));
        tabLayoutBottom.addTab(tabLayoutBottom.newTab().setIcon(R.drawable.ic_account));
        tabLayoutBottom.setTabGravity(TabLayout.GRAVITY_FILL);
        ;
        final CustomViewPager viewPagerBottom = (CustomViewPager) findViewById(R.id.bottom_pager);
        //Need to load all of the tabs correctly it is number of tabs+1
        viewPagerBottom.setOffscreenPageLimit(5);
        viewPagerBottom.setPagingEnabled(false);
        final BottomTabAdapter adapterBottom = new BottomTabAdapter
                (getSupportFragmentManager(), tabLayoutBottom.getTabCount());
        viewPagerBottom.setAdapter(adapterBottom);
        viewPagerBottom.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutBottom));
        //Gets the right tab that you left on
        Intent intent = getIntent();
        if (intent.hasExtra("Tab Number")) {
            viewPagerBottom.setCurrentItem(intent.getIntExtra("Tab Number", 0));
        } else {
            viewPagerBottom.setCurrentItem(0);
        }
        tabLayoutBottom.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabBottom) {
                viewPagerBottom.setCurrentItem(tabBottom.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Location
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Location devicelocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
            currentLoc=devicelocation;
            updateDistance();
            Log.d("Location","Current: "+currentLoc.getLatitude());
        }
        minAge=18;
        maxAge=100;
        miles=20;
        maxRange=3;
        reset=true;
    }
    private void updateDistance()
    {
        Bundle b= new Bundle(0);
        updateData(b,true);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    public void onResume()
    {
        super.onResume();
        //Only updates every 10 seconds and if there is a 5 miles
    }
    //Communication
    @Override
    public void updateData(Bundle data,boolean flag) {
        if (fragmentCommunicator != null)
        {
            Bundle b= new Bundle(2);
            if(!flag)
            {
                if (data.containsKey("Settings")) {

                    b.putBundle("Settings", data.getBundle("Settings"));
                    Bundle a = new Bundle(2);
                    if (currentLoc != null)
                    {
                        a.putDouble("Lat", currentLoc.getLatitude());
                        a.putDouble("Lng", currentLoc.getLongitude());
                    } else
                    {
                        a.putDouble("Lat", 0);

                        a.putDouble("Lng", 0);
                    }
                    b.putBundle("Loc", a);
                } else {
                    Bundle c = new Bundle(5);
                    c.putInt("Min", minAge);
                    c.putInt("Max", maxAge);
                    c.putInt("Months", maxRange);
                    c.putBoolean("Reset", true);
                    c.putInt("Miles", miles);
                    b.putBundle("Settings", c);
                    Bundle d = new Bundle(2);
                    if (currentLoc != null) {
                        d.putDouble("Lat", currentLoc.getLatitude());
                        d.putDouble("Lng", currentLoc.getLongitude());
                    } else {
                        d.putDouble("Lat", 0);
                        d.putDouble("Lng", 0);
                    }
                    b.putBundle("Loc", d);
                }
                if (minAge != data.getBundle("Settings").getInt("Min", 18) || maxAge != data.getBundle("Settings").getInt("Max", 100)
                        || maxRange != data.getBundle("Settings").getInt("Months", 3) || miles != data.getBundle("Settings").getInt("Miles", 20))
                {
                    minAge = data.getBundle("Settings").getInt("Min", 18);
                    maxAge = data.getBundle("Settings").getInt("Max", 100);
                    maxRange = data.getBundle("Settings").getInt("Months", 3);
                    miles = data.getBundle("Settings").getInt("Miles", 20);
                    reset = data.getBundle("Settings").getBoolean("Reset");
                    Log.d("Numbers","Communicating with the frag");
                    fragmentCommunicator.passDataToFragment(b);
                }
                else
                {
                    Bundle a = new Bundle(2);
                    a.putDouble("Lat", currentLoc.getLatitude());
                    a.putDouble("Lng", currentLoc.getLongitude());
                    fragmentCommunicator.passDataToFragment(a);
                }
            }
            //Just sending the location data to swipr view
            else
            {
                Bundle a = new Bundle(2);
                a.putDouble("Lat", currentLoc.getLatitude());
                a.putDouble("Lng", currentLoc.getLongitude());
                fragmentCommunicator.passDataToFragment(a);
            }

        }
    }

}

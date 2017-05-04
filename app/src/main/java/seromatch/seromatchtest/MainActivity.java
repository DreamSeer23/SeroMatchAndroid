package seromatch.seromatchtest;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Settings_Tab.InterfaceDataCommunicator {
    public FragmentCommunicator fragmentCommunicator;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location currentLoc;
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
        locationListener = new LocationListener()
        {
            // @Override
            public void onLocationChanged(Location location)
            {
                currentLoc=location;
            }
            // @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {

            }
            // @Override
            public void onProviderEnabled(String provider)
            {

            }
            // @Override
            public void onProviderDisabled(String provider)
            {

            }
        };
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Location devicelocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
            currentLoc=devicelocation;
            updateDistance(devicelocation.getLatitude(),devicelocation.getLongitude());
        }
        else
        {
            Log.d("Location","Updating");
        }
    }
    private void updateDistance(double lat, double lng)
    {
        Bundle b= new Bundle(0);
        Log.d("Test","AT UPDATE");

        updateData(b);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            locationManager.removeUpdates(locationListener);
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        //Only updates every 10 seconds and if there is a 5 miles
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000,
                    8046, locationListener);
        }
    }
    //Communication
    @Override
    public void updateData(Bundle data) {
        if (fragmentCommunicator != null)
        {
            Bundle b= new Bundle(2);
            if(data.containsKey("Settings"))
            {
                b.putBundle("Settings", data);
                Bundle a = new Bundle(2);
                if (currentLoc != null) {
                    a.putDouble("Lat", currentLoc.getLatitude());
                    a.putDouble("Lng", currentLoc.getLongitude());
                } else {
                    a.putDouble("Lat", 0);

                    a.putDouble("Lng", 0);
                }
                b.putBundle("Loc",a);
            }
            else
            {
                if (currentLoc != null) {
                    b.putDouble("Lat", currentLoc.getLatitude());
                    b.putDouble("Lng", currentLoc.getLongitude());
                } else {
                    b.putDouble("Lat", 0);

                    b.putDouble("Lng", 0);
                }
            }
            fragmentCommunicator.passDataToFragment(b);
        }
    }

}

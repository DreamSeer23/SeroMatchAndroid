package seromatch.seromatchtest;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String TAG = "Utils";
    private static URL url;
    private static double mylat;
    private static double mylng;
    private double dis;
    GoogleMapper model;

    public static List<Profile> loadProfiles(Context context)
    {
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            //Where data is pulled
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "profiles.json"));
            List<Profile> profileList= new ArrayList<>();
            for(int i=0;i<array.length();i++)
            {
                Profile profile = gson.fromJson(array.getString(i), Profile.class);
                profileList.add(profile);
            }
            return profileList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Message> loadMessages(Context context)
    {
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            //Where data is pulled
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "messages.json"));
            List<Message> messageList= new ArrayList<>();
            for(int i=0;i<array.length();i++)
            {
                Message message = gson.fromJson(array.getString(i), Message.class);
                messageList.add(message);
            }
            return messageList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Message> loadDrafts(Context context)
    {
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            //Where data is pulled
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "drafts.json"));
            List<Message> messageList= new ArrayList<>();
            for(int i=0;i<array.length();i++)
            {
                Message message = gson.fromJson(array.getString(i), Message.class);
                messageList.add(message);
            }
            return messageList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Message> loadSent(Context context)
    {
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            //Where data is pulled
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "sent.json"));
            List<Message> messageList= new ArrayList<>();
            for(int i=0;i<array.length();i++)
            {
                Message message = gson.fromJson(array.getString(i), Message.class);
                messageList.add(message);
            }
            return messageList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public List<Double>  distance(String address)
    {
        //getting map address

        try{
            address=address.replaceAll(" ","%20");
            url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + address);
            Log.d("Test","Made it to distance util "+url);
            List<Double> temp = new ArrayList<Double>();
            AsyncTask maps= new MapsAsync();
            maps.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            double lat2;
            double lng2;
            maps.get();
            List<Results> test=model.getResults();
            if(test.size()!=0) {
                lat2 = test.get(0).getGeometry().getLocation().getLat();
                lng2 = test.get(0).getGeometry().getLocation().getLng();
            }
            else
            {
                lat2=0;
                lng2=0;
            }
            temp.add(lat2);
            temp.add(lng2);
            Log.d("Test","Lat2 and Lng2: "+lat2+" "+lng2);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String loadJSONFromAsset(Context context, String jsonFileName)
    {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG, "path " + jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    private class MapsAsync extends AsyncTask<Object, String, GoogleMapper>
    {


        @Override
        protected GoogleMapper doInBackground(Object... arg0) {
            try
            {
                Log.d("Test","Got the url in Async: "+url);
                BufferedReader address2 = new BufferedReader(new InputStreamReader(url.openStream()));
                Gson gson = new GsonBuilder().serializeNulls().create();
                model=gson.fromJson(address2,GoogleMapper.class);
                address2.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                cancel(true);
            }
            finally {
                isCancelled();

            }
            return model;
        }
        @Override
        protected void onCancelled(GoogleMapper   result) {
        }
        @Override
        protected void onPostExecute(GoogleMapper   result) {

        }
        @Override
        protected void onPreExecute()
        {

        }

        @Override
        protected void onProgressUpdate(String... text)
        {


        }
    }


    public double distanceTotal(List<Double> latlng1, double lat2, double lon2, double el1, double el2)
    {

            final int R = 6371; // Radius of the earth
            double lat1 = latlng1.get(0);
            double lon1 = latlng1.get(1);
            Double latDistance = Math.toRadians(lat2 - lat1);
            Double lonDistance = Math.toRadians(lon2 - lon1);
            Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c * 1000; // convert to meters

            double height = el1 - el2;

            distance = Math.pow(distance, 2) + Math.pow(height, 2);
            dis = Math.sqrt(distance);
            double inches = (39.370078 * dis);
            int miles = (int) (inches / 63360);
            return miles;

    }
}

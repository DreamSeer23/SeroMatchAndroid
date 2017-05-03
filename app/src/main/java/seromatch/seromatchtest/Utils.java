package seromatch.seromatchtest;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String TAG = "Utils";

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
    private static String loadJSONFromAsset(Context context, String jsonFileName) {
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
}

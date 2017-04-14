package seromatch.seromatchtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jason_000 on 4/14/2017.
 */

public class Login extends AppCompatActivity
{
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        password = (EditText) findViewById(R.id.password);
    }
    public void login(View v)
    {
        Intent start= new Intent(getApplicationContext(),MainActivity.class);
        //Hash entered password then check vs the saved hashed
        startActivity(start);

    }
    //Sign up on click
    //Open Fragment with the Sign in details

    //Show Password OnClick
    public void showPassword(View v)
    {
        if (password.getInputType()== 129)
        {
            Log.d("TEXT","text");
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        else
        {
            Log.d("TEXT","password");
            password.setInputType(129);
        }
    }
}

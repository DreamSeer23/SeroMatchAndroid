package seromatch.seromatchtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start);
        finish();
    }
    //Sign up on click
    //Open Fragment with the Sign in details
    public void signUp(View v)
    {

    }
    //Show Password OnClick
    public void showPassword(View v)
    {
        if (password.getInputType()== 129)
        {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        else
        {
            password.setInputType(129);
        }
    }
}

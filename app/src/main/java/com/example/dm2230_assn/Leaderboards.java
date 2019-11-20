package com.example.dm2230_assn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class Leaderboards extends Activity implements OnClickListener
{
    private Button btn_back; // Button name is the one that you have defined in the mainmenu.xml;

    @Override
    protected void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide the title upon the display. As we are creating a game,
        //, we do not need that
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // The above code is to allow the display to be fullscreen.
        setContentView(R.layout.leaderboards);

        // To allow the buttons to be able to be used, we need to implement an interface "OnClickListener"
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) // Invoke a callback on clicked on an event in a view
    {
        // For an event to occur or action to happen, as explained in Video 2-3 when creating the androidmanifest.xml.

        Intent intent = new Intent();
        // Intent is an action to be performed.
        // Intent is an object that provides runtime binding.

        if (v == btn_back) {
            intent.setClass(this, MainMenu.class);
            // If press start button, it should go from the current class to another class
            // For now, we do not have other activity class to transit to so we go back to and from to Splash page.
        }
        startActivity(intent); // Whichever you press, the activity class will be activated. Hence, change scene.
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}

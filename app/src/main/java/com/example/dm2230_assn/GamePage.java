package com.example.dm2230_assn;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class GamePage extends Activity
{
    // Define a few parameters
    protected boolean _active = true;

    @Override // An annotation to assure that the subclass method is overriding the parent class method.
    // If it is not able to do so, compile with error will occur.
    protected void onCreate(Bundle savedInstancedState)
    {
        super.onCreate(savedInstancedState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide the title upon the display. As we are creating a game, we do not need that
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // The above code is to allow the display to be fullscreen.
        setContentView(R.layout.gamepage);


        Thread gameThread = new Thread()
        {
            public void run()
            {
                if (_active == false)
                {
                    finish();
                    // Create new activity based on and intent to do with the current activity.
                    // Go to Main Menu when time is up.
                    Intent intent = new Intent(GamePage.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        };// Create a new instance of an object for this case is a thread
        gameThread.start(); // To start the thread running
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // Why boolean is because either YES or NO on if touch of the screen by user input.
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            // Action down meant an input on the screen is checked.
            // User touch the screen.
            _active = false; // Meant timer have to stop, intent to push to main menu activity to start the main menu
        }
        return true; // From the touch event.
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    protected void onStop() { super.onStop(); }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}

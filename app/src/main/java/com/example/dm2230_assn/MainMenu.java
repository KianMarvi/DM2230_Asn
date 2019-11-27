package com.example.dm2230_assn;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.media.MediaPlayer;

public class MainMenu extends Activity implements OnClickListener, StateBase
{
    private Button btn_start;

    private Button btn_options;
    private Button btn_tutorial;
    private Button btn_leaderboards;
    MediaPlayer music;


    //music = MediaPlayer.




    @Override // An annotation to assure that the subclass method is overriding the parent class method.
    // If it is not able to do so, compile with error will occur.

    protected void onCreate(Bundle savedInstancedState)
    {
        super.onCreate(savedInstancedState);

        music = MediaPlayer.create(MainMenu.this, R.raw.music);
        music.start();

        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide the title upon the display. As we are creating a game,
                                                        //, we do not need that
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // The above code is to allow the display to be fullscreen.
        setContentView(R.layout.mainmenu);

        // To allow the buttons to be able to be used, we need to implement an interface "OnClickListener"

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); // Set the listener to the button
       // btn_back = (Button)findViewById(R.id.btn_back);
       // btn_back.setOnClickListener(this);
        btn_options = (Button)findViewById(R.id.btn_options);
        btn_options.setOnClickListener(this);
        btn_tutorial = (Button)findViewById(R.id.btn_tutorial);
        btn_tutorial.setOnClickListener(this);
        btn_leaderboards = (Button)findViewById(R.id.btn_leaderboards);
        btn_leaderboards.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) // Invoke a callback on clicked on an event in a view
    {
        // For an event to occur or action to happen, as explained in Video 2-3 when creating the androidmanifest.xml.

        Intent intent = new Intent();
        // Intent is an action to be performed.
        // Intent is an object that provides runtime binding.

        if (v == btn_start)
        {
            intent.setClass(this, GamePage.class);
            // If press start button, it should go from the current class to another class
            // For now, we do not have other activity class to transit to so we go back to and from to Splash page.
            music.stop();
        }
        else if (v == btn_options)
        {
            intent.setClass(this, Options.class);
        }
        else if (v == btn_tutorial)
        {
            intent.setClass(this, Tutorial.class);
        }
        else if (v == btn_leaderboards)
        {
            intent.setClass(this, Leaderboards.class); // For now, there is no where to go.
        }

        startActivity(intent); // Whichever you press, the activity class will be activated. Hence, change scene.
    }

    @Override
    public void Render(Canvas _canvas) {
    }

    @Override
    public void OnEnter(SurfaceView _view) {
    }

    @Override
    public void OnExit() {
    }

    @Override
    public void Update(float _dt) {
    }

    @Override
    public String GetName() {
        return "MainMenu";
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        music.stop();
    }
}

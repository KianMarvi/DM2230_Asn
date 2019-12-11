package com.example.dm2230_assn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PauseButton implements EntityBase
{
    private boolean isInit = false;
    private boolean isDone = false;

    private Bitmap bmpPause = null;

    private int xPos = 0;
    private int yPos = 0;

    private int ScreenWidth, ScreenHeight;

    private boolean pause = false;


    @Override
    public boolean IsDone()
    {
        return isDone;
    }
    @Override
    public void SetIsDone(boolean _isDone)
    {
        isDone = _isDone;
    }
    @Override
    public void Init(SurfaceView _view)
    {
        isInit = true;
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        bmpPause = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(_view.getResources(), R.drawable.arrow)), ScreenWidth / 12,  (ScreenHeight) / 7, true);

        xPos = ScreenWidth - 150;
        yPos = 150;

    }

    @Override
    public void Update(float _dt)
    {
        if (TouchManager.Instance.HasTouch()) // Detecting a touch
        {
            if (TouchManager.Instance.IsDown() && !pause)
            {
                // Check Collision

                float imgRadius = bmpPause.getHeight() * 0.5f;

                // Check for Collided
                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius))
                {
                    pause = true;

                    // Option 1 = use your alert dialog to ask user if they want to pause
                    // Button is clicked then show the popup
                    if (PauseConfirmDialog.IsShown) {
                        return;
                    }

                    PauseConfirmDialog newPauseConfirm = new PauseConfirmDialog();
                    newPauseConfirm.show(GamePage.Instance.getFragmentManager(), "PauseConfirm");
                    //smurfX += 10.f * _dt;

                    // Option 2 = Don't have the alert dialog, just pause
                  //  GameSystem.Instance.GetIsPaused(!GameSystem.Instance.GetIsPaused());
                }
            }
        }
        else
            pause = false;

        // Do more handling - add more code here
    }
    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(bmpPause, xPos - bmpPause.getWidth() * 0.5f, yPos - bmpPause.getHeight() * 0.5f, null);
    }

    @Override
    public boolean IsInit()
    {
        return isInit;
    }

    @Override
    public int GetRenderLayer()
    {
        return LayerConstants.BUTTON_LAYER;
    }
    @Override
    public void SetRenderLayer (int _newLayer)
    {
        return;
    }

    public static PauseButton Create()
    {
        PauseButton result = new PauseButton();
        EntityManager.Instance.AddEntity(result);

        return result;
    }

}

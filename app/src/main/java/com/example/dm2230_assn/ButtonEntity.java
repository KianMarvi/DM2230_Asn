package com.example.dm2230_assn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.Button;

public class ButtonEntity implements EntityBase
{
    private boolean isDone = false;
    private boolean isInit = false;
    private float rightButtonX, rightButtonY;
    private int ScreenWidth, ScreenHeight;
    private Bitmap btn_moveright = null;
    private float smurfX, smurfY;
    private SmurfEntity smurf = new SmurfEntity();

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
        btn_moveright = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(_view.getResources(), R.drawable.arrow)), ScreenWidth / 10,  (ScreenHeight) / 5, true);

        rightButtonX =  _view.getWidth() * 0.25f;
        rightButtonY =  _view.getHeight() * 0.75f;
        smurfX = smurf.GetPosX();
        smurfY = smurf.GetPosY();
    }
    @Override
    public void Update(float _dt)
    {
        if (TouchManager.Instance.HasTouch()) // Detecting a touch
        {
            if (TouchManager.Instance.IsDown())
            {

                float imgRadius = btn_moveright.getHeight() * 0.5f;
                // Check for Collided
                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, rightButtonX, rightButtonY, imgRadius)) {
                    smurfX += 10.f * _dt;
                    SetIsDone(true);
                }
            }
        }
    }
    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(btn_moveright, rightButtonX, rightButtonY, null);
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

    public static ButtonEntity Create()
    {
        ButtonEntity result = new ButtonEntity();
        EntityManager.Instance.AddEntity(result);

        return result;
    }

}

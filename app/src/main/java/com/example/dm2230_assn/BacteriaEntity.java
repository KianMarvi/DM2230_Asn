package com.example.dm2230_assn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;
import android.os.Build;
import android.util.DisplayMetrics;

public class BacteriaEntity implements EntityBase, Collidable
{
    private boolean isDone = false;
    private Bitmap bacteria = null;
    private float xPos, yPos, xDir, yDir, lifeTime = 0;
    int ScreenWidth, ScreenHeight;

    private boolean isInit = false;

    private int renderLayer = LayerConstants.GAMEOBJECTS_LAYER;

    private int health = 100;



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
        bacteria = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bacteria);
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        xPos =  _view.getWidth() * 0.95f;
        yPos =  _view.getHeight() * 0.5f;

        bacteria = Bitmap.createScaledBitmap(bacteria, ScreenWidth / 8, ScreenHeight /8, true);

        isInit = true;
        //lifeTime = 30.0f; // Lifetime will decrease till zero, then something will happen
    }
    @Override
    public void Update(float _dt)
    {
        // Movement Code
        xPos -= _dt * 500;
        if (xPos < -ScreenWidth)
        {
            xPos = ScreenWidth;
        }


    }
    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(bacteria, xPos, yPos, null);

        // Transformation
    }
    @Override
    public boolean IsInit()
    {
        return isInit;
    }

    @Override
    public int GetRenderLayer()
    {
        return renderLayer;
    }
    @Override
    public void SetRenderLayer (int _newLayer)
    {
        renderLayer = _newLayer;
    }

    public static BacteriaEntity Create()
    {
        BacteriaEntity result = new BacteriaEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
    public static BacteriaEntity Create(int _Layer)
    {
        BacteriaEntity result = Create();
        result.SetRenderLayer(_Layer);
        return result;
    }

    @Override
    public String GetType()
    {
        return "BacteriaEntity";
    }
    @Override
    public float GetPosX()
    {
        return xPos;
    }
    @Override
    public float GetPosY()
    {
        return yPos;
    }
    @Override
    public float GetRadius()
    {
        return bacteria.getHeight() * 0.5f;
    }
    @Override
    public void OnHit (Collidable _other)
    {
        if (_other.GetType() == "ProjectileEntity") // If hit by projectile, bacteria will die
        {
           SetIsDone(true);
        }
    }
}

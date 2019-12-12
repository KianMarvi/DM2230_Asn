package com.example.dm2230_assn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.content.Context;
import android.util.Log;
import android.view.SurfaceView;
import android.os.Build;

public class Projectile implements EntityBase, Collidable
{
    //Init any variables here
    private boolean isDone = false;
    private boolean isInit = false;

    private float xPos, yPos, lifeTime;
    private Sprite spritesheet = null;

    private int renderLayer = LayerConstants.GAMEOBJECTS_LAYER;


    @Override
    public boolean IsDone()
    {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        spritesheet = new Sprite(BitmapFactory.decodeResource(_view.getResources(), R.drawable.wbc_stationary), 1, 1, 10);
        lifeTime = 30.0f;


        xPos = 0.2f * _view.getWidth();
        isInit = true;

    }

    @Override
    public void Update(float _dt)
    {
        // Update based on dt
    }


    @Override
    public void Render(Canvas _canvas) {
        // Render anything
        spritesheet.Render(_canvas, (int) xPos, (int) yPos);
    }

    @Override
    public int GetRenderLayer()
    {
        return renderLayer;
    }

    public void SetRenderLayer(int _newLayer)
    {
        renderLayer = _newLayer;
    }


    @Override
    public boolean IsInit() {
        return isInit;
    }

    public static Projectile Create(float yPos)
    {
        Projectile result = new Projectile();
        result.yPos = yPos;
        EntityManager.Instance.AddEntity(result);
        return result;
    }


    @Override
    public String GetType() {
        return "ProjectileEntity";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return spritesheet.GetHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if ( _other.GetType() == "BacteriaEntity"  ) // If player dies, go back to main menu screen
        {
            SetIsDone(true);
        }
    }
}

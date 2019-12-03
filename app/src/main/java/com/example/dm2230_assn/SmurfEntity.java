package com.example.dm2230_assn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;

import java.util.Random;

public class SmurfEntity implements EntityBase, Collidable
{
   private Sprite spritesheet = null;
   private boolean isDone = false;

   private float xPos, yPos, xDir, yDir, lifeTime;

   private boolean isInit = false;

   private int renderLayer = 0;


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
       spritesheet = new Sprite(BitmapFactory.decodeResource(_view.getResources(),R.drawable.whitebloodcell), 4,4, 16);

 //     bmp = BitmapFactory.decodeResource(_view.getResources())
       Random ranGen = new Random();

       xPos = ranGen.nextFloat() * _view.getWidth();
       yPos = ranGen.nextFloat() * _view.getHeight();

       xDir = ranGen.nextFloat() * 100.0f - 50.0f;
       yDir = ranGen.nextFloat() * 100.0f - 50.0f;

       isInit = true;
       //lifeTime = 30.0f; // Lifetime will decrease till zero, then something will happen
   }
    @Override
    public void Update(float _dt)
    {
        spritesheet.Update(_dt);


        if (TouchManager.Instance.IsDown()) // Detecting a touch
        {
            // Detect the touch on character, then you will need to do some collision check.
            // Collision check will be based off the point of your
            // Finger that touches the screen with the centre of the character

            // If you want to drag the character/image, TouchManager.Instance.IsMove()

            // Check for Collided
            float imgRadius = spritesheet.GetHeight() * 0.5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0, xPos, yPos, imgRadius))
            {
                xPos += xDir * _dt;
                yPos += yDir * _dt;

                SetIsDone(true);
            }
        }
    }
    @Override
    public void Render(Canvas _canvas)
    {
        spritesheet.Render(_canvas, (int)xPos, (int)yPos);

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

    public static SmurfEntity Create()
    {
        SmurfEntity result = new SmurfEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
    public static SmurfEntity Create(int _Layer)
    {
        SmurfEntity result = Create();
        result.SetRenderLayer(_Layer);
        return result;
    }

    @Override
    public String GetType()
    {
        return "SmurfEntity";
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
        return spritesheet.GetHeight() * 0.5f;
    }
    @Override
    public void OnHit (Collidable _other)
    {
        if (_other.GetType() == "SmurfEntity") // Enemy
        {
            // Do Something here
        }
    }

}


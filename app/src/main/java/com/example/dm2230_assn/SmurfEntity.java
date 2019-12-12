package com.example.dm2230_assn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;
import android.widget.Button;

import java.util.Random;

public class SmurfEntity implements EntityBase, Collidable
{
   private Sprite spritesheet = null;
   private boolean isDone = false;

   private float xPos, yPos, xDir, yDir, lifeTime = 0;

   public float ElapsedTime, TimeToShoot;

   private boolean isInit = false;

   private int renderLayer = LayerConstants.GAMEOBJECTS_LAYER;

   private int health = 100;
    Projectile projectile = null;





   public void Move(SurfaceView _view)
   {
       spritesheet = new Sprite(BitmapFactory.decodeResource(_view.getResources(),R.drawable.wbc), 4,6, 30);
   }



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
        Move(_view);

       xPos =  _view.getWidth() * 0.25f;
       yPos =  _view.getHeight() * 0.5f;

       xDir =  100.0f - 50.0f;
       yDir = 100.0f - 50.0f;

       isInit = true;
       //lifeTime = 30.0f; // Lifetime will decrease till zero, then something will happen
   }
    @Override
    public void Update(float _dt)
    {
        spritesheet.Update(_dt);

        // Movement Code
        float move = _dt * 500;
        if (TouchManager.Instance.HasTouch()) // Head to the direction of where the player has click on the screen
        {
            if (TouchManager.Instance.GetPosY() > yPos) {
                if ((TouchManager.Instance.GetPosY() - yPos) < move)
                    yPos = TouchManager.Instance.GetPosY();
                else
                    yPos += move;
            } else if (TouchManager.Instance.GetPosY() < yPos)
            {
                if ((yPos - TouchManager.Instance.GetPosY()) < move)
                    yPos = TouchManager.Instance.GetPosY();
                else
                    yPos -= move;
            }
        }
    }
    @Override
    public void Render(Canvas _canvas)
    {
        spritesheet.Render(_canvas, (int)xPos, (int)yPos);


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
        if (_other.GetType() == "BacteriaEntity") // Enemy
        {
            StateManager.Instance.ChangeState("MainMenu");
            StateManager.Instance.Clean();
        }
    }

}


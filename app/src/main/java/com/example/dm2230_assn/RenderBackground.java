package com.example.dm2230_assn;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderBackground implements EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos = 0;

    int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;

    // Render Text or font
    Typeface myFont;

    @Override
    public boolean IsDone() {return isDone;}

    @Override
    public void SetIsDone(boolean _isDone)
    {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view)
    {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bloodvessel);
        // Retrieve information of your surfaceview or any device size view
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);

       // myFont = Typeface.create(_view.getContext().getString())
    }

    @Override
    public void Update(float _dt)
    {
        xPos -= _dt * 500;
        if (xPos < - ScreenWidth)
        {
            xPos = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(scaledbmp, xPos, yPos, null);
        _canvas.drawBitmap(scaledbmp, xPos + ScreenWidth, yPos, null);

        Paint paint = new Paint();
        paint.setARGB(255, 255, 255,255);
        paint.setStrokeWidth(200);
        paint.setTypeface(myFont);
        paint.setTextSize(70);
        _canvas.drawText("Score: ", 200, 200, paint);
    }

    @Override
    public boolean IsInit()
    {
        return bmp != null;
    }

    @Override
    public int GetRenderLayer()
    {
        return LayerConstants.BACKGROUND_LAYER;
    }
    @Override
    public void SetRenderLayer (int _newLayer)
    {
        return;
    }

    public static RenderBackground Create()
    {
        RenderBackground result = new RenderBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

}

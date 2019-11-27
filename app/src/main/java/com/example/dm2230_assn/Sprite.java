package com.example.dm2230_assn;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite
{
    private int row = 0;
    private int col = 0;
    private int width = 0;
    private int height = 0;

    private Bitmap bmp;

    private int currentFrame = 0;
    private int startFrame = 0;
    private int endFrame = 0;

    private float timePerFrame = 0.0f;
    private float timeAcc = 0.0f;

    public Sprite(Bitmap _bmp, int _row, int _col, int _fps)
    {
        bmp = _bmp;
        row = _row;
        col = _col;

        width = bmp.getWidth();
        height = bmp.getHeight();

        timePerFrame = 1.0f/(float) _fps;
        endFrame = _col * _row;
    }

    public void Update(float _dt)
    {
        timeAcc += _dt;
        if (timeAcc > timePerFrame) {
            ++currentFrame;
            if (currentFrame >= endFrame) {
                currentFrame = startFrame;
            }
            timeAcc = 0.0f;
        }
    }

    public void Render(Canvas _canvas, int _x, int _y)
    {
        int frameX = currentFrame % col;
        int frameY = currentFrame / col;

        int srcX = frameX * width;
        int srcY = frameY * height;

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(_x, _y, _x + width, _y + height);
        _canvas.drawBitmap(bmp, src, dst, null);
    }
    public void SetAnimationFrames(int _start, int _end)
    {
        timeAcc = 0.0f;
        currentFrame = _start;
        startFrame = _start;
        endFrame = _end;
    }

    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }

}

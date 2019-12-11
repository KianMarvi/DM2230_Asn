package com.example.dm2230_assn;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

// Created by TanSiewLan2019

public class MainGameSceneState implements StateBase
{
    private float timer = 0.0f;

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        ButtonEntity.Create();
        PauseButton.Create();
        SmurfEntity.Create();
        // Example to include another Renderview for Pause Button
    }

    @Override
    public void OnExit()
    {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

    @Override
    public void Update(float _dt)
    {
        // Check for pause to open


        EntityManager.Instance.Update(_dt);



    }
}




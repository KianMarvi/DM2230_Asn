package com.example.dm2230_assn;

// Created by TanSiewLan2019

public class Collision {

    public static boolean SphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;

        float distSquared = xVec * xVec + yVec * yVec;

        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        if (distSquared > rSquared)
            return false;

        return true;
    }
    public static boolean CheckAABBCollision(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2)
    {
        float distance_x = Math.abs(x1 - x2);
        float distance_y = Math.abs(y1 - y2);

        if (distance_x < ((w1/2) + (w2/2)) && distance_y < ((w1/2) + (w2/2)))
            return true;

        return false;
    }
}

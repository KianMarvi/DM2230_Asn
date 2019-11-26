package com.example.dm2230_assn;


public interface Collidable
{
    String GetType();

    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(Collidable _other);
}


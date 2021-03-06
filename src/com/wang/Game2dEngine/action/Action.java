/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.Game2dEngine.action;

import com.wang.Game2dEngine.sprite.Sprite;

import java.util.Random;

/**
 * @author ricolwang
 */
public abstract class Action
{

    public String identifier = this.getClass().getName();
    protected boolean bImmediately;

    public static final double MINIMUM = 1e-03;
    public static final double EQUAL_STANDARD = 1e-10;
    public static final double MINIMUM_DURATION = 1e-3;

    public Random theRandom = new Random();
    protected Sprite theSprite = null;
    public boolean bComplete = false;

    //runningTime in milliseconds
    public void perform(double runningTime)
    {
        bComplete = theRandom.nextBoolean();
    }

    public void setSprite(Sprite aSprite)
    {
        this.theSprite = aSprite;
    }

    public void clearSprite()
    {
        this.theSprite = null;
    }

    @Override
    public String toString()
    {
        return "Action: " + this.getClass().getName() + " - id: " + identifier;
    }

    public Sprite getTheSprite()
    {
        return this.theSprite;
    }
}

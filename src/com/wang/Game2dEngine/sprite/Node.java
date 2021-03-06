/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.Game2dEngine.sprite;

import com.wang.Game2dEngine.Shape.ECircleShape;
import com.wang.Game2dEngine.Shape.Interface.IEShape;

import java.util.Random;

/**
 * @author ricolwang
 */
public class Node
{

    public String identifier;
    private double x = 0;
    private double y = 0;
    private double width = 0;
    private double height = 0;

    protected Random theRandom = new Random();
    private IEShape theShape = null;

    public Node(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public void setX(double x)
    {
        double oldX = this.x;
        this.x = x;
        this.refreshShape(x - oldX, 0, 0, 0);
    }

    public void setY(double y)
    {
        double oldY = this.y;
        this.y = y;
        this.refreshShape(0, y - oldY, 0, 0);
    }

    public void setWidth(double width)
    {
        double oldWidth = this.width;
        this.width = width;
        this.refreshShape(0, 0, width - oldWidth, 0);
    }

    public void setHeight(double height)
    {
        double oldHeight = this.height;
        this.height = height;
        this.refreshShape(0, 0, 0, height - oldHeight);
    }

    public IEShape getTheShape()
    {
        return this.theShape.getShape();
    }

    public void setTheShape(IEShape theShape)
    {
        this.theShape = theShape;

        if (this.theShape != null)
        {
            this.theShape.setTheNode(this);
        }

        this.onShapeAdded(theShape);
    }

    public double getCentreX()
    {
        return this.x + width / 2.0;
    }

    public double getCentreY()
    {
        return this.y + height / 2.0;
    }

    public void setCentreX(double value)
    {
        this.setX(value - width / 2.0);
    }

    public void setCentreY(double value)
    {
        this.setY(value - height / 2.0);
    }

    public void onShapeAdded(IEShape theShape)
    {

    }

    void refreshShape(double changeX, double changeY, double changeWidth, double changeHeight)
    {
        if (this.theShape == null)
        {
            ECircleShape aCircleShape = new ECircleShape(this.getCentreX(), this.getCentreY(), this.getWidth() > this.getHeight() ? this.getWidth() / 2.0f : this.getHeight() / 2.0f);
            this.setTheShape(aCircleShape);
        }

        this.theShape.refresh(changeX, changeY, changeWidth, changeHeight);
    }
}

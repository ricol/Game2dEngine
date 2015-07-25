/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.Game2dEngine.node;

import java.util.Random;

/**
 *
 * @author ricolwang
 */
public class Node
{

    protected double x;
    protected double y;
    protected double width;
    protected double height;

    protected Random theRandom = new Random();

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
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public boolean overlaps(float targetX, float targetY, float targetWidth, float targetHeight)
    {
        return x < targetX + targetWidth && x + width > targetX && y < targetY + targetHeight && y + height > targetY;
    }
}

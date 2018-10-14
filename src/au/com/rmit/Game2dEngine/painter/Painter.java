/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.Game2dEngine.painter;

import au.com.rmit.Game2dEngine.painter.interfaces.IEngineGraphics;
import au.com.rmit.Game2dEngine.painter.interfaces.IPainter;
import au.com.rmit.Game2dEngine.painter.interfaces.IPanelDelegate;
import au.com.rmit.Game2dEngine.painter.interfaces.IUserInteraction;
import au.com.rmit.Game2dEngine.painter.interfaces.IWindow;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author ricolwang
 */
class Panel extends JPanel
{

    IPanelDelegate delegate;

    Panel(IPanelDelegate delegate)
    {
        super();
        this.delegate = delegate;
    }

    @Override
    public void paint(Graphics g)
    {
        delegate.update(g);
        this.repaint();
    }
}

public class Painter implements IPainter, IUserInteraction, IWindow, IPanelDelegate
{

    Panel panel = new Panel(this);
    protected IEngineGraphics theEngineGraphics;

    @Override
    public int getHeight()
    {
        return panel.getHeight();
    }

    @Override
    public int getWidth()
    {
        return panel.getWidth();
    }

    public void loop(Graphics g)
    {

    }

    @Override
    public Component getComponent()
    {
        return panel;
    }

    @Override
    public void addMouseListener(MouseListener listener)
    {
        panel.addMouseListener(listener);
    }

    @Override
    public void addComponentListener(ComponentAdapter componentAdapter)
    {
        panel.addComponentListener(componentAdapter);
    }

    @Override
    public void setSize(Dimension d)
    {
        panel.setSize(d);
    }

    @Override
    public void update(Graphics g)
    {
        loop(g);
    }
}

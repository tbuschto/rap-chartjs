package org.eclipse.rap.chartjs;

public class Axis
{
    boolean display = true;
    
    private Ticks ticks = new Ticks();

    public Ticks getTicks()
    {
        return ticks;
    }
    
    public void setDisplay(boolean display)
    {
        this.display = display;
    }
    
    public boolean isDisplay()
    {
        return display;
    }
}

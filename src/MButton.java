import java.awt.*;
class MButton extends AFigElement
{
    public MButton(FigCanvas c, AFigure f, int x, int y)
    {
        canvas=c;
        offX=x;
        offY=y;
        onoff=false;
        radius=4;
        ancestor=f;
    }
    public int radius;
    public boolean isSelected(int x, int y)
    {
        double d;
        DoublePoint p0=absPosition();
        d=Math.sqrt(  ((double)x-p0.x)*((double)x-p0.x)
                     +((double)y-p0.y)*((double)y-p0.y));
        if(d<=(double)radius) {
            onoff=!onoff;
            return true;
        }
        return false;
    }
    private boolean onoff;
    public void setOnOff(boolean tf) {
    	System.out.println("");
    	onoff=tf;
    }
    public boolean isOn() {
    	return onoff;
    }
    public MButton(FigCanvas c,int x, int y)
    {
        canvas=c;
        offX=x;
        offY=y;
        onoff=false;
        radius=4;
    }
    public void draw(Graphics g)
    {
        if(onoff) drawFCircle(g,radius);
        else drawCircle(g,radius);
    }
}


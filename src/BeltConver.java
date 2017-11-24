import java.awt.*;
class BeltConveyer extends AssembleMachine
{
    public int length;
    public void init(FigCanvas c)
    {
        bconv=new Lines(c);
        bconv.add(0,12);
        bconv.add(length,12);
        bconv.add(length,-12);
        bconv.add(0,-12);
        bconv.add(0,12);
    }
    public Lines bconv;
    public void draw(Graphics g)
    {
        bconv.draw(g);
    }
    public BeltConveyer(FigCanvas c, int x, int y,int len, double t)
    {
        length=len;
        init(c);
        bconv.canvas=c;
        bconv.offX=x;
        bconv.offY=y;
        bconv.direction=t;
    }
}


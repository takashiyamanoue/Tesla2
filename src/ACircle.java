import java.awt.*;
class ACircle extends AFigElement
{
    public int radius;
    public void draw(Graphics g)
    {
        drawCircle(g,radius);
    }
    public ACircle(FigCanvas c, AFigure f, int x, int y, int r)
    {
        canvas=c;
        offX=x;
        offY=y;
        radius=r;
        ancestor=f;
     }
}


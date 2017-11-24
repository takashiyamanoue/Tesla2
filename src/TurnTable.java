import java.awt.*;
class TurnTable extends AssembleMachine
{
    public ABlock ablockOn(int x, int y, int z)
    {
        return canvas.fs.select(x,y);
    }
    public MButton button;
    public TurnTable()
    {
    }
    public Figures ttable;
    public TurnTable(FigCanvas c, int x, int y)
    {
        canvas=c;
        offX=x;
        offY=y;
        init(c);
    }
    public void init(FigCanvas c)
    {
        ttable=new Figures(c,null,offX,offY);
        button=new MButton(c,ttable,-14,14);
        ACircle circle=new ACircle(c,ttable,0,0,15);
        ttable.add(circle);
        ttable.add(button);
    }
    public void draw(Graphics g, String s)
    {
        ttable.draw(g);
        ttable.drawString(g,s,-2,2);
    }
    public int radius;
}


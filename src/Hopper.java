import java.awt.* ;
class Hopper extends AssembleMachine
{
    public void advance()
    {
        if(button.isOn()){
            hopABlock();
            button.setOnOff(false);
        }
    }
    public void hopABlock()
    {
           if(canvas.fs==null)
                 canvas.fs=new Blocks(canvas);
            ABlock1 b=new ABlock1(canvas);
            b.offX=hopper.offX+b.width/2+10;
            b.offY=hopper.offY;
            b.showhide=true;
            canvas.fs.add(b);
     }
    public boolean isSelected(int x, int y)
    {
        if(hopper.isSelected(x,y))
        {/*
            if(canvas.fs==null)
                 canvas.fs=new Blocks(canvas);
            ABlock1 b=new ABlock1(canvas);
            b.offX=hopper.offX+b.width/2+10;
            b.offY=hopper.offY;
            b.showhide=true;
            canvas.fs.add(b);*/
            hopABlock();
            button.setOnOff(false);
            return true;
        }
        else
        {
            return false;
        }
    }
    public MButton button;
    public Lines shape;
    public Hopper(FigCanvas c, int x, int y, double d)
    {
        canvas=c;
        init(c);
        hopper.canvas=c;
        hopper.offX=x;
        hopper.offY=y;
        hopper.direction=d;
    }
    public Figures hopper;
    public void init(FigCanvas c)
    {
        hopper=new Figures(c);
        shape=new Lines(c,hopper);
        shape.add(0,15);
        shape.add(-3,12);
        shape.add(-22,12);
        shape.add(-22,-12);
        shape.add(-3,-12);
        shape.add(0,-15);
        button=new MButton(c,hopper,-15,0);
        hopper.add(shape);
        hopper.add(button);
    }
    public void draw(Graphics g)
    {
        hopper.draw(g);
    }
}


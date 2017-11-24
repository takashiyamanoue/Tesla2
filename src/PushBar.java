import java.awt.*;
class PushBar extends AssembleMachine
{
    public boolean isExpanded;
    public void advance()
    {
        double dd=1.0;
        if(button.onoff)
        {
            if(expand<extlength){
                double dx=(dd
                        *Math.cos(-direction));
                double dy=(dd
                        *Math.sin(-direction));
                DoublePoint dp=shape.absPosition();
                double cx=(expand*Math.cos(-direction)+
                                 dp.x) ;
                double cy=(expand*Math.sin(-direction)+
                                 dp.y) ;
                canvas.fs.push(null,cx,cy,dx,dy);
                expand+=dd;
            } else isExpanded=true;

        }
        else
        {
            if(expand>0) expand-=dd;
            isExpanded=false;
        }
    }
    public boolean isSelected(int x, int y)
    {
        return pushbar.isSelected(x,y);
    }
    public int  extlength;
    public Figures pushbar;
    public MButton button;
    public PushBar(FigCanvas c, int x, int y, int w, double d)
    {
        canvas=c;
        pushbar = new Figures(c);
        pushbar.offX=x;
        pushbar.offY=y;
        extlength=0;
        expand=0;
        width=w;
        offX=x;
        offY=y;
        this.direction=d;
        init(c);
        isExpanded=false;
    }
    public int width;
    public double expand;
    public void reshape()
    {
        int exp=(int)expand;
        pushbar.remove(shape);
        shape=null;
        shape=new Lines(canvas,pushbar);
        shape.offX=0;
        shape.offY=0;
  //      pushbar.mag2=0.5;
        shape.direction=this.direction;
        shape.add(exp,0);
        shape.add(exp,width/2);
        shape.add(exp-3,width/2);
        shape.add(exp-3,width/4);
        shape.add(exp-10,width/4);
        shape.add(exp-10,width/3);
        shape.add(-30,width/3);
        shape.add(-30,-width/3);
        shape.add(exp-10,-width/3);
        shape.add(exp-10,-width/4);
        shape.add(exp-3,-width/4);
        shape.add(exp-3,-width/2);
        shape.add(exp,-width/2);
        shape.add(exp,0);
        pushbar.add(shape);
    }
    public void init(FigCanvas c)
    {
        button=new MButton(c, pushbar,-20,0);
        button.onoff=false;
        button.direction=this.direction;
        pushbar.add(button);
        reshape();
    }
    public Lines shape;
    public void draw(Graphics g)
    {
     /*
       if(button.onoff)
           expand=extlength;
       else
           expand=0;
    */
       reshape();
       pushbar.draw(g);
    }
}


import java.awt.*;
class Lines extends AFigElement
{
    public Lines(FigCanvas c, AFigure f)
    {
        canvas=c;
        super.init();
        ancestor=f;
        plist=null;
    }
    public void add(PointList px)
    {
        PointList p1,p2;
        px.next=null;
        if(plist==null) { plist=px; return;}
        p2=plist; p1=p2.next;
        while(p1!=null){ p1=p1.next; p2=p2.next;}
        p2.next=px;
        return;
    }
    public Lines(FigCanvas c)
    {
        canvas=c;
        super.init();
        plist=null;
    }
    public void draw(Graphics g)
    {
        PointList p1,p2;
        p1=plist;
        if(p1==null) return;
        p2=p1.next;
        if(p2==null) { return;}
        while(p2!=null){
            drawLine(g, p1.x, p1.y,
                        p2.x, p2.y);
            p1=p1.next; p2=p2.next;
        }
    }
    public void add(int x, int y)
    {
        PointList p=new PointList();
        PointList p1,p2;
        p.x=x; p.y=y; p.next=null;
        if(plist==null) { plist=p; return;}
        p2=plist; p1=p2.next;
        while(p1!=null){ p1=p1.next; p2=p2.next;}
        p2.next=p;
        return;
    }
    public PointList plist;
}


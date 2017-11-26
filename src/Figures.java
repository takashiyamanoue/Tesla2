import java.awt.*;
class Figures extends AFigElement
{
    public Figures(FigCanvas c, AFigure f, int x, int y)
    {
        canvas=c;
        ancestor=f;
        offX=x;
        offY=y;
        flist=null;
    }
    public Figures(FigCanvas c)
    {
        canvas=c;
        flist=null;
    }
    public void remove(AFigure f)
    {
        FigList p1,p2;
        p2=flist;
        if(p2==null) return;
        p1=p2.next;
        if(f==p2.f) {p2.f=null; flist=p1; return;}
        while(p1!=null){
            if(f==p1.f){
                p1.f=null;p2.next=p1.next; return;
            }
            p1=p1.next; p2=p2.next;
        }
    }
    public boolean isSelected(int x, int y)
    {
        FigList p;
        int mx, my;
  //      mx=(int)((double)x/mag2);
        mx=logicalX(x);
//        my=(int)((double)y/mag2);
        my=logicalY(y);
        p=flist;
        while(p!=null){
//            if(p.f.isSelected(mx,my)) return true;
        	if(p.f.isSelected(x, y)) return true;
            p=p.next;
        }
        return false;
    }
    public void add(AFigure f)
    {
        FigList p=new FigList();
        p.f=f;
        p.next=flist;
        flist=p;
    }
    public void draw(Graphics g)
    {
        FigList p;
        p=flist;
        while(p!=null){
            p.f.draw(g);
            p=p.next;
        }
    }
    public FigList flist;
}


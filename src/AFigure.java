import java.awt.* ;
class AFigure extends java.lang.Object
{
    public void drawFRect2(Graphics g,int x, int y, int w, int h, Color c)
    {
        int ix1,iy1,ix2,iy2;
        DoublePoint p1=new DoublePoint();
        DoublePoint p2=new DoublePoint();
        DoublePoint p0=absPosition();
        p1.x=x; p1.y=y;
        p2.x=x+w; p2.y=y+h;
//        p1.magnify0( canvas.magnif);
        p1.rotate0( direction);
//        p2.magnify0( canvas.magnif);
        p2.rotate0(  direction);
        ix1=physicalX(p0.x+p1.x); iy1=physicalY(p0.y+p1.y);
        ix2=physicalX(p0.x+p2.x); iy2=physicalY(p0.y+p2.y);
//        System.out.println("AFigure.drawFRect2-ix1="+ix1+"-iy1="+iy1+"-ix2="+ix2+"-iy2="+iy2+"-c="+c);
        int xa[]={ix1,ix2,ix2,ix1};
        int ya[]={iy1,iy1,iy2,iy2};
        Color cc=g.getColor();
        g.setColor(c);
        g.fillPolygon(xa,ya,4);
        g.setColor(cc);
    }
    /*
  ln_f3
     vertical line
     argument
              (x0,y0)
                 |
                 |---
                 |   |
     (x1,y1)-----------------(x2,y2)
               (x,y)

        input    x0,y0, x1,y1, x2,y2,
        output   (x,y)

*/
    public Point vLine1(double x0, double y0,
                       double x1, double y1, double x2, double y2)
 {
   double d,x,y,a,b,c;
   Equation2D eq=new Equation2D();
   Point p;
   p=null;
   eq.x1=x1; eq.y1=y1; eq.x2=x2; eq.y2=y2;
   if(!eq.edges2abc()) return p;
   p=new Point(0,0);
   a=eq.a; b=eq.b; c=eq.c;
   d=a*a+b*b;

   x=(b*b*x0+a*c-a*b*y0)/d;  p.x=(int)x;
   y=(a*a*y0+b*c-a*b*x0)/d;  p.y=(int)y;
   return p;
 }

    public boolean isOntheLine(int xr, int yr, int x1, int y1, int x2, int y2)
    {
        double w1,w2,d,dxr,dyr,dx1,dx2,dy1,dy2;
        double error=3.0;
        Point p;
        p=vLine1((double)xr,(double)yr,(double)x1,(double)y1,
                                     (double)x2,(double)y2);
        if(p==null) return false;
        if((p.x-x1)*(p.x-x2)>0) return false;
        if((p.y-y1)*(p.y-y2)>0) return false;
        d=Math.sqrt(((double)xr-(double)p.x)*((double)xr-(double)p.x)
                +   ((double)yr-(double)p.y)*((double)yr-(double)p.y));

        if(d>error) return false;
        return true;

    }

    public boolean isNear(int x1, int y1, int x2, int y2)
    {
        int error=3;
        if(Math.abs(x1-x2)>error) return false;
        if(Math.abs(y1-y2)>error) return false;
        return true;
    }
    public int physicalY(double y)
    {
        return (int)(y*canvas.magnif);
    }
    public int physicalX(double x)
    {
        return (int)(x*canvas.magnif);
    }

    public FigCanvas canvas;
    public int physicalY(int y)
    {
        return (int)((double)y*canvas.magnif);
    }
    public int physicalX(int x)
    {
        return (int)((double)x*canvas.magnif);
    }
    public int logicalY(int y)
    {
         if(canvas.magnif==0.0) {
            System.out.println("error");
            return 0;
        }
        return (int)((double)y/canvas.magnif);
   }
    public int logicalX(int x)
    {
        if(canvas.magnif==0.0) {
            System.out.println("error");
            return 0;
        }
        return (int)((double)x/canvas.magnif);
    }
    public void drawString(Graphics g, String s, int x, int y)
    {
        int ix,iy;
        DoublePoint p=absPosition();
        ix=physicalX(p.x+x);
        iy=physicalY(p.y+y);
        g.drawString(s,ix,iy);

    }
    public void drawRect(Graphics g, int x, int y, int w, int h)
    {
        this.drawLine(g,x,y,x+w,y);
        this.drawLine(g,x+w,y,x+w,y+w);
        this.drawLine(g,x+w,y+w,x,y+w);
        this.drawLine(g,x,y+w,x,y);
    }
    public void drawFRect(Graphics g, int x, int y, int w, int h)
    {
        int ix1,iy1,ix2,iy2;
        DoublePoint p1=new DoublePoint();
        DoublePoint p2=new DoublePoint();
        DoublePoint p0=absPosition();
        p1.x=x; p1.y=y;
        p2.x=x+w; p2.y=y+h;
//        p1.magnify0( canvas.magnif);
        p1.rotate0( direction);
//        p2.magnify0( canvas.magnif);
        p2.rotate0(  direction);
        ix1=physicalX(p0.x+p1.x); iy1=physicalY(p0.y+p1.y);
        ix2=physicalX(p0.x+p2.x); iy2=physicalY(p0.y+p2.y);
        int xa[]={ix1,ix2,ix2,ix1};
        int ya[]={iy1,iy1,iy2,iy2};
        g.fillPolygon(xa,ya,4);
    }
    public double absDirection()
    {
        if(ancestor!=null)
            return this.direction+ancestor.absDirection();
        else return this.direction;
    }
    public DoublePoint absPosition()
    {
        int x0,y0;
        DoublePoint p=new DoublePoint();
        if(ancestor!=null){
            DoublePoint pa=ancestor.absPosition();
            p.x=(double)offX;
            p.y=(double)offY;
//            p.magnify0(canvas.magnif);
            p.rotate0(direction+ancestor.absDirection());
            p.move(pa.x,pa.y);
        }
        else
        {
            p.x=(double)offX;
            p.y=(double)offY;
        }
        return p;
    }
    public AFigure ancestor;
    public void drawFCircle(Graphics g, int r)
    {
        int ix,iy,ir,x0,y0;
        DoublePoint p=absPosition();
        ix=physicalX((int)(p.x));
        iy=physicalY((int)(p.y));
        ir=(int)(canvas.magnif*r);
        g.fillOval(ix-ir,iy-ir,ir*2,ir*2);
    }
    public boolean isSelected(int x, int y)
    {
        return false;
    }
    public void draw(Graphics g)
    {
    }
    public void drawCircle(Graphics g, int r)
    {
        int ix,iy,ir,x0,y0;
        DoublePoint p=absPosition();
        ix=physicalX(p.x);
        iy=physicalY(p.y);
        ir=physicalX(r);
        g.drawOval(ix-ir,iy-ir,ir*2,ir*2);
    }
    public double mag2;
    public AFigure()
    {
        ancestor=null;
        init();
    }
    public void init()
    {
 //       mag2=0.5;
        direction=0.0;
        magnify=2.0; //<-1.0
        offX=0;
        offY=0;
    }
    public double magnify;
    public void drawLine(Graphics g, int x1, int y1, int x2, int y2)
    {
        int ix1,iy1,ix2,iy2;
        DoublePoint p1=new DoublePoint();
        DoublePoint p2=new DoublePoint();
        DoublePoint p0=absPosition();
        p1.x=x1; p1.y=y1;
        p2.x=x2; p2.y=y2;
//        p1.magnify0( canvas.magnif);
        p1.rotate0( direction);
//        p2.magnify0( canvas.magnif);
        p2.rotate0(  direction);
        ix1=physicalX(p0.x+p1.x); iy1=physicalY(p0.y+p1.y);
        ix2=physicalX(p0.x+p2.x); iy2=physicalY(p0.y+p2.y);
        g.drawLine(ix1,iy1,ix2,iy2);
    }
    public int logicalX(double x)
    {
        if(canvas.magnif==0.0) {
            System.out.println("error");
            return 0;
        }
        return (int)(x/canvas.magnif);
    }
    public int logicalY(double y)
    {
        if(canvas.magnif==0.0) {
            System.out.println("error");
            return 0;
        }
        return (int)(y/canvas.magnif);
    }
    public double widthMag;
    public double lengthMag;
    public double direction;
    public double offZ;
    public double offY;
    public double offX;
}


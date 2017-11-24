class DoublePoint extends java.lang.Object
{
    public void magnify(double xc, double yc, double r)
    {
        DoublePoint p1=new DoublePoint();
        p1.x=this.x; p1.y=this.y;
        p1.move(-xc,-yc);
        p1.magnify0(r);
        p1.move(xc,yc);
        this.x=p1.x; this.y=p1.y;
    }
    public void rotate(double xc, double yc, double t)
    {
        DoublePoint p1=new DoublePoint();
        p1.x=this.x; p1.y=this.y;
        p1.move(-xc,-yc);
        p1.rotate0(t);
        p1.move(xc,yc);
        this.x=p1.x; this.y=p1.y;
    }
    public void rotate0(double t)
    {
        double x0,y0;
        x0=x; y0=y;
        x=x0*Math.cos(t)+y0*Math.sin(t);
        y=-x0*Math.sin(t)+y0*Math.cos(t);
    }
    public void magnify0(double r)
    {
        x=x*r; y=y*r;
    }
    public void move(double a, double b)
    {
        x=x+a; y=y+b;
    }
    public double y;
    public double x;
}


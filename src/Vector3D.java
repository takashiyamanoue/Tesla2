class Vector3D extends java.lang.Object
{
    public Vector3D magnify(double r)
    {
        Vector3D v= new Vector3D(x*r, y*r, z*r);
        return v;
    }
    public Vector3D unit()
    {
        double r=length();
        Vector3D v=new Vector3D(x/r,y/r,z/r);
        return v;

    }
    public double length()
    {
        double d;
        d=x*x+y*y+z*z;
        if(d==0.0){
            d=d;
           return 0;
        }
        return Math.sqrt(d);
    }
    public Vector3D subtract(Vector3D v)
    {
        Vector3D u=new Vector3D(0,0,0);
        u.x=x-v.x;
        u.y=y-v.y;
        u.z=z-v.z;
        u.ix=(int)(u.x); u.iy=(int)(u.y); u.iz=(int)(u.z);
        return u;
    }
    public Vector3D vectorproduct(Vector3D v)
    {
        Vector3D u=new Vector3D(0,0,0);
        u.x=y*(v.z)-z*(v.y);
        u.y=z*(v.x)-x*(v.z);
        u.z=x*(v.y)-y*(v.x);
        u.ix=(int)(u.x); u.iy=(int)(u.y); u.iz=(int)(u.z);
        return u;
    }
    public double scalarproduct(Vector3D v)
    {
        return x*(v.x)+y*(v.y)+z*(v.z);
    }
    public Vector3D add(Vector3D v)
    {
        Vector3D u=new Vector3D(0,0,0);
        u.x=x+v.x;
        u.y=y+v.y;
        u.z=z+v.z;
        u.ix=(int)(u.x); u.iy=(int)(u.y); u.iz=(int)(u.z);
        return u;
    }
    public Vector3D(double dx, double dy, double dz)
    {
        x=dx; y=dy; z=dz;
        ix=(int)dx; iy=(int)dy; iz=(int)dz;
    }
    public Vector3D(int dx, int dy, int dz)
    {
        ix=dx; iy=dy; iz=dz;
        x=(double)dx; y=(double)dy; z=(double)dz;
    }
    public int iz;
    public int iy;
    public int ix;
    public double z;
    public double y;
    public double x;
}


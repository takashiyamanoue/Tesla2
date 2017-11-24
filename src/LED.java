class LED extends java.lang.Object
{
    public Vector3D lv;
    public LED()
    {
        lightOn=false;
    }
    public Vector3D localV()
    {
        face.currentCenter();
        if(direction==0){
            int d0=face.dig0();
            int cdir=face.block.currentDirection(d0);
            (face.block).faces[cdir].centerVector();
            Vector3D v0=(face.block).faces[cdir].vec;
            Vector3D v1=(v0.unit()).magnify((double)r);
            return v1;
        }
        else
        if(direction==1){
            Vector3D v0=(face.vec).unit();
            Vector3D v1=(face.leds[0].localV()).unit();
            Vector3D v=(v0.vectorproduct(v1)).magnify((double)r);
            return v;
        }
        else
        if(direction==2){
            return (face.leds[0].localV()).magnify(-1.0);
        }
        else
        if(direction==3){
            return (face.leds[1].localV()).magnify(-1.0);
        }
        return null;
    }
    public Vector3D position()
    {
        Vector3D v,v0;
        lv=localV();
        v0=new Vector3D(face.block.offX,
                        face.block.offY,
                        face.block.offZ);
        face.currentCenter();
        v=v0.add(face.vec.add(lv));
        return v;
    }
    public int direction;
    public int r;
    public AFaceState face;
    public boolean lightOn;
    public LED(AFaceState fs, int rr, int d)
    {
        face=fs;
        r=rr;
        direction=d;
   //     lv=localV();
        lightOn=false;
    }
}


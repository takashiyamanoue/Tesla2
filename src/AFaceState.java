import java.awt.*;
class AFaceState extends AFigure
{
    public Color thisColor()
    {
        if(faceID==0) return Color.yellow;
        if(faceID==1) return Color.green;
        if(faceID==2) return Color.red;
        if(faceID==3) return Color.blue;
        if(faceID==4) return Color.gray;
        if(faceID==5) return Color.white;
        else return Color.white;
    }
    public void wmes(String s)
    {
        block.canvas.editdispatch.mes.appendText(s+"\n");
    }
    public String nextdoorIDs;
    public void setinfo(AnalizedInfo ainfo)
    {
        nextdoorIDs=ainfo.nextdoorBlock;
        nextdoorFace=ainfo.nextdoorFace;
        phaseDifference=ainfo.phaseDifference;
    }
    public void printState(AnalizedInfos ainfo)
    {
        AnalizedInfo x=new AnalizedInfo();
        x.blockID=block.sID;
        x.faceID=faceID;
        x.phaseDifference=phaseDifference;
        x.nextdoorBlock=nextdoorBlock.sID;
        x.nextdoorFace=nextdoorFace;
        ainfo.add(x);
        wmes("Block="+block.sID);
        wmes("  faceID="+faceID);
        wmes("  phaseDiff="+phaseDifference);
        wmes("  nextdoorBlock="+
                                 nextdoorBlock.sID);
        wmes("  nextdoorFace="+
                                 nextdoorFace);
    }
    public boolean isSensing(Blocks bs)
    {
        return ptr.isSensingLight(bs);
    }
    public boolean isConnected(Blocks bs)
    {
        int i;
        for(i=0;i<4;i++){
            leds[i].lightOn=true;
            nextdoorBlock=bs.sensing(this.block);
            if(nextdoorBlock!=null){
               nextdoorFace=nextdoorBlock.sensing(bs);
               phaseDifference=i;
               leds[i].lightOn=false;
               return true;
            }
            leds[i].lightOn=false;
        }
        if(nextdoorBlock==null) return false;
        else return true;
    }
    public ABlock nextdoorBlock;
    //public String nextdoorBlock;
    public PhotoTr ptr;
    public LED leds[];
    public boolean isOnThisFace(double x, double y, double z)
    {
        double error=4.0;
        Vector3D v1=new Vector3D(x,y,z);
        currentCenter();
        Vector3D v2=new Vector3D(centerX, centerY, centerZ);
        Vector3D v3=v1.subtract(v2);
        if(v3.length()>width/5.0) return false;
        if(Math.abs(vec.scalarproduct(v3))
                   >(vec.length()+error)) return false;
        return true;
    }
    public void centerVector()
    {
        if(this==block.faces[0]){
            vec=new Vector3D(0,0,width/2);
            return;
        }
        if(this==block.faces[1]){
            vec=new Vector3D(width/2,0,0);
            return;
        }
        if(this==block.faces[2]){
            vec=new Vector3D(0,width/2,0);
            return;
        }
        if(this==block.faces[3]){
            vec=new Vector3D(-width/2,0,0);
            return;
        }
        if(this==block.faces[4]){
            vec=new Vector3D(0,-width/2, 0);
            return;
        }
        if(this==block.faces[5]){
            vec=new Vector3D(0,0,-width/2);
            return;
        }
        else
            System.out.println("error\n");

    }
    public Vector3D vec;
    public void currentCenter()
    {
        Vector3D v=new Vector3D(block.offX,block.offY,block.offZ);
        centerVector();
        Vector3D u=v.add(vec);
        centerX=u.x;
        centerY=u.y;
        centerZ=u.z;
    }
    public void currentX1Y1X2Y2()
    {
        width=block.width;
        x1=(int)(block.offX-(width)/2 -1);
        y1=(int)(block.offY-(width)/2 -1);
        x2=x1+width-1; y2=y1+width-1;
    }
    public int y2;
    public int x2;
    public int y1;
    public int x1;
/*
             4

         -----------
        /          /|
       /    0     / |
      /          /  |
  3  /-----------   |
     |          | 1 |
     |     2    |   /
     |          |  /
     |          | /
     ------------/

           5
*/
   public int dig0()
    {
        int i;
        if(faceID==0){
            return 4;
        }
        else
        if(faceID==1){
            return 5;
        }
        else
        if(faceID==2){
            return 0;
        }
        else
        if(faceID==3){
            return 2;
        }
        else
        if(faceID==4){
            return 1;
        }
        else
        if(faceID==5){
            return 3;
        }
        else{ return -1;}
    }
    public int deg0x;
    public int width;
    public void drawTemp(Graphics g)
    {
        draw(g);
        offX=block.offX;
        offY=block.offY;
        drawFRect(g,(int)(x1-offX),(int)(y1-offY),3,3); 
        drawFRect(g,(int)(x2-offX),(int)(y1-offY),3,3);
        drawFRect(g,(int)(x1-offX),(int)(y2-offY),3,3); 
        drawFRect(g,(int)(x2-offX),(int)(y2-offY),3,3);
    }
    public void draw(Graphics g)
    {
        int d;
        offX=block.offX;
        offY=block.offY;
        d=block.currentDirection(dig0());
        currentX1Y1X2Y2();
        super.drawFRect2(g,(int)(x1-offX),(int)(y1-offY),width,width,
                                     thisColor());
        if(d==1){
            super.drawFRect(g,(int)(x2-2-offX),(int)((y1+y2)/2-1-offY),4,4);
        }
        else
        if(d==2){
            super.drawFRect(g,(int)((x1+x2)/2-1-offX),(int)(y2-2-offY),4,4);
//            g.fillRect((x1+x2)/2-1,y2-2,2,2);
        }
        else
        if(d==3){
            super.drawFRect(g,(int)(x1-offX),(int)((y1+y2)/2-1-offY),4,4);
//            g.fillRect(x1,(y1+y2)/2-1,2,2);
        }
        else
        if(d==4){
            super.drawFRect(g,(int)((x1+x2)/2-1-offX),(int)(y1-offY),4,4);
//            g.fillRect((x1+x2)/2-1,y1,2,2);
        }
        super.drawRect(g,(int)(x1-offX),(int)(y1-offY),width,width);
//        g.drawRect(x1,y1, width,width);
        super.drawString(g,""+faceID, -2, +4);
//        g.drawString(""+faceID, block.offX-2, block.offY+3);
    }
    public ABlock block;
    public double centerZ;
    public double centerY;
    public double centerX;
    public AFaceState(FigCanvas c, ABlock b,int id)
    {
        int i;
        canvas=c;
        faceID=id;
        block=b;
        width=b.width;
        leds=new LED[4];
        for(i=0;i<4;i++)
             leds[i]=new LED(this,width/4,i);
        ptr=new PhotoTr(this,width/4,0);
        nextdoorBlock=null;
        super.init();

    }
  /*
               4

         -----------
        /          /|
       /    0     / |
      /          /  |
  3  /-----------   |
     |          | 1 |
     |     2    |   /
     |          |  /
     |          | /
     ------------/
           5

  */
    public int currentDirection;
    public int phaseDifference;
    public int nextdoorFace;
    public int nextdoorID;
  /*

        -----------              ---------
       /          /|            /|        |
      /    0     / |           / |        |      * top corner of the cube
     /          /  |          /  |    4   |      # bottom corner of the cube
    -----------*   |         | 3 |        |
    |          | 1 |         |   #--------/
    |     2    |   /         |  /        /
    |          |  /          | /   5    /
    |          | /           |/        /
    ------------/            ----------

    opposite face
       0-5
       1-3
       2-4

    next door face
       0 ... clock wize order: 3,4,1,2  ... reverse order of 5
       1 ...                 : 0,4,5,2  ... reverse order of 3
       2 ...                 : 3,0,1,5  ... reverse order of 4
       3 ...                 : 4,0,2,5  ... reverse order of 1
       4 ...                 : 3,5,1,0  ... reverse order of 2
       5 ...                 : 2,1,4,3  ... reverse order of 0

    (Upside) Mark of each face
       0 ... 1
       1 ... 2
       2 ... 3
       3 ... 5
       5 ... 4
       4 ... 0


    Substitute Group

     0-5 axis   (3,4,1,2)
     1-3 axis   (0,4,5,2)
     2-4 axis   (3,0,1,5)


  */
  public int faceID;
}


/*
ABlock

 for teleportation 
 
 t. yamanoue 
   
*/
import java.lang.*;
import java.awt.*;
import java.net.*;
import java.io.*;
class ABlock extends AFigure
{
    public void setinfo(AnalizedInfo ainfo)
    {
        sID=ainfo.blockID;
        System.out.println("ABlock.setinfo sID="+sID);
        for(int i=0;i<6;i++){
            if(faces[i].faceID==ainfo.faceID){
                faces[i].setinfo(ainfo);
                return;
            }
        }
    }
    public String sID;
    public int sensing(Blocks bs)
    {
        int i;
        for(i=0;i<6;i++)
          if(faces[i].isSensing(bs))
              return faces[i].faceID;
        return -1;
    }
    public void analyze(Blocks bs,String id,
                 AnalizedInfos ainfo)
    {
        int i,j;
/*
        for(i=0;i<6;i++)
          if(faces[i].ptr.isSensing(bs));
*/
        if(anaMark) return;
        anaMark=true;
        sID=id;
        System.out.println("ABlock sID="+sID);
        canvas.editdispatch.mes.appendText(sID+" being Analized...\n");
        for(i=0;i<6;i++)
           if(faces[i].isConnected(bs))
              faces[i].nextdoorBlock.analyze(bs,sID+"-"+i,ainfo);
        for(i=0;i<6;i++)
           if(faces[i].nextdoorBlock!=null)
               faces[i].printState(ainfo);
    }
    public boolean anaMark;
    public void rotateHlz()
    {
        AFaceState faceWork;
        System.out.println("ABlock.rotateHlz:");
        System.out.println("-up="+faces[currentUpFace()].faceID+"-dn="+faces[currentDownFace()].faceID+
        		"-nr="+faces[currentNorthFace()].faceID+"-ea="+faces[currentEastFace()].faceID+
        		"-so"+faces[currentSouthFace()].faceID+"-we="+faces[currentWestFace()].faceID);
        int work=currentEastFace();
        faceWork=faces[currentEastFace()];
        faces[currentEastFace()]=faces[currentSouthFace()];
        faces[currentSouthFace()]=faces[currentWestFace()];
        faces[currentWestFace()]=faces[currentNorthFace()];
        faces[currentNorthFace()]=faceWork;
        System.out.println("-up="+faces[currentUpFace()].faceID+"-dn="+faces[currentDownFace()].faceID+
        		"-nr="+faces[currentNorthFace()].faceID+"-ea="+faces[currentEastFace()].faceID+
        		"-so"+faces[currentSouthFace()].faceID+"-we="+faces[currentWestFace()].faceID);
      	System.out.println("ABlock.draw sID="+sID+"-currentUpFace()="+faces[currentUpFace()].faceID);
    }
    public boolean moveMark;
    public void push(Blocks bs, ABlock bb, double x, double y, double dx, double dy)
    {

        int face=isOnThisBlock(x,y,0.0);
        if(face<0) return;
        if(moveMark) return;
        int oface=oppositeFace(face);
        faces[oface].currentCenter();
        moveMark=true;
        bs.push(this, faces[oface].centerX,
                      faces[oface].centerY, dx, dy);

        Vector3D vb = new Vector3D(offX,offY,0.0);
        Vector3D vc = new Vector3D(x,y,0.0);
        Vector3D vs = vb.subtract(vc);
        Vector3D vd = new Vector3D(dx,dy,0.0);

        Vector3D nv = vc.add(vs.add(vd));

        moveMark=false;
        offX=nv.x; offY=nv.y;
    }

    public int isOnThisBlock(double x, double y, double z)
    {
        int i;
        double zx;
        int error=3;

        zx=0.0;
        for(i=0;i<6;i++)
            if(faces[i].isOnThisFace(x,y,zx)) return i;
        return -1;
       /*
        if(x<offX-width/2) return -1;
        if(x>offX+width/2) return -1;
        if(y<offY-width/2) return -1;
        if(y>offY+width/2) return -1;
        if(abs(x-(offX-width/2))<=error) return 3;
        if(abs(x-(offX+width/2))<=error) return 1;
        if(abs(y-(offY-width/2))<=error) return 4;
        if(abs(y-(offY+width/2))<=error) return 2;
        return false;
        */
    }
    public int currentDirection(int f)
    {
        if(f==faces[currentUpFace()].faceID)    return 0;
        if(f==faces[currentDownFace()].faceID)  return 5;
        if(f==faces[currentEastFace()].faceID)  return 1;
        if(f==faces[currentSouthFace()].faceID) return 2;
        if(f==faces[currentWestFace()].faceID)  return 3;
        if(f==faces[currentNorthFace()].faceID) return 4;
        return -1;
    }
    public void move(int x, int y)
    {
        offX=logicalX(x); offY=logicalY(y);
    }
    /*
     state= "Normal"
            "Moving"
            "RotatingEW"
            "RotatingNS"
    */
    public String state;
    public void alligin2Grid(int xx, int yy)
    {
        int x,y,n;
//        x=logicalX(xx); y=logicalY(yy);
        x=xx;y=yy;
        n=x / width;
        offX=n*width+width/2;
        n=y / width;
        offY=n*width+width/2;
    }
    public void init(FigCanvas c)
    {
         int i;
         super.init();
         anaMark=false;
         offX=0; offY=0; offZ=0;
         width=20;
         UpFace=0;
         NorthFace=4;
         selected=false;
         moveMark=false;
         faces=new AFaceState[6];
         for(i=0;i<6;i++) faces[i]=new AFaceState(c,this,i);
   }
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
    public void rotateEW()
    {
        AFaceState faceWork;
        System.out.println("ABlock.rotateEW:");
        System.out.println("-up="+faces[currentUpFace()].faceID+"-dn="+faces[currentDownFace()].faceID+
        		"-nr="+faces[currentNorthFace()].faceID+"-ea="+faces[currentEastFace()].faceID+
        		"-so"+faces[currentSouthFace()].faceID+"-we="+faces[currentWestFace()].faceID);
        faceWork=faces[currentWestFace()];
        faces[currentWestFace()]=faces[currentDownFace()];
        faces[currentDownFace()]=faces[currentEastFace()];
        faces[currentEastFace()]=faces[currentUpFace()];
        faces[currentUpFace()]=faceWork;
  //      UpFace=currentWestFace();
        System.out.println("-up="+faces[currentUpFace()].faceID+"-dn="+faces[currentDownFace()].faceID+
        		"-nr="+faces[currentNorthFace()].faceID+"-ea="+faces[currentEastFace()].faceID+
        		"-so"+faces[currentSouthFace()].faceID+"-we="+faces[currentWestFace()].faceID);
      	System.out.println("ABlock.draw sID="+sID+"-currentUpFace()="+faces[currentUpFace()].faceID);
    }


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
    public void rotateNS()
    {
        AFaceState faceWork;
        System.out.println("ABlock.rotateNS:");
        System.out.println("-up="+faces[currentUpFace()].faceID+"-dn="+faces[currentDownFace()].faceID+
        		"-nr="+faces[currentNorthFace()].faceID+"-ea="+faces[currentEastFace()].faceID+
        		"-so"+faces[currentSouthFace()].faceID+"-we="+faces[currentWestFace()].faceID);
        int work=currentSouthFace();
        faceWork=faces[currentUpFace()];
        faces[currentUpFace()]=faces[currentSouthFace()];
        faces[currentSouthFace()]=faces[currentDownFace()];
        faces[currentDownFace()]=faces[currentNorthFace()];
        faces[currentNorthFace()]=faceWork;
//        NorthFace=currentUpFace();
//        UpFace=work;
        System.out.println("-up="+faces[currentUpFace()].faceID+"-dn="+faces[currentDownFace()].faceID+
        		"-nr="+faces[currentNorthFace()].faceID+"-ea="+faces[currentEastFace()].faceID+
        		"-so"+faces[currentSouthFace()].faceID+"-we="+faces[currentWestFace()].faceID); 
      	System.out.println("ABlock.draw sID="+sID+"-currentUpFace()="+faces[currentUpFace()].faceID);
    }
    public int width;

    public AFaceState faces[];
    public int currentWestFace()
    {
        return  oppositeFace(currentEastFace());
    }
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

    Substitute Group

     0-5 axis   (3,4,1,2)
     1-3 axis   (0,4,5,2)
     2-4 axis   (3,0,1,5)


  */
    public int oppositeFace(int x)
    {
        if(x==0) return 5;
        if(x==1) return 3;
        if(x==2) return 4;
        if(x==3) return 1;
        if(x==4) return 2;
        if(x==5) return 0;
        return -1;
    }
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
    public int currentEastFace()
    {
        if(UpFace==0){
            if(NorthFace==3) return 4;
            if(NorthFace==4) return 1;
            if(NorthFace==1) return 2;
            if(NorthFace==2) return 3;
            return -1;}
         if(UpFace==1){
            if(NorthFace==0) return 4;
            if(NorthFace==4) return 5;
            if(NorthFace==5) return 2;
            if(NorthFace==2) return 0;
            return -1;}
         if(UpFace==2){
            if(NorthFace==3) return 0;
            if(NorthFace==0) return 1;
            if(NorthFace==1) return 5;
            if(NorthFace==5) return 3;
            return -1;}
         if(UpFace==3){
            if(NorthFace==0) return 2;
            if(NorthFace==2) return 5;
            if(NorthFace==5) return 4;
            if(NorthFace==4) return 0;
            return -1;}
         if(UpFace==4){
            if(NorthFace==0) return 3;
            if(NorthFace==1) return 0;
            if(NorthFace==5) return 1;
            if(NorthFace==3) return 5;
            return -1;}
         if(UpFace==5){
            if(NorthFace==4) return 3;
            if(NorthFace==1) return 4;
            if(NorthFace==2) return 1;
            if(NorthFace==3) return 2;
            return -1;}
         return -1;
    }
  public int currentSouthFace()
    {
        return oppositeFace(NorthFace);
    }
    public int currentDownFace()
    {
        return oppositeFace(UpFace);
    }
    public int currentNorthFace()
    {
        return NorthFace;
    }
    public int currentUpFace()
    {
        return UpFace;
    }
    public int NorthFace;
    public int UpFace;
    public int ID;
    public boolean strmWrite(DataOutputStream outS, String s)
    {
        /*
        char a[];
        byte b[];
        int size,i;
        a=s.toCharArray();
        size=s.length();
        b = new byte[size];
        for(i=0;i<size;i++) b[i]=(byte)a[i];
        try{outS.write(b,0,size-1);}
        */
        try{outS.writeBytes(s);}
        catch(IOException e){ return false;}
        return true;
    }
    public boolean save(DataOutputStream outs)
    {
        return true;
    }
    public int step;
    public void writeMessage(String s)
    {
        canvas.editdispatch.mes.appendText(s);
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
    private boolean selected;
    public void setSelected(boolean tf) {
    	selected=tf;
    }
    public boolean isSelected() {
    	return selected;
    }
    public ABlock()
    {
        init();
    }
    public boolean showhide;
    public ABlock(FigCanvas c)
    {
        init(c);
        this.canvas=c;
    }
    public void drawTemp(Graphics g)
    {
       faces[currentUpFace()].drawTemp(g);
    }
    public void showEdge(Graphics g,int x, int y)
    {
        g.fillRect(x-2,y-2,4,4);
    }
    public void draw(Graphics g)
    {
//    	System.out.println("ABlock.draw sID="+sID+"-currentUpFace()="+faces[currentUpFace()].faceID);
        if(showhide){
//        	if(this.sID==null) return;
        	AFaceState fs=faces[currentUpFace()];
            if(selected)  drawTemp(g);
            else          fs.draw(g);
        }
    }
    public boolean isPointed(int x, int y, int x2, int y2)
    {
        int error=2;
        if(!(Math.abs(x-x2)<error)) return false;
        if(!(Math.abs(y-y2)<error)) return false;
        return true;
    }
    public boolean isSelected(int xx, int yy)
    {
        int x,y;
//        x=logicalX(xx); y=logicalY(yy);
        x=xx; y=yy;
        if(x<offX-width/2) return false;
        if(x>offX+width/2) return false;
        if(y<offY-width/2) return false;
        if(y>offY+width/2) return false;
        return true;
    }
}

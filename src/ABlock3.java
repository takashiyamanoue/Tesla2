/*
NetPaint

     by T. Yamanoue,
     Kyushu Institute of Technology, Japan,
     Aug.1, 1997

 
   A Paint tool for the Internet.
   
   Drawing tool on a Web brouser.
   A Co-operative drawing tool.
   Drawing a paint on the Internet by linking parts
   
   
*/
import java.awt.*;
import java.io.*;
class ABlock3 extends ABlockElement
{
    /*
    public boolean save(DataOutputStream outS)
    {
        if(!strmWrite(outS, "rectangle("+offX+","+offY+","
                             +(offX+x2)+","+(offY+y2)+")\n")) return false;
         return true;
    }
    public boolean isSelected(int x, int y)
    {
        if(isPointed(offX,offY,x,y)) return true;
        if(isPointed(offX+x2,offY+y2,x,y)) return true;
        if(isPointed(offX+x2,offY,x,y)) return true;
        if(isPointed(offX,offY+y2,x,y)) return true;
        if(isOntheLine(x,y,offX,offY,offX+x2,offY)) return true;
        if(isOntheLine(x,y,offX+x2,offY,offX+x2,offY+y2)) return true;
        if(isOntheLine(x,y,offX+x2,offY+y2,offX,offY+y2)) return true;
        if(isOntheLine(x,y,offX,offY+y2,offX,offY)) return true;
        return false;
     }
    public ARectangle()
    {
        selected=false;
    }
    public ARectangle(FigCanvas c)
    {
        canvas=c;
        offX=0; offY=0; x2=20; y2=20;
        selected=false;
    }
    public void drawTemp(Graphics g)
    {
        g.drawRect(offX,offY,x2,y2);
        showEdge(g,offX,offY);
        showEdge(g,offX+x2,offY);
        showEdge(g,offX+x2,offY+y2);
        showEdge(g,offX,offY+y2);
    }
    public void draw(Graphics g)
    {
        if(selected) drawTemp(g);
        else    g.drawRect(offX,offY,x2,y2);
    }
    public int y2;
    public int x2;
    */
}


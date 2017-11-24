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
class FigCanvas extends java.awt.Canvas
{
    public double magnif;
    public void repaint()
    {
        Graphics g=this.getGraphics();
        paint(g);
    }
    public void init()
    {
       offImg=createImage(size().width, size().height);
       offGr=offImg.getGraphics();

       magnif=2.0; // <- 2.0

    }
    public Graphics offGr;
    public Image offImg;
    public boolean keyDown(Event e, int key)
    {
        editdispatch.keyDown(e,key);
        repaint();
        return true;
    }
    public boolean mouseUp(Event e, int x, int y)
    {
  //      editdispatch.mouseUp(e, logicalX(x), logicalY(y));
    	editdispatch.mouseUp(e, x, y);
        repaint();
        return true;
    }
    public boolean mouseExit(Event e, int x, int y)
    {
//        editdispatch.mouseExit(e);
    	editdispatch.mouseExit(e);
        repaint();
        return true;
    }
    public boolean mouseMove(Event e, int x, int y)
    {
//        editdispatch.mouseMove(e,logicalX(x),logicalY(y));
    	editdispatch.mouseMove(e, x, y);
        repaint();
        return true;
    }
    public boolean mouseEnter(Event e, int x, int y)
    {
//        editdispatch.mouseEnter(e,logicalX(x),logicalY(y));
    	editdispatch.mouseEnter(e, x, y);
        repaint();
        return true;
    }
    public boolean mouseDrag(Event e, int x, int y)
    {
//        editdispatch.mouseDrag(e,logicalX(x),logicalY(y));
    	editdispatch.mouseDrag(e, x, y);
        repaint();
        return true;
    }
    public boolean mouseDown(Event e, int x, int y)
    {
//        editdispatch.mouseDown(e,logicalX(x),logicalY(y));
    	editdispatch.mouseDown(e, x, y);
        repaint();
        return true;
    }
    public EditDispatcher editdispatch;
    public FigCanvas()
    {
        fs=null;
        ftemp=null;
    }
    public ABlock ftemp;
    public Blocks fs;
    public void paint(Graphics g)
    {
        offGr.setColor(getBackground());
        offGr.fillRect(0,0,size().width,size().height);
        offGr.setColor(getForeground());
        if(fs!=null) fs.draw(offGr);
        if(ftemp!=null) ftemp.draw(offGr);
        g.drawImage(offImg,0,0,this);
    }
       public int physicalY(int y)
    {
        return (int)((double)y*magnif);
    }
    public int physicalX(int x)
    {
        return (int)((double)x*magnif);
    }
    public int logicalY(int y)
    {
         if(magnif==0.0) {
            System.out.println("error");
            return 0;
        }
        return (int)((double)y/magnif);
   }
    public int logicalX(int x)
    {
        if(magnif==0.0) {
            System.out.println("error");
            return 0;
        }
        return (int)((double)x/magnif);
    }

}


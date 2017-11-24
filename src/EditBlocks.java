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
import java.lang.* ;
interface EditBlocks
{
    public void mouseEnter(int x, int y);
    public void mouseExit();
    public void mouseDrag(int x, int y);
    public void mouseMove(int x, int y);
    public void mouseUp(int x, int y);
    public void mouseDown(int x, int y);
    public void keyDown(int key);
   /*
     step=0:  ... no edge is fixed
          1:  ... one edge is fixed
          ...

     step=-1: ...
   */
 
}


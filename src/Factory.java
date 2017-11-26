import java.awt.*;
class Factory extends FigCanvas
{
    public Factory()
    {
        fs=null;
        ftemp=null;
    }
    public FactoryController fcontroller;
    public void repaint()
    {
        Graphics g=this.getGraphics();
        paint(g);
    }
    public void init()
    {
        offImg=createImage(size().width, size().height);
        offGr=offImg.getGraphics();

        magnif=1.4; //<-0.7
    }
    public boolean keyDown(Event e, int key)
    {
//        fcontroller.keyDown(e,key);
        //repaint();
        return true;
    }
    public boolean mouseUp(Event e, int x, int y)
    {
 //       fcontroller.mouseUp(e, x, y);
        //repaint();
        return true;
    }
    public boolean mouseExit(Event e, int x, int y)
    {
//        fcontroller.mouseExit(e);
        //repaint();
        return true;
    }
    public boolean mouseMove(Event e, int x, int y)
    {
 //       fcontroller.mouseMove(e,x,y);
        //repaint();
        return true;
    }
    public boolean mouseEnter(Event e, int x, int y)
    {
 //       fcontroller.mouseEnter(e,x,y);
 //       repaint();
        return true;
    }
    public boolean mouseDrag(Event e, int x, int y)
    {
 //       fcontroller.mouseDrag(e,x,y);
  //      repaint();
        return true;
    }
    public boolean mouseDown(Event e, int xp, int yp)
    {
//        fcontroller.mouseDown(e,x,y);
    	int x=logicalX(xp);
    	int y=logicalY(yp);
        boolean b;
        if(ms!=null) b=ms.isSelected(x,y);
//        repaint();
        return true;
    }
    public void paint(Graphics g)
    {
        offGr.setColor(getBackground());
        offGr.fillRect(0,0,size().width,size().height);
        offGr.setColor(getForeground());
        if(ms!=null) ms.draw(offGr);
        if(fs!=null)
        fs.draw(offGr);
        if(ftemp!=null) ftemp.draw(offGr);
        g.drawImage(offImg,0,0,this);
    }
    public AssembleMachines ms;
}


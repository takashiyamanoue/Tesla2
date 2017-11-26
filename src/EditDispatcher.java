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
import java.net.*;
class EditDispatcher extends java.lang.Object
{
    public void Edit(String ef)
    {
        changeMode(ef);
        if(ef.equals("RotateEW")){
            if(fc.ftemp!=null){
                fc.ftemp.rotateEW();
                fc.repaint();
            }
            return;
        }
        if(ef.equals("RotateNS")){
            if(fc.ftemp!=null){
                fc.ftemp.rotateNS();
                fc.repaint();
            }
            return;
        }
    }
//    public InputQueue inQ;
    
    public void keyDown(Event e, int key)
    {
        if(state.equals("New")){
            return;
            }
        return;

    }
    public void select()
    {
        changeMode("Select");
        gui.editFunField.setText("Select");
        gui.figNamField.setText("");
    }
    public TESLA2 gui;
    public void rmBlock()
    {
           gui.editFunField.setText("Cut");
            changeMode("Cut");
            fc.ftemp=null;
             fc.repaint();
             select();
    }
    public ABlock ftemp;
    public void newBlock(String ft)
    {
        gui.editFunField.setText("New");
        gui.figNamField.setText(ft);
        changeMode("New");

        mes.appendText("new block = "+ft+"\n");
        if(fc.ftemp!=null) {
            fc.ftemp.setSelected(false);
            fc.fs.add(fc.ftemp);
            fc.ftemp=null;
            }
        if(ft.equals("Block1")){
            mes.appendText("edispatch, Block1 is selected\n");
            newblock=new ABlock1(fc);
            fc.ftemp=(ABlock)newblock;
            fc.ftemp.state="Moving";
            fc.ftemp.setSelected(true);
            fc.ftemp.showhide=true;
            return;}

     }
    public ABlock newblock;
    public Blocks fs;
    public void changeMode(String mode)
    {
        state=mode;
        gui.editFunField.setText(mode);
        mes.appendText("Edit mode to "+state+"\n");
    }
    public EditDispatcher()
    {
    }
    public TextArea mes;
    public FigCanvas fc;
    public EditDispatcher(FigCanvas f, TextArea m, TESLA2 np)
    {
        fc=f; mes=m;
        mes.appendText("EditDispatcher starting\n");
        state="New";
        f.fs=new Blocks(f);
        fs=f.fs;
        f.ftemp=null;
        f.editdispatch=this;
        ftemp=null;
        gui=np;
    }
   /*
     state= : New
            : Select
            : Cut
            : Done
   */
    public String state;
    public void mouseUp(Event e, int x, int y)
    {
 //       mes.appendText("edispatch mouseUp("+x+","+y+")\n");
         if(state.equals("New")){
            return;
            }
        if(state.equals("Select")){        }
        return;
  }
    public void mouseMove(Event e, int x, int y)
    {
        // mes.appendText("edispatch mouseDown("+x+","+y+")\n");
        if(state.equals("New")){
            if(fc.ftemp==null) newBlock("Block1");
            fc.ftemp.showhide=true;
            fc.ftemp.move(x,y);
            return;
            }
        if(state.equals("Select")){
            return;
        }
        return;
    }
    public void mouseExit(Event e)
    {
 //       mes.appendText("edispatch mouseExit\n");
         if(state.equals("New")){
              if(fc.ftemp!=null) fc.ftemp.showhide=false;
            return;
            }
        if(state.equals("Select")){
            return;
        }
        return;   }
    public void mouseEnter(Event e, int x, int y)
    {
         mes.appendText("edispatch mouseEnter("+x+","+y+")\n");
         if(state.equals("New")){
            if(fc.ftemp!=null) fc.ftemp.showhide=true;
            return;
            }
        if(state.equals("Select")){
            return;
        }
        return;   }
    public void mouseDrag(Event e, int x, int y)
    {

        // mes.appendText("edispatch mouseDrag("+x+","+y+")\n");
        if(state.equals("New")){
            return;
            }
        if(state.equals("Select")){
            return;
        }
        return;
        }
    public void mouseDown(Event e, int xp, int yp)
    {
    	int x=fc.logicalX(xp);
    	int y=fc.logicalY(yp);
//    	int x=xp;
//    	int y=yp;
//        mes.appendText("edispatch mouseDown("+x+","+y+")\n");
        if(state.equals("New")){
            if(fc.ftemp!=null){
               fc.ftemp.alligin2Grid(x,y);
               fc.ftemp.setSelected(false);
               fc.fs.add(fc.ftemp);
               fc.ftemp=null;
                newBlock(gui.figNamField.getText());
            }
            fc.repaint();
            return;
            }
        if(state.equals("Select")){
            ABlock f;
            f=fs.select(x,y);
            if(f!=null){
                if(fc.ftemp!=null) {
                    ABlock w=f;
                    fc.fs.remove(f);
                    fc.ftemp.setSelected(false);
                    fc.fs.add(fc.ftemp);
                    fc.ftemp=w;
                    fc.ftemp.setSelected(true);
                }
                else
                {
                    fc.ftemp=f;
                    fc.ftemp.setSelected(true);
                    fs.remove(f);
                }
            }
            else
            {
                fc.ftemp.setSelected(false);
                fs.add(fc.ftemp);
                fc.ftemp=null;
            }
            return;
        }
        if(state.equals("RotateNS")){
            if(fc.ftemp!=null){
              if(!fc.ftemp.isSelected(x,y)){
                fs.add(fc.ftemp);
                fc.ftemp=fs.select(x,y);
                changeMode("Select");
                fc.repaint();
                return;
              }
              fc.ftemp.rotateNS();
              fs.add(fc.ftemp);
              fc.ftemp=null;
            }
            changeMode("Select");
            fc.repaint();
            return;
        }
        if(state.equals("RotateEW")){
            if(fc.ftemp!=null){
              if(!fc.ftemp.isSelected(x,y)){
                fs.add(fc.ftemp);
                fc.ftemp=fs.select(x,y);
                changeMode("Select");
                fc.repaint();
                return;
              }
              fc.ftemp.rotateEW();
              fs.add(fc.ftemp);
              fc.ftemp=null;
            }
            changeMode("Select");
            fc.repaint();
            return;
        }
        return;
    }
}


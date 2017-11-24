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
class Blocks extends ABlock
{
    public ABlock sensing(ABlock bx)
    {
        BlockList p=bp;
        while(p!=null){
            ABlock bb=p.block;
            if(bb!=bx){
               if(bb.sensing(this)>=0) return bb;
            }
            p=p.next;
        }
        return null;
    }
    /*
    public ABlock blockOnThisPoint(int x, int y, int z)
    {
        BlockList p=bp;
        ABlock b;
        while(p!=null){
             b=p.block;
             int f=b.isOnThisFace(x,y,z);
             if(f>0) return b;
             p=p.next;
        }
        return null;
    }
    */
    /*
    public boolean save(DataOutputStream outS)
    {
       BlockList p;
       p=bp;
       if (!strmWrite(outS,"figures(\n")) return false;
       while(p!=null){
        if(p.block.save(outS)) p=p.next;
        else return false;
       }
       if(!strmWrite(outS,")\n")) return false;
       return true;
    }
    */
    public void draw(Graphics g)
    {
        BlockList p;
        p=bp;
        while(p!=null){
            p.block.draw(g);
            p=p.next;
        }
    }
    public void drawTemp(Graphics g)
    {
        BlockList p;
        p=bp;
        while(p!=null){
            p.block.drawTemp(g);
            p=p.next;
        }
    }
    public void remove(ABlock f)
    {
        BlockList p1,p2;
        p1=bp;
        if(p1.block==f) {bp=p1.next; return;}
        p2=bp; p1=p1.next;
        while(p1!=null){
            if(p1.block==f){p2.next=p1.next; return;}
            p1=p1.next; p2=p2.next;
        }
    }
    public ABlock select(int x, int y)
    {
        BlockList p;
        ABlock fs;
        p=bp;
        fs=null;
        while(p!=null) {
            if(p.block==null) writeMessage("Blocks.select, p.fig is null\n");
            if(p.block.isSelected(x,y)&&(fs==null)){
                p.block.selected=true;
                fs=p.block;}
            else p.block.selected=false;
            p=p.next;
        }
        return fs;
    }
    public void add(ABlock f)
    {
        BlockList p;
        p=new BlockList();
        p.block=f; p.next=bp; bp=p;
    }
    public Blocks(FigCanvas f)
    {
        bp=null;
        canvas=f;
    }
    public BlockList bp   ;
    public void push(ABlock bb, double x, double y, double dx, double dy)
    {
        BlockList p=bp;
        ABlock b;
        while(p!=null){
             b=p.block;
             b.push(this,bb,x,y,dx,dy);
             p=p.next;
        }
    }
}


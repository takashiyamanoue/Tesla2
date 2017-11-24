import java.awt.*;
import java.util.*;
class FactoryController extends java.lang.Object implements Runnable
{
    public void setUp(int up,ABlock bx)
    {
        int state=0;
         VirticalTurnTable vtb=
          (VirticalTurnTable)machines.get("vtb");
        while(state<4){
             if(bx.faces[0].faceID==up) return;
             vtb.button.onoff=true;
             while(vtb.button.onoff==true) {
                doTick();
                try{ myThread.sleep(300);}
                catch(InterruptedException e){}
             }
             bx.rotateNS();
             state++;
        }
    }
    public void setEast(int east,ABlock bx)
    {
        if(setE1(east,bx)) return;

        PushBar p2=(PushBar)machines.get("pb2");
        p2.button.onoff=true;
        while(!p2.isExpanded) doTick();
        p2.button.onoff=false;

        VirticalTurnTable vtb=
          (VirticalTurnTable)machines.get("vtb");
        vtb.button.onoff=true;
        while(vtb.button.onoff) doTick();
        bx.rotateNS();

        PushBar p5=(PushBar)machines.get("pb5");
        p5.button.onoff=true;
        while(!p5.isExpanded) doTick();
        p5.button.onoff=false;

        boolean t=setE1(east,bx);

    }
    public boolean setE1(int east,ABlock bx)
    {
        int state=0;
        if(bx==null) bx=new ABlock(fc);
        HorizontalTurnTable htb=
          (HorizontalTurnTable)machines.get("htb");
        while(state<4){
             if(bx.faces[1].faceID==east) return true;
             htb.button.onoff=true;
             while(htb.button.onoff==true) {
                doTick();
                try{ myThread.sleep(300); }
                catch(InterruptedException e){}
             }
             bx.rotateHlz();
             state++;
        }
        return false;
    }
    public void start()
    {
        if(myThread == null){
            myThread = new Thread(this);
            myThread.start();
        }
    }
    public void pushBlockY(int iy)
    {
        PushBar p1=(PushBar)machines.get("pb6");
        PushBar p2=(PushBar)machines.get("pb7");
        PushBar p3=(PushBar)machines.get("pb8");
        int len=60+iy*20;
        p1.extlength=len;
        p2.extlength=len;
        p3.extlength=len;
        p1.button.onoff=true;
        p2.button.onoff=true;
        p3.button.onoff=true;
        while(!(p1.isExpanded &&
                p2.isExpanded &&
                p3.isExpanded  ) ) doTick();
        p1.button.onoff=false;
        p2.button.onoff=false;
        p3.button.onoff=false;
    }
    public void pushBlockX(int ix)
    {
        PushBar p1=(PushBar)machines.get("pb3");
        p1.extlength=102+ix*20;
        p1.button.onoff=true;
        while(!p1.isExpanded) doTick();
        p1.button.onoff=false;
    }
    public void doTick()
    {
        fc.ms.advance();
        fc.repaint();
        try{ Thread.sleep(delay); }
        catch(InterruptedException e) {}

    }
    public void setNewBlock(int uf, int ef)
    {
        Hopper h=(Hopper)machines.get("h1");
        h.button.onoff=true;
        while(h.button.onoff==true){
            doTick();
        }

        PushBar p1=(PushBar)machines.get("pb1");
        p1.button.onoff=true;
        while(!p1.isExpanded) doTick();
        p1.button.onoff=false;


        ABlock bx= new ABlock(fc);
        setEast(ef,bx);

        PushBar p2=(PushBar)machines.get("pb2");
        p2.button.onoff=true;
        while(!p2.isExpanded) doTick();
        p2.button.onoff=false;

        setUp(uf,bx);

        PushBar p4=(PushBar)machines.get("pb4");
        p4.button.onoff=true;
        while(!p4.isExpanded) doTick();
        p4.button.onoff=false;
    }
    public Hashtable machines;
    public Factory fc;
    public void stop()
    {
        if(myThread !=null){
            myThread.stop();
            myThread = null;
        }
    }
    private Thread myThread;
    public int delay;
    public void run()
    {
        while(myThread!=null)
        {
            doTick();
            /*
            fc.repaint();
            try{ Thread.sleep(delay); }
            catch(InterruptedException e) {this.stop();}
            fc.ms.advance();
            */
        }
    }
    public int x;
    public FactoryController(Factory factory, TESLA2 t)
    {
        machines=new Hashtable();
        fc=factory;
      //  gui=t;
      //  mes=gui.textArea1;
        fc.fs=new Blocks(factory);
        fc.ftemp=null;
 //       fc.editdispatch=this;
//        ftemp=null;
        init();
        delay=10;
/*
        myThread = new Thread(this);
        myThread.start();
        */
        myThread =null;
        start();

    }
    public void init()
    {
        PushBar mm;
        fc.ms=new AssembleMachines();
        Hopper h1=new Hopper(fc, 40,70,0.0);
        fc.ms.add(h1);
        machines.put("h1",h1);
        Hopper h2=new Hopper(fc, 40,100,0.0);
        fc.ms.add(h2);
        machines.put("h2",h2);
        Hopper h3=new Hopper(fc, 40,130,0.0);
        fc.ms.add(h3);
        machines.put("h3",h3);
        PushBar pb1=new PushBar(fc,           60,  40, 20, -3.141592/2);
        pb1.extlength=115;
        fc.ms.add(pb1);
        machines.put("pb1",pb1);
        PushBar pb2=new PushBar(fc,           40, 165, 20, 0.0);
        pb2.extlength=42;
        fc.ms.add(pb2);
        machines.put("pb2",pb2);
        PushBar pb3=new PushBar(fc,           70, 208, 20, 0.0);
        pb3.extlength=100;
        fc.ms.add(pb3);
        machines.put("pb3",pb3);
 //       BeltConveyer bc=new BeltConveyer(fc,  90, 150, 60, -3.141592/2.0);
 //       fc.ms.add(bc);
        PushBar pb4=new PushBar(fc,           92, 145, 20, -3.141592/2.0);
        pb4.extlength=52;
        fc.ms.add(pb4);
        machines.put("pb4",pb4);
        HorizontalTurnTable htb=new HorizontalTurnTable(fc, 60,165);
        fc.ms.add(htb);
        machines.put("htb",htb);
        VirticalTurnTable vtb=  new VirticalTurnTable(fc,   92,165);
        fc.ms.add(vtb);
        machines.put("vtb",vtb);
        PushBar pb5=new PushBar(fc,           115,165, 20, -3.141592);
        pb5.extlength=42;
        fc.ms.add(pb5);
        machines.put("pb5",pb5);
        PushBar pb6=new PushBar(fc,          182, 182, 20, -3.141592/2.0);
        pb6.extlength=45;
        fc.ms.add(pb6);
        machines.put("pb6",pb6);
        PushBar pb7=new PushBar(fc,          202, 182, 20, -3.141592/2.0);
        pb7.extlength=45;
        fc.ms.add(pb7);
        machines.put("pb7",pb7);
        PushBar pb8=new PushBar(fc,          222, 182, 20, -3.141592/2.0);
        pb8.extlength=45;
        fc.ms.add(pb8);
        machines.put("pb8",pb8);
        fc.repaint();
        x=0;
    }
}


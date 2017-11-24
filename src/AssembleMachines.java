import java.awt.* ;
class AssembleMachines extends AssembleMachine
{
    public void advance()
    {
        MachineList p;
        p=mlist;
        while(p!=null){
            p.m.advance();
            p=p.next;
        }
    }
    public boolean isSelected(int x, int y)
    {
        MachineList p;
        p=mlist;
        while(p!=null){
            if(p.m.isSelected(x,y)) return true;
            p=p.next;
        }
        return false;
    }
    public AssembleMachines()
    {
        mlist=null;
    }
    public void draw(Graphics g)
    {
        MachineList p;
        p=mlist;
        while(p!=null){
            p.m.draw(g);
            p=p.next;
        }
    }
    public void add(AssembleMachine m)
    {
        MachineList p=new MachineList();
        p.m=m;
        p.next=mlist;
        mlist=p;
    }
    public MachineList mlist;
}


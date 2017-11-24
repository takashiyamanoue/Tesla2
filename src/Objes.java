class Objes extends java.lang.Object
{
    public void init()
    {
        olist=null;
    }
    public Object select1(Object x)
    {
        ObjList p=olist;
        while(p!=null){
            if(p.obj==x) return p.obj;
            p=p.next;
        }
        return null;
    }
    public boolean remove(Object x)
    {
        ObjList p1,p2;
        p1=olist;
        if(p1.obj==x) {olist=p1.next; return true;}
        p2=olist; p1=p1.next;
        while(p1!=null){
            if(p1.obj==x){p2.next=p1.next; return true;}
            p1=p1.next; p2=p2.next;
        }
        return false;
    }
    public Objes()
    {
        init();
    }
    public ObjList olist;
    public void add(Object x)
    {
        ObjList p=new ObjList();
        p.obj=x;
        p.next=olist;
        olist=p;
    }
}


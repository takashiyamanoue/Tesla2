class PhotoTr extends LED
{
    public boolean isSensingLight(Blocks bs)
    {
        BlockList p=bs.bp;
        lv=localV();
        while(p!=null){
            if(p.block!=face.block){
                int i,j;
                for(i=0;i<6;i++){
                    for(j=0;j<4;j++){
                        LED l= p.block.faces[i].leds[j];
                        if(l.lightOn){
                            Vector3D vl=l.position();
                            Vector3D vp=this.position();
                            double d=(vl.subtract(vp)).length();
                            if(d<2.0) return true;
                        }
                    }
                }
            }
            p=p.next;
        }
        return false;
    }
    public PhotoTr(AFaceState f, int rr, int d)
    {
        face=f;
        r=rr;
        direction=d;
    //    lv=localV();
    }
}


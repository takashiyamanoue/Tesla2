class AnalizedInfos extends Objes
{
    public FactoryController factoryCntlr;
    public void reConstruct3()
    {
       int i,j;
       factoryCntlr.stop();
       for(j=0;j<numY;j++){
          int jw=numY-j-1;
          for(i=0;i<numX;i++){
            int iw=numX-i-1;
            if(blocksInArray[jw][iw]!=null){
                int upSide=blocksInArray[jw][iw].faces[0].faceID;
                int eastSide=blocksInArray[jw][iw].faces[1].faceID;
                factoryCntlr.setNewBlock(upSide,eastSide);
                factoryCntlr.pushBlockX(iw);
            }
          }
          factoryCntlr.pushBlockY(jw);
       }
       factoryCntlr.start();
    }
    public void reConstruct2()
    {
        numX=0; numY=0; offXmax=offXmin=0; offYmax=offYmin=0;
        if(bl.bp==null) return;
        ABlock blk0=bl.bp.block;
        offXmax=offXmin=blk0.offX;
        offYmax=offYmin=blk0.offY;
        BlockList p=bl.bp;
        p=p.next;
        while(p!=null){
            ABlock blk1=p.block;
            if(offXmax<blk1.offX) offXmax=blk1.offX;
            if(offXmin>blk1.offX) offXmin=blk1.offX;
            if(offYmax<blk1.offY) offYmax=blk1.offY;
            if(offYmin>blk1.offY) offYmin=blk1.offY;
            p=p.next;
        }
        int w2=blk0.width/2;
        numX=(int)((offXmax+w2-(offXmin-w2))/(blk0.width));
        numY=(int)((offYmax+w2-(offYmin-w2))/(blk0.width));
        blocksInArray=new ABlock[numY][numX];
        for(int i=0;i<numX;i++) for(int j=0;j<numY;j++)
            blocksInArray[j][i]=null;
        p=bl.bp;
        while(p!=null){
            ABlock blk1=p.block;
            int ix=(int)((blk1.offX-offXmin)/(blk0.width));
            int iy=(int)((blk1.offY-offYmin)/(blk0.width));
            blocksInArray[iy][ix]=blk1;
            p=p.next;
        }

    }
    public ABlock blocksInArray[][];
    public int numY; /* blocks number in Y direction */
    public int numX; /* blocks number in X direction */
    public double offYmax;
    public double offYmin;
    public double offXmax;
    public double offXmin;
    public Blocks bl;
    public void connect(Blocks bs,ABlock b1, int i,ABlock b2)
    {
         int x1=(int)b1.offX;
         int y1=(int)b1.offY;
         int z1=(int)b1.offZ;
         int x2=x1;
         int y2=y1;
         int z2=z1;
 //        int cd=b1.currentDirection(i);
         int cd=i;
         if(cd==0){
            z2=z1+b1.width;
         } else
         if(cd==1){
            x2=x1+b1.width;
         } else
         if(cd==2){
            y2=y1+b1.width;
         } else
         if(cd==3){
            x2=x1-b1.width;
         } else
         if(cd==4){
            y2=y1-b1.width;
         } else
         if(cd==5){
            z2=z1-b1.width;
         }
         b2.offX=x2; b2.offY=y2; b2.offZ=z2;
         for(BlockRotator br=new BlockRotator(b2);!(br.end());br.next()){
            if(b1.faces[i].nextdoorFace==
                b2.faces[b2.oppositeFace(i)].faceID) break;
         }
         bs.add(b2);
         int pd=b1.faces[i].phaseDifference;
         b1.faces[i].leds[pd].lightOn=true;
         if(i==0||i==5){
            for(BlockRotator br=new BlockRotator(b2);
                   !(br.endHlz()); br.nextHlz())
                if(b2.faces[b2.oppositeFace(i)].ptr.isSensingLight(bs))
                    break;
         }
         if(i==1||i==3){
            for(BlockRotator br=new BlockRotator(b2);
                   !(br.endNS()); br.nextNS())
                if(b2.faces[b2.oppositeFace(i)].ptr.isSensingLight(bs))
                    break;
         }
         if(i==2||i==4){
            for(BlockRotator br=new BlockRotator(b2);
                   !(br.endEW()); br.nextEW())
                if(b2.faces[b2.oppositeFace(i)].ptr.isSensingLight(bs))
                    break;
         }
         b1.faces[i].leds[pd].lightOn=false;
    }
    public int adjoint(ABlock b1, ABlock b2)
    {
        int i;
        String nID=null;
        for(i=0;i<6;i++){
           nID=b1.faces[i].nextdoorIDs;
           if(nID!=null)
           if(nID.equals(b2.sID)) return i;
        }
        return -1;
    }
    public boolean addifPossible(Blocks bs, ABlock b)
    {
        int i;
       if(bs.bp==null){
           b.offX=200;
           b.offY=100;
           b.offZ=0;
           bs.add(b);
           return true;
       }
       BlockList p=bs.bp;
       while(p!=null){
          i=adjoint(p.block,b);
          if(i>=0){
            connect(bs,p.block,i,b);
            return true;
          }
          p=p.next;
       }
       return false;
    }
    public void reConstruct1(FigCanvas canvas)
    {
        ObjList p=olist;
        bl=new Blocks(canvas);
        Blocks bx=new Blocks(canvas);
        ABlock b=new ABlock(canvas);
        for(BlockRotator br=new BlockRotator(b);!(br.end());br.next())
          if(canvas.fs.bp.block.faces[0].faceID==
              b.faces[0].faceID) break;
        while(p!=null){
            String bid=((AnalizedInfo)(p.obj)).blockID;
            b.setinfo((AnalizedInfo)p.obj); b.showhide=true;
            p=p.next;
            while(p!=null&&bid.equals(((AnalizedInfo)(p.obj)).blockID)){
                b.setinfo((AnalizedInfo)p.obj); b.showhide=true;
                p=p.next;
            }
            if(!addifPossible(bl,b)) {
                bx.add(b);
            }
            b=new ABlock(canvas);
        }

        /*    */
        int max=100;
        int i=0;
        while(bx.bp!=null){
        BlockList bxx=bx.bp;
        while(bxx!=null){
            if(addifPossible(bl,bxx.block)) bx.remove(bxx.block);
            bxx=bxx.next;
        }
        i++;
        if(i>max) return;
        }
        reConstruct2();
        reConstruct3();
    }
    public AnalizedInfos()
    {
        super.init();
    }
}


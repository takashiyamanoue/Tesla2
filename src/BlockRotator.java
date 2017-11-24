class BlockRotator extends AssembleMachine
{
    public boolean endNS()
    {
        if(state>3) return true; else return false;
    }
    public void nextNS()
    {
        block.rotateNS();
        state++;
    }
    public boolean endEW()
    {
        if(state>3) return true; else return false;
    }
    public void nextEW()
    {
        block.rotateEW();
        state++;
    }
    public boolean endHlz()
    {
        if(state>3) return true; else return false;
    }
    public void nextHlz()
    {
        block.rotateHlz();
        state++;
    }
    public void init()
    {
        state=0;
    }
    public boolean end()
    {
        if(state>15) return true;
        return false;
    }
    public void next()
    {
        if(state==0) {
             block.rotateNS();
             state++;
        }
        else
        if(state==1) {
            block.rotateNS();
            state++;
        }
        else
        if(state==2) {
            block.rotateNS();
            state++;
        }
        else
        if(state==3) {
            block.rotateNS();
            state++;
        }
        else
        if(state==4) {
            block.rotateEW();
            state++;
        }
        else
        if(state==5) {
            block.rotateEW();
            state++;
        }
        else
        if(state==6) {
            block.rotateEW();
            state++;
        }
        else
        if(state==7) {
            block.rotateEW();
            state++;
        }
        else
        if(state==8) {
            block.rotateHlz();
            state++;
        }
        else
        if(state==9) {
            block.rotateHlz();
            state++;
        }
        else
        if(state==10) {
            block.rotateHlz();
            state++;
        }
        if(state==11) {
            block.rotateHlz();
            state++;
        }
        if(state==12) {
            block.rotateEW();
            state++;
        }
        if(state==13){
            block.rotateHlz();
            state++;
        }
        if(state==14){
            block.rotateHlz();
            state++;
        }
        if(state==15){
            block.rotateHlz();
            state++;
        }
    }
    public int state;
    public ABlock block;
    public BlockRotator(ABlock b)
    {
        block=b;
        init();
    }
}


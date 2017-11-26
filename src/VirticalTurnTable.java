import java.awt.*;
class VirticalTurnTable extends TurnTable
{
    public void advance()
    {
         if(button.isOn()){
            ABlock ab=ablockOn(offX,offY,0);
            if(ab!=null) {
                ab.rotateNS();
                ab.setSelected(false);
            }
            button.setOnOff(false);
        }
    }
    public VirticalTurnTable(FigCanvas c, int x, int y)
    {
        canvas=c;
        offX=x;
        offY=y;
        init(c);
        radius=12;
        init(c);
    }
        public boolean isSelected(int x, int y)
    {
        if(ttable.isSelected(x,y))
        {
            ABlock ab=ablockOn(offX,offY,0);
            if(ab!=null){
                ab.rotateNS();
                ab.setSelected(false);
            }
            button.setOnOff(false);
            return true;
        }
        else
        {
            return false;
        }
    }
    public void draw(Graphics g)
    {
        draw(g,"V");
    }
}


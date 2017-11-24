import java.awt.*;
class ParseFig extends java.lang.Object
{
    /*
    public TextArea message;
    public boolean parseText()
    {

        while(iq.rString(" "));
        if(!iq.rString("text(")) return false;
        Text fig=new Text(canvas);
        if(!rNumPair()) return false;
        int offX=x1; int offY=y1;
        fig.offX=x1; fig.offY=y1;

        if(!iq.rString(",")) return false;
        StringBuffer str=iq.rStrConst();
        if(str==null) return false;

        fig.text=str;

        while(iq.rString(" "));
        if(!iq.rString(")")) return false;

        fig.font=canvas.getFont();
        fig.fmetrics=canvas.getFontMetrics(fig.font);

        afig=fig;
        return true;
    }
    public boolean parseFree()
    {
        while(iq.rString(" "));
        if(!iq.rString("free(")) return false;
        if(!rNumPair()) return false;
        int offX=x1; int offY=y1;
        AFreeHandCurve fig=new AFreeHandCurve(canvas);
        fig.offX=x1; fig.offY=y1;
        while(rNumPair()){
            fig.addPoint(x1-offX,y1-offY);
        }
        while(iq.rString(" "));
        if(!iq.rString(")")) return false;
        afig=fig;
        return true;
    }
    public boolean parseAnOval()
    {
        while(iq.rString(" "));
        if(!iq.rString("oval(")) return false;
        AnOval fig=new AnOval(canvas);
        if(!rNumPair()) return false;
        int offX=x1; int offY=y1;
        if(!iq.rString(",")) return false;
        if(!rNumPair()) return false;
        int x2=x1-offX; int y2=y1-offY;
        fig.offX=offX; fig.offY=offY;
        fig.x2=y2;     fig.y2=y2;

        while(iq.rString(" "));
        if(!iq.rString(")")) return false;
        afig=fig;
        return true;
    }
    public boolean parseARectangle()
    {
        while(iq.rString(" "));
        if(!iq.rString("rectangle(")) return false;
        ARectangle fig=new ARectangle(canvas);
        if(!rNumPair()) return false;
        int offX=x1; int offY=y1;
        if(!iq.rString(",")) return false;
        if(!rNumPair()) return false;
        int x2=x1; int y2=y1;
        fig.offX=offX; fig.offY=offY;
        fig.x2=x2;     fig.y2=y2;

        while(iq.rString(" "));
        if(!iq.rString(")")) return false;
        afig=fig;
        return true;
    }
    public boolean parseLines()
    {
        int offX,offY;
        while(iq.rString(" "));
        if(!iq.rString("lines(")) return false;

        message.appendText("parsing lines(");

        if(!rNumPair()) return false;
        offX=x1; offY=y1;
        ALines fig=new ALines(canvas);
        fig.offX=x1; fig.offY=y1;

        message.appendText(""+offX+","+offY+"...\n");

        while(rNumPair()){
            fig.addPoint(x1-offX,y1-offY);
//            message.appendText(" "+x1+","+y1+"\n");
        }
        while(iq.rString(" "));
        message.appendText(")\n");
        if(!iq.rString(")")) return false;
        afig=fig;
        return true;
    }
    public AFigure afig;
    public FigCanvas canvas;
    public boolean parseALine()
    {

        while(iq.rString(" "));
        if(!iq.rString("line(")) return false;

        message.appendText("parsing line(");

        if(!rNumPair()) return false;
        int offX=x1; int offY=y1;
        while(iq.rString(" "));
        if(!iq.rString(",")) return false;
        if(!rNumPair()) return false;
        int x2=x1-offX; int y2=y1-offY;
        ALine fig=new ALine(canvas);
        fig.offX=offX; fig.offY=offY;
        fig.x2=x2;     fig.y2=y2;

        while(iq.rString(" "));
        if(!iq.rString(")")) return false;
        afig=fig;

        message.appendText(""+offX+","+offY+","+x2+","+y2+")\n");

        return true;
    }
    public boolean parseFig()
    {
        if( parseALine()     ||
            parseLines()     ||
            parseARectangle()||
            parseAnOval()    ||
            parseFree()      ||
            parseText()      ||
            parseFigures())  return true;
        return false;
    }
    public boolean rNumPair()
    {
        while(iq.rString(" "));
        Integer x=iq.rInteger();
        if(x==null) return false;
        x1=x.intValue();
        while(iq.rString(" "));
        if(!iq.rString(",")) return false;
        while(iq.rString(" "));
        Integer y=iq.rInteger();
        if(y==null) return false;
        y1=y.intValue();
        return true;
    }
    public int y1;
    public int  x1;
    public boolean parseFigures()
    {
         Figures f=new Figures(canvas);
         while(iq.rString(" "));
         if(!iq.rString("figures(")) return false;
         int x=1;
         while(parseFig())
               {f.add(afig);};
         while(iq.rString(" "));
         if(!iq.rString(")")) return false;
         figs=f;
         return true;
    }
    public ParseFig(InputQueue q, FigCanvas c, TextArea m)
    {
        iq=q;
        canvas=c;
        message=m;
    }
    public Figures figs;
    public InputQueue iq;
    */
}


/*
class OutputURL extends java.net.URLConnection
{
    public OutputURL(String urlName)
    {
        DataOutputStream outputStream;
        try{
           URL url=new URL(npMain.urlAddress.getText());
           URLConnection urlc=new URLConnection(url);
        }
        catch(MalformedURLException e)
          {mes.appendText("save:MalformedURL Exception \n");}
        try{
           outputStream=new DataOutputStream(urlc.getOutputStream());
        }
        catch(UnknownServiceException e)
         { mes.appendText("UnknownService Exception while new output Stream\n");}
        mes.appendText("saving, "+npMain.urlAddress.getText()+"\n");
        if(!(fc.fs.save(outputStream))){
            mes.appendText("error while saving\n");}
        try{
          outputStream.flush();
        }
        catch(IOException e) { mes.appendText("save:IOExceptin while flushing.\n");}
        try{
          outputStream.close();

        }
        catch(IOException e) { mes.appendText("save:IOException while closing.\n");}
    }
}

*/
import java.net.*;
import java.io.*;
public class client {
    Socket socket;
    BufferedReader bd;
    PrintWriter out;

    public client(){
        try{
            System.out.println("Sending request to server");
            socket = new Socket("Rex",7777);
            System.out.println("Connected to server");

            bd = new BufferedReader(new InputStreamReader(socket.getInputStream()));//taking input from object
            out = new PrintWriter(socket.getOutputStream());//Sending output from object

      startReading();
      startWriting();

        }catch(Exception e){
            
        }
    }
    public void startReading(){
        Runnable r1=()->{
           System.out.println("Reading...");
           try{
              while(true){
               String msgg = bd.readLine();
               if(msgg.equals("exit")){
                   System.out.println("Server terminated the chat");
                   socket.close();
                   break;
                 }
                 System.out.println("Server: "+msgg);
           }
         }catch(Exception f){
            System.out.println(f+"Enter String");
         


        }
   };
   new Thread(r1).start();
}
   public void startWriting(){
      
       Runnable r2=()->{
        System.out.println("Writing...");
        try{
            while(true){
                   BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                   String content = br1.readLine();
                   System.out.println(content);
                   System.out.flush();
                   if(content.equals("exit")){
                    socket.close();
                    break;
                }


               }
           }catch(Exception e){
            e.printStackTrace();
         }

       };
       new Thread(r2).start();


   }
   public static void main(String[] args){
    System.out.println("This is client...");
    new client();
   }


}

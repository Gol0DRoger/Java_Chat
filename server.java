import java.net.*;
import java.io.*;
public class server{

    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter pw;

    public server()
    {
        try{
      server = new ServerSocket(7777);
      System.out.println("Server Started now"+"\n"+"waiting...");
      socket = server.accept();

      br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//taking input from object
      pw = new PrintWriter(socket.getOutputStream());//Sending output from object

      startReading();
      startWriting();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void startReading(){
         Runnable r1=()->{
            System.out.println("Reading...");
            try{
            while(true){
                String msg = br.readLine();
                if(msg.equals("exit")){
                    System.out.println("Client terminated the chat");
                   socket.close();
                    break;
                  }
                  System.out.println("Client: "+msg);
            
          


         }
        }catch(Exception f){
           f.printStackTrace();
      }
    };
    new Thread(r1).start();
}
    public void startWriting(){
        System.out.println("Writing...");
        Runnable r2=()->{
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
    System.out.println("This is server...");
    new server();
}
}

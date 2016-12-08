package jogo.cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;
import java.util.Scanner;

//CLIENTE
public class Requester{
    
    public Requester() throws IOException{
        ServerSocket server;
        Scanner scan;
        
        try{       
            server = new ServerSocket(5000);
            while (true) {
                Socket s = server.accept();
                scan = new Scanner (s.getInputStream());
                System.out.println(scan.nextLine());
            }
        }catch (IOException e){}
    }
    public static void main(String[] args) throws IOException {
        new Requester();
    }
/*
            
    
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
	Requester(){}
	void run()
	{
		try{
			//1. creating a socket to connect to the server
			requestSocket = new Socket("localhost", 2004);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
			do{
				try{
					message = (String)in.readObject();
					System.out.println("server>" + message);
			//		sendMessage("Hi my server"); 
					
			
					System.out.println("Escreva sua mensagem");
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					message = in.readLine();
					sendMessage(message);
				
				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("bye"));
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Requester client = new Requester();
		client.run();
	} */
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.io.Writer;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import jdk.internal.util.xml.impl.Input;
import sun.applet.Main;

/**
 *
 * @author julio
 */
public class ContainerDeJanelas extends JFrame {

    Socket socket;
    PrintWriter escritor;
    JTextField textoParaEnviar;
    String player;
    PrintWriter navinha;
    Nave nave;
    int id;
    JTextArea textoRecebido;
    Nave naveRecebida;
    Scanner leitor;
     private DataInputStream in; //canal de entrada
    private DataOutput out; //canal de saida

    public ContainerDeJanelas(String player, int id) throws IOException {
        super("Player: " + player);
        this.player = player;
        this.id = id;

        JMenuBar barraMenu = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem sobre = new JMenuItem("Sobre");
        sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Jogo desenvolvido por Cassiele Thais, Julio Hippler e Mateus Walbrink, estudantes de Licenciatura em Computação na UFPR", "Informações", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JMenuItem conexao = new JMenuItem("Conexão de Rede");
        conexao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Conexão via Socket, IP 127.0.0.1,PORTA 5000");
            }
        });
        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //CHAT CLIENTEEEEEEEEE 
        textoParaEnviar = new JTextField();
        textoRecebido = new JTextArea();
        //textoRecebido.setFont(fonte);

        JButton botao = new JButton("Enviar");
        botao.addActionListener(new EnviarListener());

        JPanel envio = new JPanel();
        JPanel chat = new JPanel();

        JScrollPane scroll = new JScrollPane(textoRecebido);
        chat.setLayout(new BorderLayout());
        scroll.setPreferredSize(new Dimension(100, 100));

        envio.setLayout(new BorderLayout());
        envio.add(BorderLayout.CENTER, textoParaEnviar);
        envio.add(BorderLayout.EAST, botao);
        chat.add(BorderLayout.SOUTH, scroll);

        getContentPane().add(BorderLayout.CENTER, chat);
        getContentPane().add(BorderLayout.SOUTH, envio);

        configurarRede();

        menu.add(sobre);
        menu.add(new JSeparator());
        menu.add(conexao);
        menu.add(new JSeparator());
        menu.add(sair);
        barraMenu.add(menu);

        setJMenuBar(barraMenu);

        // add(new JButton("teste"));    
        // INTERFACE JOGO
        JPanel janela = new JPanel();
        janela.setLayout(new BorderLayout());
        janela.add(BorderLayout.CENTER, new Fase(id));

        janela.setPreferredSize(new Dimension(300, 400));
        //janela.add(new Fase());   
        getContentPane().add(BorderLayout.NORTH, janela);
        //FIM INTERFACE JOGO

        setTitle(player);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //CHAT
    }

    
    // COMECA A PARTE DO SOCKET 
    private class EscutaServidor implements Runnable {
       
        @Override
        public void run() {
            try { 
                String texto;/*
                while (true) {

                    String s;
                    s = in.readUTF();
                  
                    if(s.equals("baixo")){
                        nave.y +=1;
                    }else if(s.equals("cima")){
                        nave.y -=1;
                    }else if(s.equals("tiro")){
                        
                    }*/
                        
                    while ((texto = leitor.nextLine()) != null) {
                        //naveRecebida.getImagem();
                        textoRecebido.append(texto + "\n");// add no final o novo trexto 
                    }
                
            }catch (Exception x) {}           

        }
    }
      public void initRedes(){
        try{
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            
        }catch(Exception e){}
    }
    private class EnviarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //responsavel por enviar as mensagens para o servidor
            escritor.println(player + " : " + textoParaEnviar.getText());
            escritor.flush();
//            navinha.print(nave.getImagem());
  //          navinha.flush();
            textoParaEnviar.setText("");
            textoParaEnviar.requestFocus();
            
        }

    }
    //SOCKET

    private void configurarRede() throws IOException {
        try {

            socket = new Socket("127.0.0.1", 5000);
            escritor = new PrintWriter(socket.getOutputStream());
            leitor = new Scanner(socket.getInputStream());
            new Thread(new EscutaServidor()).start();
        } catch (Exception e) {

        }
    }
  
    

    public static void main(String[] args) throws IOException {
        new ContainerDeJanelas("Player 1", 0);
        new ContainerDeJanelas("Player 2", 1);

        // new ContainerDeJanelasServer();
    }
    
    
}

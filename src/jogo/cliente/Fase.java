/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.cliente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import jdk.internal.util.xml.impl.Input;

/**
 *
 * @author julio
 */
public class Fase extends JPanel implements ActionListener{
    
    private Image fundo;
    private Nave nave, nave2;    
    private Timer timer;
    private Socket socket;
    private DataInputStream in; //canal de entrada
    private DataOutput out; //canal de saida
    
    private boolean emJogo;
    
    private List<Inimigo> inimigos;
    
    private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
        { 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
        { 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
        { 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
        { 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
        { 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
        { 920, 300 }, { 856, 328 }, { 456, 320 } };
    
    public Fase(int id){
        
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        
        ImageIcon referencia = new ImageIcon("imagens/fundo.png");
        fundo = referencia.getImage();  
     
        //nave = new Nave(0,100,100);
        
        escolhaNave(id);
     
        emJogo=false;
        
        inicializaInimigos();
        
        timer = new Timer(5, this);
        timer.start();
    }
    
    public void initRedes(){
        try{
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            
        }catch(Exception e){}
    }
    

     
    public void escolhaNave(int id){
           if (id == 0){
            nave = new Nave(0,100,90);
            nave2 = new Nave(1, 400,90);
        }else{
            nave = new Nave(1,400,100);
            nave2 = new Nave(0,100,90);
        }
    }
    public void inicializaInimigos(){
        inimigos = new ArrayList<Inimigo>();
        
        for ( int i= 0; i < coordenadas.length; i ++){
            inimigos.add(new Inimigo(coordenadas[i][0], coordenadas [i][1]));
        }
    }   
    // esse método inicia os canais de saida e enetrada e cria uma trhead da classe escuta servidor
   
    @Override
    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        
        if(emJogo){
         
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
            graficos.drawImage(nave2.getImagem(), nave2.getX(), nave2.getY(), this);
           

            List<Missel> misseis = nave.getMisseis();

            for (int i = 0; i < misseis.size(); i++) {
                Missel m = (Missel) misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

            }
           // for (int i = 0; i < inimigos.size(); i++) {
             //   Inimigo in = inimigos.get(i);
             //   graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

          //  }
            graficos.setColor(Color.WHITE);
            graficos.drawString("Inimigos: "+ inimigos.size(), 5,15);
            
            
        }else{
            ImageIcon fimJogo = new ImageIcon("imagens/game_over.jpg");
            graficos.drawImage(fimJogo.getImage(), 0,0, null);
        }
        
        g.dispose();
    }

 
    public void actionPerformed(ActionEvent e) {
        
        if(inimigos.size()==0){
            emJogo = false;
        }
        
        List<Missel> misseis = nave.getMisseis();
        
        
        for (int i = 0; i < misseis.size(); i++) {
            Missel m = (Missel) misseis.get(i);
            
            if(m.isVisivel()){
                m.mexer();
            }else{
                misseis.remove(i);
            }
        }
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo in = inimigos.get(i);
            
            if(in.isVisivel()){
                in.mexer();;
            }else{
                inimigos.remove(i);
            }
        }
        
        nave.mexer();
        
      //  checarColisoes();
        
        repaint(); // redesenhar na tela 
    }

   
    /*
    public void checarColisoes(){
        
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;
        
        for(int i = 0; i< inimigos.size(); i++){
            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();
            
            if(formaNave.intersects(formaInimigo)){
                nave.setVisivel(false);
                tempInimigo.setVisivel(false);
                
                emJogo=false;
            }
        }
        List<Missel> misseis = nave.getMisseis();
        
        for( int i = 0; i < misseis.size(); i++){
            Missel tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();
            
            for(int j =0; j <inimigos.size(); j++){
                Inimigo tempInimigo = inimigos.get(j);
                formaInimigo = tempInimigo.getBounds(); 
                
                if(formaMissel.intersects(formaInimigo)){
                    tempInimigo.setVisivel(false);
                    tempMissel.setVisivel(false);
                }
            }
        }
        
    }
    
    */
    // pega os eventos do teclado
    private class TecladoAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()== KeyEvent.VK_ENTER){
                emJogo=true;
                //nave= new Nave(0);
                //inicializaInimigos();
                
            }
            try {
                nave.keyPressed(e);
            } catch (IOException ex) {
                Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        @Override
        public void keyReleased(KeyEvent e){
            nave.keyReleased(e);
        }
    }

}
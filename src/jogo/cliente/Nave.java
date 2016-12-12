/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.cliente;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import jdk.internal.util.xml.impl.Input;

/**
 *
 * @author julio
 */
public class Nave {
    
    int x,y,id;
    private int dx,dy;
    private Image imagem;
    private int altura, largura;
    private List<Missel> misseis;
    
    private DataInputStream in;  //canal de entrada
    private DataOutputStream out;
    
    private boolean net_atira=false;
    private boolean net_baixo = false;
    private boolean net_cima = false;
    
    private boolean isVisivel;
    
    public Nave(int id, int x, int y){
        
        ImageIcon referencia = new ImageIcon("imagens/nave.gif");
        imagem = referencia.getImage();
        
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        
        misseis = new ArrayList<Missel>();
        
        this.id=id;
        this.x = x;
        this.y = y;
    }  
    
    public void mexer(){
        x += dx;
        y += dy;
    	if(this.x < 1){
            x = 1;
	}
	if(this.x > 462){
            x = 462;
	}
	if(this.y < 1){
            y = 1;
	}
        if(this.y > 370){
            y = 370;
	}    
    }
    
   
   
    public List<Missel> getMisseis() {
		return misseis;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Image getImagem() {
        return imagem;
    }
    public boolean isVisivel() {
		return isVisivel;
    }
    public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
    }
  
    
	
    public void atira(){
        
        this.misseis.add(new Missel(x+largura, y+altura/2, id));
    }

    public int getId() {
        return id;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    
    // função que vai verificar o adversario e mover a nave 
    
    
    public void keyPressed(KeyEvent tecla) throws IOException{
        
        
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_SPACE){
            atira();
            net_atira = true;
            try{
                out.writeUTF("atira");
            }catch (Exception e){}
        }
        
        if ( codigo == KeyEvent.VK_UP){
            dy = -1;
            net_cima=true;
            try{
                out.writeUTF("cima");
            }catch (Exception e){}
        }
        if (codigo == KeyEvent.VK_DOWN){
            dy = 1;
            net_baixo=true;
            try{
                out.writeUTF("baixo");
            }catch (Exception e){}
        }
        //   if ( codigo == KeyEvent.VK_LEFT){
        //     dx = -1;
       //  }
        // // if (codigo == KeyEvent.VK_RIGHT){
        //     dx = 1;
        // }
    }
    public void keyReleased(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_UP){
            dy = 0;
        }
         if (codigo == KeyEvent.VK_DOWN){
            dy = 0;
        }
       //    if ( codigo == KeyEvent.VK_LEFT){
        //     dx = 0;
       //  }
        // if (codigo == KeyEvent.VK_RIGHT){
       //      dx = 0;
       //  }
    
    }

   public Rectangle getBounds(){
	return new Rectangle(x, y, largura, altura);
    }
	
}

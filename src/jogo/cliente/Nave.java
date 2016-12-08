/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.cliente;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author julio
 */
public class Nave {
    
    private int x,y,id;
    private int dx,dy;
    private Image imagem;
    private int altura, largura;
    private List<Missel> misseis;
    
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
    
    
    
    public void keyPressed(KeyEvent tecla){
        
        
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_SPACE){
            atira();
        }
        
        if ( codigo == KeyEvent.VK_UP){
            dy = -1;
        }
        if (codigo == KeyEvent.VK_DOWN){
            dy = 1;
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

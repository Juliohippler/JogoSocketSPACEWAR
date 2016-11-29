/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.cliente;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author julio
 */
public class Missel {
    
    private Image imagem;
    private int x,y;
    private int largura, altura;
    private boolean isVisivel;
    
    private static final int LARGURA_TELA = 500;
    private static final int VELOCIDADE = 2;
    
    public Missel(int x, int y){
        this.x=x;
        this.y=y;
        
        ImageIcon referencia = new ImageIcon("imagens/missel.png");
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        isVisivel = true;
    }
    public void mexer(){
        this.x += VELOCIDADE;
        if(this.x > LARGURA_TELA){
            isVisivel = false;
        }
        
        
        
    }
    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean isVisivel() {
	return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
	this.isVisivel = isVisivel;
    }

    
    public Rectangle getBounds(){
	return new Rectangle(x, y, largura, altura);
	}
    
}


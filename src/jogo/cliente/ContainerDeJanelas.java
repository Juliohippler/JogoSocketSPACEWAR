/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.cliente;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import sun.applet.Main;

/**
 *
 * @author julio
 */
public class ContainerDeJanelas extends JFrame{
    public ContainerDeJanelas(){
        JMenuBar barraMenu = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem sobre = new JMenuItem("Sobre");
        sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Jogo desenvolvido por Julio Hippler e Mateus Walbrink, estudantes de Licenciatura em Computação na UFPR", "Informações", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JMenuItem sair= new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(sobre);
        menu.add(new JSeparator());
        menu.add(sair);

        barraMenu.add(menu);

        setJMenuBar(barraMenu);
        
       // add(new JButton("teste"));
        add(new Fase());
        setTitle("Space War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(500,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        
    }
    
    public static void main(String[] args){
        new ContainerDeJanelas();
    }
}

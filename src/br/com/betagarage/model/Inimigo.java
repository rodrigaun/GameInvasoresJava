/*PACOTE (DIRETORIO)*/
package br.com.betagarage.model;

/*IMPORTANDO BIBLIOTECAS*/
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Inimigo {

    /*VARIAVEIS GLOBAIS*/
    private Image imgInimigo;
    private int posX = (int) (Math.random()*590);
    private int posY = -50;
    private int contador;
    private Timer timer;
    private int tempoParaCair = (int) (Math.random()*25000);
    private boolean esperando = true;
    
    /*CONSTRUTOR DA CLASSE*/
    public Inimigo() {
        try {
            imgInimigo = ImageIO.read(new File("imagens/inimigo.png"));
            timer = new Timer(this);
            timer.start();
        }catch(Exception e) {
            System.out.println("Imagem Inimigo nao encontrada");
            e.printStackTrace();
        }
    }

    /*ATUALIZA ESTADO DO INIMIGO*/
    public void update() {
        if(!esperando)
            if(contador > 25) {
                posY += 1;
                contador = 0;
            }
        contador ++;
    }

    /*DESENHA NA TELA*/
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(imgInimigo, posX, posY, null);
        g2d.dispose();
    }

    /*GETs*/
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public boolean getEsperando() {
        return esperando;
    }

    /*CLASSE INTERNA TIMER*/
    private class Timer extends Thread {
        private Inimigo inimigo;

        public Timer(Inimigo i) {
            this.inimigo = i;
        }
        public void run() {
            try {
                Thread.sleep(tempoParaCair);
                esperando = false;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
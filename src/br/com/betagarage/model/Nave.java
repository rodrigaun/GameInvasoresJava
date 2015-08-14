/*PACOTE (DIRETORIO)*/
package br.com.betagarage.model;

/*IMPORTANDO BIBLIOTECAS*/
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Nave {

    /*VARIAVEIS GLOBAIS*/
    private Image imgNave;
    private int posX = 300;
    private int posY = 400;

    /*CONSTRUTOR DA CLASSE*/
    public Nave() {
        try {
            imgNave = ImageIO.read(new File("imagens/nave.png"));
        }catch(Exception e) {
            System.out.println("Imagem Nave nao encontrada");
            e.printStackTrace();
        }
    }

    /*ATUALIZA ESTADO DA NAVE*/
    public void update(boolean direcao) {		
        if(direcao) {
            if(posX < 600)
                posX +=20;
        }else {
            if(posX > -10)
                posX -=20;
        }
    }

    /*DESENHA NA TELA*/
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(imgNave, posX, posY, null);
        g2d.dispose();
    }

    /*GETs*/
    public int getPosXNave() {
        return posX + 25;
    }
}
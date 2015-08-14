/*PACOTE (DIRETORIO)*/
package br.com.betagarage.core;

/*IMPORTANDO BIBLIOTECAS*/
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import br.com.betagarage.model.Inimigo;
import br.com.betagarage.model.Missil;
import br.com.betagarage.model.Nave;

public class GameCore extends Component {

    /*VARIAVEIS GLOBAIS*/
    private Nave nave;
    private ArrayList<Inimigo> arrayInimigo;
    private ArrayList<Missil> arrayMissil;
    private long pontos = 0;
    private Font font;
    private int limiteGameOverY = 400;
    private int qtdInimigo = 5;
    private boolean gameOver = false;

    /*CONSTRUTOR DA CLASSE*/
    public GameCore() {
        nave = new Nave();
        arrayInimigo = new ArrayList<Inimigo>();
        arrayMissil = new ArrayList<Missil>();
        font = new Font("Arial", Font.BOLD, 26);
    }

    public synchronized void paint(Graphics g) {

        /*PONTUACAO*/
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(String.valueOf(pontos), 30, 30);

        /*CRIADOR DE INIMIGOS*/
        if(arrayInimigo.size() == 0) {
            qtdInimigo += 5;
            for (int i = 0; i < qtdInimigo; i++) {
                arrayInimigo.add(new Inimigo());
            }
        }

        if(gameOver) {
            /*GAME OVER*/
            g.drawString("G A M E   O V E R", 200, 200);
            g.drawString("press enter", 230, 260);

        }else {

            /*ATUALIZA ESTADO DO INIMIGO E MISSIL*/
            for (int i = 0; i < arrayInimigo.size(); i++) {
                arrayInimigo.get(i).update();
            }
            for (int i = 0; i < arrayMissil.size(); i++) {
                arrayMissil.get(i).update();
            }

            /*VERIFICA COLIZAO*/
            for (int m = 0; m < arrayMissil.size(); m++) {
                for (int i = 0; i < arrayInimigo.size(); i++) {

                    //Se estiver na direcao do inimigo
                    if(arrayMissil.get(m).getPosX() > arrayInimigo.get(i).getPosX() && 
                            arrayMissil.get(m).getPosX() < arrayInimigo.get(i).getPosX()+50) {

                        //Se atingir o inimigo
                        if(arrayMissil.get(m).getPosY() < arrayInimigo.get(i).getPosY()+50) {
                            //COLIDIU COM INIMIGO
                            if(!arrayInimigo.get(i).getEsperando()) {
                                arrayInimigo.remove(i);
                                arrayMissil.remove(m);
                                pontos += 100;
                                break;
                            }
                        }
                    }
                }
            }

            /*VERIFICA FIM JOGO*/
            for (int i = 0; i < arrayInimigo.size(); i++) {
                if(arrayInimigo.get(i).getPosY() > limiteGameOverY) {
                    gameOver = true;
                }
            }

            /*DESENHA NA TELA*/
            nave.render(g);
            for (int i = 0; i < arrayInimigo.size(); i++) {
                arrayInimigo.get(i).render(g);
            }
            for (int i = 0; i < arrayMissil.size(); i++) {
                if(arrayMissil.get(i).getAtivo())
                    arrayMissil.get(i).render(g);
                else
                    arrayMissil.remove(i);
            }
        }
        repaint();
    }

    /*ATUALIZA ESTADO DA NAVE*/
    public void atualizaNave(Boolean direcao) {
        nave.update(direcao);
    }

    /*ADICIONA NOVO MISSIL A LISTA*/
    public void atira() {
        arrayMissil.add(new Missil(nave.getPosXNave()));
    }

    /*REINICIA PARAMETROS DO JOGO*/
    public void reinicia() {
        pontos = 0;
        qtdInimigo = 5;
        gameOver = false;
        arrayInimigo.removeAll(arrayInimigo);
    }
}
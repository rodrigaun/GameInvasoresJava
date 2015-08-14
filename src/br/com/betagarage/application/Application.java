/*PACOTE (DIRETORIO)*/
package br.com.betagarage.application;

/*IMPORTANDO BIBLIOTECAS*/
import br.com.betagarage.core.GameCore;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Application implements KeyListener {

    /*VARIAVEIS GLOBAIS*/
    private JFrame frame;
    private GameCore gameCore = new GameCore();
    private boolean emExecucao = true;

    /*CONSTRUTOR DA CLASSE*/
    public Application() {
        /* Janela */
        frame = new JFrame("I N V A S O R E S");
        frame.add(gameCore);
        frame.addKeyListener(this);
        frame.setSize(640, 480);
        frame.getContentPane().setBackground( Color.BLACK );
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /*LOOPING VIDA DO JOGO*/
    public void run() {
        while (emExecucao) {
            /*precisamos deste loop infinito. Caso contrario o metodo main(principal) acaba e o programa encerra*/
        }
        System.exit(0);
    }

    /*EVENTOS DE TECLADO*/
    @Override
    public void keyPressed(KeyEvent arg0) {
        switch (arg0.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                emExecucao = false;
                break;
            case KeyEvent.VK_RIGHT:
                gameCore.atualizaNave(true);
                break;
            case KeyEvent.VK_LEFT:
                gameCore.atualizaNave(false);
                break;
            case KeyEvent.VK_SPACE:
                gameCore.atira();
                break;
            case KeyEvent.VK_ENTER:
                gameCore.reinicia();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
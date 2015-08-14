/*PACOTE (DIRETORIO)*/
package br.com.betagarage.model;

/*IMPORTANDO BIBLIOTECAS*/
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Missil {

	/*VARIAVEIS GLOBAIS*/
	private Image imgMissil;
	private int posX;
	private int posY = 430;
	private boolean ativo = true;
	
	/*CONSTRUTOR DA CLASSE*/
	public Missil(int x) {
		
		this.posX = x-5;
		try{			
			imgMissil = ImageIO.read(new File("imagens/missil.png"));
		}catch(Exception e){
			System.out.println("Imagem Missil nao encontrada");
			e.printStackTrace();
		}
	}
	
	/*ATUALIZA ESTADO DO MISSIL*/
	public void update() {
		if(ativo){
			posY -= 1;
			if(posY < -35)
				ativo = false;
		}
	}
	
	/*DESENHA NA TELA*/
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(imgMissil, posX, posY, null);
		g2d.dispose();
	}
	
	/*GETs*/
	public int getPosX() {
		return posX+5;
	}
	public int getPosY() {
		return posY;
	}
	public boolean getAtivo(){
		return ativo;
	}
}
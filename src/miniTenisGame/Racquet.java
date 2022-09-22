package miniTenisGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {	
	private static final int Y = 330;	
	private static final int WIDTH = 60; //constantes de valor barra
	private static final int HEIGHT = 10; 
	int x = 0;
	int xa = 0;
	private Game game;

	public Racquet(Game game) {
		this.game= game;
	}

	public void move() {  //movimineto barra
		if (x + xa > 0 && x + xa < game.getWidth()- WIDTH) //Limita el movimiento al margen de la pantalla
			x = x + xa; //Cambia de posicion siempre que xa tenga valor
	}

	public void paint(Graphics2D g) {	//Pinta la barra
		g.setColor(Color.BLACK);	//Elije el color
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) { //para la barra si se deja de pulsar una tecla
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {	//Controla por teclado el mov lateral
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.speed;	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.speed;
	}
	
	public Rectangle getBounds() { //retoma un objeto de posicion barra para indetificarlo en ballsprite.
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}
}
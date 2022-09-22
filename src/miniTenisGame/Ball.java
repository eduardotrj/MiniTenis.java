package miniTenisGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	int x = 0;  //posicion inicial
	int y = 0;
	int xa = 1;	//velocidad y direccion inicial
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game= game;
	}

	void move() {		//Si toca un borde "bota"
		boolean changeDirection = true; //detecta una colision
		if (x + xa < 0)
			xa = game.speed;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.speed;
		else if (y + ya < 0)
			ya = game.speed;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			ya = -game.speed;
			y = game.racquet.getTopY() - DIAMETER; //evita que se atasque en la colision lateral con la barra
			game.speed++;
		} //else 
			//changeDirection = false;  //falla mov ball
		
		if (changeDirection) 
	//		Sound.BALL.play();
		x = x + xa; //genera el movimiento
		y = y + ya;
	}
	
	private boolean collision() { //Envia true si ambos rectangulos se tocan
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.RED);	//Elije el color
		g.fillOval(x, y, DIAMETER, DIAMETER);	//ovalo posicion y tamano
	}
	
	public Rectangle getBounds() { //define los limites de colision "como un cuadrado"
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}

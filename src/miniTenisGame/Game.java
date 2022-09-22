package miniTenisGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	
	Ball ball = new Ball(this);  //crea objetos de cada
	Racquet racquet = new Racquet(this);
	int speed = 1;	//velocidad inicial

	private int getScore() {	//puntuacion
		return speed - 1;
	}
	
	public Game() {  //Crea metodo Game y envia la tecla pulsada
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true); //centra la atencion y entrada de inputs??
		Sound.BACK.loop(); //Musica de fondo se repite
	}
	
	private void move() { //Metodo move
		ball.move();
		racquet.move();
	}

	
	@Override
	public void paint(Graphics g) { //Metodos para dibujar
		super.paint(g);	//limpia la pantalla ////////////////////////
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);	//llama metodos para pintar
		racquet.paint(g2d);
		
		g2d.setColor(Color.GRAY);	//dibuja puntuacion
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}
	
	public void gameOver() { //Muestra ventana Game over y elige si continuar o no.
		//Debe ser publico para ser llamado desde sprite ball cuando detecte el limite inferior.
	//	Sound.BACK.stop(); //para el loop de fondo
	//	Sound.GAMEOVER.play();		//Inicia sonido gameover
		JOptionPane.showMessageDialog(this, "your score is: " + getScore(),
				"Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException { //MAIN
		JFrame frame = new JFrame("Mini Tennis"); //Dibuja la ventana de la app
		Game game = new Game();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();	//aplica metodo move
			game.repaint();	//Repinta cuando es posible para actualizar la posicion.
			Thread.sleep(10); //Pasa el relevo para que los otros thread ejecuten pintar
			/*procesador: el thread que se está ejecutando descanse por 10 milisegundos lo que permite
			que el procesador ejecute otros threads y en particular el thread AWT-EventQueue que 
			llama al método paint.*/
		}
	}


}
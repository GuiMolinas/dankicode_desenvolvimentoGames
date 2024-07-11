package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

//Canvas - propriedades do JFrame
public class Game extends Canvas implements Runnable {

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	//não muda tamanho
	public final int WIDTH = 240;
	public final int HEIGHT = 160;
	private final int SCALE = 3;
	
	private BufferedImage image;
	
	private Spritesheet sheet;
	private BufferedImage[] player;
	private int x = 0;
	private int frames = 0;
	//De quantos em quantos frames animo meu player
	private int maxFrames = 20;
	private int curAnimation = 0;
	private int maxAnimation = 2;
	
	public Game() {
		sheet = new Spritesheet("/spritesheet.png");
		//Duas imagens player
		player = new BufferedImage[2];
		player[0] = sheet.getSprite(0, 0, 16, 16);
		player[1] = sheet.getSprite(16, 0, 16, 16);
		//tamanho da janela
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		//largura, altura, tipo
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Meu jogo");
		frame.add(this);
		//Não pode redimensionar a janela
		frame.setResizable(false);
		//Calcula dimensões
		frame.pack();
		//Janela no centro
		frame.setLocationRelativeTo(null);
		//Ao fechar, finaliza
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Ao iniciar, fica visivel
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//lógica do jogo
	public void tick() {
		//x++;
		frames++;
		if(frames > maxFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation >= maxAnimation) {
				curAnimation = 0;
			}
		}
			
	}
	
	public void render() {
		//Lida com gráficos de forma mais profissional, visando desempeenho
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//usado para renderizar
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,255));
		//x, y, largura, altura
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		//Desenhando player - Imagem, Posicao que eu quero que apareça
		//Rotação - Cast: Transforma variavel em outro tipo. Colocar ponto no meio
		Graphics2D g2 = (Graphics2D) g;
		//Criando layer para por em cima
		g2.setColor(new Color(0,0,0,100));
		//g2.fillRect(0, 0, WIDTH, HEIGHT);
		//g2.rotate(Math.toRadians(45), 90+8, 90+8);
		g2.drawImage(player[curAnimation], 90, 20, null);
		
		//Desenhando Circulo
		g.setColor(Color.BLUE);
		g.fillOval(0, 40, 50, 50);
		
		//Escrevendo - Fonte, tipo, tamanho
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString("Olá Mundo", 10, 20);
		//Renderizando jogo, otimização
		g.dispose();
		//Primeiro desenho, depois aplico imagem
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();
	}
	
	public static void main(String [] args) {
		Game game = new Game();
		game.start();
	}
	
	
	@Override
	public void run() {
		//Limitando FPS
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		//Verifica os 60 FPS
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >=1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println(frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stop();
		
	}

}

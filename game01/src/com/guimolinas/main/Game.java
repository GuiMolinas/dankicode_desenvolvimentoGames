package com.guimolinas.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.guimolinas.entities.Entity;
import com.guimolinas.entities.Player;
import com.guimolinas.graficos.Spritesheet;
import com.guimolinas.world.World;

public class Game extends Canvas implements Runnable, KeyListener {


	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	//não muda tamanho
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	private final int SCALE = 3;
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	
	public static World world;
	
	public static Player player;
	
	public Game() {
		addKeyListener(this);
		//tamanho da janela
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		//largura, altura, tipo
		//Iniciando objetos
		
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		
		player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 16, 16));
		entities.add(player);
		
		world = new World("/map.png");
		
	}
	
	public void initFrame() {
		frame = new JFrame("Bear & Bees");
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
			for(int i = 0; i < entities.size();i++) {
				Entity e = entities.get(i);
				e.tick();
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
			g.setColor(new Color(0,0,0));
			//x, y, largura, altura
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			//Renderizando jogo
			world.render(g);
			for(int i = 0; i < entities.size();i++) {
				Entity e = entities.get(i);
				e.render(g);
			}
			
			//Desenhando player - Imagem, Posicao que eu quero que apareça
			//Rotação - Cast: Transforma variavel em outro tipo. Colocar ponto no meio
			Graphics2D g2 = (Graphics2D) g;
			//Criando layer para por em cima
			g2.setColor(new Color(0,0,0,100));
			
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

		//Eventos de teclado
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() ==  KeyEvent.VK_D) {
				player.right = true;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() ==  KeyEvent.VK_A) {
				player.left = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() ==  KeyEvent.VK_W) {
				player.up = true;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() ==  KeyEvent.VK_S) {
				player.down = true;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() ==  KeyEvent.VK_D) {
				player.right = false;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() ==  KeyEvent.VK_A) {
				player.left = false;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() ==  KeyEvent.VK_W) {
				player.up = false;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() ==  KeyEvent.VK_S) {
				player.down = false;
			}
			
		}
	
}

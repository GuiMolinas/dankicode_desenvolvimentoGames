package inicio_logica_games;

import java.util.ArrayList;

//Permite rodar interfaces
public class Game implements Runnable{
	
	//isRunning é false por padrão
	private boolean isRunning;
	private Thread thread;
	
	//criando uma array List
	private ArrayList<Entidade> entidades = new ArrayList<>();
	
	
	public Game() {
		//Adiciona um objeto
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		
		//percorrendo lista
		for(int i = 0; i < entidades.size(); i++) {
			System.out.println(i);
			//Pegando objeto do loop
			Entidade e = entidades.get(0);
		}
	}
	
	public static void main(String [] args) {
		Game game = new Game();
		game.start();
	}
	
	//Mantém o jogo sincronizado ao dar start
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//Atualizar - "Relogio"
	public void tick() {
		System.out.println("Tick rodando");
	}
	
	//Renderizar
	public void render() {
		System.out.println("Render rodando");
	}
	
	@Override
	public void run() {
		
		//Enquanto estiver rodando
		while(isRunning) {
			tick();
			render();
			//Limitando FPS
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

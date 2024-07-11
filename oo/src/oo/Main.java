package oo;

public class Main extends Player{
	
	private String nome = "Guilherme";
	public static final int VIDA_MAXIMA = 100;
	
	public Main(int n1, int n2) {
		super(n1, n2);
		System.out.println("Classe criada.");
	}
	
	public String getNome() {
		return nome;
	}
	
	public static void main(String[] args) {
	
		Main m = new Main(100,200);
		System.out.println(m.getNome());
		System.out.println(m.VIDA_MAXIMA);
	}
}


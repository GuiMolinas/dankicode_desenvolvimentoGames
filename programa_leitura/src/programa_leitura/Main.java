package programa_leitura;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String [] args) {
		
		
		
		/*
		String nome, idade, peso, cidade, estado, pais;
		
		System.out.println("Digite aqui o seu nome: ");
		nome = in.nextLine();
		
		System.out.println("Digite aqui a sua idade: ");
		idade = in.nextLine();
		
		System.out.println("Digite aqui o seu peso: ");
		peso = in.nextLine();
		
		System.out.println("Digite aqui a sua cidade: ");
		cidade = in.nextLine();
		
		System.out.println("Digite aqui o seu estado: ");
		estado = in.nextLine();
		
		System.out.println("Digite aqui o seu país: ");
		pais = in.nextLine();
		
		System.out.println("=================================");
		
		//Mostrando resultado final
		System.out.println("Nome: "+ nome);
		System.out.println("Idade: "+ idade);
		System.out.println("Peso: "+ peso);
		System.out.println("Cidade: "+ cidade);
		System.out.println("Estado: "+ estado);
		System.out.println("País: "+ pais);
		*/
		
		//Leitura - Scanner
		Scanner in = new Scanner(System.in);
		
		String nome, comando;
		
		//Número aleatorio
		Random rand = new Random();
		int randomico = rand.nextInt(100); // 0 a 99
		
		System.out.println("Seja bem-vindo(a)!");
		System.out.println("Insira seu nome aqui: ");
		nome = in.nextLine();
		System.out.println("Seja bem-vindo(a) "+ nome);
		
		System.out.println("Escolha uma direção para avançar (w,s,a,d): ");
		comando = in.nextLine();
		
		System.out.println("=======================");
		
		if(comando.equals("w")) {
			System.out.println("Você segue em frente.");
			System.out.println("Um inimigo aparece em seu caminho. O que você faz?(a -> atacar, c -> correr)");
			comando = in.nextLine();
			if(comando.equals("a")) {
				if(randomico < 75) {
					System.out.println("Você matou a criatura.");
				}
				
				else {
					System.out.println("Você sucumbiu à criatura. Fim de jogo.");
				}
			}
			
			else {
				System.out.println("Você fugiu. Fim de Jogo");
			}
		}
		
		else if(comando.equals("scu")) {
			System.out.println("Você correu para a colina mais prôxima");
		}
		
	}
}

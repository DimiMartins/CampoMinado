package br.com.coder.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.coder.cm.excessao.ExplosaoException;
import br.com.coder.cm.excessao.SairException;
import br.com.coder.cm.modelo.Tabuleiro;

public class TabulerioConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	public TabulerioConsole(Tabuleiro tabuleiro ) {
	    this.tabuleiro = tabuleiro;
	    executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar= true;
			while (continuar) {// enquanto continuar for verdadeiro ele executa a aplicação
				cicloDoJogo();
				
				
				System.out.println("Outra partida ? (S/n)");
				String resposta = entrada.nextLine();
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
			
		} catch (SairException e) {
			System.out.println("Tchau!");
		}finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				String digitado = capturarValorDigitado("Digite (x/y)");
				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();
				digitado = capturarValorDigitado("1 - Abrir\n2 - Marcar");
				if (digitado.equals("1")) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if (digitado.equals("2")) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
				
			}
			
			System.out.println(tabuleiro);
			System.out.println("Você ganhou !!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}

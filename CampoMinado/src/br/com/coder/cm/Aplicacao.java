package br.com.coder.cm;

import br.com.coder.cm.modelo.Tabuleiro;
import br.com.coder.cm.visao.TabulerioConsole;

public class Aplicacao {

	public static void main(String[] args) {
		Tabuleiro t1 = new Tabuleiro(6, 6, 6);
		new TabulerioConsole(t1);
	}
}

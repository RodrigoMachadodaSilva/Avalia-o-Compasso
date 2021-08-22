package emojis;

import java.util.Scanner;

public class ClasseScanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String divertido = ":)";
		String  chateado = ":(";

		int countdivertido = 0;
		int countchateado = 0;

		Scanner texto = new Scanner(System.in);
		String str;
		System.out.println("Olá, o que está achando da atividade?");
		str = texto.nextLine();
		texto.close();

		if(str!=null) {
			if(str.contains(divertido)) {
				countdivertido++;
			}

			if(str.contains(chateado)) {
				countchateado++;
			}

			if (countchateado>countdivertido){
				System.out.println("Chateado");
			}

			else if (countchateado<countdivertido) {
				System.out.println("Divertido");
			}

			else {
				System.out.println("neutro");
			}	

		}

	}

}

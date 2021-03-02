package view;

import java.util.Scanner;

import controller.RedesController;

public class Main
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner (System.in);

		RedesController redController = new RedesController();

		int opc = 0;
		String processo = "";

		String oS = redController.verificaOS();
		
		System.out.println("Olá! Bem-vindo.\n\nSeu sistema operacional atual é: "+ oS);

		while (opc != 9) {
			System.out.println("Pressione:\n  1 para visualizar os processos ativos"
					+ "\n  2 para 'matar' algum processo"
					+ "\n  9 para finalizar");
			opc = scanner.nextInt();

			switch (opc)
			{
			case 1:
				redController.processosAtivos(oS);
				limpar();
				break;

			case 2:
				System.out.print("Digite o processo (nome ou PID)");
				processo = scanner.next();
				redController.matarProcessosOS(oS, processo);
				limpar();
				break;

			case 9:
				System.out.println("Finalizando, até a próxima (=");
				break;

			default:
				System.out.println("Opção Invalida");
				limpar();
				break;
			}
		}
		scanner.close();		
	}
	private static void limpar() {
		System.out.println("====================================================\n");		
	}
}


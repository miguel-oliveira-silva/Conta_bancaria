package conta_bancaria_;
import java.util.*;
public class Menu {

	public static void main(String[] args) {
		selecao();

	}
	public static void menuGrafico() {
			System.out.printf("**************************************************\n");
			System.out.println("*\t \t 1 - criar conta \t \t *");
			System.out.println("*\t \t 2 - listar todas as contas \t *");
			System.out.println("*\t \t 3 - Buscar contas por número \t *");
			System.out.println("*\t \t 4 - Atualizar dados da conta \t *");
			System.out.println("*\t \t 5 - Apagar conta \t \t *");
			System.out.println("*\t \t 6 - Sacar \t \t \t *");
			System.out.println("*\t \t 7 - Depositar \t \t \t *");
			System.out.println("*\t \t 8 - Transferir valores \t \t *");
			System.out.println("*\t \t 0 - Sair \t \t \t *");
			System.out.println("*\t \t \t \t \t \t *");
			System.out.printf("**************************************************\n");
			System.out.println("Entre com a opção desejada: \t \t ");
	}
	public static void selecao() {
		Scanner teclado = new Scanner(System.in);
		int i = 0;
		do {
			menuGrafico();
			i = selecao(teclado.nextLine());
			
		}while(i != 0);
		teclado.close();
	}
	public static int selecao(String valorDigitado) {
		if(valorDigitado.isEmpty()) {
			return 1;
		}
		char primeiro;
		primeiro = valorDigitado.charAt(0);
		int indice = 0;
		indice = Integer.valueOf(valorDigitado);
		switch(indice) {
			case 1:
				System.out.println("Não implementado");
				break;
			case 2:
				System.out.println("Não implementado");
				break;
			case 3:
				System.out.println("Não implementado");
				break;
			case 4:
				System.out.println("Não implementado");
				break;
			case 5:
				System.out.println("Não implementado");
				break;
			case 6:
				System.out.println("Não implementado");
				break;
			case 7:
				System.out.println("Não implementado");
				break;
			case 8:
				System.out.println("Não implementado");
				break;
			case 0:
				break;
			default:
				System.out.println("Invalido");
				break;
		}
		return indice;
		
	}

}

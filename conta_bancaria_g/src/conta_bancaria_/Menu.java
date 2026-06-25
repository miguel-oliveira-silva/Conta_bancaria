package conta_bancaria_;
import conta_bancaria_.util.Cores;
import conta_bancaria_.controller.*;
import conta_bancaria_.model.ContaCorrente;
import conta_bancaria_.model.ContaPoupanca;
import java.util.*;
public class Menu {
	final static Scanner teclado = new Scanner(System.in); 
	public static void main(String[] args) {
		ContaController contaController = new ContaController();
		contaController.cadastrar(new ContaCorrente(contaController.gerarNumero(), 456, 1, "Thuany Silva", 1000000.00f, 100000.00f));
		contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), 456, 2, "Marcia Condarco", 1000000.00f, 10));
		selecao();
		teclado.close();
	}
	public static void menuGrafico() {
			System.out.printf(Cores.ANSI_BLACK_BACKGROUND+Cores.TEXT_WHITE_BOLD+"**************************************************\n");
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
			System.out.println("Entre com a opção desejada: \t \t \t  "+Cores.TEXT_RESET);
	}
	public static void selecao() {
		int i = 0;
		do {
			menuGrafico();
			try {
			i = selecao(teclado.nextInt());
			teclado.nextLine();
			}catch (InputMismatchException e) {
				i = 0;
				System.out.println("Digite um número entre 0 e 8");
				teclado.nextLine();
			}
		}while(i != 0);
	}
	public static int selecao(int i) {
		switch(i) {
			case 1:
				criarConta();
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
	public static void criarConta() {
		//criar objeto conta para o usario e voltar com ela
	}
	public void keypress(Scanner teclado) {
		System.out.println("Presssione eter para continuar...");
		teclado.next();
	}
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Miguel Oliveira da Silva");
		System.out.println("Generation Brasil - miguels1@genstudents.org");
		System.out.println("github.com/miguel-oliveira-silva");
		System.out.println("*********************************************************");
	}
}

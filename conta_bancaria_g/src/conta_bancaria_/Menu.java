package conta_bancaria_;
import conta_bancaria_.util.Cores;
import conta_bancaria_.controller.*;
import conta_bancaria_.model.Conta;
import conta_bancaria_.model.ContaCorrente;
import conta_bancaria_.model.ContaPoupanca;
import java.util.*;
public class Menu {
	final static Scanner teclado = new Scanner(System.in); 
	static ContaController contaController = new ContaController();
	public static void main(String[] args) {
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
				contaController.listarTodas();
				keyPress();
				break;
			case 3:
				int numero = 0;
				System.out.println("Digite número da conta: ");
				try {
					numero = teclado.nextInt();
					teclado.next();
				} catch (Exception e) {
					System.out.println("Digite um número.");
				}
				buscarContaPorNumero(numero);
				break;
			case 4:
				atualizarConta();
				keyPress();
				break;
			case 5:
				deletarConta();
				keyPress();
				break;
			case 6:
				sacar();
				keyPress();
				break;
			case 7:
				depositar();
				keyPress();
				break;
			case 8:
				transferir();
				keyPress();
				break;
			case 0:
				break;
			default:
				System.out.println("Invalido");
				break;
		}
		return i;
	}
	public static void criarConta() {
		System.out.print("Digite o número da agência: ");
		int agencia = teclado.nextInt();

		System.out.print("Digite o nome do titular da conta: ");
		teclado.skip("\\R");
		String titular = teclado.nextLine();

		System.out.print("Digite o tipo de conta (1 para corrente, 2 para poupança): ");
		int tipo = teclado.nextInt();

		System.out.print("Digite o saldo da conta: ");
		float saldo = teclado.nextFloat();

		switch (tipo) {
		case 1 -> {
			System.out.print("Digite o limite da conta: ");
			float limite = teclado.nextFloat();
			teclado.skip("\\R");
			contaController.cadastrar(new ContaCorrente(contaController.gerarNumero(), agencia, tipo, titular, saldo, limite));
		}
		case 2 -> {
			System.out.print("Digite o dia de aniversário da conta: ");
			int diaAniversario = teclado.nextInt();
			teclado.skip("\\R");
			contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), agencia, tipo, titular, saldo, diaAniversario));
		}
		default -> System.out.println(Cores.TEXT_RED + "Tipo de conta inválido." + Cores.TEXT_RESET);
		}
	}
	public static void keyPress() {
		System.out.println("Presssione eter para continuar...");
		teclado.next();
	}
	public static void buscarContaPorNumero(int numero){
		contaController.procurarPorNumero(numero);
	}
	public static void deletarConta() {
		System.out.println("Digite o número da conta: ");
		int numero = teclado.nextInt();
		teclado.nextLine();
 
		Optional<Conta> conta = contaController.buscarNaCollection(numero);
 
		if (conta.isPresent()) {
 
			// confirmar exclusão
			System.out.printf("\nTem certeza que você deseja excluir a conta número %d? (S/N)", numero);
			String confirmacao = teclado.nextLine();
 
			if (confirmacao.equalsIgnoreCase("S"))
				contaController.deletar(numero);
 
			else
				System.out.println("\nOperação cancelada!");
 
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
	}
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Miguel Oliveira da Silva");
		System.out.println("Generation Brasil - miguels1@genstudents.org");
		System.out.println("github.com/miguel-oliveira-silva");
		System.out.println("*********************************************************");
	}
	public static void atualizarConta() {
		System.out.println("Digite o número da conta:");
		int numero = teclado.nextInt();
		teclado.nextLine();

		Optional<Conta> conta = contaController.buscarNaCollection(numero);

		if (conta.isPresent()) {
			int agencia = conta.get().getAgencia();
			String titular = conta.get().getTitular();
			int tipo = conta.get().getTipo();
			float saldo = conta.get().getSaldo();

			System.out.printf("Agência atual: %d", agencia);
			System.out.println("\nDigite o novo número de agência (ou pressione ENTER para manter o valor atual):");
			String entrada = teclado.nextLine();
			agencia = entrada.isEmpty() ? agencia : Integer.parseInt(entrada);

			System.out.printf("Titular atual: %s", titular);
			System.out.println("\nDigite o novo nome do titular (ou pressione ENTER para manter o valor atual):");
			entrada = teclado.nextLine();
			titular = entrada.isEmpty() ? titular : entrada.trim();

			System.out.printf("Saldo atual: %.2f", saldo);
			System.out.println("\nDigite o novo valor do saldo (ou pressione ENTER para manter o valor atual):");
			entrada = teclado.nextLine();
			saldo = entrada.isEmpty() ? saldo : Float.parseFloat(entrada.replace(",", "."));

			switch (tipo) {
				case 1 -> {
					ContaCorrente contaCorrente = (ContaCorrente) conta.get();
					float limite = contaCorrente.getLimite();
					System.out.printf("\nLimite atual: %.2f", limite);
					System.out.println("\nDigite o novo valor do limite (ou pressione ENTER para manter o valor atual):");
					entrada = teclado.nextLine();
					limite = entrada.isEmpty() ? limite : Float.parseFloat(entrada.replace(",", "."));
					contaController.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					ContaPoupanca contaPoupanca = (ContaPoupanca) conta.get();
					int diaAniversario = contaPoupanca.getAniversario();
					System.out.printf("\nAniversário atual: %d", diaAniversario);
					System.out.println("\nDigite o novo dia de aniversário da conta (ou pressione ENTER para manter o valor atual):");
					entrada = teclado.nextLine();
					diaAniversario = entrada.isEmpty() ? diaAniversario : Integer.parseInt(entrada);
					contaController.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, diaAniversario));
				}
				default -> System.out.println(Cores.TEXT_RED + "Tipo de conta inválido!" + Cores.TEXT_RESET);
			}
		} else {
			System.out.printf("\nA conta número %d não foi encontrada.", numero);
		}
	}
	public static void sacar() {
		System.out.println("Digite o número da conta:");
		int numero = teclado.nextInt();

		System.out.print("Digite o valor do saque: ");
		float valor = teclado.nextFloat();

		contaController.sacar(numero, valor);
	}
	public static void depositar() {
		System.out.println("Digite o número da conta:");
		int numero = teclado.nextInt();

		System.out.print("Digite o valor do depósito: ");
		float valor = teclado.nextFloat();

		contaController.depositar(numero, valor);
	}
	public static void transferir() {
		System.out.println("Digite o número da conta de origem:");
		int numeroOrigem = teclado.nextInt();

		System.out.println("Digite o número da conta de destino:");
		int numeroDestino = teclado.nextInt();

		System.out.print("Digite o valor da transferência: ");
		float valor = teclado.nextFloat();
		teclado.nextLine();

		contaController.transferir(numeroOrigem, numeroDestino, valor);
	}
	public static void listarPorTitular() {
		System.out.print("Digite o nome do titular da conta: ");
		String titular = teclado.nextLine();

		contaController.listarPorTitular(titular);
	}
}

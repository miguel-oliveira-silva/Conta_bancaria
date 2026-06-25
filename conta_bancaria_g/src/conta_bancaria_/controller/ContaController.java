package conta_bancaria_.controller;
import conta_bancaria_.repository.*;
import conta_bancaria_.model.*;
import java.util.*;
public class ContaController implements ContaRepository {
	private List<Conta> contas = new ArrayList<Conta>();
	@Override
	public void listarTodas() {
		for(Conta conta: contas) {
			conta.visualizar();
		}
	}
	@Override
	public void cadastrar(Conta conta) {
		contas.add(conta);
		System.out.println("Conta cadastrada");
	}
	@Override
	public void procurarPorNumero(int numero) {
		Conta conta;
		conta = contas.get(numero);
		conta.visualizar();
		
	}
	
	@Override
	public void atualizar(Conta conta) {
	}
	@Override
	public void deletar(int numero) {
		
	}
	@Override
	public void sacar(int numero, float valor) {
		
	}
	@Override
	public void depositar(int numero, float valor) {}
	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) { }
		
	}

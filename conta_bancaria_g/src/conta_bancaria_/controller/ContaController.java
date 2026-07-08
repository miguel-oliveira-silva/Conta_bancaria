package conta_bancaria_.controller;

import conta_bancaria_.repository.*;
import conta_bancaria_.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class ContaController implements ContaRepository {

	private List<Conta> listaContas = new ArrayList<Conta>();
	private int numero = 0;

	@Override
	public void listarTodas() {
		for (Conta conta : listaContas) {
			conta.visualizar();
		}
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.printf("A conta número %d foi criada com sucesso!\n", conta.getNumero());
	}

	@Override
	public void procurarPorNumero(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			conta.get().visualizar();
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
	}

	@Override
	public void atualizar(Conta conta) {
		Optional<Conta> contaBuscada = buscarNaCollection(conta.getNumero());

		if (contaBuscada.isPresent()) {
			listaContas.set(listaContas.indexOf(contaBuscada.get()), conta);
			System.out.printf("\nA conta número %d foi atualizada com sucesso!", conta.getNumero());
		} else {
			System.out.printf("\nA conta número %d não foi encontrada.", conta.getNumero());
		}
	}

	@Override
	public void deletar(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			if (listaContas.remove(conta.get())) {
				System.out.printf("\nA conta número %d foi excluída com sucesso.", numero);
			}
		} else {
			System.out.printf("A conta número %d não foi encontrada!", numero);
		}
	}

	@Override
	public void sacar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			if (conta.get().sacar(valor)) {
				System.out.printf("\nO saque no valor de %.2f na conta número %d foi efetuado com sucesso.", valor, numero);
			} else {
				System.out.printf("\nO saque no valor de %.2f na conta número %d não foi efetuado devido ao saldo insuficiente.", valor, numero);
			}
		} else {
			System.out.printf("A conta número %d não foi encontrada!", numero);
		}
	}

	@Override
	public void depositar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			conta.get().depositar(valor);
			System.out.printf("\nO depósito no valor de %.2f na conta número %d foi efetuado com sucesso.", valor, numero);
		} else {
			System.out.printf("A conta número %d não foi encontrada!", numero);
		}
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
		Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);

		if (contaOrigem.isPresent() && contaDestino.isPresent()) {
			if (contaOrigem.get().sacar(valor)) {
				contaDestino.get().depositar(valor);
				System.out.printf("\nA transferência no valor de %.2f da conta número %d para a conta %d foi efetuada com sucesso.", valor, numeroOrigem, numeroDestino);
			} else {
				System.out.printf("\nO saque no valor de %.2f na conta número %d para a conta %d não foi efetuado devido ao saldo insuficiente da conta de origem.", valor, numeroOrigem, numeroDestino);
			}
		} else {
			System.out.printf("\nA conta número %d e/ou a conta %d não foram encontradas!", numeroOrigem, numeroDestino);
		}
	}

	public int gerarNumero() {
		return ++numero;
	}

	public Optional<Conta> buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return Optional.of(conta);
			}
		}
		return Optional.empty();
	}

	public void listarPorTitular(String titular) {
		List<Conta> listaTitulares = listaContas.stream()
				.filter(conta -> conta.getTitular().toUpperCase().contains(titular.toUpperCase()))
				.collect(Collectors.toList());

		if (listaTitulares.isEmpty()) {
			System.out.printf("\nNenhum titular com o nome '%s'", titular);
		}
		listaTitulares.forEach(conta -> conta.visualizar());
	}
}
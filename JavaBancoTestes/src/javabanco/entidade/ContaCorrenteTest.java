package javabanco.entidade;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ContaCorrenteTest {

	@Test
	public void testSaldo() {
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		assertEquals(0, cc.saldo(), 0);
	}

	@Test
	public void testCredito() {
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		cc.credito(100);
		assertEquals(100, cc.saldo(), 0);
	}

	@Test
	public void testDebito() {
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		cc.debito(100);
		assertEquals(-100, cc.saldo(), 0);
	}

	@Test
	public void testExtrato() {
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		cc.credito(100);
		cc.debito(100);
		ArrayList<Operacao> op = (ArrayList<Operacao>) cc.extrato(0, null);
		assertEquals(2, op.size());
		assertEquals(100, op.get(0).getValor(), 0);
		assertEquals("CREDITO", op.get(0).getTipoOperacao());
		assertEquals(100, op.get(1).getValor(),0);
		assertEquals("DEBITO", op.get(1).getTipoOperacao());
	}
	
	@Test
	public void testSaldoPequenosFloats() {
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		cc.credito(0.1f);
		cc.credito(0.2f);
		assertEquals(0.3f, cc.saldo(), 0.0f);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreditoValorNegativo() {
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		
		cc.credito(-10);		
	}
	@Test
	public void testConstrutor(){
		ContaCorrente cc = new ContaCorrente(12345, "João da Silva");
		assertNotNull(cc);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDebitoValorNegativo() {
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		
		cc.debito(-10);		
	}
	
	@Test
	public void testGetTitular(){
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		assertEquals("Joao da Silva", cc.getTitular());
	}
	
	@Test
	public void testGetNumero(){
		ContaCorrente cc = new ContaCorrente(12345, "Joao da Silva");
		assertEquals(12345, cc.getNumero());
	}
	
	@Test
	public void Transferencial(){
		ContaCorrente ccOrigem = new ContaCorrente(12345, "Joao da Silva");
		ContaCorrente ccDestino = new ContaCorrente(12345, "Joao da Silva");
		//transfere
		ccOrigem.transfência(100, ccDestino);
		//testa
		assertEquals(-100, ccOrigem.saldo(),0);
		assertEquals(100, ccDestino.saldo(),0);
	}
 
}

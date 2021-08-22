package com.compasso.loja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void salvaProdutos() {
		ProductJpaDAO productDao = ProductJpaDAO.getInstance();
		
		for (int i=1; i<=3; i++) {
			Product product = new Product(
					null,
					"Produto" + i, 
					"Descricao" + i, 
					i, 
					BigDecimal.valueOf(i * 100), 
					LocalDate.now(), 
					LocalDate.now());
			
			productDao.persist(product);
		}
	}
	
	public static void atualizaPrimeiroProduto() {
		
		ProductJpaDAO productDao = ProductJpaDAO.getInstance();

		Product product = productDao.getById(1);
		product.setDataAlteracao(LocalDate.now());
		
		productDao.merge(product);
	}
	
	public static void removeProduto() {
		ProductJpaDAO productDao = ProductJpaDAO.getInstance();

		productDao.removeById(2);
	}
	
	public static void main(String[] args) {
		
		try {
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Selecione uma opção:");

			ProductJpaDAO productDao = ProductJpaDAO.getInstance();
		
			while (scanner.hasNextInt()) {
				int opcao = scanner.nextInt();
				switch (opcao) {
				case 0:
					System.out.println("Saindo");
					scanner.close();
					System.exit(0);
					break;
				case 1:
					System.out.println("Opção 1 selecionada, cadastrando 3 novos produtos");
					salvaProdutos();
					
					List<Product> productList = productDao.findAll();
					
					for (Product savedProduct : productList) {
						System.out.println("Produto salvo: " + savedProduct.getId());
					}
					
					break;
				case 2:
					System.out.println("Opção 2 selecionada, atualizando data de cadastro");
					atualizaPrimeiroProduto();
					Product product = productDao.getById(1);
					System.out.println("Data de atualização produto 1: " + product.getDataAlteracao());
					
					break;
				case 3:
					System.out.println("Opção 3 selecionada, remove produto 2");
					removeProduto();
					System.out.println("Produto 2 removido");
					break;	
					
				default:
					break;
				}		    					
			}

		} catch (Exception e) {
			System.out.println("");
		}
			
	}

}

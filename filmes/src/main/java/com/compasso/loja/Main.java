package com.compasso.loja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			
			FilmeJpaDAO filmeDao = FilmeJpaDAO.getInstance();
			for (int i=1; i<=20; i++) {
				Filme filme = new Filme(
						null,
						"Nome" + i, 
						"Descricao" + i, 
						2010);
				
				filmeDao.persist(filme);
			}
			
			List<Filme> filmes = filmeDao.findAll();
					
			for (Filme filme : filmes) {
				System.out.println("Filmes salvos: " + filme.getId());
			}
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Insira o número de resultados: ");
			int limit = scanner.nextInt();
			System.out.println("Insira a página desejada: ");
			int offset = scanner.nextInt();
			
			List<Filme> filmesFiltrados = filmeDao.findWithLimit(limit, offset);
			
			for (Filme filme : filmesFiltrados) {
				System.out.println("Filmes: " + filme.getId());
			}

		} catch (Exception e) {
			System.out.println("");
		}
			
	}

}

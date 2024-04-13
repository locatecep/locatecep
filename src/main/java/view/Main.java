package view;

import java.util.Scanner;

import service.ConsoleService;

public class Main {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Informe o CEP para busca: ");
		String numeracao = scan.nextLine();
		
		String response = ConsoleService.console(numeracao);
		System.out.println(response);
		
		scan.close();
		
	}
}

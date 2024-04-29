package io.github.locatecep.view;

import io.github.locatecep.model.Cep;
import io.github.locatecep.service.CepService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Informe o CEP");
        String cepDigit = scan.nextLine();

        if (cepDigit.length() > 8) {
            System.out.println("CEP inválido");
            System.exit(1);
        }

        try {
            CepService cepService = new CepService();
            Cep cep = cepService.buscar(cepDigit);

            if (cep != null) {
                System.out.println("CEP: " + cep.getNumeracao());
                System.out.println("UF: " + cep.getUf());
                System.out.println("Cidade: " + cep.getCidade());
            } else {
                System.out.println("Não foram encontrados registros para o CEP informado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar informações do CEP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
package io.github.locatecep.view;

import io.github.locatecep.model.Cep;
import java.util.Scanner;

public class CepView {

    public String getCepInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o CEP: ");
        return scanner.nextLine();
    }

    public void displayCep(Cep cep) {
        System.out.println("CEP: " + cep.getNumeracao());
        System.out.println("UF: " + cep.getUf());
        System.out.println("Cidade: " + cep.getCidade());
    }

    public void displayError(String message) {
        System.err.println("Erro: " + message);
    }
}

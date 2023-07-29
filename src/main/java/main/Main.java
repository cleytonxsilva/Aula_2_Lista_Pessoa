package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Endereco> enderecos = new ArrayList<>();
    private static List<Pessoa> pessoas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int escolha = 0;

        do{
            System.out.println("\nDigite 1 para cadastrar pessoas:");
            System.out.println("Digite 2 para visualizar:");
            System.out.println("Digite 3 para fazer uma pesquisa por nome:");
            System.out.println("Digite 5 para sair:");
            try {
                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        Cadastrar();
                        break;

                    case 2:
                        Visualizar();
                        break;

                    case 3:
                        if (pessoas.isEmpty()){
                            System.out.println("Não existem pessoas cadastradas!\n");
                            break;
                        }
                        BuscarPessoa();
                        break;

                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Digite novamente.\n");
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Erro: você deve digitar um número inteiro válido!\n");
                scanner.next();
                escolha=0;
            }

        }while (escolha != 5);
        scanner.close();
    }


    private static void Cadastrar() {

        System.out.println("Quantas pessoas serão cadastradas?");
        int qtdPessoas = 0;
        try {
            qtdPessoas = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro: você deve digitar um número inteiro válido para a quantidade de pessoas.\n");
            scanner.next(); // Limpar o buffer do Scanner para evitar um loop infinito
            return; // Sair do método para evitar o cadastro com valor inválido
        }

        for (int i = 0; i < qtdPessoas; i++) {
            System.out.println("Digite um nome:");
            String nome = scanner.next();
            System.out.println("Digite a idade:");
            int idade = scanner.nextInt();
            System.out.println("Quantos endereços deseja cadastrar?");
            int qtdEndereco = scanner.nextInt();

            for (int j = 0; j < qtdEndereco; j++) {
                System.out.println("Digite a rua:");
                String rua = scanner.next();
                System.out.println("Digite o número:");
                int numero = scanner.nextInt();
                enderecos.add(new Endereco(rua, numero));
            }
            pessoas.add(new Pessoa(nome, idade, enderecos));
            //enderecos.clear(); ESTAVA BUGANDO E NÃO MOSTRAVA ENEDEREÇO
        }

    }

    private static void Visualizar() {
        int qtdPessoas = pessoas.size();
        if (qtdPessoas == 0) {
            System.out.println("Nenhuma pessoa cadastrada.\n");
        } else {
            for (int i = 0; i < pessoas.size(); i++) {
                Pessoa pessoa = pessoas.get(i);
                System.out.println(i + " - O nome é: " + pessoa.getNome() + ", idade: " + pessoa.getIdade());

                List<Endereco> enderecos = pessoa.getEnderecos();
                for (int j = 0; j < enderecos.size(); j++) {
                    Endereco endereco = enderecos.get(j);
                    System.out.println("Mora na rua: " + endereco.getRua() + ", número: " + endereco.getNumero());

                }
            }
        }
    }
    private static void BuscarPessoa() {

        String buscar;
        System.out.println("Digite o nome da Pessoa que deseja ver se já está cadastrado na lista:");
        buscar = scanner.next();
        boolean encontrada = false;
        for (Pessoa pessoa : pessoas){
            if (pessoa.getNome().equalsIgnoreCase(buscar)) {
                encontrada = true;
                if (encontrada) {
                    System.out.println("A pessoa " + buscar + " está cadastrada na lista.\n*Dados*:\n");
                    System.out.println("Nome: " + pessoas.get(0).getNome());
                    System.out.println("Idade: " + pessoas.get(0).getIdade());
                    System.out.println("Rua: " + pessoas.get(0).getEnderecos().get(0).getRua());
                    System.out.println("Número: " + pessoas.get(0).getEnderecos().get(0).getNumero());
                    break;
                }
            }
            else{
                System.out.println("A pessoa " + buscar + " não está cadastrada na lista.");
            }
        }
    }
//    private static void Clear() {
//        for (int i = 0; i < 10; i++) {
//            System.out.println();
//        }
//    }
}
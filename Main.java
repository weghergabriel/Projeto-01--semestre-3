import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FipeApiClient fipe = new FipeApiClient();
        Scanner scanner = new Scanner(System.in);  // scanner no escopo da main
        int op, typeV;

        do {
            System.out.println("Escolha alguma das seguintes opcoes:");
            System.out.println("1-Vendas");
            System.out.println("2-Veiculos cadastrados");
            System.out.println("3-Cadastro de veiculos");
            System.out.println("4-Sair");
            op = scanner.nextInt();  // Aqui você espera a entrada

            switch (op) {
                case 1:
                System.out.println("Qual veiculo pretende comprar?");
                fipe.MostrarVeiculosVenda();
                
                    break;
                case 2:
                    fipe.MostrarVeiculos();
                    System.out.println("Deseja atualizar o preço de algum dos veiculos cadastrados?");
                    System.out.println("1-Sim/2-Não");
                    op = scanner.nextInt();
                    scanner.nextLine();
                    if(op == 1){
                        fipe.AtualizarPreco();
                    }
                    break;
                case 3:
                    Cadastro_de_veiculos(scanner, fipe); // Passar o scanner e o objeto fipe para o método
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (op != 4);

        scanner.close(); // Fechar o scanner no final da execução
    }

    public static void Cadastro_de_veiculos(Scanner scanner, FipeApiClient fipe) {
        int typeV, IdMarca;
        String Ano;
        String AnoECombs;
        String tipoVeiculo;

        System.out.println("Você entrou na função de cadastro");
        System.out.println("1-Cadastrar veiculos no catalogo de vendas");
        System.out.println("2-Remover veiculos do catalogo de vendas");
        System.out.println("3-Consultar marcas disponíveis por veiculo");
        System.out.println("4-Consultar modelos por marca e ano");
        System.out.println("5-Voltar ao menu");
        int op = scanner.nextInt();  // Aqui você usa o mesmo scanner passado como parâmetro
        
        switch (op) {
            case 1:
            System.out.println("Qual tipo de veiculo pretende cadastrar?");
            System.out.println("1-Carros");
            System.out.println("2-Motos");
            System.out.println("3-Caminhões");
            typeV = scanner.nextInt();  // Lendo a entrada para tipo de veículo

            if (typeV == 1) {
                tipoVeiculo = "cars";
            } else if (typeV == 2) {
                tipoVeiculo = "motorcycles";
            } else if (typeV == 3) {
                tipoVeiculo = "trucks";
            } else {
                System.out.println("Tipo de veiculo inválido!");
                System.out.println("Voltando ao menu");
                return;
            }

            System.out.println("Digite o Id da marca desejada:");
                IdMarca = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o código do modelo desejado:");
                int IdModelo = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o ano desejado e logo após o tipo de combustivel");
                System.out.println("Ex:'-1'Para gasolina/'-2'Para alcool/'-3'Para diesel");
                AnoECombs = scanner.nextLine();

                fipe.DetalhesVeiculo(tipoVeiculo, IdMarca, IdModelo, AnoECombs);

                break;
            case 2:
                fipe.RemoverVeiculo();
                break;
            case 3:
                System.out.println("Qual tipo de veiculo pretende procurar?");
                System.out.println("1-Carros");
                System.out.println("2-Motos");
                System.out.println("3-Caminhões");
                typeV = scanner.nextInt();  // Lendo a entrada para tipo de veículo

                if (typeV == 1) {
                    tipoVeiculo = "cars";
                } else if (typeV == 2) {
                    tipoVeiculo = "motorcycles";
                } else if (typeV == 3) {
                    tipoVeiculo = "trucks";
                } else {
                    System.out.println("Tipo de veiculo inválido!");
                    System.out.println("Voltando ao menu");
                    return;
                }

                // Adicionando feedback antes da requisição
                System.out.println("Buscando marcas para " + tipoVeiculo + "...");
                
                // Chamando o método para mostrar marcas
                fipe.MostrarMarcas(tipoVeiculo); 
                break;
            case 4:
                System.out.println("Qual tipo de veiculo pretende procurar?");
                System.out.println("1-Carros");
                System.out.println("2-Motos");
                System.out.println("3-Caminhões");
                typeV = scanner.nextInt();  // Lendo a entrada para tipo de veículo

                if (typeV == 1) {
                    tipoVeiculo = "cars";
                } else if (typeV == 2) {
                    tipoVeiculo = "motorcycles";
                } else if (typeV == 3) {
                    tipoVeiculo = "trucks";
                } else {
                    System.out.println("Tipo de veiculo inválido!");
                    System.out.println("Voltando ao menu");
                    return;
                }
                System.out.println("Digite o Id da marca desejada:");
                IdMarca = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o ano desejado e logo após o tipo de combustivel");
                System.out.println("Ex:'-1'Para gasolina/'-2'Para alcool/'-3'Para diesel");
                AnoECombs = scanner.nextLine();
                
                fipe.ModelosPorMarca(tipoVeiculo, IdMarca, AnoECombs);

                break;
            case 5:
                System.out.println("Voltando ao menu principal...");
                return; // Sai da função Cadastro_de_veiculos e volta ao menu principal
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }
}

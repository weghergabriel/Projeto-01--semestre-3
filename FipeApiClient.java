import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FipeApiClient {
    ArrayList <Veiculo> listaVeiculos = new ArrayList<>();

    // Função para mostrar as marcas de um tipo de veículo
    public void MostrarMarcas(String tipoVeiculo) {
        String urlMarcas = "https://parallelum.com.br/api/v2/" + tipoVeiculo + "/brands";
        try {
            System.out.println("Tentando acessar a URL: " + urlMarcas);  // Log para depuração
            String resposta = fazerRequisicao(urlMarcas);
    
            // Remover os colchetes externos e processar o conteúdo
            resposta = resposta.replace("[", "").replace("]", "");
    
            // Separar as marcas usando "},{" como delimitador
            String[] marcasSeparadas = resposta.split("},\\{");
    
            System.out.println("\nMarcas disponíveis:\n");
    
            for (String marca : marcasSeparadas) {
                // Remover chaves extras e dividir "code" e "name"
                marca = marca.replace("{", "").replace("}", "").replace("\"", "");
                String[] atributos = marca.split(",");
    
                String codigo = "";
                String nome = "";
    
                for (String atributo : atributos) {
                    if (atributo.startsWith("code:")) {
                        codigo = atributo.split(":")[1];
                    } else if (atributo.startsWith("name:")) {
                        nome = atributo.split(":")[1];
                    }
                }
    
                // Exibir as marcas
                System.out.println(" - Código: " + codigo + " | Marca: " + nome);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar marcas: " + e.getMessage());
        }
    }

    // Função para mostrar os modelos de um veículo, com base no tipo, marca, ano e tipo de combustível
    public void ModelosPorMarca(String tipoVeiculo, int IdMarca, String AnoECombs) {
        try {
            String urlModelos = "https://parallelum.com.br/api/v2/" + tipoVeiculo + "/brands/" + IdMarca + "/years/"+ AnoECombs+"/models";
    
            System.out.println("Tentando acessar a URL: " + urlModelos); // Depuração
            String respostaModelos = fazerRequisicao(urlModelos);
    
            // Removendo os colchetes externos da resposta JSON
            respostaModelos = respostaModelos.replace("[", "").replace("]", "");
    
            // Separando cada modelo individualmente usando "},{" como delimitador
            String[] modelosSeparados = respostaModelos.split("},\\{");
    
            System.out.println("\nModelos disponíveis para o tipo " + tipoVeiculo + ", marca " + IdMarca + ", ano e combustível " + AnoECombs + ":\n");
    
            for (String modelo : modelosSeparados) {
                // Removendo chaves extras e dividindo "code" e "name"
                modelo = modelo.replace("{", "").replace("}", "").replace("\"", "");
                String[] atributos = modelo.split(",");
    
                String codigo = "";
                String nome = "";
    
                for (String atributo : atributos) {
                    if (atributo.startsWith("code:")) {
                        codigo = atributo.split(":")[1];
                    } else if (atributo.startsWith("name:")) {
                        nome = atributo.split(":")[1];
                    }
                }
    
                System.out.println(" - Código: " + codigo + " | Modelo: " + nome);
            }
    
        } catch (Exception e) {
            System.out.println("Erro ao buscar modelos: " + e.getMessage());
        }
    }

    public void DetalhesVeiculo(String tipoVeiculo, int IdMarca, int IdModelo, String AnoECombs) {
        try {
            String urlDetalhes = "https://parallelum.com.br/api/v2/" + tipoVeiculo + "/brands/" + IdMarca + "/models/" + IdModelo + "/years/" + AnoECombs;
            System.out.println("\n Acessando URL: " + urlDetalhes);
    
            String resposta = fazerRequisicao(urlDetalhes);
    
            // Removendo as chaves externas da resposta JSON
            resposta = resposta.replace("{", "").replace("}", "").replace("\"", "");
    
            // Separando os atributos usando vírgula como delimitador
            String[] atributos = resposta.split(",");
    
            // Variáveis para armazenar os valores extraídos
            String marca = "", modelo = "", ano = "", codigoFipe = "", preco = "";
            String combustivel = "", acronCombustivel = "", mesReferencia = "";
    
            // Extraindo cada informação
            for (String atributo : atributos) {
                if (atributo.startsWith("brand:")) {
                    marca = atributo.split(":")[1];
                } else if (atributo.startsWith("model:")) {
                    modelo = atributo.split(":")[1];
                } else if (atributo.startsWith("modelYear:")) {
                    ano = atributo.split(":")[1];
                } else if (atributo.startsWith("codeFipe:")) {
                    codigoFipe = atributo.split(":")[1];
                } else if (atributo.startsWith("price:")) {
                    preco = atributo.split(":")[1];
                } else if (atributo.startsWith("fuel:")) {
                    combustivel = atributo.split(":")[1];
                } else if (atributo.startsWith("fuelAcronym:")) {
                    acronCombustivel = atributo.split(":")[1];
                } else if (atributo.startsWith("referenceMonth:")) {
                    mesReferencia = atributo.split(":")[1];
                }
            }
    
            // Exibir os detalhes formatados
            System.out.println("\n**Detalhes do Veículo:**\n");
            System.out.println(" Marca: " + marca);
            System.out.println(" Modelo: " + modelo);
            System.out.println(" Ano: " + ano);
            System.out.println(" Código FIPE: " + codigoFipe);
            System.out.println(" Preço: " + preco);
            System.out.println(" Combustível: " + combustivel + " (" + acronCombustivel + ")");
            System.out.println(" Mês de Referência: " + mesReferencia);
            System.out.println("\n");

            Veiculo veiculo;
            if (tipoVeiculo.equals("cars")) {
                veiculo = new Carro(1, preco, marca, modelo, Integer.parseInt(ano), combustivel, codigoFipe, mesReferencia, acronCombustivel);
            } else if (tipoVeiculo.equals("motorcycles")) {
                veiculo = new Moto(2, preco, marca, modelo, Integer.parseInt(ano), combustivel, codigoFipe, mesReferencia, acronCombustivel);
            } else {
                veiculo = new Caminhao(3, preco, marca, modelo, Integer.parseInt(ano), combustivel, codigoFipe, mesReferencia, acronCombustivel);
            }

            // Adiciona o veículo à lista
            listaVeiculos.add(veiculo);

            MostrarVeiculos();
    
        } catch (Exception e) {
            System.out.println(" Erro ao buscar detalhes do veículo: " + e.getMessage());
        }
    }

    public void AtualizarPreco(){
        Scanner scanner = new Scanner(System.in);
        int index;
        System.out.println("Digite a posição na lista do veiculo que deseja atualizar o preço:");
            index = scanner.nextInt();
            scanner.nextLine();
            if(index >= 0 && index<listaVeiculos.size()){
                System.out.println("Digite o novo preço com as siglas:");
                String novoPreco = scanner.nextLine();
                listaVeiculos.get(index).setPreco(novoPreco);
            }else{
                System.out.println("Índice fora dos limites.");
            }
    }

    public void RemoverVeiculo(){
        Scanner scanner = new Scanner(System.in);
        int index;
        if(listaVeiculos.isEmpty()){
            System.out.println("Não há nenhum veiculo adicionado para ser removido!");
        }else{
            System.out.println("Digite a posição na lista do veiculo que deseja deletar:");
            index = scanner.nextInt();
            scanner.nextLine();
            if(index >= 0 && index<listaVeiculos.size()){
                listaVeiculos.remove(index);
                System.out.println("Veiculo deletado com sucesso!");
                System.out.println("Mostrando lista de veiculos atualizada:");
                MostrarVeiculos();
            }else{
                System.out.println("Índice fora dos limites.");
            }
        }
    }
    
    public void MostrarVeiculos(){
        if(listaVeiculos.isEmpty()){
            System.out.println("Nenhum veiculo adicionado ainda!");
        }else{
            for (Veiculo v : listaVeiculos) {
                System.out.println("Tipo: " + (v.getTipoVeiculo() == 1 ? "Carro" : v.getTipoVeiculo() == 2 ? "Moto" : "Caminhão"));
                System.out.println("Marca: " + v.getMarca());
                System.out.println("Modelo: " + v.getModelo());
                System.out.println("Ano: " + v.getAno());
                System.out.println("Preço: " + v.getPreco());
                System.out.println("Combustível: " + v.getCombustivel());
                System.out.println("Código Fipe: " + v.getCodigoFipe());
                System.out.println("Mês de Referência: " + v.getMesReferencia());
                System.out.println("Acrônimo Combustível: " + v.getAcronCombustivel());
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    //Função de Vendas
    public void MostrarVeiculosVenda(){
        Scanner scanner = new Scanner(System.in);
        String metodoPagamento = null;
        int index;
        int posicao = 1;
        if(listaVeiculos.isEmpty()){
            System.out.println("Nenhum veiculo adicionado ainda!");
        }else{
            for (Veiculo v : listaVeiculos) {
                System.out.println(posicao + " - " +
                (v.getTipoVeiculo() == 1 ? "Carro" : v.getTipoVeiculo() == 2 ? "Moto" : "Caminhão") + " " +v.getMarca() + " " +v.getModelo() + " - " +v.getCombustivel() + " - " +v.getAno() + " | " +v.getPreco() + " - " +v.getCodigoFipe()+" |");
                System.out.println("--------------------------------------------------------");
                posicao++;
            }

            System.out.println("Digite o numero de posição do veículo que deseja comprar:");
            index = scanner.nextInt();
            scanner.nextLine();
            index = index - 1;
            if(index >= 0 && index<listaVeiculos.size()){
                Veiculo veiculoSelecionado = listaVeiculos.get(index);
                System.out.println("\nVocê selecionou o seguinte veículo:");
                System.out.println("Tipo: " + (veiculoSelecionado.getTipoVeiculo() == 1 ? "Carro" : veiculoSelecionado.getTipoVeiculo() == 2 ? "Moto" : "Caminhão"));
                System.out.println("Marca: " + veiculoSelecionado.getMarca());
                System.out.println("Modelo: " + veiculoSelecionado.getModelo());
                System.out.println("Ano: " + veiculoSelecionado.getAno());
                System.out.println("Preço: " + veiculoSelecionado.getPreco());
                System.out.println("Combustível: " + veiculoSelecionado.getCombustivel());
                System.out.println("Código Fipe: " + veiculoSelecionado.getCodigoFipe());

                System.out.println("\nDeseja confirmar a compra? (1-Sim / 2-Nao)");
                int confirmacao = scanner.nextInt();
                scanner.nextLine();
                if(confirmacao != 1){
                    System.out.println("Compra cancelada, voltando ao menu...");
                    return;
                }

                System.out.println("Selecione a forma de pagamento:");
                System.out.println("1 - Cartão de Crédito");
                System.out.println("2 - Cartão de Débito");
                System.out.println("3 - Pix");
                System.out.println("4 - Dinheiro");
                int formaPagamento = scanner.nextInt();
                scanner.nextLine();
                switch(formaPagamento){
                    case 1 :
                        metodoPagamento = "Cartão de Crédito";
                        break;
                    case 2 :
                        metodoPagamento = "Cartão de Débito";
                        break;
                    case 3 :
                        metodoPagamento = "Pix";
                        break;
                    case 4 :
                        metodoPagamento = "Dinheiro";
                        break;        
                    default:
                        System.out.println("Forma de Pagamento invalida");
                        break;
                }

                System.out.println("\nCompra realizada com sucesso!");
                System.out.println("Forma de pagamento escolhida: " + metodoPagamento);
                System.out.println("Obrigado por comprar conosco!\n");
                return;

            }else{
                System.out.println("Numero invalido retornando ao menu...");
                return;

            }
        }
    }

    // Método para fazer requisições HTTP
    public String fazerRequisicao(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
    
            int codigoResposta = conexao.getResponseCode();
            if (codigoResposta != 200) {
                System.out.println("Erro na requisição! Código: " + codigoResposta);
                return null;
            }
    
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            
            leitor.close();
            return resposta.toString();
            
        } catch (Exception e) {
            System.out.println("Erro ao fazer requisição: " + e.getMessage());
            return null;
        }
    }
}

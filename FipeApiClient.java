import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FipeApiClient {

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

    // Método para fazer requisições HTTP
    public static String fazerRequisicao(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        int status = conn.getResponseCode();  // Verifica o código de status HTTP

        if (status != 200) {
            // Caso a requisição não tenha sido bem-sucedida
            throw new Exception("Falha na requisição. Código de status HTTP: " + status);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String linha;
        StringBuilder resposta = new StringBuilder();
        while ((linha = reader.readLine()) != null) {
            resposta.append(linha);
        }
        reader.close();
        return resposta.toString();
    }
}

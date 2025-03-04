import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FipeApiClient {

    // Função para mostrar as marcas de um tipo de veículo
    public void MostrarMarcas(String tipoVeiculo) {
        String urlMarcas = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas";
        try {
            System.out.println("Tentando acessar a URL: " + urlMarcas);  // Log para depuração
            String resposta = fazerRequisicao(urlMarcas);
            System.out.println("Marcas disponíveis: ");
            System.out.println(resposta);
        } catch (Exception e) {
            System.out.println("Erro ao buscar marcas: " + e.getMessage());
        }
    }

    // Função para mostrar os modelos de um veículo, com base no tipo, marca, ano e tipo de combustível
    public void ModelosPorMarca(String tipoVeiculo, int IdMarca) {
        try {
            String urlModelos = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + IdMarca + "/modelos";
            
            System.out.println("Tentando acessar a URL: " + urlModelos); // Depuração
            String respostaModelos = fazerRequisicao(urlModelos);
            System.out.println("Modelos disponíveis para o tipo " + tipoVeiculo + ", marca " + IdMarca +": ");
            System.out.println(respostaModelos);
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

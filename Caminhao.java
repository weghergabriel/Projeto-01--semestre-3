public class Caminhao extends Veiculo {
    public Caminhao(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel, String codigoFipe, String mesReferencia, String acronCombustivel){
        super(veiculoTipo, preco, marcaVeiculo, modelo, anoModelo, combustivel, codigoFipe, mesReferencia, acronCombustivel);
    }
    
    public void setVeiculoTipo(int veiculoTipo){
        this.veiculoTipo = veiculoTipo;
    }

    public void setPreco(String preco){
        this.preco = preco;
    }
    
    public void setMarcaVeiculo(String marcaVeiculo){
        this.marcaVeiculo = marcaVeiculo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public void setAnoModelo(int anoModelo){
        this.anoModelo = anoModelo;
    }

    public void setCombustivel(String combustivel){
        this.combustivel = combustivel;
    }

    public void setCodigoFipe(String codigoFipe){
        this.codigoFipe = codigoFipe;
    }

    public void setMesReferencia(String mesReferencia){
        this.mesReferencia = mesReferencia;
    }

    public void setAcronCombustivel(String acronCombustivel){
        this.acronCombustivel = acronCombustivel;
    }
}

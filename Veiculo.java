public abstract class Veiculo {
    protected int veiculoTipo;
    protected String preco;
    protected String marcaVeiculo;
    protected String modelo;
    protected int anoModelo;
    protected String combustivel;
    protected String codigoFipe;
    protected String mesReferencia;
    protected String acronCombustivel;

    public Veiculo(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel, String codigoFipe, String mesReferencia, String acronCombustivel){
        this.veiculoTipo = veiculoTipo;
        this.preco = preco;
        this.marcaVeiculo = marcaVeiculo;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.combustivel = combustivel;
        this.codigoFipe = codigoFipe;
        this.mesReferencia = mesReferencia;
        this.acronCombustivel = acronCombustivel;
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

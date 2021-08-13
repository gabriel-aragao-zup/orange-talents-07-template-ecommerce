package br.com.zup.mercadolivre.shared.config.validation;

public class ValidationError {

    private String campo;
    private String erro;

    ValidationError(String campo, String erro){
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}

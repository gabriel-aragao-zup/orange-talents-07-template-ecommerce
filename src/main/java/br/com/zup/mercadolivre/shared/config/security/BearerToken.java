package br.com.zup.mercadolivre.shared.config.security;

public class BearerToken {
    private String value;
    private String type = "Bearer";;

    public BearerToken(Token token) {
        this.value = token.getValue();
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}

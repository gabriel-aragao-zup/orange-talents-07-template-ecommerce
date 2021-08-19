package br.com.zup.mercadolivre.shared.config.security.token;

public class BearerToken implements Token{
    private String value;
    private String type = "Bearer";;

    public BearerToken(Token token) {
        this.value = token.getValue();
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}

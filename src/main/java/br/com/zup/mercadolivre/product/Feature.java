package br.com.zup.mercadolivre.product;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Feature {

    @NotBlank
    private String key;
    @NotBlank
    private String value;

    public Feature() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Feature(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return key.equals(feature.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

package br.com.zup.mercadolivre.product.images;

import java.util.Set;

public interface Uploader {

    public Set<String> send(FormImages formImages);
}

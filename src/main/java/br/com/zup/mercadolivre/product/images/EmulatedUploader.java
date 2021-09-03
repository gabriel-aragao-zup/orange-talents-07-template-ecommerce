package br.com.zup.mercadolivre.product.images;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EmulatedUploader implements Uploader {
    public Set<String> send(FormImages formImages) {
        return formImages.getImages()
                .stream()
                .map(image -> "hhtp://bucket.io/"
                        + UUID.randomUUID().toString()
                        +"-"
                        +image.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}

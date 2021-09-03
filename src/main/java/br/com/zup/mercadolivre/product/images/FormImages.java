package br.com.zup.mercadolivre.product.images;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class FormImages {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> images;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormImages(List<MultipartFile> images) {
        this.images = images;
    }

    public List<MultipartFile> getImages() {
        return images;
    }
}

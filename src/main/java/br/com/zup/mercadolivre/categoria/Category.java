package br.com.zup.mercadolivre.categoria;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Category motherCategory;

    @Deprecated
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void setMotherCategory(Category motherCategory) {
        this.motherCategory = motherCategory;
    }
}

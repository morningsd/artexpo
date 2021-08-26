package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

import java.util.Objects;

public class Category implements DataTransferObject {
    private long id;
    private String nameEn;
    private String nameRu;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(nameEn, category.nameEn) && Objects.equals(nameRu, category.nameRu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEn, nameRu);
    }
}

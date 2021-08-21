package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

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
}

package com.augwit.myapp.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Cource entity.
 */
public class CourceDTO implements Serializable {

    private Long id;

    private Long courceId;

    private String courceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourceId() {
        return courceId;
    }

    public void setCourceId(Long courceId) {
        this.courceId = courceId;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourceDTO courceDTO = (CourceDTO) o;
        if(courceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourceDTO{" +
            "id=" + getId() +
            ", courceId='" + getCourceId() + "'" +
            ", courceName='" + getCourceName() + "'" +
            "}";
    }
}

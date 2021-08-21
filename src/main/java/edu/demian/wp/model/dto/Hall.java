package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

import java.util.Objects;

public class Hall implements DataTransferObject {
    private long id;
    private long expositionId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExpositionId() {
        return expositionId;
    }

    public void setExpositionId(long expositionId) {
        this.expositionId = expositionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return id == hall.id && expositionId == hall.expositionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expositionId);
    }

    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", expositionId=" + expositionId +
                '}';
    }
}

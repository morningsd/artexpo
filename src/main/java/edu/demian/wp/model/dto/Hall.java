package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

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

    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", expositionId=" + expositionId +
                '}';
    }
}

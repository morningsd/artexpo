package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

public class Ticket implements DataTransferObject {
    private long id;
    private long expositionId;
    private long purchaseId;
    private int quantity;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getExpositionId() {
        return expositionId;
    }

    public void setExpositionId(long expositionId) {
        this.expositionId = expositionId;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", expositionId=" + expositionId +
                ", purchaseId=" + purchaseId +
                ", quantity=" + quantity +
                '}';
    }
}

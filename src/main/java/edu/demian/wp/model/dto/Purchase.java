package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

public class Purchase implements DataTransferObject {
    private long id;
    private long accountId;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

}

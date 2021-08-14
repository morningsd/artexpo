package edu.demian.wp.model.dto;

public enum Role {
    CLIENT, MODERATOR;

    public static Role getRole(Account account) {
        int roleId = account.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

package edu.demian.wp.model.util;

public interface SQLConstant {
    String SQL_USER_ID = "account_id";
    String SQL_USER_FNAME = "first_name";
    String SQL_USER_LNAME = "last_name";
    String SQL_USER_EMAIL = "email";
    String SQL_USER_ROLE_ID = "account_role";

    String SQL_FIND_USER_BY_ID = "SELECT * FROM account WHERE account_id=?;";
    String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM account WHERE email=? AND passwordSha=?;";
    String SQL_INSERT_USER = "INSERT INTO account (first_name, last_name, email, passwordSha, account_role) " +
            "VALUES(?,?,?,?,?) RETURNING account_id;";
    String SQL_DELETE_USER = "DELETE FROM account WHERE account_id=?;";
    String SQL_UPDATE_USER = "UPDATE account SET first_name=?, last_name=?, email=?, passwordSha=?, account_role=? " +
            "WHERE account_id=?;";
}

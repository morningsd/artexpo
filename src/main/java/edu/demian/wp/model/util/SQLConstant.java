package edu.demian.wp.model.util;

public interface SQLConstant {
    String SQL_USER_ID = "account_id";
    String SQL_USER_FNAME = "first_name";
    String SQL_USER_LNAME = "last_name";
    String SQL_USER_EMAIL = "email";
    String SQL_USER_ROLE_ID = "account_role";

    String SQL_FIND_USER_BY_ID = "SELECT * FROM account WHERE account_id=?";
    String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM account WHERE email=? AND passwordSha=?";
    String SQL_INSERT_USER = "INSERT INTO account (first_name, last_name, email, passwordSha, account_role) " +
            "VALUES(?,?,?,?,?) RETURNING account_id";
    String SQL_DELETE_USER = "DELETE FROM account WHERE account_id=?";
    String SQL_UPDATE_USER = "UPDATE account SET first_name=?, last_name=?, email=?, passwordSha=?, account_role=?" +
            "WHERE account_id=?";

    String SQL_EXPOSITION_ID = "exposition_id";
    String SQL_EXPOSITION_TOPIC = "topic";
    String SQL_EXPOSITION_DESCRIPTION = "description";
    String SQL_EXPOSITION_START_DATE = "start_date";
    String SQL_EXPOSITION_END_DATE = "end_date";
    String SQL_EXPOSITION_START_WORK_TIME = "start_work_time";
    String SQL_EXPOSITION_END_WORK_TIME = "end_work_time";
    String SQL_EXPOSITION_CATEGORY_ID = "exposition_category";
    String SQL_EXPOSITION_PRICE = "price";

    String SQL_FIND_EXPOSITION_BY_ID = "SELECT * FROM exposition WHERE exposition_id=?";
    String SQL_INSERT_EXPOSITION = "INSERT INTO exposition (topic, description, start_date, end_date, start_work_time, end_work_time, exposition_category, price) " +
            "VALUES(?,?,?,?,?,?,?,?) RETURNING exposition_id";
    String SQL_FIND_ALL_EXPOSITIONS = "SELECT * FROM exposition ORDER BY end_date %s, price %s LIMIT ? OFFSET ?";
    String SQL_DELETE_EXPOSITION = "DELETE FROM exposition WHERE exposition_id=?";
    String SQL_UPDATE_EXPOSITION = "UPDATE exposition SET topic=?, description=?, start_date=?, end_date=?, start_work_time=?, end_work_time=?, exposition_category=?, price=? WHERE exposition_id=?";
    String SQL_FIND_EXPOSITION_BY_TOPIC = "SELECT * FROM exposition WHERE topic ILIKE ? ORDER BY end_date %s, price %s " +
            "LIMIT ? OFFSET ?";
    String SQL_FIND_EXPOSITION_BY_TOPIC_AND_CATEGORY = "SELECT * FROM exposition WHERE topic ILIKE ? AND exposition_category IN (%s) " +
            "ORDER BY end_date %s, price %s LIMIT ? OFFSET ?";
    String SQL_FIND_EXPOSITION_BY_CATEGORY = "SELECT * FROM exposition WHERE exposition_category IN (%s) " +
            "ORDER BY end_date %s, price %s LIMIT ? OFFSET ?";
    String SQL_FIND_ALL_NUM_OF_ROWS = "SELECT COUNT(*) FROM exposition";
    String SQL_FIND_BY_CATEGORY_NUM_OF_ROWS = "SELECT COUNT(*) FROM exposition WHERE exposition_category IN (%s)";
    String SQL_FIND_BY_TOPIC_NUM_OF_ROWS = "SELECT COUNT(*) FROM exposition WHERE topic ILIKE ?";
    String SQL_FIND_BY_TOPIC_AND_CATEGORY_NUM_OF_ROWS = "SELECT COUNT(*) FROM exposition WHERE topic ILIKE ? AND exposition_category IN (%s)";

    String SQL_CATEGORY_ID = "category_id";
    String SQL_CATEGORY_NAME_EN = "name_en";
    String SQL_CATEGORY_NAME_RU = "name_ru";

    String SQL_FIND_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id=?";
    String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM category";
    String SQL_DELETE_CATEGORY = "DELETE FROM category WHERE category_id=?";
    String SQL_INSERT_CATEGORY = "INSERT INTO category (name_en, name_ru) VALUES(?,?) RETURNING category_id";

    String SQL_HALL_ID = "hall_id";
    String SQL_HALL_EXPOSITION_ID = "hall_exposition";

    String SQL_FIND_HALL_BY_ID = "SELECT * FROM hall WHERE hall_id=?";
    String SQL_FIND_ALL_EXPOSITION_HALLS = "SELECT * FROM hall WHERE hall_exposition=?";
    String SQL_FIND_ALL_HALLS = "SELECT * FROM hall";
    String SQL_FIND_FREE_HALLS = "SELECT * FROM hall WHERE hall_exposition IS NULL";
    String SQL_UPDATE_HALL = "UPDATE hall SET hall_exposition=? WHERE hall_id=?";

    String SQL_TICKET_ID = "ticket_id";
    String SQL_TICKET_EXPOSITION_ID = "ticket_exposition";
    String SQL_TICKET_ACCOUNT_ID = "ticket_account";
    String SQL_TICKET_QUANTITY = "quantity";
    String SQL_TICKET_TOTAL_PRICE = "total_price";
    String SQL_TICKET_CREATION_DATE = "creation_date";

    String SQL_INSERT_TICKET = "INSERT INTO ticket (ticket_exposition, ticket_account, quantity, total_price) " +
            "VALUES(?,?,?,?) RETURNING ticket_id";
    String SQL_FIND_ACCOUNT_TICKETS = "SELECT * FROM ticket WHERE ticket_account=?";
    String SQL_FIND_EXPOSITION_TICKETS = "SELECT * FROM ticket WHERE ticket_exposition=?";
}

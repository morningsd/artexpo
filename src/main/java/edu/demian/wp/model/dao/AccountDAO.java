package edu.demian.wp.model.dao;

import com.google.common.hash.Hashing;
import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dto.Account;
import edu.demian.wp.model.util.DTOMapper;
import edu.demian.wp.model.util.DataAccessObject;
import edu.demian.wp.model.util.DataTransferObject;
import edu.demian.wp.model.util.SQLConstant;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO extends DataAccessObject<Account> {

    public AccountDAO(Connection con) {
        super(con);
    }

    @Override
    public Account findById(long id) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return account;
    }

    public Account findByEmailAndPassword(String email, String password) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_USER_BY_EMAIL_AND_PASSWORD);
            pstmt.setString(1, email);
            pstmt.setString(2, getPasswordSha(password));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account update(Account dto) {
        return null;
    }

    @Override
    public Account create(Account dto) {
        return null;
    }

    public Account create(Account dto, String password) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQLConstant.SQL_INSERT_USER);
            int k = 1;
            pstmt.setString(k++, dto.getFirstName());
            pstmt.setString(k++, dto.getLastName());
            pstmt.setString(k++, dto.getEmail());
            pstmt.setString(k++, getPasswordSha(password));
            pstmt.setInt(k, dto.getRoleId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setId(rs.getLong("account_id"));
            } else {
                throw new SQLException("Can't insert account into db");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return dto;
    }

    @Override
    public void delete(long id) {

    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    private static class AccountMapper implements DTOMapper<Account> {

        public Account mapRow(ResultSet rs) {
            try {
                Account account = new Account();
                account.setId(rs.getLong(SQLConstant.SQL_USER_ID));
                account.setFirstName(rs.getString(SQLConstant.SQL_USER_FNAME));
                account.setLastName(rs.getString(SQLConstant.SQL_USER_LNAME));
                account.setEmail(rs.getString(SQLConstant.SQL_USER_EMAIL));
                account.setRoleId(rs.getInt(SQLConstant.SQL_USER_ROLE_ID));
                return account;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

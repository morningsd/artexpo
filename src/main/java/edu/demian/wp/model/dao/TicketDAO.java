package edu.demian.wp.model.dao;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dto.Account;
import edu.demian.wp.model.dto.Ticket;
import edu.demian.wp.model.util.DTOMapper;
import edu.demian.wp.model.util.DataAccessObject;
import edu.demian.wp.model.util.SQLConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO extends DataAccessObject<Ticket> {

    @Override
    public Ticket findById(long id) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    public List<Ticket> findByAccountId(long id) {
        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            TicketMapper mapper = new TicketMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_ACCOUNT_TICKETS);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tickets.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return tickets;
    }

    public List<Ticket> findByExpositionId(long id) {
        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = DBManager.getInstance().getConnection();
        try {
            con = DBManager.getInstance().getConnection();
            TicketMapper mapper = new TicketMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_EXPOSITION_TICKETS);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tickets.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return tickets;
    }

    @Override
    public Ticket update(Ticket dto) {
        return null;
    }

    @Override
    public Ticket create(Ticket dto) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQLConstant.SQL_INSERT_TICKET);
            int k = 1;
            pstmt.setLong(k++, dto.getExpositionId());
            pstmt.setLong(k++, dto.getAccountId());
            pstmt.setInt(k++, dto.getQuantity());
            pstmt.setBigDecimal(k, dto.getTotalPrice());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setId(rs.getLong(SQLConstant.SQL_TICKET_ID));
            } else {
                throw new SQLException("Can't insert ticket into db");
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

    private static class TicketMapper implements DTOMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs) {
            try {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong(SQLConstant.SQL_TICKET_ID));
                ticket.setExpositionId(rs.getLong(SQLConstant.SQL_TICKET_EXPOSITION_ID));
                ticket.setAccountId(rs.getLong(SQLConstant.SQL_TICKET_ACCOUNT_ID));
                ticket.setQuantity(rs.getInt(SQLConstant.SQL_TICKET_QUANTITY));
                ticket.setTotalPrice(rs.getBigDecimal(SQLConstant.SQL_TICKET_TOTAL_PRICE));
                ticket.setCreationDate(rs.getObject(SQLConstant.SQL_TICKET_CREATION_DATE, LocalDateTime.class));
                return ticket;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

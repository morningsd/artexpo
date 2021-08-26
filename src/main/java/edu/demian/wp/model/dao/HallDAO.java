package edu.demian.wp.model.dao;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dto.Hall;
import edu.demian.wp.model.util.DataAccessObject;
import edu.demian.wp.model.util.SQLConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HallDAO extends DataAccessObject<Hall> {

    @Override
    public Hall findById(long id) {
        Hall hall = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_HALL_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                hall = new Hall();
                hall.setId(rs.getLong(SQLConstant.SQL_HALL_ID));
                hall.setExpositionId(rs.getLong(SQLConstant.SQL_HALL_EXPOSITION_ID));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return hall;
    }

    @Override
    public List<Hall> findAll() {
        List<Hall> halls = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLConstant.SQL_FIND_ALL_HALLS);
            while (rs.next()) {
                Hall hall = new Hall();
                hall.setId(rs.getLong(SQLConstant.SQL_HALL_ID));
                hall.setExpositionId(rs.getLong(SQLConstant.SQL_HALL_EXPOSITION_ID));
                halls.add(hall);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return halls;
    }

    public List<Hall> findFreeHalls() {
        List<Hall> freeHalls = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLConstant.SQL_FIND_FREE_HALLS);
            while (rs.next()) {
                Hall hall = new Hall();
                hall.setId(rs.getLong(SQLConstant.SQL_HALL_ID));
                hall.setExpositionId(rs.getLong(SQLConstant.SQL_HALL_EXPOSITION_ID));
                freeHalls.add(hall);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return freeHalls;
    }

    @Override
    public Hall update(Hall dto) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLConstant.SQL_UPDATE_HALL);
            int k = 1;
            if (dto.getExpositionId() == 0L) {
                pstmt.setNull(k++, Types.BIGINT);
            } else {
                pstmt.setLong(k++, dto.getExpositionId());
            }
            pstmt.setLong(k, dto.getId());

            pstmt.executeUpdate();
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
    public Hall create(Hall dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    public List<Hall> expositionHallList(long id) {
        List<Hall> hallList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_ALL_EXPOSITION_HALLS);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Hall hall = new Hall();
                hall.setId(rs.getLong(SQLConstant.SQL_HALL_ID));
                hall.setExpositionId(rs.getLong(SQLConstant.SQL_HALL_EXPOSITION_ID));
                hallList.add(hall);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return hallList;
    }
}

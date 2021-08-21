package edu.demian.wp.model.dao;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dto.Exposition;
import edu.demian.wp.model.dto.Hall;
import edu.demian.wp.model.util.DTOMapper;
import edu.demian.wp.model.util.DataAccessObject;
import edu.demian.wp.model.util.SQLConstant;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ExpositionDAO extends DataAccessObject<Exposition> {

    public ExpositionDAO(Connection con) {
        super(con);
    }

    @Override
    public Exposition findById(long id) {
        Exposition exposition = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ExpositionMapper mapper = new ExpositionMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_EXPOSITION_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                exposition = mapper.mapRow(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return exposition;
    }

    @Override
    public List<Exposition> findAll() {
        List<Exposition> expositions = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            ExpositionMapper mapper = new ExpositionMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLConstant.SQL_FIND_ALL_EXPOSITIONS);
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return expositions;
    }

    @Override
    public Exposition update(Exposition dto) {
        try {
            PreparedStatement pstmt = con.prepareStatement(SQLConstant.SQL_UPDATE_EXPOSITION);
            int k = 1;
            pstmt.setString(k++, dto.getTopic());
            pstmt.setString(k++, dto.getDescription());
            pstmt.setObject(k++, dto.getStartDate());
            pstmt.setObject(k++, dto.getEndDate());
            pstmt.setObject(k++, dto.getStartWorkTime());
            pstmt.setObject(k++, dto.getEndWorkTime());
            pstmt.setLong(k++, dto.getCategoryId());
            pstmt.setBigDecimal(k++, dto.getPrice());
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
    public Exposition create(Exposition dto) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQLConstant.SQL_INSERT_EXPOSITION);
            int k = 1;
            pstmt.setString(k++, dto.getTopic());
            pstmt.setString(k++, dto.getDescription());
            pstmt.setObject(k++, dto.getStartDate());
            pstmt.setObject(k++, dto.getEndDate());
            pstmt.setObject(k++, dto.getStartWorkTime());
            pstmt.setObject(k++, dto.getEndWorkTime());
            pstmt.setLong(k++, dto.getCategoryId());
            pstmt.setBigDecimal(k, dto.getPrice());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setId(rs.getLong(SQLConstant.SQL_EXPOSITION_ID));
            } else {
                throw new SQLException("Can't insert exposition into db");
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
        try {
            PreparedStatement pstmt = con.prepareStatement(SQLConstant.SQL_DELETE_EXPOSITION);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
    }

    public List<Exposition> findByTopic(String topic) {
        List<Exposition> expositions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ExpositionMapper mapper = new ExpositionMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_EXPOSITION_BY_TOPIC);
            pstmt.setString(1, "%" + topic + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return expositions;
    }

    public List<Exposition> findByTopicAndCategory(String topic, long categoryId) {
        List<Exposition> expositions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ExpositionMapper mapper = new ExpositionMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_EXPOSITION_BY_TOPIC_AND_CATEGORY);
            pstmt.setString(1, "%" + topic + "%");
            pstmt.setLong(2, categoryId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return expositions;
    }

    public List<Exposition> findByCategory(long categoryId) {
        List<Exposition> expositions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ExpositionMapper mapper = new ExpositionMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_EXPOSITION_BY_CATEGORY);
            pstmt.setLong(1, categoryId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return expositions;
    }

    private static class ExpositionMapper implements DTOMapper<Exposition> {

        @Override
        public Exposition mapRow(ResultSet rs) {
            try {
                Exposition exposition = new Exposition();
                exposition.setId(rs.getLong(SQLConstant.SQL_EXPOSITION_ID));
                exposition.setTopic(rs.getString(SQLConstant.SQL_EXPOSITION_TOPIC));
                exposition.setDescription(rs.getString(SQLConstant.SQL_EXPOSITION_DESCRIPTION));
                exposition.setStartDate(rs.getObject(SQLConstant.SQL_EXPOSITION_START_DATE, LocalDate.class));
                exposition.setEndDate(rs.getObject(SQLConstant.SQL_EXPOSITION_END_DATE, LocalDate.class));
                exposition.setStartWorkTime(rs.getObject(SQLConstant.SQL_EXPOSITION_START_WORK_TIME, LocalTime.class));
                exposition.setEndWorkTime(rs.getObject(SQLConstant.SQL_EXPOSITION_END_WORK_TIME, LocalTime.class));
                exposition.setCategoryId(rs.getLong(SQLConstant.SQL_EXPOSITION_CATEGORY_ID));
                exposition.setPrice(rs.getBigDecimal(SQLConstant.SQL_EXPOSITION_PRICE));
                exposition.setHallList(new HallDAO(DBManager.getInstance().getConnection()).expositionHallList(exposition.getId()));
                return exposition;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

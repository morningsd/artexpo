package edu.demian.wp.model.dao;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dto.Exposition;
import edu.demian.wp.model.util.DTOMapper;
import edu.demian.wp.model.util.DataAccessObject;
import edu.demian.wp.model.util.SQLConstant;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ExpositionDAO extends DataAccessObject<Exposition> {

    @Override
    public Exposition findById(long id) {
        Exposition exposition = null;
        PreparedStatement pstmt;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ExpositionMapper mapper = new ExpositionMapper();
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_EXPOSITION_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                exposition = mapper.mapRow(rs);
            }
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
        return null;
    }

    public List<Exposition> findAll(String dateOrder, String priceOrder, int limit, long offset) {
        List<Exposition> expositions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ExpositionMapper mapper = new ExpositionMapper();
            String sql = String.format(SQLConstant.SQL_FIND_ALL_EXPOSITIONS, dateOrder, priceOrder);
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, limit);
            pstmt.setLong(2, offset);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
            rs.close();
            pstmt.close();
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
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
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
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
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
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
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

    public List<Exposition> findByTopic(String topic, String dateOrder, String priceOrder, int limit, long offset) {
        List<Exposition> expositions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ExpositionMapper mapper = new ExpositionMapper();
            String sql = String.format(SQLConstant.SQL_FIND_EXPOSITION_BY_TOPIC, dateOrder, priceOrder);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + topic + "%");
            pstmt.setInt(2, limit);
            pstmt.setLong(3, offset);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return expositions;
    }

    public List<Exposition> findByTopicAndCategory(String topic, long[] categoryIdArr,
                                                   String dateOrder, String priceOrder,
                                                   int limit, long offset) {
        List<Exposition> expositions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ExpositionMapper mapper = new ExpositionMapper();
            String markers = ",?".repeat(categoryIdArr.length).substring(1);
            String sql = String.format(SQLConstant.SQL_FIND_EXPOSITION_BY_TOPIC_AND_CATEGORY, markers, dateOrder, priceOrder);
            pstmt = con.prepareStatement(sql);
            int k = 1;
            pstmt.setString(k++, "%" + topic + "%");
            for (long l : categoryIdArr) {
                pstmt.setLong(k++, l);
            }
            pstmt.setInt(k++, limit);
            pstmt.setLong(k, offset);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return expositions;
    }

    public List<Exposition> findByCategory(long[] categoryIdArr,
                                           String dateOrder, String priceOrder,
                                           int limit, long offset) {
        List<Exposition> expositions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ExpositionMapper mapper = new ExpositionMapper();
            String markers = ",?".repeat(categoryIdArr.length).substring(1);
            String sql = String.format(SQLConstant.SQL_FIND_EXPOSITION_BY_CATEGORY, markers, dateOrder, priceOrder);
            pstmt = con.prepareStatement(sql);
            int k = 1;
            for (long l : categoryIdArr) {
                pstmt.setLong(k++, l);
            }
            pstmt.setInt(k++, limit);
            pstmt.setLong(k, offset);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expositions.add(mapper.mapRow(rs));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return expositions;
    }

    public long getNumOfRowsFindAll() {
        Connection con = null;
        long result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLConstant.SQL_FIND_ALL_NUM_OF_ROWS);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getLong(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return result;
    }

    public long getNumOfRowsFindByCategory(long[] categoryIdArr) {
        Connection con = null;
        long result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String markers = ",?".repeat(categoryIdArr.length).substring(1);
            String sql = String.format(SQLConstant.SQL_FIND_BY_CATEGORY_NUM_OF_ROWS, markers);
            PreparedStatement pstmt = con.prepareStatement(sql);
            int k = 1;
            for (long l : categoryIdArr) {
                pstmt.setLong(k++, l);
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getLong(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return result;
    }

    public long getNumOfRowsFindByTopic(String topic) {
        Connection con = null;
        long result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLConstant.SQL_FIND_BY_TOPIC_NUM_OF_ROWS);
            pstmt.setString(1, "%" + topic + "%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getLong(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return result;
    }

    public long getNumOfRowsFindByTopicAndCategory(String topic, long[] categoryIdArr) {
        Connection con = null;
        long result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String markers = ",?".repeat(categoryIdArr.length).substring(1);
            String sql = String.format(SQLConstant.SQL_FIND_BY_TOPIC_AND_CATEGORY_NUM_OF_ROWS, markers);
            PreparedStatement pstmt = con.prepareStatement(sql);
            int k = 1;
            pstmt.setString(k++, "%" + topic + "%");
            for (long l : categoryIdArr) {
                pstmt.setLong(k++, l);
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getLong(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return result;
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
                exposition.setHallList(new HallDAO().expositionHallList(exposition.getId()));
                return exposition;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

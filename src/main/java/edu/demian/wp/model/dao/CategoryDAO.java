package edu.demian.wp.model.dao;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dto.Category;
import edu.demian.wp.model.util.DataAccessObject;
import edu.demian.wp.model.util.SQLConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DataAccessObject<Category> {
    public CategoryDAO(Connection con) {
        super(con);
    }

    @Override
    public Category findById(long id) {
        Category category = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQLConstant.SQL_FIND_CATEGORY_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category.setId(rs.getLong(SQLConstant.SQL_CATEGORY_ID));
                category.setNameEn(rs.getString(SQLConstant.SQL_CATEGORY_NAME_EN));
                category.setNameRu(rs.getString(SQLConstant.SQL_CATEGORY_NAME_RU));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLConstant.SQL_FIND_ALL_CATEGORIES);
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong(SQLConstant.SQL_CATEGORY_ID));
                category.setNameEn(rs.getString(SQLConstant.SQL_CATEGORY_NAME_EN));
                category.setNameRu(rs.getString(SQLConstant.SQL_CATEGORY_NAME_RU));
                categories.add(category);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitClose(con);
        }
        return categories;
    }

    @Override
    public Category update(Category dto) {
        return null;
    }

    @Override
    public Category create(Category dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}

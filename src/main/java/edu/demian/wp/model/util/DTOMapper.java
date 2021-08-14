package edu.demian.wp.model.util;

import java.sql.ResultSet;

public interface DTOMapper<T> {
    T mapRow(ResultSet rs);
}

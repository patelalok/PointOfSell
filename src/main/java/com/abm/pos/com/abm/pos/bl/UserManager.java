package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.UserDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by asp5045 on 5/25/16.
 */
@Component
public class UserManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addUserToDB(UserDto userDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addUserQuery,
                    userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.getUserRole(),
                    userDto.getCreatedDate());
            System.out.println("User Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editUserToDB(UserDto userDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editUserQuery,
                    userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.getUserRole(),
                    userDto.getCreatedDate());
            System.out.println("USer Edited Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void getUserDetails(String userId) {

        try {
            jdbcTemplate.query(sqlQuery.getUserDetails, new UserManager.AddUserMapper());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private static final class AddUserMapper implements RowMapper<UserDto>
    {

        @Override
        public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            UserDto user = new UserDto();

            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setUserRole(rs.getString("USER_ROLE"));
            user.setCreatedDate(rs.getString("USER_CREATED_DATE"));

            return user;
        }
    }

    public void deleteVendorToDB(String vendorId) {
    }


}

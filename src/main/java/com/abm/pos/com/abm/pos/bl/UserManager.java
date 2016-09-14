package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.UserClockInDto;
import com.abm.pos.com.abm.pos.dto.UserDto;
import com.abm.pos.com.abm.pos.dto.UserLogin;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

                    userDto.getPassword(),
                    userDto.getUserRole(),
                    userDto.getUsername());

            System.out.println("User Edited Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public UserLogin getUserLoginDetails(String username, String password) {

        List<UserDto> user = new ArrayList<>();

        UserLogin userLogin = new UserLogin();
        boolean response = false;
        try
        {
            user = jdbcTemplate.query(sqlQuery.getUserDetails, new UserManager.AddUserMapper());

            for(int i = 0; i<= user.size(); i++)
            {
                UserDto u = user.get(i);

                if(u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password))
                {
                    userLogin.setValidUser(true);
                    userLogin.setUserRole(u.getUserRole());
                    userLogin.setUserId(u.getUserId());
                    break;
                }
                else
                {
                    userLogin.setValidUser(false);
                    userLogin.setUserRole(null);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return userLogin;
    }



    private static final class AddUserMapper implements RowMapper<UserDto>
    {

        @Override
        public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            UserDto user = new UserDto();

            user.setUserId(rs.getInt("USER_ID"));
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setUserRole(rs.getString("USER_ROLE"));
            user.setCreatedDate(rs.getString("USER_CREATED_DATE"));

            return user;
        }
    }

    public List<UserDto> getUserDetails() {

        List<UserDto> user = new ArrayList<>();

        try
        {
            user = jdbcTemplate.query(sqlQuery.getUserDetails, new UserManager.AddUserMapper());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return user;
        }

    //ADDING CLOCK IN AND CLOCK OUT TIME OF THE USER GETTING AT THE ONLY LOGOUT TIME.
    public boolean addUserClockIn(UserClockInDto userClockIn) {

        boolean response = false;
        try
        {

            UserLogin userLogin = new UserLogin();

            userLogin = getUserLoginDetails(userClockIn.getUsername(), userClockIn.getPassword());

            if(userLogin.isValidUser()) {


                jdbcTemplate.update(sqlQuery.addUserClockIn,
                        userClockIn.getUsername(),
                        userClockIn.getClockInTime(),
                        userClockIn.getDate());

                response = true;
                System.out.println("User Clocked in Successfully");
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return response;

    }

    public boolean addClockOut(UserClockInDto userDto) {

        boolean response = false;


        try
        {

            UserLogin userLogin = new UserLogin();

            userLogin = getUserLoginDetails(userDto.getUsername(), userDto.getPassword());

            if(userLogin.isValidUser()) {


                jdbcTemplate.update(sqlQuery.updateUserClockInDetails,
                        userDto.getClockOutTime(),
                        userDto.getNoOfhours(),
                        userDto.getClockInId());
                response = true;

                System.out.println("User Clocked Out in Successfully");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return response;

    }
//TO GET CLOCK IN AND CLOCK OUT DETAILS OF USER.
    public List<UserClockInDto> getUserClockIn(String username, String date) {

        List<UserClockInDto> userClockInDtoList = new ArrayList<>();

        try
        {
            userClockInDtoList = jdbcTemplate.query(sqlQuery.getUserClockInDetails, new UserClockInMapper(), username,date );
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return userClockInDtoList;
    }


    public List<UserClockInDto> getUserClockInForSetup(String username, String startDate, String endDate) {

        List<UserClockInDto> userClockInDtoList = new ArrayList<>();

        try
        {
            userClockInDtoList = jdbcTemplate.query(sqlQuery.getUserClockInForSetup, new UserClockInMapper(), username,startDate,endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return userClockInDtoList;
    }

    private static final class UserClockInMapper implements RowMapper<UserClockInDto>
    {

        @Override
        public UserClockInDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            UserClockInDto user = new UserClockInDto();


            user.setClockInId(rs.getInt("USER_CLOCK_IN_ID"));
            user.setUsername(rs.getString("USERNAME"));
            user.setClockInTime(rs.getString("CLOCK_IN"));
            user.setClockOutTime(rs.getString("CLOCK_OUT"));
            user.setNoOfhours(rs.getString("NOOFHOURS"));
            user.setDate(rs.getString("DATE"));

            return user;
        }
    }

    public void deleteVendorToDB(String vendorId) {
    }


}

package com.baobaotao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.User;

// After spring 2.5, annotation is able to use inject bean like the way, configuration from the xml.
// Here Dao bean is defined by @Repository
@Repository
public class UserDao {

    // By using the annotation, '@Autowired', the Bean of the Spring container is injected.
    // JDBC API must work as following procedure: accept connect, create statement, execute data operation,
    // obtain results, close statement and result set, close connect, and exception operation.

    // A DataSource is needed to be created to get or return connect, by which JDBCTemplate

    // SpringJDBC elevate all this API of JDBC to discouple of the code. Simply, SpringJDBC is a template class though
    // which developer is able to access data easily.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password) {
        String sqlStr = " SELECT count(*) FROM t_user "
                + " WHERE user_name =? and password=? ";
        return jdbcTemplate.queryForInt(sqlStr, new Object[] { userName, password });
    }


    // ********************************************Callback*************************************** /
    public User findUserByUserName(final String userName) {
        String sqlStr = " SELECT user_id,user_name,credits "
                + " FROM t_user WHERE user_name =? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[] { userName },
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sqlStr = " UPDATE t_user SET last_visit=?,last_ip=?,credits=? "
                + " WHERE user_id =?";
        jdbcTemplate.update(sqlStr, new Object[] { user.getLastVisit(),
                user.getLastIp(),user.getCredits(),user.getUserId()});
    }

    public void insertRegisterUserInfo(User user) {
        String sqlStr = " INSERT INTO t_user SET user_name=?,password=?,last_visit=?,last_ip=?,credits=? "
                + " user_id=>";
        jdbcTemplate.update(sqlStr, new Object[] { user.getUserName(),
                user.getPassword(),
                user.getLastVisit(),
                user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}

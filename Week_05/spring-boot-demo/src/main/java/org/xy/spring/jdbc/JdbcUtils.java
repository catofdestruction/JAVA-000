package org.xy.spring.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xy.spring.jdbc.model.ConcurrentResult;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * jdbc utils
 * ref: https://juejin.im/post/6844904195204595720#heading-4
 *
 * @author wangxinyu
 * @date 2020/11/18
 */
@Slf4j
@Getter
@Component
public class JdbcUtils {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static DataSource dataSource = null;

    @Value("${spring.datasource.driver-class-name}")
    public void setDriver(String driver) {
        JdbcUtils.driver = driver;
    }

    @Value("${spring.datasource.url}")
    public void setUrl(String url) {
        JdbcUtils.url = url;
    }

    @Value("${spring.datasource.username}")
    public void setUsername(String username) {
        JdbcUtils.username = username;
    }

    @Value("${spring.datasource.password}")
    public void setPassword(String password) {
        JdbcUtils.password = password;
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            // 连接超时：5秒
            config.addDataSourceProperty("connectionTimeout", "5000");
            // 空闲超时：60秒
            config.addDataSourceProperty("idleTimeout", "60000");
            // 最大连接数：10
            config.addDataSourceProperty("maximumPoolSize", "10");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "100");
            config.setDriverClassName(driver);
            config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }

    /**
     * batch insert
     * ref: https://www.liaoxuefeng.com/wiki/1252599548343744/1322290857902113
     * ref: https://www.liaoxuefeng.com/wiki/1252599548343744/1321748500840481
     *
     * @param results List of ConcurrentResult
     * @param showSql showSql
     * @throws SQLException SQLException
     */
    public static void batchInsert(List<ConcurrentResult> results, boolean showSql) throws SQLException {
        Connection connection = getDataSource().getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // 关闭自动提交事务
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO concurrent_trip (name, duration, result) VALUES (?, ?, ?)")) {
                // 对同一个PreparedStatement反复设置参数并调用addBatch():
                for (ConcurrentResult r : results) {
                    preparedStatement.setString(1, r.getName());
                    preparedStatement.setInt(2, r.getDuration());
                    preparedStatement.setInt(3, r.getResult());
                    if (showSql) {
                        log.info("\n> {}\n", preparedStatement.toString());
                    }
                    // 添加到batch
                    preparedStatement.addBatch();
                }
                // 执行batch:
                preparedStatement.executeBatch();
            }
            // 提交事务:
            connection.commit();
        } catch (SQLException throwable) {
            // 回滚事务:
            connection.rollback();
            throwable.printStackTrace();
            log.warn("roll back!!!");
        } finally {
            // 恢复AutoCommit设置
            connection.setAutoCommit(true);
            connection.close();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        close(stmt, conn);

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
package ua.sevenLevelLabs.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sevenLevelLabs.model.Car;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Tatiana Marchuk
 *         16.03.16 : 11:11
 */
@Repository
@Transactional(readOnly = true)
public class CarDaoImpl implements CarDao {

    private static final Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);

    private static final BeanPropertyRowMapper<Car> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Car.class);

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate getJdbcTemplate() {

        return new JdbcTemplate(dataSource);
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

package ua.sevenLevelLabs.repository;

import java.sql.Connection;

/**
 * @author Tatiana Marchuk
 *         16.03.16 : 11:11
 */
public interface CarDao {
    Connection getConnection();
}

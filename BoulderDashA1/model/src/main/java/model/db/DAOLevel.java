package model.db;

import model.level.Level;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Dao level
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */

public class DAOLevel extends DAOData<Level> {

    /**
     * Constructor for DAOLevel
     * @param connection connection to database
     * @throws SQLException retrieve the sql errors
     */
    public DAOLevel(final Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public Level find(final int id) {
        Level level = new Level();

        try {
            final String sql = "{call SelectID(?)}";
            final CallableStatement call = this.getConnection().prepareCall(sql);
            call.setInt(1, id);
            call.execute();
            final ResultSet resultSet = call.getResultSet();
            if (resultSet.first()) {
                level = new Level(id, resultSet.getInt("time"), resultSet.getInt("number_diamond"), resultSet.getString("level_map"));
            }
            return level;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

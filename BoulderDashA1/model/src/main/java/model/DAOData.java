package model;

import entity.Data;
import java.sql.Connection;
import java.sql.SQLException;

    /**
     * The Class DAOData.
     *
     * @author CANDAT ETIENNE AND DENEUVE GREGORY
     *
     * @param <D>
     *          the element type
     */
    abstract class DAOData<D extends Data> {

        /** The connection. */
        private final Connection connection;

        /**
         * Instantiates a new DAO entity.
         *
         * @param connection
         *          the connection
         * @throws SQLException
         *           the SQL exception
         */
        public DAOData(final Connection connection) throws SQLException {
            this.connection = connection;
        }

        /**
         * Gets the connection.
         *
         * @return the connection
         */
        protected Connection getConnection() {
            return this.connection;
        }



        public abstract D find(int id);

    }



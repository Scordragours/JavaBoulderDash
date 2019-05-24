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

        /**
         * Creates the.
         *
         * @param Data
         *          the entity
         * @return true, if successful
         */
        public abstract boolean create(D Data);

        /**
         * Delete.
         *
         * @param Data
         *          the entity
         * @return true, if successful
         */
        public abstract boolean delete(D Data);

        /**
         * Update.
         *
         * @param Data
         *          the entity
         * @return true, if successful
         */
        public abstract boolean update(D Data);

        /**
         * Find.
         *
         * @param id
         *          the id
         * @return the e
         */
        public abstract D find(int id);

        /**
         * Find.
         *
         * @param code
         *          the code
         * @return the e
         */
        public abstract D find(String code);

    }




package mvc.util;

import java.sql.*;

public class DBReset {

    private static String dbUrl;

    static {
        dbUrl = System.getenv("DATABASE_URL");
        dbUrl = dbUrl.replaceAll("postgres://(.*):(.*)@(.*)", "jdbc:postgresql://$3?user=$1&password=$2");
    }


    private static void dbUpdate(String sql) throws SQLException {
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection(dbUrl);
            Statement stmt = dbConn.createStatement();
            stmt.executeUpdate(sql);
        } finally {
            if (dbConn != null) dbConn.close();
        }
    }



    public static void createTable() throws SQLException {
        System.out.println("Creating ticks table.");
	dbUpdate("CREATE SCHEMA spitter;");
        dbUpdate("DROP TABLE IF EXISTS spitter.spitter, spitter.Spittle ;");
        dbUpdate("DROP TABLE IF EXISTS spitter.Spittle ;");
        dbUpdate("CREATE TABLE spitter.Spitter ( id integer PRIMARY KEY, name varchar(40) );");
        dbUpdate("CREATE TABLE spitter.Spittle ( id integer PRIMARY KEY, spitter varchar(40), content varchar(40), spitter_id integer );");
    }

}

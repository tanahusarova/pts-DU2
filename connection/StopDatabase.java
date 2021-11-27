package connection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Vector;

public class StopDatabase extends Stop{
    private Connection connection;


    public StopDatabase(StopName stopName, Connection connection) {
        super(stopName);
        this.connection = connection;
    }

    @Override
    public void search(){
        String sql = "SELECT DISTINCT lineName" + "FROM lines" +
                "JOIN linesStops ON lines.lineId = linesStops.lineId" +
                "WHERE stopName = " + stopName.toString();

        Vector<LineName> lines = new Vector<>();

        try (Connection conn = this.connection;
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                lines.add(new LineName(rs.getString("lineName")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.lines = lines;
    }



}


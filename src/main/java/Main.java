import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // creating list of quadratic equations
        List<Quadratic> quadratics=new ArrayList<>();
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        // reading sql file
        try{
            connection = ConnectionPool.getInstance().retrieve();
            ps = connection.prepareStatement("SELECT * FROM equations");
            rs = ps.executeQuery();
            while(rs.next()){
                // placing data inside quadratic equation list
                quadratics.add(new Quadratic(rs.getInt("a"),rs.getInt("b"),rs.getInt("c")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // closing the connections
            try {
                if(connection!=null) connection.close();
                if(ps!=null) ps.close();
                if(rs!=null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // writing data into the file.
        try(FileWriter fileWriter = new FileWriter("src/main/resources/Result.txt")) {
            int i=1;
            for (Quadratic qe: quadratics) {
                fileWriter.write("#"+i+"\t"+qe+"\n");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

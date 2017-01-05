package wasdev.sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");
         // response.getWriter().print("Hello World!")
        Context initContext;
        try {
            initContext = new InitialContext();
            DataSource ds = (DataSource)initContext.lookup("jdbc/dashdb_tx_sql_2");
            Connection conn = ds.getConnection();

             //System.out.println("yes");
            response.getWriter().print("DashDB is connected now!</br>");
            response.getWriter().print("</br>Dogs table:</br> NAME, AGE, DOGTYPE, COST</br>");
            Statement stmt = conn.createStatement();
 
            stmt.execute("DELETE FROM DOGS WHERE ID = 7");
            stmt.execute("INSERT INTO DOGS VALUES (7, 'NEW', 6, 'NEW', 100.00 )");
            stmt.executeQuery("SELECT * FROM DOGS");
            ResultSet rs = stmt.getResultSet();
            System.out.println("yes");
            while(rs.next()) {
                    response.getWriter().print(rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</br>");
            }
            stmt.close();
            conn.close();
            } catch (NamingException | SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }

}

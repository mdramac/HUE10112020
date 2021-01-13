package application.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class AccessDb {

    static {
        try {
            Class.forName(("net.ucanaccess.jdbc.UcanaccessDriver"));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private static Connection connection =null;

    //Verinfache Singleton-Pattern
    public  static  Connection getConnection(){
        //Wenn connection nicht befüllt wurde ((connection= null)
        //da es sich um eine statische Variale handelt, ist diese
        //in allen Objektinstanzen glaiech"
        if (connection == null){
            try{

                connection= DriverManager.getConnection("jdbc:ucanaccess://C:/Schule/3AHITN_SEW/HUE10112020/db/Tickets_System.accdb");

            }catch (SQLException throwables){

            }
        }
        return connection;
    }
}

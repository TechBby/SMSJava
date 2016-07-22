/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Harvey
 */
public class DatabaseHelper {

    public Connection connection;
    public ResultSet resultSet;
    public ResultSetMetaData metaData;
    public int numberOfRows;
    public Statement statement;
     public boolean connectedToDatabase;
     public ResultSet result;
     public ResultSetMetaData metadata;

    public DatabaseHelper() throws SQLException {

        this.numberOfRows = 0;
        this.statement = null;
        this.connectedToDatabase = false;
        this.resultSet = null;
        this.metaData = null;
        this.connection = null;

        createDB();//create the database if it does not exist
        // update database connection status
        connectedToDatabase = true;
    }

 
    public final void setQuery(String query)
            throws SQLException, IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // specify query and execute it WATCH OUT: statement.executequery doesnot 
//        run on queries that return number of rows such as inserts, so I use statement.execute()
        statement.execute(query);
        resultSet = statement.getResultSet();
        if (resultSet != null) {//a resultset is null if it is an update, count, and I think insert
            // determine number of rows in ResultSet
            resultSet.last(); // move to last row
            numberOfRows = resultSet.getRow(); // get row number
            metaData = resultSet.getMetaData();
        }else{
            numberOfRows = 0;
        }

    } // end method setQuery

    public int getColumnCount() throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine number of columns
        try {
            return metaData.getColumnCount();
        } // end try
        catch (SQLException sqlException) {
        } // end catch

        return 0; // if problems occur above, return 0 for number of columns
    } // end method getColumnCount

    // get name of a particular column in ResultSet
    public String getColumnName(int column) throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine column name
        try {
            return metaData.getColumnName(column + 1);
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string for column name
    } // end method getColumnName
    // return number of rows in ResultSet

    public int getRowCount() throws IllegalStateException {
        // ensure database connection is available

        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        return numberOfRows;
    } // end method getRowCount

    public Object getValueAt(int row, int column)
            throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string object
    } // end method getValueAt

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void disconnectFromDatabase() {
        if (connectedToDatabase) {
// close Statement and Connection
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } // end try
            catch (SQLException sqlException) {
            } // end catch
            finally // update database connection status
            {
                connectedToDatabase = false;
            } // end finally
        } // end if
    } // end method disconnectFromDatabase
    
    
    public int Query(String sql)
    {
        try{
            statement = connection.createStatement();
           // System.out.println(sql);
            return  statement.executeUpdate(sql);
        
        }
        catch(SQLException pp)
        {
            System.out.println(sql);
            System.out.println(pp.toString());
        }
        return 0;
    
    
    }
    public ArrayList ExecuteQuery(String sql) throws SQLException
    {
        ArrayList resultSet = new  ArrayList();
       try
       { 
           if(connection!=null)
           {
      statement = connection.createStatement();
        result = statement.executeQuery(sql);
       metadata = result.getMetaData();
     //  String[] res=null;
       int numcolls = metadata.getColumnCount();
       while(result.next())
       {
       
       for ( int i = 1; i <= numcolls; i++ )
       {
            
             System.out.println(i+":"+result.getObject(i));  
            
             resultSet.add(result.getObject(i));
       
            
       }
       }
           }
       }
       
       
       
       catch(SQLException pp)
       {
           System.out.println(pp.toString());
       }
       
       if(resultSet.isEmpty())
           resultSet.add(0);
        return resultSet;
       }

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    private void createDB()  {
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + "/.smsDb";
        System.setProperty("derby.system.home", systemDir);
        String url = "jdbc:derby:smsDb;create=true";
        Properties props = new Properties();
        props.put("user", "password");
        try {
            //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(url, props);
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        try{
            String qu;
            qu = "CREATE TABLE smsDb.INVENTORY("
                    +"ITEM_ID INTEGER NOT NULL,"
                    +"ITEM_NAME VARCHAR(50) NOT NULL,"
                    +"PURCHASE_DATE DATE NOT NULL,"
                    +"ITEM_CODE VARCHAR(8) NOT NULL,"
                    +"ITEM_STATE VARCHAR(10) NOT NULL, "
                    +"QUANTITY INTEGER NOT NULL, "
                    +"PRIMARY KEY(ITEM_ID)"
                    + ")";
            System.out.println(qu);
            statement.execute(qu);
            qu = "CREATE TABLE smsDb.BOOKS_IN_LIBRARY("//Has bn modified
                    +"BOOK_ISBN VARCHAR(20)NOT NULL,"
                    +"BOOK_CODE VARCHAR(8),"
                    +"BOOK_SHELF INTEGER NOT NULL,"
                    +"TITLE VARCHAR(50) NOT NULL, "
                    +"AUTHOR VARCHAR(100) NOT NULL,"
                    +"PUBLISHER VARCHAR(20),"
                    +"EDITION VARCHAR(20),"
                    +"NUMBER_OF_COPIES INTEGER NOT NULL, "
                    +"PRIMARY KEY(BOOK_ISBN)"
                    +")";
            System.out.println(qu);
            statement.execute(qu);
            qu = "CREATE TABLE smsDb.CARD_VALIDITY("
                    +"CARD_VALIDITY_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
                    +"STUD_ID INTEGER NOT NULL, "
                    +"VALID_UNTIL INTEGER NOT NULL, "
                    +"USER_CATEGORY VARCHAR(10) NOT NULL, "
                    +"PRIMARY KEY(CARD_VALIDITY_ID)"
                    +")";
            System.out.println(qu);
            statement.execute(qu);
            qu = "CREATE TABLE smsDb.BOOK_LENT("
                    +"BOOKS_LENTID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    +"BOOK_ISBN VARCHAR(20) NOT NULL,"
                    +"USERID INTEGER NOT NULL, "
                    +"DATE_OF_ISSUE VARCHAR(50) NOT NULL,"//EG 08/09/2014
                    +"PRIMARY KEY(BOOKS_LENTID)"
                    +")";
            System.out.println(qu);
            statement.execute(qu);
            qu = "CREATE TABLE smsDb.NON_STUDENTS_REG("
                    +"USERID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10001, INCREMENT BY 1),"
                    +"USERNAME VARCHAR(50) NOT NULL,"
                    +"DATE_OF_BIRTH DATE NOT NULL, "
                    +"ADDRESS VARCHAR(30) NOT NULL,"
                    +"TELEPHONE VARCHAR(14),"
                    +"GARANTOR_ID INTEGER NOT NULL,"
                    +"GARANTOR_ADDRESS VARCHAR(30),"
                    +"GARANTOR_TELEPHONE VARCHAR(14),"
                    +"PRIMARY KEY(USERID)"
                    +")";
            System.out.println(qu);
            statement.execute(qu);
            qu = "CREATE TABLE smsDb.STUDENTS_MARKS("
                    +"STUDENTS_MARKSID INTEGER NOT NULL, "
                    +"SEQUENCE INTEGER NOT NULL,"
                    +"STUDENTID INTEGER NOT NULL,"
                    +"SUBJECTID VARCHAR(8) NOT NULL,"
                    +"MARK NUMERIC(4,2) NOT NULL,"
                    +"PRIMARY KEY(STUDENTS_MARKSID)"
                    + ")";
            System.out.println(qu);
            statement.execute(qu);//ALL THE ABOVE TABLES HAVE BEEN CREATED AND NOW EXIST
            //This is the query string
            String query;

            System.out.println("creating login  table");
            
            query = "CREATE TABLE smsDb.LOGIN ("
                    + "NAME VARCHAR(45),"
                    + "PASSWORD VARCHAR(45),"
                    + "PRIMARY KEY (NAME)"
                    + ")";
            statement.execute(query);
            
            System.out.println("++++++++++++++++++this is the second table++++++++++++++++++++++++");
            
            query = "insert into smsDb.LOGIN (smsDb.LOGIN.NAME,smsDb.LOGIN.PASSWORD) values ('user','user')";
            statement.execute(query);
            
           // System.out.println("this is insert " + query);
            
            query = "CREATE  TABLE smsDb.Student (" 
                    +"  idStudent int NOT NULL GENERATED ALWAYS AS IDENTITY"
                    + "(START WITH 1, INCREMENT BY 1) ," +
                    "  Name VARCHAR(45) NOT NULL ," +
                    "  Class VARCHAR(45) ," +
                    "  DateOfBirth VARCHAR(50),"+
                    "  Tel VARCHAR(45) NOT NULL," +
                    "  Address VARCHAR(45) NOT NULL ," +
                    "  Defect VARCHAR(45) ," +
                    "  Email VARCHAR(45) NOT NULL, "
                    + "Gender VARCHAR(10),"
                    + //"StudentPic Blob" +
                    "  PRIMARY KEY (Name) )";
           statement.execute(query);
           query = "CREATE TABLE smsDb.FINES_PAID("
                   + "fineID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                   +"payerID INTEGER NOT NULL,"
                   +"dateOfPayment VARCHAR(10),"
                   +"amountDue INTEGER NOT NULL"
                   + ")";
            System.out.println("query");
            statement.execute(query);
            //sample data in table                        
            query = "insert into smsDb.Student (smsDb.Student.Name,smsDb.Student.Class,smsDb.Student.DateOfBirth,smsDb.Student.Tel,smsDb.Student.Address,smsDb.Student.Defect,smsDb.Student.Email,smsDb.Student.Gender) values ('Waba','form 4','1987/07/31','97979401','New Layout','','wabanas2@yahoo.com','male')";
            statement.execute(query);
            System.out.println("This is the query " + query);
            
            query = "CREATE TABLE smsDb.Guardian ("
                    +"Name VARCHAR(45) NOT NULL ,"
                    +"Address VARCHAR(45) NOT NULL ,"
                    +"Tel VARCHAR(45) NOT NULL ,"
                    +"idStudent char NOT NULL ,"
                    +"PRIMARY KEY (Name) )";
             System.out.println("the Guardian tabel " + query);
             statement.execute(query);
            
             query = "CREATE TABLE smsDb.Class ("
                     +"idClass int NOT NULL GENERATED ALWAYS AS IDENTITY"
                     + "(START WITH 1, INCREMENT BY 1),"
                     +"Name VARCHAR(10) NOT NULL ,"
                     +"Division VARCHAR(10),"
                     + "class Varchar(20),"
                     + "Description Varchar(45),"
                     +"PRIMARY KEY (Name,Division))";
                    statement.execute(query);
             
             System.out.println("table is been created+++++++++++++++++++++++++++++++++++++++");
              query = "CREATE TABLE smsDb.Staff ("
                     + "idStaff int NOT NULL GENERATED ALWAYS AS IDENTITY"
                     + "(START WITH 1, INCREMENT BY 1),"
                     + "Name VARCHAR(45) NOT NULL,"
                     + "Address  VARCHAR(45) NOT NULL,"
                     + "Email VARCHAR(45) NOT NULL,"
                     + "TeL   VARCHAR(45) NOT NULL,"
                      + "Gender VARCHAR(45) NOT NULL,"
                     + "PRIMARY KEY (idStaff,Name))";
              System.out.println("the query is " + query);
              statement.execute(query);
               query = "insert into smsDb.Staff (smsDb.Staff.Name,smsDb.Staff.Address,smsDb.Staff.Email,smsDb.Staff.TeL,smsDb.Staff.Gender) values ('Ngatchu','St Micheal Kumba','ngatchu@yahoo.com','77884455','male')";
             statement.execute(query);
              System.out.println("the staff table has been created!");
              
              query = "Create Table smsDb.UserType ("
                      + "idType int not null GENERATED ALWAYS AS IDENTITY"
                      + "(start with 1, increment by 1),"
                      + "Type varchar(45) not null,"
                      + "PRIMARY KEY (Type) )";
              statement.execute(query);
              
              query = "insert into smsDb.UserType (Type) values ('Teacher')";
             statement.execute(query);
              System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
              
              query = "Create Table smsDb.Subject ("
                      + "idSubject int not null Generated Always as identity"
                      + "(start with 1, increment by 1),"
                      + "Name varchar(15) not null,"
                      + "Department varchar(15) not null,"
                      + "Teacher varchar(45) not null,"
                      + "class varchar(15) not null,"
                      + "Description varchar(20) )";
              statement.execute(query);
               System.out.println("all done dbase");
        }
        catch(SQLException ex)
        {
            System.out.println(""+ex.getCause());
        }
  }
}


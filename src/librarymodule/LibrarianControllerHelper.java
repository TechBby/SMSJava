/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package librarymodule;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Waba
 */
//i want to do my functions here and use oop to adapt things and put them inside the main class
public class LibrarianControllerHelper {
    //instantiating DatabaseHelper class
    DatabaseHelper dbaseHelper; 
    Date today,issueDateCompanion,returnDateCompanion,validityCompanion;
    DateFormat issueDate,returnDate,validity;
    SimpleDateFormat stamp;
    //declaring variables for the InsertStuff function
    String isbn,bcode,title,author,publisher,edition,now,telNo,nonStudAddr,nonStudTel,nonStudGarantorName,nonStudDOB,nonStudGarantorTel,nonStudName,nonStudGender2;
    int shelfNo,copies,userid,status,fine,validUntil;
    //this is the constructor for this class
    public LibrarianControllerHelper(){
        try {
            this.dbaseHelper = new DatabaseHelper();
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        today = new Date();
        issueDate = new SimpleDateFormat("yyyy/MM/dd");
        returnDate = new SimpleDateFormat("yyyy/MM/dd");
        stamp = new SimpleDateFormat("yyyy/MM/dd");
    }
    
    //getting and setting isbn for the books
    public void setIsbn(String isbn){
            this.isbn = isbn;
    }
    public String getIsbn(){
        return this.isbn;
    }
    //getting and setting book code
    public void setBcode(String bcode){
            this.bcode = bcode;
    }
    public String getBcode(){
        return this.bcode;
    }
    //getting and setting book title
    public void setTitle(String title){
            this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    //getting and setting book author
    public void setAuthor(String author){
            this.author = author;
    }
    public String getAuthor(){
        return this.author;
    }
    //getting and setting book publisher
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public String getPublisher(){
        return this.publisher;
    }
    //getting and setting book edition
    public void setEdition(String edition){
            this.edition = edition;
    }
    public String getEdition(){
        return this.edition;
    }
    //getting and setting shelf number
    public void setShelfNo(int shelfNo){
            this.shelfNo = shelfNo;
    }
    public int getShelfNo(){
        return this.shelfNo;
    }
    //getting and setting number of copies
    public void setCopies(int copies){
            this.copies = copies;
    }
    public int getCopies(){
        return this.copies;
    }
    public void setUserid(int userid){
        this.userid = userid;
    }
    public int getUserid(){
        return this.userid;
    }
    //getting and setting fines
    public void setFine(int fine){
        this.fine = fine;
    }
    public int getFine(){
        return this.fine;
    }
    //getting and setting validity
    public void setValidUntil(int validUntil){
        this.validUntil = validUntil;
    }
    public int getValidUntil(){
        return this.validUntil;
    }
    //getting and setting telephone numbers
    public void setTelNo(String telNo){
        this.telNo = telNo;
    }
    public String getTelNo(){
        return this.telNo;
    }
    //getting and setting nonStudAddr
    public void setNonStudAddr(String nonStudAddr)
    {
        this.nonStudAddr = nonStudAddr;
    }
    public String getNonStudAddr(){
        return this.nonStudAddr;
    }
    //getting and setting nonStudGarantorName
    public void setNonStudGarantorName(String nonStudGarantorName){
        this.nonStudGarantorName = nonStudGarantorName;
    }
    public String getNonStudGarantorName(){
        return this.nonStudGarantorName;
    }
    //getting and setting nonStudGarantorTel
    public void setNonStudGarantorTel(String nonStudGarantorTel){
        this.nonStudGarantorTel = nonStudGarantorTel;
    }
    public String getNonStudGarantorTel(){
        return this.nonStudGarantorTel;
    }
    //getting and setting nonStudGender
    public void setNonStudGender2(String nonStudGender2){
        this.nonStudGender2 = nonStudGender2;
    }
    public String getNonStudGender2(){
        return this.nonStudGender2;
    }
    //getting and setting nonStudName
    public void setNonStudName(String nonStudName){
        this.nonStudName = nonStudName;
    }
    public String getNonStudName(){
        return this.nonStudName;
    }
    public void setNonStudDOB(String nonStudDOB){
        this.nonStudDOB = nonStudDOB;
    }
    public String getNonStudDOB(){
        return this.nonStudDOB;
    }
    public void setNonStudTel(String nonStudTel){
        this.nonStudTel = nonStudTel;
    }
    public String getNonStudTel(){
        return this.nonStudTel;
    }
    //and now the function to insert a new book into the BOOKS_IN_LIBRARY table
    public boolean AddNewBookInsertMethod(){
        String prequery = "SELECT * FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN = '"
                +getIsbn()
                + "'";
        try {
            dbaseHelper.setQuery(prequery);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() < 1){//here we are sure that there is no book already registered with the same isbn
            String nquery = "INSERT INTO smsDb.BOOKS_IN_LIBRARY ("
                    +"smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN ,"
                    +"smsDb.BOOKS_IN_LIBRARY.BOOK_CODE ,"
                    +"smsDb.BOOKS_IN_LIBRARY.BOOK_SHELF ,"
                    +"smsDb.BOOKS_IN_LIBRARY.TITLE , "
                    +"smsDb.BOOKS_IN_LIBRARY.AUTHOR ,"
                    +"smsDb.BOOKS_IN_LIBRARY.PUBLISHER ,"
                    +"smsDb.BOOKS_IN_LIBRARY.EDITION ,"
                    +"smsDb.BOOKS_IN_LIBRARY.NUMBER_OF_COPIES "
                    +")VALUES ('"//values still pending
                    +getIsbn()+"','"
                    +getBcode()+"',"
                    +getShelfNo()+",'"
                    +getTitle()+"','"
                    +getAuthor()+"','"
                    +getPublisher()+"','"
                    +getEdition()+"',"
                    +getCopies()
                    + ")";
            try {
                dbaseHelper.setQuery(nquery);
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IllegalStateException ex) {
                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            System.out.println("This is the query for the insertion: "+nquery);
            return true;
        }
        else{//now we are sure that there is already a book in the library with this isbn
            return false;
        }
    }
    //function to register non student users in the library
       public boolean SubmitNonStudentsDetailsToDBMethod(){
           String query = "SELECT smsDb.Staff.idStaff FROM smsDb.Staff WHERE smsDb.Staff.TeL = "
                   + getTelNo()
                   + "";
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
           System.out.println("This query is "+query);
           if(dbaseHelper.getRowCount() == 0){//now we are sure there is such a guarantor as a member of staff
               //dbaseHelper.getValueAt(0, 0); This should be the id of the guarantor
               String nquery = "INSERT INTO smsDb.NON_STUDENTS_REG ()";
               return true;
           }
           else{//report that there is no such garantor as a member of staff
               return false;
           }
       }
    //function to update the number of copies of an already existing book
       public boolean UpdateNoCopiesInsertMethod() throws SQLException{
        String prequery = "SELECT * FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN = '"
                +getIsbn()
                + "'";
        System.out.println("The prequery reads :"+prequery);
 
        dbaseHelper.setQuery(prequery);
        System.out.println("The number of rows from the above prequery is :"+dbaseHelper.getRowCount());
      if(dbaseHelper.getRowCount()== 1){//there is a book  in the database with similar isbn
        try{
            String query = "UPDATE smsDb.BOOKS_IN_LIBRARY SET smsDb.BOOKS_IN_LIBRARY.NUMBER_OF_COPIES = "
                    + getCopies()
                    + "WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN = '"
                    + getIsbn()
                    + "'";
            //now print query in console just to confirm that the query is properly typed
            System.out.println(query);
            dbaseHelper.setQuery(query);
            
            }
        catch(SQLException sqlException){
           sqlException.printStackTrace();
        }
        catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
        }
      else{//that is to say that there are already some copies of that book in the database
            return false;
        }
    }

    public boolean FillDetailsForBookReturn(){
        String query = "SELECT  smsDb.BOOK_LENT.BOOK_ISBN, smsDb.BOOK_LENT.DATE_OF_ISSUE FROM smsDb.BOOK_LENT WHERE smsDb.BOOK_LENT.USERID ="
                + getUserid()
                +"";
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() == 1){//a book has been borrowed by this current suitor
             System.out.println("This should be the isbn: "+dbaseHelper.getValueAt(0, 0));
             System.out.println("This should be the date of issue: "+dbaseHelper.getValueAt(0, 1));
             //these are the preliminaries needed for comparing dates as desired
            try {
                issueDateCompanion = issueDate.parse(dbaseHelper.getValueAt(0, 1).toString());
            } catch (ParseException ex) {
                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            //and now time to get value of day, month and year for borrowing date
            Calendar cal = Calendar.getInstance();
            cal.setTime(issueDateCompanion);
            int month = cal.get(Calendar.MONTH)+1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);
            System.out.println("This is the day of issue: "+day);
            System.out.println("This is the month of issue: "+month);
            System.out.println("This is the year of issue: "+year);
            //we should now get the date of today and compare to get fines that are to be given the the one returning the book
            try {
                returnDateCompanion = returnDate.parse(stamp.format(today));
            } catch (ParseException ex) {
                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(returnDateCompanion);
            int returnDateMonth = cal2.get(Calendar.MONTH)+1;
            int returnDateDay = cal2.get(Calendar.DAY_OF_MONTH);
            int returnDateYear = cal2.get(Calendar.YEAR);
            System.out.println("This is the day of return: "+returnDateDay);
            System.out.println("This is the month of return: "+returnDateMonth);
            System.out.println("This is the year of return: "+returnDateYear);
            //and now the number of days between two days
            cal.set(year, month, day);
            cal2.set(returnDateYear, returnDateMonth, returnDateDay);
            Date d1 = cal.getTime();
            Date d2 = cal2.getTime();
            int numberOfDays = (int)diffDays(d1, d2);
            if((numberOfDays-7) > 0 ){
                System.out.println("The number of days between "+stamp.format(today)+" and "+dbaseHelper.getValueAt(0, 1).toString()+" is :"+numberOfDays);
                this.fine = (numberOfDays-7)*25;//7 is the maximum number of days an individual can keep a book, and 25 is the cost of keeping the book per day after limit has expired
                System.out.println("Your fine for spending "+(numberOfDays-7)+" Extra days is "+fine);
            }
            else{
                System.out.println("The number of days between "+stamp.format(today)+" and "+dbaseHelper.getValueAt(0, 1).toString()+" is :"+numberOfDays);
                this.fine = 0;//7 is the maximum number of days an individual can keep a book, and 25 is the cost of keeping the book per day after limit has expired
                System.out.println("You aren't owing any fine ");
            }
            return true;
        }
        else{//no book has been borrowed by this current suitor
            
            return false;
        }
    }
    //this function gets the difference in days between two dates as a double
    public static final double diffDays(Date from, Date to){
        return (to.getTime()-from.getTime())/(1000.0*24.0*60.0*60.0);
    }
    //now the function that validates the return
    public boolean ValidateBookReturn(){//
         String fquery = "SELECT * FROM smsDb.BOOK_LENT WHERE smsDb.BOOK_LENT.USERID = "
                +getUserid()
                + "";
         System.out.println("This is the query that selects stuff from the book lent table: "+fquery);
        try {
            dbaseHelper.setQuery(fquery);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("The number of rows returned is :"+fquery);
        if(dbaseHelper.getRowCount() == 1){
                System.out.println("This should be the id for the table:"+dbaseHelper.getValueAt(0, 0));
                System.out.println("This should be the isbn:"+dbaseHelper.getValueAt(0, 1));
                System.out.println("This should be the user id :"+dbaseHelper.getValueAt(0, 2));
                int x = (int) dbaseHelper.getValueAt(0, 2);
                System.out.println("This should be the date of issue:"+dbaseHelper.getValueAt(0, 3));
                System.out.println("The fine should be the fine:"+fine);
                System.out.println("The date of payment should be :"+stamp.format(today));
                //before this insertion, let us be sure that no such values exist in that table
                String tquery = "SELECT * FROM smsDb.FINES_PAID WHERE smsDb.FINES_PAID.payerID = "
                        + dbaseHelper.getValueAt(0, 2)
                        + " AND smsDb.FINES_PAID.dateOfPayment = '"
                        + stamp.format(today)+"'"
                        +" AND smsDb.FINES_PAID.amountDue =  "
                        + fine
                        +"";
                System.out.println("This is the select query :"+tquery);
             try {
                 dbaseHelper.setQuery(tquery);
             } catch (SQLException ex) {
                 Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalStateException ex) {
                 Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
             }
             if(dbaseHelper.getRowCount() == 0){//no similar copy of information exists
                    String nquery = "INSERT INTO smsDb.FINES_PAID ("
                    +"smsDb.FINES_PAID.payerID ,"
                    +"smsDb.FINES_PAID.dateOfPayment ,"
                    +"smsDb.FINES_PAID.amountDue "
                    +")VALUES (" 
                    +x 
                    +",'"//put payerID here
                    +stamp.format(today)+"',"//put dateOfPayment here
                    +fine//put amountDue here
                    + ")";
                System.out.println("This is the value of x :"+x);
                System.out.println("This is the insertion query that inserts stuff into fines table"+nquery);
                try {
                    dbaseHelper.setQuery(nquery);
                } catch (SQLException ex) {
                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalStateException ex) {
                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
                 System.out.println("Book Return details successfully entered");
                 String query  = "DELETE FROM smsDb.BOOK_LENT WHERE smsDb.BOOK_LENT.USERID = "
                       +getUserid()
                       + "";
               System.out.println("This is the delete query that removes book from the table of books lent"+query);
               try {
                   dbaseHelper.setQuery(query);
               } catch (SQLException ex) {
                   Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IllegalStateException ex) {
                   Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
               }
                 System.out.println("Book has been successfully deleted");
                return true;
             }
             else{
                 System.out.println("There is already a book with such Book return details. Just check to be sure that this is not an error");
                 return false;
             }
        }
        else{//did not find any such information
            return false;
        }
  
    }
    //this function helps fill renew card's form details
    public boolean FillDetailsForCardRenewal(){
        if(getUserid() <= 10000){//this is a regular student user
            String query = "SELECT smsDb.Student.Name, smsDb.Student.Class, smsDb.Student.DateOfBirth, smsDb.Student.Gender FROM smsDb.Student";
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() == 1){
            System.out.println("This is the Name :"+dbaseHelper.getValueAt(0, 0));
            System.out.println("This is the Class :"+dbaseHelper.getValueAt(0, 1));
            System.out.println("This is the Date of Birth :"+dbaseHelper.getValueAt(0, 2));
            System.out.println("This is the Gender :"+dbaseHelper.getValueAt(0, 3));
               /* try {
                    validityCompanion = validity.parse(stamp.format(today));
                } catch (ParseException ex) {
                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                /*Calendar calendar = Calendar.getInstance();
                calendar.setTime(validityCompanion);
                int until = calendar.get(Calendar.YEAR);
                validUntil = until+1;//since library card will be renewed after every one year
                System.out.println("This library Card should be valid until the year :"+validUntil);*/
            return true;
        }
        else{
            System.out.println("Student is not registered within the system");
            return false;
        }
        }
        else{//this is a non student user, so change student to nonstudent
            String query = "SELECT smsDb.Student.Name, smsDb.Student.Class, smsDb.Student.DateOfBirth, smsDb.Student.Gender FROM smsDb.Student";
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() == 1){
            System.out.println("This is the Name :"+dbaseHelper.getValueAt(0, 0));
            System.out.println("This is the Class :"+dbaseHelper.getValueAt(0, 1));
            System.out.println("This is the Date of Birth :"+dbaseHelper.getValueAt(0, 2));
            System.out.println("This is the Gender :"+dbaseHelper.getValueAt(0, 3));
            
            return true;
        }
        else{
            System.out.println("Student is not registered within the system");
            return false;
        }
        }
    }
//this function helps fill make card form's user information
    public boolean FillDetailsForCardEstablishment(){
        if(getUserid() <= 10000){//this is a regular student user
            String query = "SELECT smsDb.Student.Name, smsDb.Student.Class, smsDb.Student.DateOfBirth, smsDb.Student.Gender FROM smsDb.Student";
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() == 1){
            System.out.println("This is the Name :"+dbaseHelper.getValueAt(0, 0));
            System.out.println("This is the Class :"+dbaseHelper.getValueAt(0, 1));
            System.out.println("This is the Date of Birth :"+dbaseHelper.getValueAt(0, 2));
            System.out.println("This is the Gender :"+dbaseHelper.getValueAt(0, 3));
               /* try {
                    validityCompanion = validity.parse(stamp.format(today));
                } catch (ParseException ex) {
                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                /*Calendar calendar = Calendar.getInstance();
                calendar.setTime(validityCompanion);
                int until = calendar.get(Calendar.YEAR);
                validUntil = until+1;//since library card will be renewed after every one year
                System.out.println("This library Card should be valid until the year :"+validUntil);*/
            return true;
        }
        else{
            System.out.println("Student is not registered within the system");
            return false;
        }
        }
        else{//this is a non student user, so change student to nonstudent
            String query = "SELECT smsDb.Student.Name, smsDb.Student.Class, smsDb.Student.DateOfBirth, smsDb.Student.Gender FROM smsDb.Student";
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() == 1){
            System.out.println("This is the Name :"+dbaseHelper.getValueAt(0, 0));
            System.out.println("This is the Class :"+dbaseHelper.getValueAt(0, 1));
            System.out.println("This is the Date of Birth :"+dbaseHelper.getValueAt(0, 2));
            System.out.println("This is the Gender :"+dbaseHelper.getValueAt(0, 3));
            
            return true;
        }
        else{
            System.out.println("Student is not registered within the system");
            return false;
        }
        }
    }
    public boolean FillDetailsForDelete(){
        String query = "SELECT * FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN = '"
                +getIsbn()
                + "'";
        System.out.println("This is the query that ensures that book is found in system "+query);
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() == 1){//the lone copy of this book has been found
            System.out.println("This should be the isbn: "+dbaseHelper.getValueAt(0, 0));
            System.out.println("This should be the book code: "+dbaseHelper.getValueAt(0, 1));
            System.out.println("This should be the shelf number: "+dbaseHelper.getValueAt(0, 2));
            System.out.println("This should be the book title: "+dbaseHelper.getValueAt(0, 3));
            System.out.println("This should be the book author: "+dbaseHelper.getValueAt(0, 4));
            System.out.println("This should be the book publisher: "+dbaseHelper.getValueAt(0, 5));
            System.out.println("This should be the book edition: "+dbaseHelper.getValueAt(0, 6));
            System.out.println("This should be the number of copies of the book: "+dbaseHelper.getValueAt(0, 7));
            return true;
        }
        else{//There is more than one book
            System.out.println("Many books exist with this isbn number");
            return false;
        }
    }
    //function to delete book from database
    public boolean DeleteOldBookMethod(){//this is called when the delete button of the delete button form is pressed
        String query  = "DELETE FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN = '"
                +getIsbn()
                + "'";
        System.out.println(query);
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    //function to insert information about someone borrowing a book
    public boolean BorrowBookInformation(){
            System.out.println("This is the date of today: "+stamp.format(today));//this prints out sample date for today in some figures but it is a date
            //System.out.println("what data type is this date ?"+stamp.format(today).getClass());
                    if(getUserid()<= 10000){//user is a regular student
                        String qu = "SELECT * FROM smsDb.Student WHERE smsDb.Student.idStudent ="
                    + getUserid()+ "";
                    try {
                        dbaseHelper.setQuery(qu);
                    } catch (SQLException ex) {
                        Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalStateException ex) {
                        Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(dbaseHelper.getRowCount() == 1){//here student is registered
                        String q = "SELECT * FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN ='"
                                + getIsbn()+"'";
                            try {
                                dbaseHelper.setQuery(q);
                            } catch (SQLException ex) {
                                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalStateException ex) {
                                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(dbaseHelper.getRowCount() == 1){//book has been added to the library
                                    String fquery = "SELECT * FROM smsDb.BOOK_LENT WHERE smsDb.BOOK_LENT.USERID = "
                                        + getUserid()+"";//make sure that the user has not got more than a book to his name already
                                    System.out.println("this is the query that finds out if suitor already has books borrowed: "+fquery);
                                    try {
                                    dbaseHelper.setQuery(fquery);
                                    } catch (SQLException ex) {
                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalStateException ex) {
                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    int rows = dbaseHelper.getRowCount();//if greater than zero it means that user already has some books to his name 
                                    if(rows >= 1){//means students has the maximum number of books in his keeping
                                        System.out.println("Student has already borrowed exceeded the maximum number of books allowed. Please return that one so that you can borrow another");
                                        this.status = 1;
                                        return false;
                                    }
                                    else{// we can now proceed to insert but only if number of copies of book have not been exhausted
                                        String squery = "SELECT * FROM smsDb.BOOK_LENT WHERE smsDb.BOOK_LENT.BOOK_ISBN = '"
                                        + getIsbn()+"'";
                                            System.out.println("this is the query that finds out if a particular book has already been borrowed: "+squery);
                                        try {
                                            dbaseHelper.setQuery(squery);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalStateException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        //Now lets verify if a particular book's copies have been exhausted or not 
                                            int borrowed = dbaseHelper.getRowCount();//if greater than zero, it means that some copies of this book have already bn borrowed
                                            String tquery = "SELECT smsDb.BOOKS_IN_LIBRARY.NUMBER_OF_COPIES FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN ="
                                                    + "'"
                                                    + getIsbn()+"'";//this tells us the number of copies of this book in existence in the library
                                            System.out.println("this is the query that recalls the total number of copies of this book found in the library: "+tquery);
                                        try {
                                            dbaseHelper.setQuery(tquery);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalStateException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                         int available = (int)dbaseHelper.getValueAt(0, 0);//these are the number of books available
                                        if(available > 0){
                                            int AvailableToBeLent = available - borrowed;
                                            System.out.println("number of copies still available for borrowing out are: "+AvailableToBeLent);
                                            String query = "INSERT INTO smsDb.BOOK_LENT (smsDb.BOOK_LENT.USERID, smsDb.BOOK_LENT.BOOK_ISBN,smsDb.BOOK_LENT.DATE_OF_ISSUE) VALUES ("
                                                    +getUserid()+","
                                                    +"'"
                                                +getIsbn()+"'"+","
                                                +"'"
                                                +stamp.format(today) //this is where i put in the date now let us see how it goes
                                                +"')";
                                                System.out.println(query);
                                                try {
                                                    dbaseHelper.setQuery(query);
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (IllegalStateException ex) {
                                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                               return true;
                                        }
                                        else{//meaning no more copies are available
                                            System.out.println("Copies are no longer available for borrowing");
                                            this.status = 2;
                                            return false;
                                        }
                                
                                    }
                            }
                            else{//book is not registered
                                return false;
                          }
                    }
                    else{//here student is not registered
                        System.out.println("Student is not registered");
                        this.status = 3;
                        return false;
                    }
                } 
                else{//user is non-student
                        String qu = "SELECT * FROM smsDb.NON_STUDENTS_REG WHERE smsDb.NON_STUDENTS_REG.USERID ="
                    + getUserid()+ "";
                     try {
                        dbaseHelper.setQuery(qu);
                    } catch (SQLException ex) {
                        Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalStateException ex) {
                        Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(dbaseHelper.getRowCount() == 1){//here student is registered
                        String q = "SELECT * FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN ='"
                                + getIsbn()+"'";
                            try {
                                dbaseHelper.setQuery(q);
                            } catch (SQLException ex) {
                                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalStateException ex) {
                                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(dbaseHelper.getRowCount() == 1){//book has been added to the library
                                    String fquery = "SELECT * FROM smsDb.BOOK_LENT WHERE smsDb.BOOK_LENT.USERID = "
                                        + getUserid()+"";//make sure that the user has not got more than a book to his name already
                                    System.out.println("this is the query that finds out if suitor already has books borrowed: "+fquery);
                                    try {
                                    dbaseHelper.setQuery(fquery);
                                    } catch (SQLException ex) {
                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalStateException ex) {
                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    int rows = dbaseHelper.getRowCount();//if greater than zero it means that user already has some books to his name 
                                    if(rows >= 1){//means students has the maximum number of books in his keeping
                                        System.out.println("Student has already borrowed exceeded the maximum number of books allowed. Please return that one so that you can borrow another");
                                        this.status = 1;
                                        return false;
                                    }
                                    else{// we can now proceed to insert but only if number of copies of book have not been exhausted
                                        String squery = "SELECT * FROM smsDb.BOOK_LENT WHERE smsDb.BOOK_LENT.BOOK_ISBN = '"
                                        + getIsbn()+"'";
                                            System.out.println("this is the query that finds out if a particular book has already been borrowed: "+squery);
                                        try {
                                            dbaseHelper.setQuery(squery);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalStateException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        //Now lets verify if a particular book's copies have been exhausted or not 
                                            int borrowed = dbaseHelper.getRowCount();//if greater than zero, it means that some copies of this book have already bn borrowed
                                            String tquery = "SELECT smsDb.BOOKS_IN_LIBRARY.NUMBER_OF_COPIES FROM smsDb.BOOKS_IN_LIBRARY WHERE smsDb.BOOKS_IN_LIBRARY.BOOK_ISBN ="
                                                    + "'"
                                                    + getIsbn()+"'";//this tells us the number of copies of this book in existence in the library
                                            System.out.println("this is the query that recalls the total number of copies of this book found in the library: "+tquery);
                                        try {
                                            dbaseHelper.setQuery(tquery);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalStateException ex) {
                                            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                         int available = (int)dbaseHelper.getValueAt(0, 0);//these are the number of books available
                                        if(available > 0){
                                            int AvailableToBeLent = available - borrowed;
                                            System.out.println("number of copies still available for borrowing out are: "+AvailableToBeLent);
                                            String query = "INSERT INTO smsDb.BOOK_LENT (smsDb.BOOK_LENT.USERID, smsDb.BOOK_LENT.BOOK_ISBN,smsDb.BOOK_LENT.DATE_OF_ISSUE) VALUES ("
                                                    +getUserid()+","
                                                    +"'"
                                                +getIsbn()+"'"+","
                                                +"'"
                                                +stamp.format(today) //this is where i put in the date now let us see how it goes
                                                +"')";
                                                System.out.println(query);
                                                try {
                                                    dbaseHelper.setQuery(query);
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (IllegalStateException ex) {
                                                    Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                               return true;
                                        }
                                        else{//meaning no more copies are available
                                            System.out.println("Copies are no longer available for borrowing");
                                            this.status = 2;
                                            return false;
                                        }
                                
                                    }
                            }
                            else{//book is not registered
                                return false;
                          }
                    }
                    else{//here student is not registered
                        System.out.println("Student is not registered");
                        this.status = 3;
                        return false;
                    }
                }
    }
    public int statusOfBorrowBookInformation(){
        if(status ==1 ){//student already has a book in his name
            return 1;
        }
        else if(status ==2 ){//no more copies are available
            return 2;
        }
        else if(status ==3 ){//student is not registered
            return 3;
        }
        else if(status == 0){
            return 0;
        }
        else{
            return 5;
        }
    }
    public boolean NonStudentRegistration(){
        //ensure that garantor's exist
        String query = "SELECT * FROM smsDb.Staff WHERE smsDb.Staff.TeL = '"
                + getNonStudGarantorTel()+"'";
        System.out.println("This is the query that selects the garantor for particular student :"+query);
        try {
            dbaseHelper.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dbaseHelper.getRowCount() == 1){// garantor is registered in the system
            String nquery = "INSERT INTO smsDb.NON_STUDENTS_REG (smsDb.NON_STUDENTS_REG.USERNAME, smsDb.NON_STUDENTS_REG.DATE_OF_BIRTH, smsDb.NON_STUDENTS_REG.ADDRESS, smsDb.NON_STUDENTS_REG.TELEPHONE, smsDb.NON_STUDENTS_REG.GARANTOR_ID, smsDb.NON_STUDENTS_REG.GARANTOR_ADDRESS, smsDb.NON_STUDENTS_REG.GARANTOR_TELEPHONE) VALUES ('"
                    + getNonStudName()+"','"
                    +getNonStudDOB()+"','"
                    +getNonStudAddr()+"','"
                    +getNonStudTel()+"',"
                    +dbaseHelper.getValueAt(0, 0)+",'"
                    +dbaseHelper.getValueAt(0, 2)+"','"
                    +dbaseHelper.getValueAt(0, 4)
                    +"')";
            System.out.println("This is the query that inserts details into the non student's registration"+nquery);
            try {
                dbaseHelper.setQuery(nquery);
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(LibrarianControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        else{
            System.out.println("No member of staff with that phone number is registered in this system");
            return false;
        }
    }
}
    

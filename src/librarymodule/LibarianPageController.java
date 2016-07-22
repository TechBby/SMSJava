/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package librarymodule;
import java.util.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Waba
 */
public class LibarianPageController implements Initializable {
    @FXML
    private Pane deleteBook;
    @FXML
    private TextField deleteBookISBN;
    @FXML
    private Button deleteBookSearchBtn;
    @FXML
    private TextField deleteBookTitle;
    @FXML
    private TextField deleteBookAuthor;
    @FXML
    private TextField deleteBookEdition;
    @FXML
    private TextField deleteBookBcode;
    @FXML
    private TextField deleteBookCopies;
    @FXML
    private TextField deleteBookShelfNo;
    @FXML
    private Pane addBook;
    @FXML
    private TextField addBookISBN1;
    @FXML
    private TextField addBookTitle1;
    @FXML
    private TextField addBookAuthor;
    @FXML
    private TextField addBookAuthor1;
    @FXML
    private TextField addBookEdition1;
    @FXML
    private TextField addBookBcode1;
    @FXML
    private TextField addBookCopies1;
    @FXML
    private TextField addBookShelfNo1;
    @FXML
    private Pane borrowBook;
    @FXML
    private TextField bookBorrowISBN;
    @FXML
    private TextField bookBorrowSuitorMat;
    @FXML
    private Pane returnBook;
    @FXML
    private TextField bookReturnSuitorMat;
    @FXML
    private Button bookReturnSearchBtn;
    @FXML
    private TextField bookReturnISBN;
    @FXML
    private TextField bookReturnTodayDate;
    @FXML
    private TextField bookReturnRetDate;
    @FXML
    private TextField bookReturnFine;
    @FXML
    private Pane nonStudReg;
    @FXML
    private TextField nonStudName;
    @FXML
    private TextField nonStudAddr;
    @FXML
    private TextField nonStudTel;
    @FXML
    private RadioButton nonStudGender1;
    @FXML
    private RadioButton nonStudGender2;
    @FXML
    private TextField nonStudGarantorName;
    @FXML
    private TextField nonStudGarantorTel;
    @FXML
    private Button nonStudSubmitBtn;
    @FXML
    private Button nonStudCancelBtn;
    @FXML
    private Pane estLibraryCard;
    @FXML
    private TextField estUserName;
    @FXML
    private TextField estCardUserMat;
    @FXML
    private Button estCardSearch;
    @FXML
    private TextField estUserDOB;
    @FXML
    private TextField estCardValidity;
    @FXML
    private TextField estCardGender;
    @FXML
    private TextField estCardUserCategory;
    @FXML
    private Pane renewCard;
    @FXML
    private TextField renewCardUserName1;
    @FXML
    private TextField renewCardUserMat1;
    @FXML
    private Button renewCardSearch;
    @FXML
    private TextField renewCardUserDOB1;
    @FXML
    private TextField renewCardValidity;
    @FXML
    private Button renewCardSubmitBtn;
    @FXML
    private Button renewCardCancelBtn;
    @FXML
    private TextField renewCardUserGender;
    DatabaseHelper dbaseHelper;
    Date today;
    SimpleDateFormat stamp;
    LibrarianControllerHelper lchelper;
    //for the add book method
     String isbn,bcode,title,author,publisher,edition;
    int shelfNo,copies,userid,fine;
    boolean skipThisIf;//by default it should  be false;
    @FXML
    private TextField deleteBookPublisher;
    @FXML
    private Button deleteBookDeletetBtn;
    @FXML
    private Pane updateCopies;
    @FXML
    private TextField updateBookISBN11;
    @FXML
    private TextField updateBookTitle11;
    @FXML
    private TextField updateBookAuthor1;
    @FXML
    private TextField updateBookEdition11;
    @FXML
    private TextField updateBookBcode11;
    @FXML
    private TextField updateBookCopies11;
    @FXML
    private TextField updateBookShelfNo11;
    @FXML
    private TextField updateBookPublisher1;
    @FXML
    private DatePicker nonStudDOB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //instantiating database helper class
        lchelper = new LibrarianControllerHelper ();
        //instantiating dates
        today = new Date();
        stamp = new SimpleDateFormat("dd/MM/YYYY");
        //instantiating the LibrarianControllerHelper class
        }   
    public void makeVisible(){
        renewCard.setVisible(false);
        estLibraryCard.setVisible(false);
        returnBook.setVisible(false);
        borrowBook.setVisible(false);
        nonStudReg.setVisible(false);
        addBook.setVisible(false);
        deleteBook.setVisible(false);
        updateCopies.setVisible(false);
    }

    @FXML
    private void FillBookDetails4Delete(MouseEvent event) {
         //before call to , we pick the content of the isbn field
        String isbn = deleteBookISBN.getText();
        //now we set this using its set method
        lchelper.setIsbn(isbn);
        if(lchelper.FillDetailsForDelete()){
            //book found, fill details for delete to proceed
            deleteBookTitle.setText(lchelper.dbaseHelper.getValueAt(0, 3).toString());
            deleteBookAuthor.setText(lchelper.dbaseHelper.getValueAt(0, 4).toString());
            deleteBookPublisher.setText(lchelper.dbaseHelper.getValueAt(0, 5).toString());
            deleteBookEdition.setText(lchelper.dbaseHelper.getValueAt(0, 6).toString());
            deleteBookBcode.setText(lchelper.dbaseHelper.getValueAt(0, 1).toString());
            deleteBookCopies.setText(lchelper.dbaseHelper.getValueAt(0, 7).toString());
            deleteBookShelfNo.setText(lchelper.dbaseHelper.getValueAt(0, 2).toString());
        }
        else{
            //book not found, thus report that no book was found
            Dialogs.create().title("Warning").message("No such book is registered in the system").showInformation();
            deleteBookISBN.clear();
         }
        //display full info about book for librarian to confirm delete
        
    }

    @FXML
    private void DeleteBookFunction(MouseEvent event) {
        makeVisible();
        deleteBook.setVisible(true);
    }

    @FXML
    private void AddBookFunction(MouseEvent event) {
        makeVisible();
        addBook.setVisible(true);
    }

    @FXML
    private void InsertBook2DB(MouseEvent event) throws SQLException {
        if (bookBorrowISBN.getText().length() == 0 || bookBorrowISBN.getText().length() > 20){
            Dialogs.create().title("Warning").message("Isbn field is empty or is longer than expected").showInformation();
        }
        else{
            isbn = bookBorrowISBN.getText();
        }
        if (bookBorrowSuitorMat.getText().length() == 0){
            Dialogs.create().title("Warning").message("Isbn field is empty or is longer than expected").showInformation();
        }
        else{
            userid = Integer.parseInt(bookBorrowSuitorMat.getText());
        }
        lchelper.setUserid(userid);
        lchelper.setIsbn(isbn);
        if(lchelper.BorrowBookInformation()){
            Dialogs.create().title("Congratulations").message("Information successfully added").showInformation();
        }
        else{
            if(lchelper.statusOfBorrowBookInformation()==1){
                Dialogs.create().title("Waring").message("Operation unsuccessful. Student already has a book in his keeping, let the student return that first").showInformation();
            }
            else if(lchelper.statusOfBorrowBookInformation()==2){
                Dialogs.create().title("Waring").message("Operation unsuccessful. No more copies available for borrowing").showInformation();
            }
            else if(lchelper.statusOfBorrowBookInformation()==3){
                Dialogs.create().title("Waring").message("Operation unsuccessful.This student is not registered in this system").showInformation();
            }
            else if(lchelper.statusOfBorrowBookInformation() == 0){
                Dialogs.create().title("Warning").message("Operation unsuccessful, This book is not yet registered").showInformation();
            }
            else{
                Dialogs.create().title("Waring").message("Operation unsuccessful.i ain't got any suggestion").showInformation();
            } 
        }
        //we now clear the fields
        bookBorrowSuitorMat.clear();
        bookBorrowISBN.clear();
    }

    @FXML
    private void FillBookReturnDetailsFunction(MouseEvent event) {
        userid = Integer.parseInt(bookReturnSuitorMat.getText());
        lchelper.setUserid(userid);//this gets the id from the text field
        System.out.println("the isbn is "+lchelper.getIsbn());
        if(lchelper.FillDetailsForBookReturn()){
            //fill fields in the form
            lchelper.setIsbn(isbn);
            bookReturnISBN.setText(lchelper.getIsbn());
            bookReturnTodayDate.setText(lchelper.dbaseHelper.getValueAt(0, 1).toString());
            bookReturnRetDate.setText(lchelper.stamp.format(today));
            bookReturnISBN.setText(lchelper.dbaseHelper.getValueAt(0, 0).toString());
             fine = lchelper.getFine();
             String Fine = Integer.toString(fine);
             bookReturnFine.setText(Fine);
            //bookReturnFine.setText(fine);
        }
        else{
            //display messages suggesting possible sources of error
            Dialogs.create().title("Warning").message("There is not a book to this suitor's name").showInformation();
        }
    }

    @FXML
    private void ValidateBookReturnFunction(MouseEvent event) {
        //get these details and convert those that need conversion
       fine = Integer.parseInt(bookReturnFine.getText());//conversion of fine to int
       userid = Integer.parseInt(bookReturnSuitorMat.getText());//conversion of matricule or id to int
        isbn = bookReturnISBN.getText();
        bookReturnTodayDate.getText();
        bookReturnRetDate.getText();
        lchelper.setFine(fine);
        lchelper.setUserid(userid);
        lchelper.setIsbn(isbn);
        if(lchelper.ValidateBookReturn()){
            //display a message to say that operation was successful
            Dialogs.create().title("Congratulations").message("Book successfully returned").showInformation();
        }
        else{
            //display message to say that operation was unsuccessful
            Dialogs.create().title("Warning").message("Book return failed").showInformation();
        }
        bookReturnISBN.clear();
        bookReturnFine.clear();
        bookReturnRetDate.clear();
        bookReturnTodayDate.clear();
        bookReturnSuitorMat.clear();
    }   

    @FXML
    private void SubmitNonStudDetails2DBFunction(MouseEvent event) {
       // nonStudAddr.getText();
        lchelper.setNonStudAddr(nonStudAddr.getText());
        //nonStudGarantorName.getText();
        lchelper.setNonStudGarantorName(nonStudGarantorName.getText());
        //nonStudGarantorTel.getText();
        lchelper.setNonStudGarantorTel(nonStudGarantorTel.getText());
        //nonStudGender2.getText();
        lchelper.setNonStudGender2(nonStudGender2.getText());
        //nonStudName.getText();
        lchelper.setNonStudName(nonStudName.getText());
        //nonStudTel.getText();
        lchelper.setNonStudTel(nonStudTel.getText());
        //nonStudDOB.getValue();//this value is of type LocalDate and thus must be converted to string
        lchelper.setNonStudDOB(nonStudDOB.getValue().format(DateTimeFormatter.ISO_DATE));
        if(lchelper.NonStudentRegistration()){
            //show validation message
            Dialogs.create().title("Congratulations").message("Student's details effectively entered into the system").showInformation();
        }
        else{
            //show warning
            Dialogs.create().title("Warning").message("Operation Fail").showInformation();
        }
        nonStudAddr.clear();
        nonStudGarantorName.clear();
        nonStudGarantorTel.clear();
        nonStudName.clear();
        nonStudTel.clear();
    }

    @FXML
    private void FillEstCardDetailsFunction(MouseEvent event) {//when the search button is clicked, this is the method that is called
        //we should set the fields using stuff gotten from function in the controller helper class
        if(lchelper.FillDetailsForCardEstablishment()){
            estUserName.setText(lchelper.dbaseHelper.getValueAt(0, 0).toString());
            estUserDOB.setText(lchelper.dbaseHelper.getValueAt(0, 2).toString());
            estCardGender.setText(lchelper.dbaseHelper.getValueAt(0, 3).toString());
            String Std = "Student";
            estCardUserCategory.setText(Std);
            //String validUntilYear = Integer.toString(lchelper.getValidUntil());
            //estCardValidity.setText(validUntilYear);
        }
        else{
        
        }
    }

    @FXML
    private void SubmitEstCardDetails2DB(MouseEvent event) {
    }

    @FXML
    private void FillrenewCardDetailsFunction(MouseEvent event) {
        if(lchelper.FillDetailsForCardRenewal()){
            renewCardUserName1.setText(lchelper.dbaseHelper.getValueAt(0, 0).toString());
            renewCardUserDOB1.setText(lchelper.dbaseHelper.getValueAt(0, 2).toString());
            renewCardUserGender.setText(lchelper.dbaseHelper.getValueAt(0, 3).toString());
            //solve the equation of validity
        }
        else{
            
        }
    }

    @FXML
    private void SubmitrenewCardDetails2DB(MouseEvent event) {
    }

    @FXML
    private void BorrowBookFunction(MouseEvent event) {
        makeVisible();
        borrowBook.setVisible(true);
    }

    @FXML
    private void ReturnBookFunction(MouseEvent event) {
        makeVisible();
        returnBook.setVisible(true);
    }

    @FXML
    private void EstablishCardFunction(MouseEvent event) {
        makeVisible();
        //now we fill the combo box
        /*String[] genderItem = {"Male","Female"};
        for (String gender: genderItem){
           // estCardGender.
        }*/
        estLibraryCard.setVisible(true);
    }

    @FXML
    private void RenewCardFunction(MouseEvent event) {
        makeVisible();
        renewCard.setVisible(true);
    }

    @FXML
    private void AddNonStudentUsersFunction(MouseEvent event) {
        makeVisible();
        nonStudReg.setVisible(true);
    }

    @FXML
    private void DeleteBookfromDBFunction(MouseEvent event) {
        //deleting book
        if(lchelper.DeleteOldBookMethod()){
            Dialogs.create().title("Congratulations").message("Delete Operation was successful").showInformation();
        }
        else{
            Dialogs.create().title("Warning").message("Delete Operation failed").showInformation();
        }
        deleteBookAuthor.clear();
        deleteBookBcode.clear();
        deleteBookCopies.clear();
        deleteBookEdition.clear();
        deleteBookISBN.clear();
        deleteBookPublisher.clear();
        deleteBookShelfNo.clear();
        deleteBookTitle.clear();
    }

    @FXML
    private void AddBook2DBFunction(MouseEvent event) throws SQLException {
        //check for empty fields and validate on success
        //begining with addBookISBN1
        if(addBookISBN1.getText().length() == 0 || addBookISBN1.getText().length() > 20 ){
            Dialogs.create().title("Warning").message(" Book ISBN field is empty or exceeds the number of characters allowed").showInformation();
        }
        else{
            isbn = addBookISBN1.getText();
        }
        //title
        if(addBookTitle1.getText().length() == 0 || addBookTitle1.getText().length() > 50 ){
            Dialogs.create().title("Warning").message(" Book Title field is empty or exceeds the number of characters allowed").showInformation();
        }
        else{
             title = addBookTitle1.getText();
        }
        //author
         if(addBookAuthor.getText().length() == 0 || addBookAuthor.getText().length() > 100 ){
            Dialogs.create().title("Warning").message(" Book Title field is empty or exceeds the number of characters allowed").showInformation();
        }
        else{
             author = addBookAuthor.getText();
        }
         //publisher
         if(addBookAuthor1.getText().length() == 0 || addBookAuthor1.getText().length() > 20){
             Dialogs.create().title("Warning").message("Publisher field is empty or exceeds the number of characters allowed").showInformation();
         }
         else{
             publisher = addBookAuthor1.getText();
         }
         //edition
         if(addBookEdition1.getText().length() == 0 || addBookEdition1.getText().length() > 20){
             Dialogs.create().title("Warning").message("Book Edition field is empty or exceeds the number of characters allowed").showInformation();
         }
         else{
             edition = addBookEdition1.getText();
         }
         //book code
         if(addBookBcode1.getText().length() == 0 || addBookBcode1.getText().length() > 20){
             Dialogs.create().title("Warning").message("Book Code field is empty").showInformation();
         }
         else{
             bcode = addBookBcode1.getText();
         }
        //before call to InsertStuff, we pick the content of each field
         if(addBookCopies1.getText().length()== 0){
             Dialogs.create().title("Warning").message("Number of Copies Field is empty").showInformation();
         }
         else{
             copies = Integer.parseInt(addBookCopies1.getText());//making the copies-string taken an int
         }
        if(addBookShelfNo1.getText().length() == 0){
            Dialogs.create().title("Warning").message("Book shelf number Field is empty").showInformation();
        }
        else{
            shelfNo = Integer.parseInt(addBookShelfNo1.getText());//making the shelfNo-string taken an int
        }
        //we now set the values
        lchelper.setAuthor(author);
        lchelper.setBcode(bcode);
        lchelper.setCopies(copies);
        lchelper.setEdition(edition);
        lchelper.setTitle(title);
        lchelper.setIsbn(isbn);
        lchelper.setShelfNo(shelfNo);
        lchelper.setPublisher(publisher);
        //we now make sure that no field is empty 
        //call InsertStuff function here; this is intended to use the parameters gotten above here and insert them into the database
        if(lchelper.AddNewBookInsertMethod()){
            //System.out.println("Insert Operation Successful");
            Dialogs.create().title("Congratulations").message("Book has been successfully inserted into the Database").showInformation();
        }
        else{
            //System.out.println("Insert Operation not Successful");
            Dialogs.create().title("Alert").message("Insert Operation not successful. There is seemingly a book with the same ISBN already inserted in the Database. You may rather try to update the number of copies").showInformation();
        }
        //now clearing form fields
        addBookISBN1.clear();
        addBookTitle1.clear();
        addBookAuthor.clear();
        addBookAuthor1.clear();
        addBookBcode1.clear();
        addBookCopies1.clear();
        addBookShelfNo1.clear();
        addBookEdition1.clear();
    }

    @FXML
    private void AddNoCopies(MouseEvent event) {//this is the function that is called if we wish to add number of copies so that a form now appears
        makeVisible();
        updateCopies.setVisible(true);
    }

    @FXML
    private void FillBookDetails4Update(MouseEvent event) {
             //before call to , we pick the content of the isbn field
        String isbn = updateBookISBN11.getText();
        //now we set this using its set method
        lchelper.setIsbn(isbn);
        //display full info about book for librarian to confirm update
        if(lchelper.FillDetailsForDelete()){
            //the book has been found, we now fill in details in the various fields
            updateBookTitle11.setText(lchelper.dbaseHelper.getValueAt(0, 3).toString());
            updateBookAuthor1.setText(lchelper.dbaseHelper.getValueAt(0, 4).toString());
            updateBookPublisher1.setText(lchelper.dbaseHelper.getValueAt(0, 5).toString()); 
            updateBookEdition11.setText(lchelper.dbaseHelper.getValueAt(0, 6).toString());
            updateBookBcode11.setText(lchelper.dbaseHelper.getValueAt(0, 1).toString());
            updateBookCopies11.setText(lchelper.dbaseHelper.getValueAt(0, 7).toString());
            updateBookShelfNo11.setText(lchelper.dbaseHelper.getValueAt(0, 2).toString());
        }
        else{
            //the book has not been found, we report this
            Dialogs.create().title("Warning").message("There is no book registered in this system with the given isbn. Please try adding this book first before updating").showInformation();
            updateBookISBN11.clear();
        }
    }
    @FXML
    private void UpdateNoCopies(MouseEvent event) throws SQLException {//updating copies
        //first we set these copies
        copies = Integer.parseInt(updateBookCopies11.getText());//conversion to int
        isbn = updateBookISBN11.getText();
        lchelper.setIsbn(isbn);
        lchelper.setCopies(copies);
        if(lchelper.UpdateNoCopiesInsertMethod()){
            //System.out.println("Delete Operation was successful");
            Dialogs.create().title("Congratulations").message("Number of Copies successfully updated").showInformation();
        }
        else{
            //System.out.println("Delete Operation was not successful");
            Dialogs.create().title("Alert").message("Update of Copies failed").showInformation();
        }
        updateBookTitle11.clear();
        updateBookAuthor1.clear();
        updateBookPublisher1.clear();
        updateBookEdition11.clear();
        updateBookBcode11.clear();
        updateBookCopies11.clear();
        updateBookShelfNo11.clear();
        updateBookISBN11.clear();
    }
}

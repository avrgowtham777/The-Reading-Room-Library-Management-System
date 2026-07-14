import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book
{
    private int bookID;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookID, String title, String author, boolean isIssued) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }

    public void issueBook()
    {
        isIssued = true;
    }

    public void returnBook()
    {
        isIssued = false;
    }

    public void displayBookDetails()
    {
        System.out.println("BookID = " + bookID);
        System.out.println("Title = " + title);
        System.out.println("Author = " + author);
        System.out.println("IsIssued = " + isIssued);
    }

}

class Member
{
    private int memberID;
    private String memberName;


    public Member(int memberID, String memberName) 
    {
        this.memberID = memberID;
        this.memberName = memberName;
    }


    public int getMemberID() {
        return memberID;
    }


    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }


    public String getMemberName() {
        return memberName;
    }


    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void displayMemberDetails()
    {
        System.out.println("Member ID = " + memberID);
        System.out.println("Member name = " + memberName);
    }

}

interface LibraryOperations
{
    void addBook(Book book);
    void removeBook(int bookID);
    void issueBook(int bookID) throws BookNotFoundException;
    void returnBook(int bookID) throws BookNotFoundException;

}

class Library implements LibraryOperations
{
    Library()
    {
        allbooks.add(b1);
        allbooks.add(b2);
        allbooks.add(b3);
        allbooks.add(b4);
        allbooks.add(b5);
    }
    List<Book> allbooks = new ArrayList<>();

    Book b1 = new Book(301, "Puspha", "AlluArjun", true);
    Book b2 = new Book(302, "Temper", "Tarak", true);
    Book b3 = new Book(303, "Salaar", "Prabas", true);
    Book b4 = new Book(304, "Athadu", "Mahesh", true);
    Book b5 = new Book(305, "Chirutha", "RamCharan", true);

    public void addBook(Book book)
    {
        allbooks.add(book);

    }

    public Book searchBook(int bookID)
    {
        for(Book b : allbooks)
        {
            if(b.getBookID() == bookID)
            {
                return b;
                
            }
        }
           return null;
            

        }  
    

    public void removeBook(int bookID)
    {
        Book b = searchBook(bookID);
        
            if(b != null)
            {
                allbooks.remove(b);
                System.out.println("Book removed sucessfully");
            }
            else
            {
                System.out.println("Book not found");
            }
            
        
    }

    public void issueBook(int bookID)throws BookNotFoundException
    {
        Book b = searchBook(bookID);

        if(b == null)
        {
            throw new BookNotFoundException("BOOKID : " + bookID + "Not found");
        
        }
        
        b.issueBook();
        System.out.println("Book issued sucessfully");
           
    }

    public void returnBook(int bookID)throws BookNotFoundException
    {
        Book b = searchBook(bookID);

        if(b == null)
        {
            throw new BookNotFoundException("book not returned");
        }
        b.returnBook();
        System.out.println("book returned ");
    }

    public void DisplayAllBook()
    {
        for(Book b : allbooks)
        {
            b.displayBookDetails();
            System.out.println();
        }
        

    }

    public void saveBooksToFile() throws FileNotFoundException
{
    PrintWriter writer = new PrintWriter("books.txt");

        for(Book b : allbooks)
        {
            writer.println(
                    b.getBookID() + "," +
                    b.getTitle() + "," +
                    b.getAuthor() + "," +
                    b.isIssued());

            ;
        }

        writer.close();

        System.out.println("Books saved successfully.");
    }

    


    public void loadBooksFromFile() throws IOException
{
    
        BufferedReader reader =new BufferedReader(new FileReader("books.txt"));

        allbooks.clear();

        String line;

        while((line = reader.readLine()) != null)
        {
            String[] data = line.split(",");

            Book b = new Book(
                    Integer.parseInt(data[0]),
                    data[1],
                    data[2],
                    Boolean.parseBoolean(data[3]));

            allbooks.add(b);
        }

        reader.close();

        System.out.println("Books loaded successfully.");
    }

    
}
   
    
class BookNotFoundException extends Exception
{
    BookNotFoundException (String a)
    {
        super(a);
    }
}

class Ebook extends Book
{

    public Ebook(int bookID, String title, String author, boolean isIssued) {
        super(bookID, title, author, isIssued);
    }

    public void displayBookDetails()
    {
        System.out.println("BookID = " + getBookID());
        System.out.println("Title = " + getTitle());
        System.out.println("Author = " + getAuthor());
        System.out.println("IsIssued = " + isIssued());
    }

}    

public class Main 
{
    public static void main(String args[]) throws NumberFormatException, IOException
    {
        Library library = new Library();

        Scanner sc = new Scanner(System.in);

        while(true){
        

        System.out.println("______MY LIBRARY______");
        System.out.println("1. ADD BOOK");
        System.out.println("2. REMOVE BOOK");
        System.out.println("3. SEARCH BOOK");
        System.out.println("4. ISSUE BOOK ");
        System.out.println("5. RETURN BOOK");
        System.out.println("6. DISPLAY ALL BOOKS");
        System.out.println("7. SAVE BOOKS");
        System.out.println("8. LOAD BOOKS");
        System.out.println("9. EXIT");

        System.out.println("Enter your choice");
        

        int choice = sc.nextInt();
        
        switch(choice)
        {
            case 1 :

            System.out.println("Enter Book ID");
            int Id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Book title");
            String booktitle = sc.nextLine();

            System.out.println("Enter author name");
            String bookauthor = sc.nextLine();

            Book book = new Book(Id, booktitle, bookauthor, false);

            library.addBook(book);
            System.out.println(" Book Added Sucessfully");

            break;

            case 2 :

            System.out.println("Enter Book ID");
            int removeid = sc.nextInt();

            library.removeBook(removeid);
            
            break;

            case 3 :

            System.out.println("Enter Book ID");
            int searchid = sc.nextInt();

            Book found = library.searchBook(searchid);

            if(found != null)
            {
                found.displayBookDetails();
               
            }
            else 
            {
                System.out.println("book not found");

            }

            break;

            case 4:

            System.out.println("Enter Book ID");
            int issueid = sc.nextInt();

            try
            {
                library.issueBook(issueid);
                
            }

            catch(BookNotFoundException e)
            {
                System.out.println(e.getMessage());
            }

            break;

            case 5 :

            System.out.println("Enter Book ID");
            int returnid = sc.nextInt();

            try
            {
                library.returnBook(returnid);
                System.out.println("Book returned sucessfully");

            }

            catch(BookNotFoundException e)
            {
                System.out.println(e.getMessage());
    

            }
            break;

            case 6 :

            library.DisplayAllBook();
            System.out.println();

            break;

            case 7 :

            library.saveBooksToFile();
            System.out.println("books saved sucessfully");
            
            break;

            case 8 :

            library.loadBooksFromFile();
            System.out.println("books loaded to the library sucessfully");

            break;

            case 9 :

            System.out.println("THANK YOU");
            
            System.exit(0);

            break;

            default :
            System.out.println("invalid choice");




sc.close();


        



        }

        
        

        
    }
    
}
}

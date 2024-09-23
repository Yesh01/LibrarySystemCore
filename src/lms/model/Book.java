package lms.model;

public class Book {

    /*Setter & Getter Construction */

    private int id;
    private int srlNo;
    private String bookName;
    private String authorName;
    private int bookQty;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSrlNo() {
        return srlNo;
    }

    public void setSrlNo(int srlNo){
        this.srlNo = srlNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getBookQty() {
        return bookQty;
    }

    public void setBookQty(int bookQty) {
        this.bookQty = bookQty;
    }

}

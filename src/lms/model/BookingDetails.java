package lms.model;

public class BookingDetails {
    
    /*id

     * std id
     * book id
     *
     * S bookname
     * S authorName
     *
     * I qty
     * I srl
     *
     */


     public int Id;
     public int studentId;
     public int bookId;

     // ------------ eme lang

     public String bookName;
     public String authorName;

     // ------------- eme

     public int qty;
     public int srl_no;

     public int getId(){ // amp [refractor] taena 2 hours koto hinanap as get() prev commited...
        return Id;
     }

     public void setId(int Id) {
        this.Id = Id;
     }

     public int getStudentId() {
        return studentId;
     }

     public void setStudentId(int studentId) {
        this.studentId = studentId;
     }

     public int getBookId(){
        return bookId;
     }

     public void setBookId(int bookId) {
        this.bookId = bookId;
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

     public int getQty() {
        return qty;
     }

     public void setQty(int qty) {
        this.qty = qty;
     }

     public int getSrlNo() {
        return srl_no;
     }

     public void setSrlNo(int srl_no) {
        this.srl_no = srl_no;
     }

}

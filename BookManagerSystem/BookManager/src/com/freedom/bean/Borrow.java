package com.freedom.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int borrowno;
    @ManyToOne
    @JoinColumn(name = "BOOKNO", referencedColumnName = "bookno")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "READERNO", referencedColumnName = "readerno")
    private Reader reader;
    private String time;
    private String rtime;

    public Borrow() {
	super();
    }

    public Borrow(int borrowno, Book book, Reader reader, String time,
	    String rtime) {
	super();
	this.borrowno = borrowno;
	this.book = book;
	this.reader = reader;
	this.time = time;
	this.rtime = rtime;
    }

    public int getBorrowno() {
	return borrowno;
    }

    public void setBorrowno(int borrowno) {
	this.borrowno = borrowno;
    }

    public Book getBook() {
	return book;
    }

    public void setBook(Book book) {
	this.book = book;
    }

    public Reader getReader() {
	return reader;
    }

    public void setReader(Reader reader) {
	this.reader = reader;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

    public String getRtime() {
	return rtime;
    }

    public void setRtime(String rtime) {
	this.rtime = rtime;
    }

}

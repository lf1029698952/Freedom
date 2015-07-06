package com.freedom.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int bookno;
    private String type;
    private String name;
    private String author;
    private String press;
    private String isbn;
    private float price;
    private int number;
    private String time;
    private String remark;

    public Book() {
	super();
    }

    public Book(String type, String name, String author, String press,
	    String isbn, float price, int number, String time, String remark) {
	super();
	this.type = type;
	this.name = name;
	this.author = author;
	this.press = press;
	this.isbn = isbn;
	this.price = price;
	this.number = number;
	this.time = time;
	this.remark = remark;
    }

    public int getBookno() {
	return bookno;
    }

    public void setBookno(int bookno) {
	this.bookno = bookno;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getPress() {
	return press;
    }

    public void setPress(String press) {
	this.press = press;
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public float getPrice() {
	return price;
    }

    public void setPrice(float price) {
	this.price = price;
    }

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

}

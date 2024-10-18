package com.banking.app.service;

import com.banking.app.entity.User;

public interface BookDao {
    void viewAllBooks();
    boolean borrowBook(User user, String title);
    boolean returnBook(User user, int bookId);
    void viewAllOfMyBooks(User user);
    void viewAllAvailableBooks();
    void searchBookByTitle(String title);
    void searchBookByGenre(String genre);
    void searchBookByAuthor(String author);
}

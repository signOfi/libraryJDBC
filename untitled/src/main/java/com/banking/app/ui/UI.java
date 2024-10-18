package com.banking.app.ui;

import com.banking.app.entity.User;
import com.banking.app.service.BookDao;
import com.banking.app.service.UserDao;
import com.banking.app.service.impl.UserDaoImpl;
import com.banking.app.util.InputValidation;

import java.util.Scanner;

public class UI {

    private final Scanner scanner;
    private UserDao userDao;
    private BookDao bookDao;

    public UI(Scanner scanner) {
        this.scanner = scanner;
        userDao = new UserDaoImpl();

    }

    public void start() {

        String userInput;

        /* Register and login screen */
        do {
            printLoginRegisterMenu();
            userInput = scanner.nextLine().trim();

            switch (userInput) {
                case "1":
                    register();
                    break;
                case "2":

                    User user;
                    String title;
                    do {
                        user = login();
                        System.out.println("Login credentials not correct");
                    } while (user == null);

                    do {

                        printUserOptions();
                        userInput = scanner.next().trim();

                        switch (userInput) {
                            case "1":
                                System.out.print("Enter title here: ");
                                title = scanner.nextLine().trim();
                                bookDao.searchBookByTitle(title);
                                break;
                            case "2":
                                System.out.print("Enter author here: ");
                                String author = scanner.nextLine().trim();
                                bookDao.searchBookByAuthor(author);
                                break;
                            case "3":
                                System.out.print("Enter genre here: ");
                                String genre = scanner.nextLine().trim();
                                bookDao.searchBookByTitle(genre);
                                break;
                            case "4":
                                System.out.print("Enter title here: ");
                                title = scanner.nextLine().trim();
                                if (bookDao.borrowBook(user, title))
                                    System.out.println("Book has been borrowed");
                                else
                                    System.out.println("Not able to borrow book");
                                break;
                            case "5":
                                System.out.print("Enter book id to return here: ");
                                String bookId = scanner.nextLine().trim();
                                if (InputValidation.isAnIntegerAndGreaterThanZero(bookId)) {
                                    if (bookDao.returnBook(user, Integer.parseInt(bookId))) {
                                        System.out.println("Book has been returned");
                                    } else {
                                        System.out.println("Something went wrong with book id");
                                    }
                                } else {
                                    System.out.println("Not valid input");
                                }
                                break;
                            case "6":
                                bookDao.viewAllOfMyBooks(user);
                                break;
                            case "7":
                                bookDao.viewAllAvailableBooks();
                                break;
                            case "8":
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }

                    } while (!userInput.equals("8"));

                    break;
                case "3":
                    System.exit(1);
                    break;
            }

            System.out.println("Not a valid choice, try again");
        } while (true);

    }

    private User login() {
        System.out.println("******** Login Screen ********");
        System.out.println("Enter a username");
        String username = scanner.nextLine().trim();

        System.out.println("Enter a password");
        String password = scanner.nextLine().trim();

        return userDao.login(username, password);
    }

    private void register() {
        System.out.println("******** Register Screen ********");
        System.out.println("Enter a username");
        String username = scanner.nextLine().trim();

        System.out.println("Enter a password");
        String password = scanner.nextLine().trim();

        userDao.register(username, password);
    }

    public void printLoginRegisterMenu() {
        System.out.println("******** Would you like to register or login ********");
        System.out.println("Option 1: Register");
        System.out.println("Option 2: Register");
        System.out.print("Enter option here");
    }

    public void printUserOptions() {
        System.out.println("******** User Options ********");
        System.out.println("Option 1: Search by title");
        System.out.println("Option 2: Search by author");
        System.out.println("Option 3: Search by genre");
        System.out.println("Option 4: Borrow book");
        System.out.println("Option 5: Return Book");
        System.out.println("Option 6: View all borrowed books");
        System.out.println("Option 7: View all books");
        System.out.println("Option 8: Logout");
        System.out.print("Enter option here:");
    }


}

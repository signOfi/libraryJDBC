package com.banking.app;

import com.banking.app.service.BookDao;
import com.banking.app.service.UserDao;
import com.banking.app.service.impl.BookDaoImpl;
import com.banking.app.service.impl.UserDaoImpl;
import com.banking.app.ui.UI;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        UI ui = new UI(scanner);
        ui.start();
    }
}

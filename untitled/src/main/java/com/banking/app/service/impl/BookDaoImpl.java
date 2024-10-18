package com.banking.app.service.impl;

import com.banking.app.entity.Book;
import com.banking.app.entity.User;
import com.banking.app.service.BookDao;
import com.banking.app.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDaoImpl implements BookDao {

    private final Connection connection;

    public BookDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void viewAllBooks() {

    }

    @Override
    public boolean borrowBook(User user, String title) {

        boolean bookCountIsGood = false;
        boolean userHasEnoughSpace = false;

        int bookIdToBorrow = -1;

        /* Can only borrow a book that has a count >= 1 */
        String query = "SELECT * FROM Book where title = ? AND is_available = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            preparedStatement.setBoolean(2, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bookCountIsGood = true;
                bookIdToBorrow = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }

        /* Check if User has exactly 4 books or fewer */
        query = "SELECT * FROM Book where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getUser_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            int userBookCount = 0;
            while (resultSet.next()) {
                userBookCount++;
            }
            if (userBookCount <= 4) {
                userHasEnoughSpace = true;
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }

        /* Reach here user can now borrow the book */
        if (userHasEnoughSpace && bookCountIsGood) {

            query = "UPDATE book SET is_available = ? where book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setBoolean(1, false);
                preparedStatement.setInt(2, bookIdToBorrow);
            } catch (SQLException e) {
                System.out.println("Error code for updating Balance " + e);
            }
        }
        return userHasEnoughSpace && bookCountIsGood;
    }

    @Override
    public boolean returnBook(User user, int bookId) {

        boolean userOwnsBook = false;

        /* Check if user owns the book */
        String query = "SELECT * FROM Book where id = ? AND user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, user.getUser_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userOwnsBook = true;
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }

        /* Update the status to true for is_available */
        if (userOwnsBook) {
            query = "UPDATE book SET is_available = ? where book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setBoolean(1, true);
                preparedStatement.setInt(2, bookId);
            } catch (SQLException e) {
                System.out.println("Error code for updating Balance " + e);
            }
        }

        return userOwnsBook;
    }

    @Override
    public void viewAllOfMyBooks(User user) {

        String query = "SELECT * FROM Book where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getUser_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getInt("user_id")
                );
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }

    }

    @Override
    public void viewAllAvailableBooks() {
        String query = "SELECT * FROM Book WHERE is_available = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getInt("user_id")
                );
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }
    }

    @Override
    public void searchBookByTitle(String title) {
        String query = "SELECT * FROM Book WHERE title = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getInt("user_id")
                );
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }
    }

    @Override
    public void searchBookByGenre(String genre) {
        String query = "SELECT * FROM Book WHERE genre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, genre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getInt("user_id")
                );
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }
    }

    @Override
    public void searchBookByAuthor(String author) {
        String query = "SELECT * FROM Book WHERE autor = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, author);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getInt("user_id")
                );
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }
    }

}

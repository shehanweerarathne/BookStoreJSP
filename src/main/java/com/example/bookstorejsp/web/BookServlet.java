package com.example.bookstorejsp.web;

import com.example.bookstorejsp.dao.BookDAO;
import com.example.bookstorejsp.model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;

    public void init() {
        bookDAO = new BookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/delete":
                    deleteBook(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                default:
                    listBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookDAO.selectAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.selectBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        double price = Double.parseDouble(request.getParameter("price"));
        Book newBook = new Book(title,author,genre,price);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        double price = Double.parseDouble(request.getParameter("price"));

        Book book = new Book(id, title,author,genre,price);
        bookDAO.updateBook(book);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        response.sendRedirect("list");

    }
}

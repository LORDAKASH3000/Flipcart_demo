/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.images;

import java.sql.*;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author tapas
 */
public class AddImage extends HttpServlet {
    String I_quarry,url="jdbc:oracle:thin:@localhost:1521:XE",url2 = "jdbc:mysql://127.0.0.1:3306/REGISTERED_USERS?autoReconnect=true&useSSL=false";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            String imageFile = "E:\\programing\\Java_web\\Flipcart_demo\\web\\icons\\myprofile1.png";
            String imageName = "myprofile1.png";
            String imageType = "image/png";
            
        // Get the image file from the request
            FileInputStream fin = new FileInputStream(imageFile);
            byte[] imageData = new byte[fin.available()];
            fin.read(imageData);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url2, "CHAKRA", "cckra");
            I_quarry = "INSERT INTO REGISTERED_USER_IMAGES (IMAGE_NAME, IMAGE_TYPE, IMAGE_DATA) VALUES (?, ?, ?)";
            PreparedStatement pstatement = conn.prepareStatement(I_quarry);
            pstatement.setString(1, imageName);
            pstatement.setString(2, imageType);
            pstatement.setBytes(3, imageData);
            pstatement.executeUpdate();
            fin.close();
        }catch(Exception e){
            response.getWriter().println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

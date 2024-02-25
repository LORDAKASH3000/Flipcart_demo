package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *
 * @author chakra
 */
public class Signup extends HttpServlet {
    protected Connection con;
    protected String Name,Email,Password,I_quarry,url="jdbc:oracle:thin:@localhost:1521:XE",url2 = "jdbc:mysql://127.0.0.1:3306/REGISTERED_USERS?autoReconnect=true&useSSL=false",url3O = "jdbc:oracle:thin:@DESKTOP-ESSNKGL:1521:ORCL";
    protected Integer Id = 1;
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
        response.setContentType("text/html;charset=UTF-8");


        //Getting the details
        this.Name=request.getParameter("User-Name");
        this.Password=request.getParameter("User-Password");
        this.Email=request.getParameter("User-Email");
        //Connecting to database
        try(PrintWriter out = response.getWriter()){
            Thread.sleep(3000);


        // Connecting to mysql data base
           Class.forName("com.mysql.cj.jdbc.Driver");
           con=DriverManager.getConnection(this.url2,"CHAKRA","cckra");
        //check if email is already exist or, not
            I_quarry = "SELECT USER_EMAIL FROM REGISTERED_USER_LIST WHERE USER_EMAIL = '"+this.Email+"'";
            PreparedStatement pstatement = con.prepareStatement(I_quarry);
            ResultSet result = pstatement.executeQuery();
            if(result.next()){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            I_quarry="INSERT INTO REGISTERED_USER_LIST(USER_NAME,USER_PASSWORD,USER_EMAIL) VALUES(?,?,?)";
            pstatement = con.prepareStatement(I_quarry);
            pstatement.setString(1,this.Name);
            pstatement.setString(2,this.Password);
            pstatement.setString(3,this.Email);
            pstatement.executeUpdate();
            I_quarry = "SELECT MAX(USER_ID) FROM REGISTERED_USER_LIST";
            pstatement = con.prepareStatement(I_quarry);
            result = pstatement.executeQuery();
            if(result.next())this.Id = result.getInt(1);
        //Closing the connection of mysql database
            con.close();


        // connecting to oracle database
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(this.url,"CHAKRA","cckra");
            I_quarry="INSERT INTO REGISTERED_USER_LIST VALUES(?,?,?,?)";
            pstatement = con.prepareStatement(I_quarry);
            pstatement.setInt(1,this.Id);
            pstatement.setString(2,this.Name);
            pstatement.setString(3,this.Password);
            pstatement.setString(4,this.Email);
            pstatement.executeUpdate();
            con.commit();
        //Closing the connection of oracle database
            con.close();
        }catch(Exception e){
            e.printStackTrace();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
package com.register;

import java.sql.*;
import java.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Chakra Sayan Roy
 */
public class Signin extends HttpServlet {
    protected Connection con;
    protected String Name,Email,password,I_quarry,url="jdbc:oracle:thin:@localhost:1521:XE",url2 = "jdbc:mysql://127.0.0.1:3306/REGISTERED_USERS?autoReconnect=true&useSSL=false";
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
        //  this.password="cckra";
        //  this.Email="tapasroy862@gmail.com";
        try(PrintWriter out = response.getWriter()) {
            Thread.sleep(3000);
            this.password=request.getParameter("User_Password");
            this.Email=request.getParameter("User_Email");
            // Class.forName("oracle.jdbc.driver.OracleDriver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url2, "CHAKRA", "cckra");
            I_quarry = "SELECT * FROM REGISTERED_USER_LIST WHERE USER_EMAIL=?";
            PreparedStatement pstatement = con.prepareStatement(I_quarry);
            pstatement.setString(1, this.Email);
            ResultSet result = pstatement.executeQuery();
            response.setContentType("text/html");
            if(!result.next()){response.sendError(3000,"User not found or, Wrong Email");}
            else if(!result.getString(3).equals(this.password)){response.sendError(3001,"Invalid Email or, password");}
            else{
                // Creating Session
                HttpSession HS = request.getSession();
                this.Name=result.getString(2);
                HS.setAttribute("Cust_Name", this.Name);
                HS.setAttribute("Cust_Email", this.Email);
                //Getting profile image
                I_quarry = "SELECT * FROM REGISTERED_USER_IMAGES WHERE IMAGE_ID=?";
                pstatement = con.prepareStatement(I_quarry);
                pstatement.setInt(1, result.getInt(1));
                result = pstatement.executeQuery();
                if(!result.next()){
                    out.println("hi2");
                    I_quarry = "SELECT * FROM REGISTERED_USER_IMAGES WHERE IMAGE_ID=?";
                    pstatement = con.prepareStatement(I_quarry);
                    pstatement.setInt(1,1);
                    result = pstatement.executeQuery();
                    if(!result.next()){response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
                }
                byte[] buffer = result.getBytes("IMAGE_DATA");
                String base64Image = "data:" + result.getString("IMAGE_TYPE") + ";base64," + Base64.getEncoder().encodeToString(buffer);
                HS.setAttribute("Cust_Pf_img", base64Image);
                result.close();
            }
        }catch(Exception e){}finally{
            try{
                con.close();
            }catch(SQLException e){}
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

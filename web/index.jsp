<%-- 
    Document   : index
    Created on : May 2, 2023, 1:52:51 AM
    Author     : Chakra Sayan Roy
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FlipKart</title>
        <link rel="shortcut icon" href="icons/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <link type="text/css" rel="stylesheet" href="css/Exp.css">
    </head>

    <body>
        <%! private String name = null,email = null,img = null; %>
        <%! private Boolean b = true; %>
        <% HttpSession HS = request.getSession(false); %>
        <% if(HS!=null){ %>
        <%      this.name = (String)HS.getAttribute("Cust_Name"); %>
        <%      this.email = (String)HS.getAttribute("Cust_Email"); %>
        <%      this.img = (String)HS.getAttribute("Cust_Pf_img"); %>
        <%      if(this.name != null && this.email!= null)b=true;else b=false;%>
        <% }%>
        <nav class="menu">
            <div class="fullmenu">
                <div class="container">
                    <div class="Logo_plus">
                        <img id="sitelogo" src="icons/flipkart.png" alt="Flipkart">
                        <a class="fullplus" href="/">
                            Explore
                            <span class="plus">Plus</span>
                            <img src="icons/plus.png" alt="Plus">
                        </a>
                    </div>
                    <div class="formsearch">
                        <form class="form" action="/Search" method="GET">
                            <div class="fullform">
                                <div class="forminput">
                                    <input class="input" type="text" title="Search for products, brands and more"
                                           placeholder="Search for products, brands and more" autocomplete="off" value>
                                </div>
                                <button class="formbutton" type="submit">
                                    <div class="btn_div">
                                        <img class="img_btn" src="icons/seic.svg" alt="search">
                                    </div>
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="login" id="logB">
                        <a href="Login.html">
                            <button class="loginbutton">
                                Login
                            </button>
                        </a>
                        <div class="hoveroptions">
                            <div class="arrow">
                                <img src="icons/arrow_up_svg.svg" alt="Dropdown">
                            </div>
                            <div class="dropdown">
                                <div class="option_container">
                                    <a href="Signup.html">
                                        <div class="options">
                                            Sign Up
                                        </div>
                                    </a>
                                    <a href="">
                                        <div class="options">
                                            About
                                        </div>
                                    </a>
                                    <a href="">
                                        <div class="options">
                                            Links
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="sfinfo" class="profile_options">
                        <div class="selfinfo">
                            <div class="outer">
                                <%-- <img class='inner_img' src="icons/profile.png"> --%>
                                <img class='inner_img' src="<%=this.img%>">
                                <!--<p>Chakra sayan roy</p>-->
                                <p><%=this.name%></p>
                            </div>
                            <span class="material-symbols-outlined">
                                expand_less
                            </span>
                        </div>
                        <div class="hoveroptions2 hoveroptions">
                            <div id="arrow2" class="arrow">
                                <img src="icons/arrow_up_svg.svg" alt="Dropdown">
                            </div>
                            <div class="dropdown">
                                <div class="option_container">
                                    <a href="Signup.html">
                                        <div class="options">
                                            Sign Up
                                        </div>
                                    </a>
                                    <a href="">
                                        <div class="options">
                                            About
                                        </div>
                                    </a>
                                    <a href="./Signout">
                                        <div class="options">
                                            Log Out
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="shopcart">
                        <div class="cart">
                            <a href="/Cart">
                                <img src="icons/cart.svg" alt="">
                                Cart</a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </body>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script>
        const element = document.getElementById("logB");
        document.addEventListener('DOMContentLoaded',function(){
            var ele = <%=b%>;
            console.log(ele);
            if(ele){
                element.style.display = "none";
                document.getElementById("sfinfo").style.display = "block";
            }
        });
        const button = document.querySelector(".loginbutton");
        const dropdown = document.querySelector(".hoveroptions");
        button.addEventListener("mouseover", () => {
            dropdown.classList.add("hover");
        });
        dropdown.addEventListener("mouseover", () => {
            dropdown.classList.add("hover");
        });
        button.addEventListener("mouseout", () => {
            dropdown.classList.remove("hover");
        });
        dropdown.addEventListener("mouseout", () => {
            dropdown.classList.remove("hover");
        });

        const info = document.querySelector(".profile_options");
        const arrow = document.querySelector(".material-symbols-outlined");
        const dropw = document.querySelector(".hoveroptions2");
        let drop = false;
        info.addEventListener("click", () => {
            if(drop){
                drop=false;
                arrow.style.transform = "rotate(0deg)";
                dropw.classList.remove("hover");
            }
            else{
                drop=true;
                arrow.style.transform = "rotate(180deg)";
                dropw.classList.add("hover");
            }
        });
        info.addEventListener("mouseover", () => {
            drop = true;
            arrow.style.transform = "rotate(180deg)";
            dropw.classList.add("hover");
        });
        info.addEventListener("mouseout", () => {
            drop = false;
            arrow.style.transform = "rotate(0deg)";
            dropw.classList.remove("hover");
        });
    </script>

</html>

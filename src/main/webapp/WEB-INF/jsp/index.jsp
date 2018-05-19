<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <title>私人博客</title>
    <!-- BOOTSTRAP CORE STYLE -->
    <link href="../assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICON STYLE -->
    <link href="../assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE CSS -->
    <link href="../assets/css/style.css" rel="stylesheet" />
</head>
<body>
<div id="header">
    <div class="overlay">
        <div class="container">
            <div class="row">
                <div class="col-md-4 logo-div">
                    <div class="logo-inner text-center">
                        <div class="logo-name">
                            <a href="index.html">
                                <img src="../assets/img/me.jpg" class="img-circle" />
                            </a>
                        </div>

                    </div>

                </div>
                <div class="col-md-8 header-text-top " id="about">
                    <h1>Nice responsive template for blogger.</h1>
                    This blogging template use bootstrap and html to create a very nice blogging page with great responsive. <br />
                    Here you can write a general notes about your blog.<br />
                    <h2><strong>Who I am ? </strong></h2>
                    <i>I am Jhon Deo </i>

                </div>
            </div>
        </div>
    </div>
</div>
<!--END HEADER SECTION-->
<div class="info-sec">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                Say hello to me at <strong>hello</strong>@yourdomain.com
            </div>
            <div class="col-md-2">
                <div class="social-link">
                    <a href="#" class="btn btn-default btn-xs"><i class="fa fa-facebook fa-2x"></i></a>
                    <a href="#" class="btn btn-default btn-xs"><i class="fa fa-linkedin fa-2x"></i></a>
                    <a href="#" class="btn btn-default btn-xs"><i class="fa fa-google-plus fa-2x"></i></a>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="copyrights">Collect from <a href="http://www.smallseashell.com/"  title="网站模板">网站模板</a></div>
<!--END INFO SECTION-->
    <jsp:include page="${container}"></jsp:include>
<!--END HOME PAGE SECTION-->
<div class="footer-sec" style="margin-top: 0px;">
    <div class="container">
        <div class="row">
            <div class="col-md-12 foo-inner">
                &copy; 2015 Yourdomain.com | More Templates <a href="http://www.smallseashell.com/" target="_blank" title="贝壳模板">贝壳模板</a> - Collect from <a href="http://www.smallseashell.com/" title="网页模板" target="_blank">网页模板</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>


<script src="../assets/js/jquery-1.12.4.min.js" />
<script src="../assets/js/bootstrap.min.js"/>
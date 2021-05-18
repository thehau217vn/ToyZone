<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lighting A Ecommerce Category Flat Bootstarp Resposive Website Template | Home :: w3layouts</title>

    <link href="<c:url value="/templateWeb/css/bootstrap.css"/>" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <!--theme style-->
    <link href="<c:url value="/templateWeb/css/style.css"/>" rel="stylesheet" type="text/css" media="all"/>
    <script src="<c:url value="/templateWeb/js/jquery.min.js"/>"></script>

    <!--//theme style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Wedding Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- start menu -->
    <script src="<c:url value="/templateWeb/js/simpleCart.min.js"/>"></script>
    <!-- start menu -->
    <link href="<c:url value="/templateWeb/css/memenu.css"/>" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="<c:url value="/templateWeb/js/memenu.js"/>"></script>
    <script>$(document).ready(function () {
        $(".memenu").memenu();
    });</script>
    <script src="<c:url value='/templateWeb/paging/jquery.twbsPagination.js' />"></script>
    <!-- /start menu -->
    <script src='<c:url value="/templateWeb/js/responsiveslides.min.js"></c:url>'></script>
    <script src="<c:url value="/templateWeb/js/bootstrap.js"></c:url>"></script>
    <script src="<c:url value='/templateWeb/paging/jquery.twbsPagination.js' />"></script>
    <script src="<c:url value='/template/admin/sweetalert/sweetalert2.min.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/template/admin/sweetalert/sweetalert2.min.css' />"/>

</head>
<body class="no-skin">

<script>
    $(function () {
        $("#slider").responsiveSlides({
            auto: true,
            nav: true,
            speed: 500,
            namespace: "callbacks",
            pager: false,
        });
    });
</script>

<!-- header -->
<%@ include file="/common/web/header.jsp" %>

<!-- header -->

<dec:body/>
<!-- footer -->
<%@ include file="/common/web/footer.jsp" %>
<!-- footer -->

</body>
</html>
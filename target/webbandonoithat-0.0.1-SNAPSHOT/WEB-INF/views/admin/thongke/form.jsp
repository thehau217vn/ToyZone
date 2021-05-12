<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<spring:eval expression="@property.getProperty('thongke.url')" var="url"/>
<c:url var="thongke_url" value="${url}"/>
<html>
<head>
    <title>Thống Kê</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                         <c:if test="${not empty message}">
									<div class="alert alert-${alert}">
  										${message}
									</div>
						</c:if>
                        <form action="${thongke_url}" method="POST" id="formSubmit" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Từ ngày : </label>
                                <div class="col-sm-9">
                                	<input type="date" name="tungay" id="tungay" class="col-xs-10 col-sm-5 "/>
                                	
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Đến ngày: </label>
                                <div class="col-sm-9">
                                	<input type="date" name="denngay" id="denngay" class="col-xs-10 col-sm-5 "/>
                                	
                                </div>
                               
                            </div>
                           
                            <button  class="btn btn-white btn-warning btn-bold">Thống Kê</button>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
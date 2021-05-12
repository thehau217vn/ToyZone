<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>


<spring:eval expression="@property.getProperty('user.url')" var="url"/>
<spring:eval expression="@property.getProperty('user.edit')" var="edit"/>
<spring:eval expression="@property.getProperty('user.api')" var="api"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>

<c:url var="url_user" value="${url}"/>
<c:url var="api_user" value="${api}"/>
<c:url var="edit_user" value="${edit}"/>

<html>
<head>
    <title>Chỉnh sửa người dùng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
           <ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/admin/trang-chu'/>">Trang
								chủ</a></li>
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_user}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Quản
								lí người dùng</a></li>
				
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                         <c:if test="${not empty message}">
									<div class="alert alert-${alert}">
  										${message}
									</div>
						</c:if>
                        <form:form id="formSubmit" class="form-horizontal" role="form" modelAttribute="user">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Họ tên</label>
                                <div class="col-sm-9">
                                	<form:input path="fullName" class="col-xs-10 col-sm-5" />
                                	<div id="fullName"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Email</label>
                                <div class="col-sm-9">
                                	<form:input path="email" class="col-xs-10 col-sm-5"/>
                                	<div id="email"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Số điện thoại</label>
                                <div class="col-sm-9">
                                	<form:input path="phone" class="col-xs-10 col-sm-5"/>
                                	<div id="phone"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tên tài khoản</label>
                                <div class="col-sm-9">
                                	<form:input path="account" class="col-xs-10 col-sm-5"/>
                                	<div id="account"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mật khẩu</label>
                                <div class="col-sm-9">
                                	<form:password path="password" showPassword="true" class="col-xs-10 col-sm-5"/>
                                	<div id="password"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Quyền</label>
                                <div class="col-sm-9">
                                <form:select path="roleId" class="col-xs-10 col-sm-5">
	                                <c:forEach items="${roles}" var="r" varStatus="status">
								        <c:choose>
								            <c:when test="${r.id eq user.roleId && not empty user.roleId}">
								                <option value="${r.id}" selected="selected">${r.name}</option>
								            </c:when>
								            <c:otherwise>
								                <option value="${r.id}">${r.name}</option>
								            </c:otherwise>
								        </c:choose> 
								    </c:forEach>
                                   
                                </form:select>
                               
                                
                                </div>
                            </div>
                             
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty user.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                    <c:if test="${empty user.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                           
                            <form:hidden path="id" id="id"/>
                        </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
	 $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        var id = $('#id').val();
        if (id == "") {
        	adduser(data);
        } else {
        	updateuser(data);
        }
    });
    function adduser(data) {
        $.ajax({
            url: '${api_user}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	if(!result.validated){
            		$.each(result.errorMessages,function(key,value){
           	            $('div[id='+key+']').html('<span class="error" style="color:red;">'+value+'</span>');
                       });
            	}else{
            		if(result.alert=='exist'){
                		window.location.href = "${edit_user}"+"?message=error_account_exist";
                	}else{
                		window.location.href = "${edit_user}/"+result.id+"?message=insert_success";
                	}
            	}
            },
            error: function (error) {
            	
            	window.location.href = "${edit_user}?message=error_system";
            }
        });
    }
    function updateuser(data) {
        $.ajax({
            url: '${api_user}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	if(!result.validated){
            		$.each(result.errorMessages,function(key,value){
           	            $('div[id='+key+']').html('<span class="error" style="color:red;">'+value+'</span>');
            		});
            	}else{
            		if(result.alert=='exist'){
                		window.location.href = "${edit_user}/"+result.id+"?message=error_account_exist";
                	}else{
                		window.location.href = "${edit_user}/"+result.id+"?message=update_success";
                	}
            	}
            },
            error: function (error) {
            	window.location.href = "${edit_user}/"+result.id+"?message=error_system";
            }
        });
    } 
</script>
</body>
</html>
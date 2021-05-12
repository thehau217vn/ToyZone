<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>


<spring:eval expression="@property.getProperty('category.url')" var="url"/>
<spring:eval expression="@property.getProperty('category.edit')" var="edit"/>
<spring:eval expression="@property.getProperty('category.api')" var="api"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>

<c:url var="url_category" value="${url}"/>
<c:url var="api_category" value="${api}"/>
<c:url var="edit_category" value="${edit}"/>

<html>
<head>
    <title>Chỉnh sửa danh mục</title>
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
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_category}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Quản
								lí danh mục</a></li>
				
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
                        <form:form id="formSubmit" class="form-horizontal" role="form" modelAttribute="category">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tên danh mục</label>
                                <div class="col-sm-9">
                                	<form:input path="name" name="id" id="name" class="col-xs-10 col-sm-5"/>
                                	<div id="name"></div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty category.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                    <c:if test="${empty category.id}">
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
            addCate(data);
        } else {
            updateCate(data);
        }
    });
    function addCate(data) {
        $.ajax({
            url: '${api_category}',
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
            		window.location.href = "${edit_category}"+result.id+"?message=insert_success";
            	}
            	
            },
            error: function (error) {
            	
            	window.location.href = "${edit_category}?message=error_system";
            }
        });
    }
    function updateCate(data) {
        $.ajax({
            url: '${api_category}',
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
            		window.location.href = "${edit_category}"+result.id+"?message=update_success";
            	}
            	
            },
            error: function (error) {
            	window.location.href = "${edit_category}"+result.id+"?message=error_system";
            }
        });
    } 
</script>
</body>
</html>

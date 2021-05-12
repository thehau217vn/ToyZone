<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>



<spring:eval expression="@property.getProperty('product.url')" var="url"/>
<spring:eval expression="@property.getProperty('product.edit')" var="edit"/>
<spring:eval expression="@property.getProperty('product.api')" var="api"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>

<c:url var="url_product" value="${url}"/>
<c:url var="api_product" value="${api}"/>
<c:url var="edit_product" value="${edit}"/>
<html>
<head>
    <title>Chỉnh Sửa Sản Phẩm</title>
    <base href="${pageContext.servletContext.contextPath}"/>
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
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_product}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Chọn
								danh mục</a></li>
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_product}/${idCategory}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Quản Lí
								sản phẩm</a></li>
				
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
                        <form:form action="${api_product}" method="POST" id="formSubmit" class="form-horizontal" role="form" modelAttribute="product" enctype="multipart/form-data">
                        	<form:hidden path="id" id="id"/>
                        	<div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Danh mục: </label>
                                <div class="col-sm-9">
                                	<form:select path="idCategory" class="col-xs-10 col-sm-5">
                                	<c:forEach items="${category.listResult}" var="item" >
									        <c:choose>
									            <c:when test="${item.id==idCategory && not empty idCategory}">
									                <option value="${item.id}" selected="selected">${item.name}</option>
									            </c:when>
									            <c:otherwise>
									                <option value="${item.id}">${item.name}</option>
									            </c:otherwise>
									        </c:choose> 
									    </c:forEach>
                                	</form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tên sản phẩm : </label>
                                <div class="col-sm-9">
                                	<form:input path="name" id="name" class="col-xs-10 col-sm-5"/>
                                	 <div class="error" style="color:red;">
                                		<form:errors path="name" class="col-xs-10 col-sm-5"/>
                               		 </div>
                                </div>
                                
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Số lượng: </label>
                                <div class="col-sm-9">
                                	<form:input path="count" id="count" class="col-xs-10 col-sm-5"/>
                                	 <div class="error" style="color:red;">
                                		<form:errors path="count" class="col-xs-10 col-sm-5"/>
                               		 </div>
                                </div>
                               
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Giá : </label>
                                <div class="col-sm-9">
                                	<form:input path="price" id="price" class="col-xs-10 col-sm-5"/>
                                	<div class="error" style="color:red;">
                                		<form:errors path="price" class="col-xs-10 col-sm-5"/>
                                	</div>
                                </div>
                                 
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Giảm giá : </label>
                                <div class="col-sm-9">
                                	<form:input path="discount" id="discount" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả : </label>
                                <div class="col-sm-9">
                                	<form:textarea path="content" id="content" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình ảnh : </label>
                                <div class="col-sm-9">
                                	<input type="file" name="file" id="file"/>
                                </div>
                                 <div class="col-sm-9">
                                	<form:hidden  path="image" />
                                </div>
                                <c:if test="${not empty product.image}">
                                      <img style=" width: 200px; height: 200px; margin-left: 200px"  src='<c:url value="${product.image}"></c:url>'/>
              						
                                    </c:if>
                                    <c:if test="${empty product.image}">
                                        
                                    </c:if>
                                </div>
                           	<!-- <input type="hidden" value="${idCategory}" name="idCategory"> -->
                               
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty product.id}">
                                        <input type="submit" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnAddOrUpdateNew"/>
                                    	
                                    </c:if>
                                    <c:if test="${empty product.id}">
                                        <input type="submit" class="btn btn-white btn-warning btn-bold" value="Thêm" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                            
                        </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
 				<script>
                // Replace the <textarea id="editor1"> with a CKEditor 4
                // instance, using default configuration.
                CKEDITOR.replace('content');
            </script>
</body>
</html>
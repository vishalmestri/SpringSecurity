<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  </head>
  <body>
    <%-- nav bar start --%>
    <nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Pricing</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled">Disabled</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
    <%-- nav bar end   --%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <form:form action="${contextPath}/employeeEdit?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="employee" enctype="multipart/form-data">
    <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Edit Employee</label>
   <form:hidden path="id"/>
   </div>
  <div class="mb-3 form-group">
    <label for="exampleInputEmail1" class="form-label">Name</label>
    <form:input path="name" class="form-control"/>
   </div>
  <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Email</label>
     <form:input path="email" class="form-control"/>
   </div>
   
   
   
   
   
    <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Birthdate</label>
     <div class="input-group date" id="datepicker">
            
            <form:input path="dob" class="form-control"/>
            <span class="input-group-append">
            <span class="input-group-text bg-white d-block">
            <i class="fa fa-calendar"></i>
            </span>
            </span>
        </div>
   </div>
   
  <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Gender</label>
     Male&nbsp;<form:radiobutton path="gender" class="form-check-input" value="0"/>
     &nbsp;&nbsp;Female&nbsp;<form:radiobutton path="gender" class="form-check-input" value="1"/>
   </div>
 
  <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Role</label>
     <form:select path="role" class="form-control" >
     <form:option value="admin">admin</form:option>
     <form:option value="user">user</form:option>
     </form:select>
   </div>
 
  
  <%-- image upload - start --%>           


<div class="mb-3 form-group">
   

   
 <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                   Select Image file
                <%--    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default active">File</button>
                        <button type="button" class="btn btn-default">URL</button>
                    </div>
                    --%> 
                </div>
                <div class="file-tab panel-body">
           <%--       <img src="data:image/jpg;base64,${employee.profilePicFileContent}" alt="Image preview" class="thumbnail"/>
             --%>
                    <label class="btn btn-primary btn-file">
                        <span>Browse</span>
                        <!-- The file is stored here. -->
                        <input type="file" name="profilePicUserName">
                    </label>
                    <button type="button" class="btn btn-danger">Delete image</button>
                </div>
                <div class="url-tab panel-body">
                    <div class="input-group">
                        <input type="text" class="form-control hasclear" placeholder="Image URL">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default">Submit</button>
                        </div>
                    </div>
                    <button type="button" class="btn btn-default">Remove</button>
                    <!-- The URL is stored here. -->
                    <input type="hidden" name="image-url" id="image-url">
                </div>
            </div>

     </div>     
 
<%-- image upload - end --%>   
  
  
  <button type="submit" class="btn btn-primary">Submit</button>
</form:form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Bootstrap datepicker JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<script src="${contextPath}/resources/imageupload/bootstrap-imageupload.js"></script>
  <link href="${contextPath}/resources/imageupload/bootstrap-imageupload.css" rel="stylesheet">
  
<script type="text/javascript">
    $(document).ready(function () {
        $('#datepicker').datepicker({ format: 'dd-M-yyyy' });
        //...
    });

    var $imageupload = $('.imageupload');
    $imageupload.imageupload();
    //$imageupload.addClass('imageupload-disabled');
    
    var $fileTab = $imageupload.find('.file-tab');
    var $browseFileButton = $fileTab.find('.btn:eq(0)');
    $browseFileButton.find('span').text('Change');
    
    var $removeFileButton = $fileTab.find('.btn:eq(1)');
    $removeFileButton.css('display', 'inline-block');
    $fileTab.prepend('<img src="data:image/jpg;base64,<c:out value="${employee.profilePicFileContent}"></c:out>" alt="Image preview" class="thumbnail" style="max-width: 250px; max-height: 250px">');
  
    </script> 

  </body>
</html>


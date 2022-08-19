<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View Employee</title>
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
    <form:form action="${contextPath}/dashboard" method="GET" modelAttribute="employee">
    <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">View Employee</label>
   <form:hidden path="id"/>
   </div>
  <div class="mb-3 form-group">
    <label for="exampleInputEmail1" class="form-label">Name</label>
    
    <c:out value="${employee.name}"/>
   </div>
  <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Email</label>
    
     <c:out value="${employee.email}"/>
   </div>
   
   <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Birthdate</label>
    
    <c:out value="${employee.dob}"/>
     
   </div>
   
  <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Gender</label>
    
    <c:if test="${employee.gender==0}">Male</c:if>
    <c:if test="${employee.gender==1}">Female</c:if>
   
   </div>
 
  <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Role</label>
     
   
      <c:out value="${employee.role}"/>
   </div>
 
   <div class="mb-3 form-group">
    <label for="exampleInputPassword1" class="form-label">Profile pic</label>
       <img src="data:image/jpg;base64,${employee.profilePicFileContent}"/>
      <c:out value="${employee.profilePicFileContent}"/>
   </div>
   
   
  <button type="submit" class="btn btn-primary">Back</button>
</form:form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>


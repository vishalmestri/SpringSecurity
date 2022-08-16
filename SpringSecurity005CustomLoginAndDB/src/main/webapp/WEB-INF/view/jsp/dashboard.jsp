<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
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
    
    <button type="button" class="btn" onclick="window.location.href='employeeNew'" ><i class="fa fa-plus-square" aria-hidden="true"></i></button>
    
    <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Gender</th>
       <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
 <c:forEach  var="employee" items="${employeeList}">
    <tr>
      <th scope="row"> <c:out value="${ employee.id}"></c:out> </th>
      <td> <c:out value="${ employee.name}"></c:out></td>
      <td><c:out value="${ employee.email}"></c:out></td>
      <td><c:out value="${ employee.gender}"></c:out></td>
      <td>
      	<button type="submit" onclick="window.location.href='delete/${employee.id}'" >
      <i class="fa fa-trash"></i>
      </button>
      
      <button type="submit" onclick="window.location.href='edit/${employee.id}'" >
      <i class="fa fa-edit"></i>
      </button>
      
      <button type="submit" onclick="window.location.href='view/${employee.id}'" >
      <i class="fa fa-eye"></i>
      </button>
      
      </td>
    </tr>
 
 </c:forEach>
 
  </tbody>
</table>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
  </body>
</html>


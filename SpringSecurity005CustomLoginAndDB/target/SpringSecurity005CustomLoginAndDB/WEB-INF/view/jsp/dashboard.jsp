<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
          <a class="nav-link"><button type="submit" onclick="window.location.href='logout'" >
      <i class="fa fa-sign-out"></i>
      </button></a>
        </li>
      </ul>
    </div>
  </div>
</nav>
    <%-- nav bar end   --%>
     <sec:authorize access="hasAuthority('admin')">
    <button type="button" class="btn" onclick="window.location.href='employeeNew'" ><i class="fa fa-plus-square" aria-hidden="true"></i></button>
    </sec:authorize>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Gender</th>
      <th scope="col">Role</th>
       <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
 <c:forEach  var="employee" items="${users}">
    <tr>
      <th scope="row"> <c:out value="${ employee.id}"></c:out> </th>
      <td> <c:out value="${ employee.name}"></c:out></td>
      <td><c:out value="${ employee.email}"></c:out></td>
      <td><c:out value="${ employee.gender}"></c:out></td>
     <td><c:out value="${ employee.role}"></c:out></td>
 
      <td>
          <sec:authorize access="hasAuthority('admin')">
         
      	<button type="submit" onclick="window.location.href='delete/${employee.id}'" >
      <i class="fa fa-trash"></i>
      </button>
      
      <button type="submit" onclick="window.location.href='edit/${employee.id}'" >
      <i class="fa fa-edit"></i>
      </button>
      
       </sec:authorize>
       
      <button type="submit" onclick="window.location.href='view/${employee.id}'" >
      <i class="fa fa-eye"></i>
      </button>
      
      </td>
    </tr>
 
 </c:forEach>
 
  </tbody>
</table>
<%-- pagination start --%>
  
  
<div id="pagination">

<c:url value="dashboard" var="first">
        <c:param name="page" value="1"/>
    </c:url>
    <c:if test="${lastPageInPagination-NUMBER_OF_PAGES > 1}">
    <a href='<c:out value="${first}" />' class="pn first">First</a>
    </c:if>
    

    <c:url value="dashboard" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="${firstPageInPagination}" end="${lastPageInPagination}" step="1" varStatus="i">
   
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="dashboard" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="dashboard" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
    
    <c:url value="dashboard" var="last">
        <c:param name="page" value="${maxPages}"/>
    </c:url>
     <c:if test="${maxPages-lastPageInPagination > NUMBER_OF_PAGES}">
    <a href='<c:out value="${last}" />' class="pn last">Last</a>
    </c:if>
</div>

<%-- pagination end--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
  </body>
</html>


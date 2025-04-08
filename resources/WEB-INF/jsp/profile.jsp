<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
      <meta charset="UTF-8">
      <title>Profile on Faces</title>
  </head>
  <body>

  <%@ include file="header.html" %>


  <div>
      <form method="post" action="/profile">
        <c:if test="${requestScope.profile.id == null}">
          <h1>
            New User
          </h1>

        </c:if>

        <table>
          <td><input type="text" name="id" hidden value="${requestScope.profile.id}"></td>

          <tr>
            <td>Email</td>
            <td><input type="email" name="email" value="${requestScope.profile.email}"></td>
          </tr>
          <tr>
            <td>Firstname</td>
            <td><input type="text" name="firstName" value="${requestScope.profile.firstName}"></td>
          </tr>
          <tr>
            <td>Lastname</td>
            <td><input type="text" name="lastName" value="${requestScope.profile.lastName}"></td>
          </tr>
          <tr>
            <td>About</td>
            <td><input type="text" name="aboutMe" value="${requestScope.profile.aboutMe}"></td>
          </tr>
          <tr>
            <td>Select Gender</td>
            <td><select name="gender">
              <option value="${requestScope.profile.gender}" selected hidden="">${requestScope.profile.gender}</option>

              <c:forEach var="gender" items="${applicationScope.genders}">
                <option value="${gender}">${gender}</option>
              </c:forEach>
            </select></td>
          </tr>
        </table>
        <button type="submit">Save</button>

      </form>
    </div>



    <%@ include file="footer.html" %>

  </body>
</html>
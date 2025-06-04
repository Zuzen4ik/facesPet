<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
      <meta charset="UTF-8">
      <title>Profile on Faces</title>
  </head>
  <body>

  <%@ include file="header.jsp" %>
  <div>
      <form method="post" action="/profile">
        <c:if test="${requestScope.profile.id != null}">
          <input type="hidden" name="_method" value="PUT">
          <input type="hidden" name="id" value="${requestScope.profile.id}">
        </c:if>

        <table>
          <tr>
            <td>${requestScope.wordBundle.get("Email")}</td>
            <td><a href="/email?id=${requestScope.profile.id}">${requestScope.profile.email}</a></td>
          </tr>
          <tr>
            <td>${requestScope.wordBundle.get("firstname")}</td>
            <td><input type="text" name="firstName" value="${requestScope.profile.firstName}"></td>
          </tr>
          <tr>
            <td>${requestScope.wordBundle.get("lastname")}</td>
            <td><input type="text" name="lastName" value="${requestScope.profile.lastName}"></td>
          </tr>
          <tr>
            <td>${requestScope.wordBundle.get("birth-date")}</td>
            <td><input type="date" name="birthDate" value="${requestScope.profile.birthDate}"></td>
          </tr>
          <tr>
            <td>${requestScope.wordBundle.get("age")}</td>
            <td>${requestScope.profile.age}</td>
          </tr>
          <tr>
            <td>${requestScope.wordBundle.get("aboutme")}</td>
            <td><input type="text" name="aboutMe" value="${requestScope.profile.aboutMe}"></td>
          </tr>
          <tr>
            <td>${requestScope.wordBundle.get("gender")}</td>
            <td><select name="gender">
              <option value="${requestScope.profile.gender}" selected hidden="">${requestScope.profile.gender}</option>

              <c:forEach var="gender" items="${applicationScope.genders}">
                <option value="${gender}">${gender}</option>
              </c:forEach>
            </select></td>
          </tr>
        </table>
        <button type="submit">${requestScope.wordBundle.get("save")}</button>
      </form>

    <c:if test="${requestScope.profile.id != null}">
      <form method="post" action="/registration">
        <input type="hidden" name="_method" value="DELETE">
        <input type="hidden" name="id" value="${requestScope.profile.id}">
        <button type="submit">${requestScope.wordBundle.get("delete")}</button>
      </form>

    </c:if>
    </div>

    <%@ include file="footer.jsp" %>

  </body>
</html>
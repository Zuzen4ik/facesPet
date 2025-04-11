<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Profiles on Faces</title>
        <style>
            table, td {
                border: 1px teal solid;
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div>
            <table>
                <tr>
                    <td>id</td>
                    <td>${requestScope.wordBundle.get("firstname")}</td>
                    <td>${requestScope.wordBundle.get("lastname")}</td>
                    <td>${requestScope.wordBundle.get("email")}</td>
                    <td>${requestScope.wordBundle.get("age")}</td>
                </tr>
                <c:forEach var="profile" items="${requestScope.profiles}">
                    <tr>
                        <td>${profile.id}</td>
                        <td>${profile.firstName}</td>
                        <td>${profile.lastName}</td>
                        <td>${profile.email}</td>
                        <td>${profile.age}</td>
                    </tr>

                </c:forEach>
            </table>
        </div>

        <%@ include file="footer.jsp" %>
    </body>
</html>
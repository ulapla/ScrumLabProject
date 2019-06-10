<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<head>
    <title>Rejestracja</title>
</head>
<%@ include file="header.jsp" %>
    <section class="dashboard-section">
        <div class="container pt-4 pb-4">
            <div class="border-dashed view-height">
                <div class="container w-25">
                    <!-- fix action, method -->
                    <!-- add name attribute for all inputs -->
                    <form action="/register" method="post" class="padding-small text-center">
                        <h1 class="text-color-darker">Rejestracja</h1>
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" name="name" placeholder="podaj imię">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="surname" name="surname" placeholder="podaj nazwisko">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="email" name="email" placeholder="podaj email">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="password" name="password" placeholder="podaj hasło">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="repassword" name="password" placeholder="powtórz hasło">
                        </div>
                        <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
                        <c:if test="${msg != null}">
                            <div class="form-group">Wprowadź takie same hasła!</div>
                        </c:if>
                    </form>
                 </div>
            </div>
        </div>
    </section>
<%@ include file="footer.jsp" %>
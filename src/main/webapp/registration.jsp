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
                        <input type="text" class="form-control" id="surname" name="surname"
                               placeholder="podaj nazwisko">
                    </div>
                    <c:if test="${regexName == false}">
                        <div class="form-group">Niepoprawny format imienia lub nazwiska</div>

                    </c:if>
                    <div class="form-group">
                        <input type="text" class="form-control" id="email" name="email" placeholder="podaj email">
                        <c:if test="${regexMail == false}">
                            <div class="form-group">Niepoprawny format adresu email</div>
                        </c:if>
                        <c:if test="${email == true}">
                            <div class="alert-info">Użytkownik o tym adresie email już istnieje</div>
                        </c:if> <br>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="podaj hasło">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="repassword" name="password"
                               placeholder="powtórz hasło">
                    </div>
                    <c:if test="${regexPassword == false}">
                        <div class="alert-info">Hasło musi mieć minimum 8 znaków!</div>
                    </c:if>
                    <c:if test="${checkPassword == false}">
                        <div class="alert-info">Wprowadź takie same hasła!</div>
                    </c:if> <br>
                    <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>

                </form>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<jsp:include page="header.jsp"></jsp:include>
<form action="/login" method="post" class="padding-small text-center">
    <section class="dashboard-section">
        <div class="container pt-4 pb-4">
            <div class="border-dashed view-height">
                <div class="container w-25">
                    <form class="padding-small text-center">
                        <h1 class="text-color-darker">Logowanie</h1>
                        <div class="form-group">
                            <input type="text" class="form-control" id="email" name="email"
                                   placeholder="podaj adres email">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="podaj hasło">
                        </div>
                        <c:if test="${admin.enable == 0}">
                            <div class="alert-info"> Konto zostało zablokowane</div> <br>
                        </c:if>
                        <c:if test="${check == false}">
                          <div class="alert-info"> Dane niepoprawne</div> <br>
                        </c:if>
                        <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</form>


<jsp:include page="footer.jsp"></jsp:include>

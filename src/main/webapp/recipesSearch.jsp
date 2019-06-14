<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en" xmlns:jsp="http://java.sun.com/JSP/Page">

<jsp:include page="header.jsp"/>

<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>
<form action="/recipes/search" method="post">
    <div class="row border-bottom border-3">

        <div class="col"><h3 class="color-header text-uppercase">Wyszukiwarka</h3></div>
        <div class="col d-flex justify-content-end mb-2">
            <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Szukaj</button>
        </div>
    </div>

    <table class="table borderless">
        <tbody>
        <tr class="d-flex">
            <th scope="row" class="col-2">Nazwa przepisu</th>
            <td class="col-7">
                <input class="w-100 p-1" name="name" value="" placeholder="Wpisz nazwę przepisu">
                <%--                <input type="hidden" name="recipeId" value="${param.id}">--%>
                <br>
                <br>
                <c:if test="${check == 0}">
                    <div class="alert-info">Nie ma takiego przepisu! Wprowadź inną nazwę.</div>
                </c:if>
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div class="orange-line w-100"></div>
<br>

<section class="mr-4 ml-4">
    <c:if test="${check == 1}">
    <table class="table">
        <thead>
        <tr class="d-flex text-color-darker">
            <th scope="col" class="col-1">ID</th>
            <th scope="col" class="col-5">NAZWA</th>
            <th scope="col" class="col-5">OPIS</th>
            <th scope="col" class="col-1">AKCJE</th>
        </tr>
        </thead>
        <tbody class="text-color-lighter">
        <c:forEach items="${list}" var="recipe">
            <tr class="d-flex">
                <th scope="row" class="col-1">${recipe.id}</th>
                <td class="col-5">
                        ${recipe.name}
                </td>
                <td class="col-5">${recipe.description}
                </td>
                <td class="col-1"><a href="/recipes/details/after/search?id=${recipe.id}"
                                     class="btn btn-info rounded-0 text-light">Szczegóły</a>
                </td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>
</section>

<section class="last-info-section padding-small">
    <div class="container">
        <div class="row">
            <div class="col">
                <h3 class="mb-4">Lorem ipsum dolor</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat
                    volutpat. Donec placerat nisl magna.</p>
            </div>
            <div class="col pl-4 ml-4">
                <h3 class="mb-4">Lorem ipsum dolor</h3>
                <ul class="container">
                    <li>consectetur adipiscing elit</li>
                    <li>sed do eiusmod tempor</li>
                    <li>incididunt ut labore</li>
                    <li>et dolore magna aliqua</li>
                </ul>
            </div>
            <div class="col">
                <h3 class="mb-4">Lorem ipsum dolor</h3>
                <div class="input-group mb-3">
                    <input type="text" class="form-control border-0 rounded-0" placeholder=""
                           aria-label="Recipient's username" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2"><a
                                href="index.html">Lorem</a></button>
                    </div>
                </div>
                <div class="container d-flex-row">
                    <a href="#">
                        <i class="fab fa-facebook-square mr-4 icon-social"></i>
                    </a>
                    <a href="#">
                        <i class="fab fa-twitter-square mr-4 icon-social"></i>

                    </a>
                    <a href="#">
                        <i class="fab fa-instagram icon-social"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>

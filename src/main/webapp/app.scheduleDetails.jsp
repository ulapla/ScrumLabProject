<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"/>"
          rel="stylesheet">
    <style type="text/css">
        <%@include file="css/style.css" %>
    </style>
    <link rel="stylesheet" href="<c:url value="https://use.fontawesome.com/releases/v5.5.0/css/all.css"/>" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>
<body>
<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-between">
        <a href="/" class="navbar-brand main-logo main-logo-smaller">
            Zaplanuj <span>Jedzonko</span>
        </a>
        <div class="d-flex justify-content-around">
            <h4 class="text-light mr-3">${admin.firstName}</h4>
            <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
            <div><a class="nav-link color-header" href="/home?logout=1">wyloguj</a></div>
        </div>
    </nav>
</header>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <ul class="nav flex-column long-bg">
            <li class="nav-item">
                <a class="nav-link" href="/app.dashboard">
                    <span>Pulpit</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app.recipes/list">
                    <span>Przepisy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app.plan/list">
                    <span>Plany</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app.editUserData">
                    <span>Edytuj dane</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="/app.editPassword">
                    <span>Zmień hasło</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app.superAdminUsers">
                    <span>Użytkownicy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </ul>

        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app.plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">${plan.name}</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.description}
                                </p>
                            </div>
                        </div>
                    </div>

                    <c:forEach var="dayName" items="${listDayName}">
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${dayName.name}</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <c:forEach var="recipePlan" items="${listRecipePlan}">
                            <c:if test="${dayName.id == recipePlan.dayNameId}">
                        <tr class="d-flex">
                            <td class="col-2">${recipePlan.mealName}</td>
                            <td class="col-7">
                                <c:forEach var="recipe" items="${hashSetRecipe}">
                                    <c:if test="${recipe.id == recipePlan.recipeId}">
                                        ${recipe.name}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td class="col-1 center">
                                <a href='<c:url value="/app.plan/details/deleteRecipe?id=${recipePlan.id}"/>' class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href='<c:url value="/app.recipe/details?id=${recipePlan.recipeId}"/>' class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
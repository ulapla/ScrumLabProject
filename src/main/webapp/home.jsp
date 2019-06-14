<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en" xmlns:jsp="http://java.sun.com/JSP/Page">

<jsp:include page="header.jsp"/>


<section class="padding-large bg-light">
    <div id="carouselExampleControls" class="carousel slide main-slider" data-ride="carousel">
        <div class="carousel-inner container">
            <div class="carousel-item active">
                <div class="container w-75 d-flex">
                    <div class="carousel-caption d-block">
                        <h1>Słodkości, pyszności i jeszcze więcej pyszności!</h1>
                        <h3> Cake gummies pastry jujubes cupcake. Cookie pudding gummi bears..</h3>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="container w-75 d-flex">
                    <div class="carousel-caption d-block">
                        <h1>Nigdzie indziej nie zobaczysz tylu wspaniałych przepisów.</h1>
                        <h3>Biscuit sesame snaps danish cotton candy powder jujubes. Soufflé ice cream sesame snaps oat
                            cake marshmallow.</h3>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="container w-75 d-flex">
                    <div class="carousel-caption d-block">
                        <h1>Mniam mniam mniam mniam mniam mnia mniam mniam!</h1>
                        <h3>Soufflé ice cream sesame snaps oat cake marshmallow. Candy gummies chocolate cake sesame
                            s</h3>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</section>

<section class="section-more padding-small">
    <div class="container d-flex justify-content-between">
        <div class="mr-4">
            <h1 class="pb-3">Zobacz przepisy naszych użytkowników!</h1>
            <h4 class="pt-1">Nasi użytkownicy jedzą zdrowo, smacznie i lubią się dzielić swoimi inspiracjami. Zobacz nasze najnowsze propozycje.</h4>
        </div>
        <div class="ml-4 align-self-center">
            <button class="btn btn-color rounded-0 mt-4 pl-4 pr-4">
                <a href="/recipes">Zobacz</a>
            </button>
        </div>
    </div>
    <section class="newsletter-section padding-small">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1>Wyszukaj przepis</h1>
            </div>
            <div class="col-5">

                    <form action="/recipes/search" method="post">
                        <input type="text" class="form-control border-0 rounded-0" placeholder="Wyszukaj przepis" name="name">
                        <div class="input-group-append">
                            <br>
                            <input class="input-group-text btn-color border-0 rounded-0" type="submit" value="Szukaj">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</section>

<section class="padding-small details bg-light">
    <div class="container">
        <div class="row">
            <div class="col text-center">
                <i class="fas fa-check icon-details"></i>
                <h1>Lorem ipsum dolor sit amet</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat
                    volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
                </p>
            </div>
            <div class="col text-center pr-4 pl-4 mr-4 ml-4">
                <i class="far fa-clock icon-details"></i>
                <h1>Lorem ipsum dolor sit amet</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat
                    volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
                </p>
            </div>
            <div class="col text-center">
                <i class="fas fa-list icon-details"></i>
                <h1>Lorem ipsum dolor sit amet</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat
                    volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
                </p>
            </div>
        </div>
    </div>
</section>



<section class="padding-medium story bg-light" id="about">
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row">
            <div class="col-4 mr-4">
                <div>
                    <img src="https://cdn.pixabay.com/photo/2017/01/31/09/30/raspberry-2023404_960_720.jpg" width="400"
                         height="350">
                </div>
            </div>

            <div class="col-7 ml-4">
                <h1 class="pb-1">Same pyszności!</h1>
                <p>Toffee tootsie roll marshmallow. Cake gummies pastry jujubes cupcake. Cookie pudding gummi bears.
                    Tiramisu chocolate cake cookie jelly cookie bear claw muffin pudding. Soufflé donut jujubes candy
                    canes caramels. Biscuit sesame snaps danish cotton candy powder jujubes. Soufflé ice cream sesame
                    snaps oat cake marshmallow. Candy gummies chocolate cake sesame snaps chupa chups caramels liquorice
                    cake tart. Pastry jelly gingerbread cake oat cake carrot cake sweet roll. Jelly beans brownie cake
                    carrot cake tart halvah bonbon gummi bears. Muffin muffin donut carrot cake macaroon cupcake. Sweet
                    roll pastry dessert bear claw sugar plum toffee. Dragée macaroon chocolate cake lemon drops pudding
                    toffee fruitcake candy canes cake. Brownie pastry gingerbread tart cake tootsie roll.
                </p>
            </div>
        </div>
    </div>
</section>

<section class="last-info-section padding-small" id="contact">
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

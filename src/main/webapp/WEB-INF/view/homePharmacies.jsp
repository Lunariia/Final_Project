<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<html>

<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/LoginStyle.css">
</head>

<body>
    <div class="backdrop"></div>
    <header class="main-header">
        <button class="mobile-btn"></button>
        <h1 class="main-header__title">Medical Garden</h1>
        <button class="main-header__sign_in" type="button">Sign in</button>
        <div class="dropdown">
            <button class="button" type="button"><i class=" fa fa-caret-down">Language</i></button>
            <div class="dropdown-content">
                <a href="#">English</a>
                <a href="#">Russian</a>
            </div>
        </div>
    </header>

    <main>
        <aside>
            <h2 class="main-nav__title">Menu</h2>
            <nav>
                <ul class="main-nav__links">
                    <li class="main-nav__link">
                        <form method = "GET" action = "/pharmacy/controller?command=aboutUs">
                        <button class="button">About Us</button>
                        </form>
                    </li>
                    <li class="main-nav__link">
                        <form method = "GET" action = "/pharmacy/controller?command=contacts">
                        <button class="button">Contacts</button>
                        </form>
                    </li>
                    <li class="main-nav__link">
                        <form method = "GET" action = "/pharmacy/controller?command=departments">
                        <button class="button">Our Pharmacies</button>
                        </form>
                    </li>
                </ul>
            </nav>
        </aside>
        <section class="main-section">
            <h2 class="main-section__title__tetra">Our Pharmacies</h2>
            <h2 class="main-section__information__departments">
                <p>st. Rokossovskogo, 1/a</p>
                <p>FarmOstrov Pharmacy No. 16 on Rokossovskogo until 22:00</p>
                <br>
                <p>st. Lynkova, 19</p>
                <p>FarmOstrov Pharmacy on Lynkova until 22:00</p>
                <br>
                <p>Rokossovsky Ave., 145/a</p>
                <p>FarmOstrov Pharmacy on Rokossovskogo until 22:00</p>
                <br>
                <p>st. V. Horuzhei, 31, pom. 1N (mn "Euroopt")</p>
                <p>InLek Pharmacy # 1 until 21:00</p>
            </h2>
        </section>
    </main>
    <script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>
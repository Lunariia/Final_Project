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
            <h2 class="main-section__title__tetra">About Us</h2>
            <h2 class="main-section__information">Medical garden pharmacies is the largest pharmacy chain in Belarus.</p>
                <p> Today, the ADEL network of pharmacies has more than 100 pharmacies throughout the territory of the Republic of Belarus!</p>
            </h2>
            </h2>
        </section>
    </main>
    <script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>
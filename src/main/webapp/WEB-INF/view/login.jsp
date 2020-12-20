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
                        <button class="button">About Us</button>
                    </li>
                    <li class="main-nav__link">
                        <button class="button">Contacts</button>
                    </li>
                    <li class="main-nav__link">
                        <button class="button">Our Pharmacies</button>
                    </li>
                </ul>
            </nav>
        </aside>
        <section class="login-section">
            <h2 class="login-section__login_title">Login</h2>
			<form method = "POST" action = "/pharmacy/controller?command=login" class="login-section__form">
                <label for="email">Email</label>
                <input type="text" id="email" name="login" />
                <label for="password">Password</label>
                <input type="password" id="password" name="password" />
                <input type="submit" value="Confirm" />
                <p class="login-section__error">${errorMessage}</p>
            </form>
        </section>
    </main>
    <script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>


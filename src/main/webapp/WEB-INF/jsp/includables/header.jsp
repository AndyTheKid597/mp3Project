            <!-- HEADER DESKTOP-->
            <header class="header-desktop">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="header-wrap">
                            <form class="form-header" action="${pageContext.servletContext.contextPath}/admin/rechercher" method="URL">
                                <input class="au-input au-input--xl" type="text" name="q" placeholder="Titre / Genre / album..." />
                                <button class="au-btn--submit" type="submit">
                                    <i class="zmdi zmdi-search"></i>
                                </button>
                            </form>
                            <div class="header-button">
                               <div class="account-wrap">
                                    <div class="account-item clearfix js-item-menu">

                                        <div class="content">
                                            <a class="js-acc-btn" href="#">${adminObjet.nom}</a>
                                        </div>
                                        <div class="account-dropdown js-dropdown">
                                            <div class="info clearfix">

                                                <div class="content">
                                                    <h5 class="name">
                                                        <a href="#">${adminObjet.nom}</a>
                                                    </h5>
                                                    <span class="email">${adminObjet.email}</span>
                                                </div>
                                            </div>
                                            <div class="account-dropdown__body">

                                                <div class="account-dropdown__item">
                                                    <a href="#">
                                                        <i class="zmdi zmdi-settings"></i>Parametres</a>
                                                </div>

                                            </div>
                                            <div class="account-dropdown__footer">
                                                <a href="${pageContext.servletContext.contextPath}/admin/logout">
                                                    <i class="zmdi zmdi-power"></i>Se deconnecter</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- END HEADER DESKTOP-->

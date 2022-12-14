<nav class="navbar fixed-top navbar-expand-lg border-bottom rounded-bottom" style="background-color: #E3F2FD">
  <div class="container">
	<a class="navbar-brand" href="/branch_offices/">
		<img src="${pageContext.request.contextPath}/static/ms_ico.svg" alt="Miscellaneous Store" width="40" height="40"
			class="d-inline-block align-text-center"><strong>Miscellaneous Store</strong>
	</a>
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	  <span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
	  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		<li class="nav-item dropdown">
		  <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
			   aria-expanded="false">Branch Offices
		  </a>
		  <ul class="dropdown-menu">
			<li><a class="dropdown-item" href="/branch_offices">Active</a></li>
			<li><hr class="dropdown-divider"></li>
			<li><a class="dropdown-item" href="/branch_offices/create">Register New</a></li>
		  </ul>
		</li>
		<li class="nav-item dropdown">
		  <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
			  aria-expanded="false">Employees
		  </a>
		  <ul class="dropdown-menu">
			<li><a class="dropdown-item" href="/administrators">Administrators</a></li>
			<li><hr class="dropdown-divider"></li>
			<li><a class="dropdown-item" href="/workers">Workers</a></li>
		  </ul>
		</li>
		<li class="nav-item">
		  <a class="nav-link" href="/clients" role="button">Clients</a>
		</li>
	  </ul>
	  <form class="d-flex" role="logout">
		  <a href="/">
			<input class="form-control me-2 btn btn-outline-danger" type="button"
					 value="Log out" aria-label="Log out">
		  </a>
      </form>
	</div>
  </div>
</nav>

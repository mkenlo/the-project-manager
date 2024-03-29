<nav class="navbar navbar-expand-lg bg-body-tertiary shadow-sm">
  <div class="container-fluid">
    <a class="navbar-brand" href="/dashboard">Project Manager</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/dashboard">Projects</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/tasks">Tasks</a>
        </li>
        
        
      </ul>
      <form class="d-flex">
        <span class="material-symbols-outlined my-2 ">person</span>
        <label class="px-2 my-2 ">${loggedUser.firstname} ${loggedUser.lastname}</label>
        <a href="/logout" class="btn btn-outline-danger">Logout</a>
      </form>
    </div>
  </div>
</nav>
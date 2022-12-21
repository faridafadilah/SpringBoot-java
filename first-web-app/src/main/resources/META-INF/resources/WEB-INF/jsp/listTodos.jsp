<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
  <h2>Your Todos</h2>
  <a href="add-todo" class="btn btn-success">Add Todo</a>
  <table class="table">
    <thead>
      <tr>
        <th>Username</th>
        <th>Description</th>
        <th>Target Date</th>
        <th>Status</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${todos}" var="todo">
        <tr>
          <td>${todo.username}</td>
          <td>${todo.description}</td>
          <td>${todo.targetDate}</td>
          <td>${todo.done}</td>
          <td>
            <a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a>
            <a href="edit-todo?id=${todo.id}" class="btn btn-primary">Edit</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
<%@ include file="common/footer.jspf" %>
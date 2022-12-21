<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<h1>Data Contact</h1>
<a href="add-contact" class="btn btn-success">Add Contact</a>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">No</th>
      <th scope="col">Name</th>
      <th scope="col">NoHp</th>
      <th scope="col">Email</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${contacts}" var="contact">
      <tr>
        <th scope="row">${contact.id}</th>
        <td>${contact.username}</td>
        <td>${contact.nohp}</td>
        <td>${contact.email}</td>
        <td>
          <a href="edit-contact?id=${contact.id}" class="btn btn-primary">Edit</a>
          <a href="delete-contact?id=${contact.id}" class="btn btn-danger">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<%@ include file="common/footer.jspf" %>
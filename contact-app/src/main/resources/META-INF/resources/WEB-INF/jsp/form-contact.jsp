<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container mt-3">
  <form:form method="post" modelAttribute="contact">
    <fieldset class="mb-3">
      <form:label path="username" class="col-sm-2 col-form-label">Username</form:label>
      <form:input type="text" path="username" class="form-control" />
      <form:errors path="username" cssClass="text-warning" />
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="email" class="col-sm-2 col-form-label">Email</form:label>
      <form:input type="text" path="email" class="form-control"/>
      <form:errors path="email" cssClass="text-warning" />
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="nohp" class="col-sm-2 col-form-label">No Hp</form:label>
      <form:input path="nohp" class="form-control"/>
      <form:errors path="nohp" cssClass="text-warning" />
    </fieldset>

    <form:input type="hidden" path="id" />
    <input type="submit" class="btn btn-primary" value="Submit">
  </form:form>
</div>
<%@ include file="common/footer.jspf" %>
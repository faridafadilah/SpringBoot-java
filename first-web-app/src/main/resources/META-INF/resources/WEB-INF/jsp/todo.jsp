<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
  <h2>Add Todo</h2>
  <form:form method="post" modelAttribute="todo">
    <fieldset class="mb-3">
      <form:label path="description">Description</form:label>
      <form:input type="text" path="description" />
      <form:errors path="description" cssClass="text-warning" />
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="targetDate">Target Date</form:label>
      <form:input type="text" path="targetDate" />
      <form:errors path="targetDate" cssClass="text-warning" />
    </fieldset>

    <form:input type="hidden" path="id" />
    <form:input type="text" path="done" />
    <input type="submit" value="Submit">
  </form:form>
</div>
<%@ include file="common/footer.jspf" %>

<script type="text/javascript">
  $('#targetDate').datepicker({
  format: 'yyyy-mm-dd'
  });
</script>
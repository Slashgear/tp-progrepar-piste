<link href="<c:url value="/resources/lib/jquery-ui-1.11.4/jquery-ui.min.css"/> " rel="stylesheet"/>
<script type="text/javascript" src="<c:url value="/resources/lib/jquery-ui-1.11.4/jquery-ui.min.js"/> "></script>
<script type="text/javascript">
  $(function () {
    $("#grid").sortable({
      tolerance: 'pointer',
      revert: 'invalid',
      forceHelperSize: true
    });
  });
</script>

<script src="jquery-1.7.2.min.js"></script>

<script>
	$(document).ready(function() {

  	});
	function createUser() {
		$.ajax({ type: 'GET', url: 's', data: {action:'addUser', name:$('#name').val(), email:$('#email').val(), password:$('#password').val()}, 
			success: function(data) {
				$('#feedback').html(data);
				if(data=='OK')
				{
<%
					if(request.getParameter("returnId")==null || request.getParameter("returnId").equals("main"))
					{
%>
							document.location='main.jsp';
<%
					}else
					{
%>
							document.location='detail.jsp?id=<%=request.getParameter("returnId")%>';
<%
					}
%>
				}
			},
			dataType: "text"
		});
	}

	function goBack() {
	<%
						if(request.getParameter("returnId")==null || request.getParameter("returnId").equals("main"))
						{
	%>
								document.location='main.jsp';
	<%
						}else
						{
	%>
								document.location='detail.jsp?id=<%=request.getParameter("returnId")%>';
	<%
						}
	%>
	}
</script>

<form action="s">
	<INPUT type="hidden" name="action" value="createuser"/>
	<table>
		<tr>
			<td>Name:</td><td><INPUT type="text" name="name" id="name"/></td>
		</tr>
		<tr>
			<td>Email:</td><td><INPUT type="text" name="email" id="email"/></td>
		</tr>
		<tr>
			<td>Password:</td><td><INPUT type="password" name="password" id="password"/></td>
		</tr>
		<tr>
			<td><INPUT type="button" id="btnCancel" onclick="goBack()" value="Cancel"/></td>
			<td><INPUT type="button" id="btnCreateUser" onclick="createUser()" value="Create User"/></td>
		</tr>
	</table>
</form>
<div id="feedback">

</div>
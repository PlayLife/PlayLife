<%@ page import="java.util.*, com.ximplar.mycubedrawing.*" %>
<script src="jquery-1.7.2.min.js"></script>

<script>
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
	<INPUT type="hidden" name="action" value="login"/>
	<INPUT type="hidden" name="returnId" value="<%=request.getParameter("returnId")%>"/>
	<table>
		<tr>
			<td>Email:</td><td><INPUT type="text" name="email"/></td>
		</tr>
		<tr>
			<td>Password:</td><td><INPUT type="password" name="password"/></td>
		</tr>
		<tr>
			<td><INPUT type="button" id="btnCancel" onclick="goBack()" value="Cancel"/></td><td><INPUT type="submit"/></td>
		</tr>
	</table>
</form>
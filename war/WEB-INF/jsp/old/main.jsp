<%@ page import="com.google.appengine.api.datastore.*, java.util.*, com.ximplar.mycubedrawing.*" %>

<%
	Entity userObj = (Entity)request.getSession().getAttribute("user");
	if(userObj!=null)
	{
%>
		Hello! <%=userObj.getProperty("name")%>   &nbsp;<a href="s?action=logout&returnId=main">logout</a>
<%
	}else
	{
%>
		<a href="login.jsp?returnId=main">Login!</a>    <a href="createuser.jsp?returnId=main">Create User</a>
<%
	}
%>

<table>
<tr>
<%
	DataOperation dOper = new DataOperation();
	
	ArrayList<Entity> entries = dOper.getLatest(5);
%>
<%
	for(int i=0; i<entries.size(); i++)
	{
%> 
		<td bgcolor="#6D85B5">
			<img src="s?action=loadImage&key=<%=entries.get(i).getKey().getId()%>" width="130px"/>
		</td>
<%
	}
%>
</tr>
<tr>
<%
	for(int i=0; i<entries.size(); i++)
	{
%> 
		<td>
			<a href="detail.jsp?id=<%=entries.get(i).getKey().getId()%>"><%=entries.get(i).getProperty("modelname")%></a>
		</td>
<%
	}
%>
</tr>
<tr>
<%
	for(int i=0; i<entries.size(); i++)
	{
%> 
		<td>
			<font size="1"><%=entries.get(i).getProperty("datetext")%></font>
		</td>
<%
	}
%>
</tr>
</table>
<a href="newmodel.jsp">Create Your Model</a>

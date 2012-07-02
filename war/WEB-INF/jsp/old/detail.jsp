<%@ page import="com.google.appengine.api.datastore.*, java.util.*, com.ximplar.mycubedrawing.*" %>

<script src="jquery-1.7.2.min.js"></script>

<%
	//user entity
	Entity userObj = (Entity)request.getSession().getAttribute("user");
	boolean loggedIn = true;
	
	//a fast variable to see if loggedIn or not
	if(userObj==null) { loggedIn=false;}
	
	if(userObj!=null)
	{
%>
		Hello! <%=userObj.getProperty("name")%>   &nbsp;<a href="s?action=logout&returnId=<%=request.getParameter("id")%>">logout</a>
<%
	}else
	{
%>
		<a href="login.jsp?returnId=<%=request.getParameter("id")%>">Login!</a>    <a href="createuser.jsp?returnId=<%=request.getParameter("id")%>">Create User</a>
<%
	}
%>

<%
	if(request.getParameter("id")==null)
	{
		response.sendRedirect("main.jsp");
			
	}
	
	
	DataOperation dOper = new DataOperation();
	
	//the model entity
	Entity en = dOper.getModel(Long.parseLong(request.getParameter("id")));
	if(en==null)
	{
		response.sendRedirect("main.jsp");
	}
	String modelName=(String)en.getProperty("modelname");
	String serial=(String)en.getProperty("serial");
	long id=en.getKey().getId();
	String datetext=(String)en.getProperty("datetext");
	System.out.println("Model Entity=" + en);
%>

<script>
	function addComment() {
		//alert('Adding comment:' + $('#commentTitle').val() +  ' text:' + $('#commentText').val());
		$.ajax({ type: 'GET', url: 's', data: {action:'addComment', serial:'<%=en.getProperty("serial")%>',   commentTitle:$('#commentTitle').val(), commentText:$('#commentText').val()}, 
			success: function(data) {
				if(data=='OK')
				{
					$('#commentTitle').val('');
					$('#commentText').val('');
				}
			},
			dataType: "text"
		});
	}
	function displayLike() {
		$.ajax({ type: 'GET', url: 's', data: {action:'getLikeCount', serial:'<%=en.getProperty("serial")%>'}, 
			success: function(data) {
					$('#divLikeCount').html(data);
			},
			dataType: "text"
		});
	}
	<%
		if(loggedIn)
		{
	%>
		function doUnLike() {
			$.ajax({ type: 'GET', url: 's', data: {action:'unlike', serial:'<%=en.getProperty("serial")%>'}, 
				success: function(data) {
						isLikedByTheUser();
						displayLike();
				},
					dataType: "text"
			});
		}
		
		function doLike() {
			$.ajax({ type: 'GET', url: 's', data: {action:'like', serial:'<%=en.getProperty("serial")%>'}, 
				success: function(data) {
						isLikedByTheUser();
						displayLike();
				},
					dataType: "text"
			});
		}
		function isLikedByTheUser() {
			$.ajax({ type: 'GET', url: 's', data: {action:'isLiked', serial:'<%=en.getProperty("serial")%>'}, 
				success: function(data) {
						if(data=='ERROR')
						{
							$('#alreadyLiked').hide();
							$('#notYetLiked').hide();
						}else if(data=='Y') {
							$('#alreadyLiked').show();
							$('#notYetLiked').hide();
						}else if(data=='N') {
							$('#alreadyLiked').hide();
							$('#notYetLiked').show();
						}
				},
					dataType: "text"
			});
		}
	<%	
		}//end loggedIn
	%>

	
	$(document).ready(function(){
		displayLike();
<%
	if(loggedIn)
	{
%>
		isLikedByTheUser();
<%
	}
%>
	});
</script>

<a href="main.jsp">Home</a>
<table>
<tr>
		<td bgcolor="#6D85B5">
			<img src="s?action=loadImage&key=<%=id%>" width="300px"/>
		</td>
		<td valign="top">
			<table>
				<tr>
					<td>
						Model Name:
					</td>
				</tr>
				<tr>
					<td>
						<%=modelName%>
					</td>
				</tr>
				<tr>
					<td>
						Submitted On:
					</td>
				</tr>
				<tr>
					<td>
						<%=datetext%>
					</td>
				</tr>	
				<tr>
					<td>
						View Model in 3D
					</td>
				</tr>
				<tr>
					<td>
						<a href="model.jsp?id=<%=id%>" target="_blank">Click Here</a>
					</td>
					
				</tr>
			</table>
		</td>
</tr>
</table>



<BR/>


Likes (<div id="divLikeCount"></div>)

<%
if(loggedIn)
{
%>
<div id="alreadyLiked">
	You already liked this drawing
	<input type="button" onclick="doUnLike()" value="UNLIKE!!"/>
</div>
<div id="notYetLiked">
	Do you want to like this drawing?
	<input type="button" onclick="doLike()" value="LIKE!"/>
</div>
<%
}
%>



<BR/>

<table>
<%
	ArrayList<Entity> alComments = dOper.getComments((String)en.getProperty("serial"));
	for(int i=0; i<alComments.size(); i++)
	{
		Entity commentObj = alComments.get(i);
//		System.out.println("COMMENT:" + commentObj);
%>
		<tr>
			<td><%=(String)commentObj.getProperty("title")%> by <%=(String)commentObj.getProperty("name")%></td>
		</tr>
		<tr>
			<td colspan="2"><%=(String)commentObj.getProperty("comment")%></td>
		</tr>
		
<%
	}
%>
</table>
<%
	if(loggedIn)
	{
%>
		<form action="s" id="addCommentForm">
		<table>
		<tr>
			<td>
				Title
			</td>
			<td>
				<input type="text" id="commentTitle"/>
			</td>
		</tr>
		<tr>
			<td>
				Comment
			</td>
			<td>
				<textarea rows="2" cols="20" id="commentText"></textarea>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
			<td>
				<input type="button" value="Comment" id="commentSubmit" onclick="addComment()"/>
			</td>
		</tr>
		
		<tr>
			<td>
				&nbsp;
			</td>
			<td>
				<input type="button" value="test like" id="testLike" onclick="isLikedByTheUser()"/>
			</td>
		</tr>
		
		</form>
		
<%	
	}
%>
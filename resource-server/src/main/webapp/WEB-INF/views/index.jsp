<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Articles Page</h2>
<table  class="table">
	<thead>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Content</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="article" items="${articles}">
			<tr>
				<td>${article.id}</td>
				<td>${article.title}</td>
				<td>${article.content}</td>
			</tr>
		</c:forEach>	
	</tbody>
</table>

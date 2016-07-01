<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
	<h2>Articles Page</h2>
	<button class="btn btn-default" id="btnClear">Clear Data</button>
	<br/><br/>
	<table class="table" id="articleTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Content</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<br/><br/>
	
	<!-- Hidden Data -->
	<input id="code" type="hidden" value="" />
	<input id="state" type="hidden" value="" />
	<input id="accessToken" type="hidden" value="" />
	
	<h2>Get Articles via</h2>
	<button class="btn btn-default" id="btnAuthCode">Authorization Code</button>
	<button class="btn btn-default" id="btnImplicit">Implicit</button>
</div>
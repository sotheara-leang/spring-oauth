<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="auth" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException" %>

<div class="col-md-6 col-md-offset-3">
	<div class="text-center">
		<h4>Confirm Access Page</h4><br/><br/>
	</div>
	<%
		boolean unOauthSession = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null && session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") instanceof UnapprovedClientAuthenticationException;
	%>
	<c:choose>
		<c:when test="unOauthSession">
			<div class="alert alert-danger" role="alert">
				<p>Access could not be granted.</p>
			</div>
			<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />
		</c:when>
		<c:otherwise>
			<h5>Application <c:out value="${authorizationRequest.clientId}" /> would like to access your protected resources with the following scopes:</h5>
			<br/><br/>
			<ul>
				<c:forEach var="scope" items="${authorizationRequest.scope}">
					<li>${scope}</li>
				</c:forEach>
			</ul>
			<br/><br/>
			<form name="confirmForm" action="<c:url value='/oauth/authorize'/>" method="post" style="display: inline-block;">
				<input name="user_oauth_approval" value="true" type="hidden" />
				<button class="btn btn-primary" type="submit">Approve</button>
			</form>
			<form name="denyForm" action="<c:url value='/oauth/authorize'/>" method="post" style="display: inline-block;">
				<input name="user_oauth_approval" value="false" type="hidden" />
				<button class="btn btn-primary" type="submit">Deny</button>
			</form>
		</c:otherwise>
	</c:choose>
</div>
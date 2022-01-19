<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
	
		<h1>[GuestBook4]</h1>
		
	<!-- 등록 폼영역 -->
	<form action="./add" method="get">
		<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="65" rows="5" name="content"></textarea></td>
			</tr>
		</table>
			<button type="submit">확인</button>
	</form>
		<!-- 등록 폼영역 -->
		<br>
		<!-- 리스트영역 -->

		<table border="1"  width="500px">
			<c:forEach items="${gList}" var="gvo"  varStatus="status">
				<tr>
					<td>${gvo.no}</td>
					<td>${gvo.name}</td>
					<td>${gvo.regDate}</td>
					<td><a href="./deleteForm?no=${gvo.no}">[삭제]</a></td>
				</tr>
				<tr>
					<td colspan="4">
					${gvo.content}
					</td>
				</tr>
			</c:forEach>
		</table>
		<br>

		
		<!-- 리스트영역 -->
	
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
	
		<h1>[GuestBook4]</h1>
		
		<br>
		<form action="./delete" method="get">
	         	비밀번호 <input  type="password" name="password" value=""> 
	         	 <input  type="hidden" name="action" value="delete"> 
			    <button type="submit"> 확인 </button>
		</form>
		
		<br>
		<a href="./gList">메인으로 돌아가기</a>
	</body>
</html>
<!-- articles.html을 바탕으로 만드는 jsp 코드입니다. -->
<!-- jsp 코드를 작성하여 임의의 사용자 정보를 만듭니다. -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 데이터베이스 접속, 쿼리 전송, 결과 수신을 담당하는 DatabaseController를 추가합니다. -->
<%@ page import="DatabaseControl.DatabaseController" %>
<!-- Connection 객체를 사용할 수 있게 추가합니다. -->
<%@ page import="java.sql.Connection" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="models.UserInfo" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<!-- w3 css 프레임워크를 사용합니다. -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
	<!-- w3-container는 좌우에 16px가 들어갑니다. -->
	<div class="w3-container">
			<h2>User List</h2>
			<p>사용자 정보를 보여줍니다.</p>
		
		<table class="w3-table w3-striped">
			<!-- tr태그: table row, 테이블의 행입니다. -->
			<tr class="w3-teal">
			
				<!-- th 태그: table head, 테이블 열의 제목입니다. -->
				<!-- 사용자 고유번호 -->
				<th>id</th>
				<!-- 사용자 이메일 -->
				<th>email</th>
				<!-- 사용자 별명 -->
				<th>name</th>
				<!-- 사용자 비밀번호 -->
				<th>password</th>
				<!-- 등록일시 -->
				<th>regdate</th>
			</tr>
			
			<!-- jsp 코드를 이용하여 사용자 정보를 반복해서 보여줍니다. -->
			<!-- 데이터베이스에서 받아온 데이터가 ArrayList와 같은 컨테이너에 저장되어 있다면
			아래와 동일하게 사용할 수 있습니다. -->
			<%
				/* 싱글톤으로 만든 DatabaseController의 객체 레퍼런스 변수를 얻습니다.
				이 레퍼런스 변수를 가지고 데이터베이스에 접속합니다. */
				DatabaseController instance = DatabaseController.getInstance();
			
				/* 사용자 정보를 ArrayList에 담아서 return하는 메모스를 실행합니다. */
				ArrayList<UserInfo> userInfoList = instance.getUserInfo();
				
				int userCount = userInfoList.size();
				
				/* ArrayList에 담긴 UserInfo 정보를 html로 보여줍니다. */
				for(int i = 0; i < userCount; i++) {
					UserInfo info = userInfoList.get(i);
					%>
				
					<!-- 사용자 정보를 나타내는 열입니다. -->
					<tr>
						<th><%= info.getId() %></th>
						<th><%= info.getEmail() %></th>
						<th><%= info.getName() %></th>
						<th><%= info.getPassword() %></th>
						<th><%= info.getRegdate() %></th>
					</tr>
			<%	} %>	
			
		</table>
	</div>
</body>
</html>
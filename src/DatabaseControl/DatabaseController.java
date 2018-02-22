package DatabaseControl;

// 데이터베이스 접속 정보를 관리하는 Connection을 임포트합니다.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.UserInfo;

/*데이터베이스에 접속하고, 쿼리를 전달하여 그 결과를 받는 클래스입니다.*/
/*싱글톤 패턴으로 만듭니다.*/
public class DatabaseController {
	/*static 변수로 선언되어 있으므로, 객체가 생성되지 않더라도 멤버 변수가 생성됩니다.*/
	/*static 변수는 메모리 중 static 영역에 생성됩니다.*/
	private static DatabaseController instance = new DatabaseController();
	
	/*생상자가 private이므로 외부에서 이 클래스의 객체를 생성할 수 없습니다.*/
	private DatabaseController() {}
	
	/*private static DatabaseController instance = new DatabaseController();*/
	/*이 코드에서 생성된 객체는 객체 레퍼런스 변수 instance가 참조하고 있습니다.
	이 객체 레퍼런스 변수를 사용하여 이 클래스가 가지고 있는 메소드를 사용할 수 있습니다.*/
	public static DatabaseController getInstance() {
		return instance;
	}
	
	/*데이터베이스에 접속을 시도합니다.
	접속이 성공하면 접속 정보를 가지고 있는 Connection 객체를 return합니다.*/
	public Connection getConnection() {
		Connection con = null;
		
		/*try 내부에서 예외가 발생하면 catch로 실행 흐름이 바뀝니다.*/
		try {
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbUser = "mooneegee";
			String dbPass = "1227";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
		} catch (Exception e) {
			/*예외가 발생하면 catch로 실행흐름이 바뀝니다. catch가 끝나면 다시 try로 돌아가지 않습니다.*/
			/*어디서 어떤 예외가 발생하였는지 콘솔에 출력합니다.*/
			e.printStackTrace();
		}
		return con;
	}
	
	public ArrayList<UserInfo> getUserInfo() {
		/*데이터베이스에 접속하기 위한 Connetion
		쿼리를 실행하기 위한 PreparedStatement
		select 쿼리의 결과를 받기 위한 ResultSet*/ 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		/*users 테이블의 모든 열 정보를  조회하는 쿼리.*/
		String sql = "select * from users";
		
		/*조회한 사용자 정보를 각각 UserInfo 객체로 만듭니다.
		그리고 이 사용자 객체를 ArrayList에 보관합니다.*/ 
		ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>();
		
		try {
			/*먼저, 데이터베이스에 접속합니다.*/
			con = getConnection();
			
			/*데이터베이스에 전달할 쿼리를 등록합니다.*/ 
			ps = con.prepareStatement(sql);
			
			/*쿼리를 실행하고, 그 결과를 ResultSet 객체에 저장합니다.*/
			rs = ps.executeQuery();
			
			/*ReslutSet의 첫 번째 행에 접근하기 위해서는 반드시 먼저 Result.next()를 실행해야 합니다.
			만약 쿼리 결과로 전달된 행이 있다면 True를 return합니다.*/
			while(rs.next()) {
				/*쿼리 결과 중 행 정보를 준비해둔 UserInfo 객체에 저장합니다.*/
				UserInfo info = new UserInfo();
				
				/*열 이름으로 값이 얻어서 UserInfo 객체 setter를 사용하여 저장합니다.*/
				info.setId(rs.getInt("id"));
				info.setEmail(rs.getString("email"));
				info.setName(rs.getString("name"));
				info.setPassword(rs.getString("passwd"));
				info.setRegdate(rs.getString("regdate"));
				
				/*사용자 정보를 다 저장한 UserInfo 객체를 ArrayList에 담습니다.*/
				userInfoList.add(info);
			}
			
			if(userInfoList.size() <= 0) {
				System.out.println("Users Table is Empty.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userInfoList;
	}
}

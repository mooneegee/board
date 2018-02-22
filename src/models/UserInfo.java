package models;

/*Users 테이블 데이터를 저장하기 위한 클래스입니다.*/
public class UserInfo {
	private int id;
	private String email;
	private String name;
	private String password;
	private String regdate;
	
	/*getter, setter는 클래스를 선택한 후,
	alt + shift + s를 누르고 generate getters and setters를 선택합니다.*/ 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}

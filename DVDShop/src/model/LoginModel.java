package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
	
	public DataAccess dataAccess;
	
	public LoginModel(){
		dataAccess = new DataAccess();
	}
	
	
	public int checkLogin(String us, String pas){
		
		String sql = "SELECT `role` FROM `user` WHERE `user_name`='"+us+"' AND `password`='"+pas+"'";
		ResultSet rs = dataAccess.getResultSet(sql);
		try {
			while(rs.next()){
				int rol = rs.getInt(1);
				return rol;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return 0;
	}
	
//	public ResultSet userTableShow(){
//	String sql = "SELECT * FROM `user`";
//	return(dataAccess.getResultSet(sql));
//}
//`user_name`, `password`, 
//String na = rs.getString(1);
//String pa = rs.getString(2);

}

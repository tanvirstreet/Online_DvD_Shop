package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterModel {
	
	public DataAccess dataAccess;
	
	public RegisterModel(){
		dataAccess = new DataAccess();
	}

	public boolean save(String cus_name,String username,String password,String gender,String dob){
		int userId = saveUserInfo(username, password);
		if(saveCustomerInfo(userId, cus_name, gender, dob) != 0){
			return true;
		}
		return false;
	}
	
	public int saveUserInfo(String username, String password){
		String sql = "INSERT INTO `user`(`user_name`, `password`, `role`) VALUES ('"+username+"','"+password+"',2)";
		if(dataAccess.executeQuery(sql) != 0){
			String sqli = "SELECT * FROM `user` WHERE `user_name`='"+username+"' AND `password`='"+password+"'";
			ResultSet rs = dataAccess.getResultSet(sqli);
			try {
				while(rs.next()){
					int id = rs.getInt(1);
					return id;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int saveCustomerInfo(int userId, String cus_name, String gender, String dob){
		String sql = "INSERT INTO `customer_info`(`usr_id`, `cus_name`, `gender`, `dob`) VALUES ("+userId+",'"+cus_name+"','"+gender+"','"+dob+"')";
		return dataAccess.executeQuery(sql);
	}
	

}

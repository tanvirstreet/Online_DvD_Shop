package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModle {

	public DataAccess dataAccess;
	
	public CustomerModle() {
		dataAccess = new DataAccess();
	}
	
	public ResultSet showCustomer(){
		String sql = "SELECT `cus_id`, `usr_id`, `cus_name`, `gender`, `dob` FROM `customer_info`";
		return dataAccess.getResultSet(sql);
	}
	
	public int getCustomerID(String username){
		String sql = "SELECT t1.`cus_id` FROM `customer_info` AS t1 INNER JOIN `user` AS t2 on t1.`usr_id` = t2.`user_id` WHERE t2.`user_name`='"+username+"'";
		ResultSet rs = dataAccess.getResultSet(sql);
		try {
			while(rs.next()){
				int cus_id = rs.getInt(1);
				return cus_id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public ResultSet searchCustomer(String cus_name){
		String sql = "SELECT `cus_id`, `usr_id`, `cus_name`, `gender`, `dob` FROM `customer_info`  WHERE `cus_name` LIKE '%"+cus_name.trim()+"%'";
		return dataAccess.getResultSet(sql);
	}

}

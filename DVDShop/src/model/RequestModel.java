package model;

import java.sql.ResultSet;

public class RequestModel {

	public DataAccess dataAccess;
	
	public RequestModel() {
		dataAccess = new DataAccess();
	}
	
	public ResultSet showRequest(){
		String sql = "SELECT t1.`req_id`, t2.`cus_name`, t1.`dvd_name`, t1.`comment` FROM `request` as t1 "
					+ "INNER JOIN `customer_info` as t2 on t1.`cus_id` = t2.`cus_id`";
		return dataAccess.getResultSet(sql);
	}
	
	public boolean saveRequest(String cus_id, String dvd_name, String comment){
		String sql = "INSERT INTO `request`(`cus_id`, `dvd_name`, `comment`) VALUES ('"+cus_id+"','"+dvd_name+"','"+comment+"')";
		if(dataAccess.executeQuery(sql) != 0){
			return true;
		}
		return false;
	}

}

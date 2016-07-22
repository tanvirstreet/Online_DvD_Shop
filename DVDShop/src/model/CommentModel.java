package model;

import java.sql.ResultSet;

public class CommentModel {

	public DataAccess dataAccess;
	
	public CommentModel() {
		dataAccess = new DataAccess();
	}
	
	public ResultSet showComments(String dvd_id){
		String sql = "SELECT t1.`com_id`, t2.`cus_name`, t1.`comment`, t1.`date` FROM `comment` AS t1 INNER JOIN `customer_info` AS t2 on t1.`cus_id`= t2.`cus_id` WHERE t1.`dvd_id`='"+dvd_id+"'";
		return dataAccess.getResultSet(sql);
	}
	
	public boolean saveComment(String dvd_id, String cus_id,String comment,String date){
		return true;
	}

}

package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DvdModel {
	
	public DataAccess dataAccess;
	
	public DvdModel(){
		dataAccess = new DataAccess();
	}
	
	public ResultSet showDVD(){
		String sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id`";
		return dataAccess.getResultSet(sql);
	}
	
	public ResultSet showDVD(String dvd_id){
		String sql = "SELECT t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`dvd_id`='"+dvd_id+"'";
		return dataAccess.getResultSet(sql);
	}
	
	public ResultSet showCategory(){
		String sql = "SELECT * FROM `category`";
		return dataAccess.getResultSet(sql);
	}
	
	public boolean saveCategory(String ctg_name){
		String sql = "INSERT INTO `category`(`ctg_name`) VALUES ('"+ctg_name+"')";
//		System.out.println(sql);
		if(dataAccess.executeQuery(sql) != 0){
			return true;
		}
		return false;
	}
	
	public boolean saveDVD(String ctg_id, String title, String actor){
		String sql = "INSERT INTO `dvd_info`(`ctg_id`, `title`, `actor`) VALUES ('"+ctg_id+"','"+title+"','"+actor+"')";
		if(dataAccess.executeQuery(sql) != 0){
			return true;
		}
		return false;
	}
	
	public boolean updateDVD(String dvd_id, String title, String actor){
		System.out.println(dvd_id + title + actor);
		String sql = "UPDATE `dvd_info` SET `title`='"+title+"',`actor`='"+actor+"' WHERE `dvd_id`='"+dvd_id+"'";
		if(dataAccess.executeQuery(sql) != 0){
			return true;
		}
		return false;
	}
	
	public ResultSet searchDvds(String search){
		String sql1;
		if(search.equals("")){
			 sql1 = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id`";
			return dataAccess.getResultSet(sql1);
		}else{
			sql1 = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`title` Like '%"+search+"%'";
			return dataAccess.getResultSet(sql1);
		}
	}
	
/*
	public ResultSet searchDvds(String ctg_id, String title, String actor){
		String sql = "";
		if(ctg_id.equalsIgnoreCase("") && title.equalsIgnoreCase("") && actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id`";
		}
		else if(ctg_id.equalsIgnoreCase("") && title.equalsIgnoreCase("") && !actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`actor` Like '%"+actor.trim()+"%'";
		}
		else if(ctg_id.equalsIgnoreCase("") && !title.equalsIgnoreCase("") && actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`title` Like '%"+title.trim()+"%'";
		}
		else if(!ctg_id.equalsIgnoreCase("") && title.equalsIgnoreCase("") && actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`ctg_id`='"+ctg_id+"'";
		}
		else if(!ctg_id.equalsIgnoreCase("") && !title.equalsIgnoreCase("") && actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`ctg_id`='"+ctg_id+"' AND t1.`title` Like '%"+title.trim()+"%'";
		}
		else if(!ctg_id.equalsIgnoreCase("") && title.equalsIgnoreCase("") && !actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`ctg_id`='"+ctg_id+"' AND t1.`actor` Like '%"+actor.trim()+"%'";
		}
		else if(ctg_id.equalsIgnoreCase("") && !title.equalsIgnoreCase("") && !actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`title` Like '%"+title.trim()+"%' AND t1.`actor` Like '%"+actor.trim()+"%'";
		}
		else if(!ctg_id.equalsIgnoreCase("") && !title.equalsIgnoreCase("") && !actor.equalsIgnoreCase("")){
			sql = "SELECT t1.`dvd_id`, t2.`ctg_name`, t1.`title`, t1.`actor` FROM `dvd_info` AS t1 INNER JOIN `category` AS t2 on t1.`ctg_id`=t2.`ctg_id` WHERE t1.`ctg_id`='"+ctg_id+"' AND t1.`title` Like '%"+title.trim()+"%' AND t1.`actor` Like '%"+actor.trim()+"%'";
		}

		return dataAccess.getResultSet(sql);
	}
	*/

}

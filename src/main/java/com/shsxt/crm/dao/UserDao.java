package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.model.User;
import com.shsxt.crm.vo.UserVO;

public interface UserDao {
	
	@Select("select id,user_name as userName, password,true_name as trueName, email, "
			+ " phone, is_valid as isValid, create_date as createDate, update_date as updateDate from t_user "
			+ " where id = #{id}")
	User findById(@Param(value="id")Integer id);
	
	@Select("select id,user_name, password,true_name, email, "
			+ " phone, is_valid, create_date, update_date from t_user ")
	List<User> find();
	
	@Select("select id,user_name,password,true_name from t_user where user_name=#{userName}")
	User findByUserName(@Param(value="userName") String userName);

	
	@Select("SELECT t1.id, t1.true_name FROM t_user t1 LEFT JOIN t_user_role t2 "
			+ " on t1.id = t2.user_id WHERE t2.role_id = 3")
	List<UserVO> findCutomerManager();

}

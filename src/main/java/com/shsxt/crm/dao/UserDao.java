package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.dto.UserQuery;
import com.shsxt.crm.model.User;
import com.shsxt.crm.vo.UserVO;

public interface UserDao {
	
	@Select("select id,user_name,password,true_name from t_user where user_name=#{userName}")
	User findByUserName(@Param(value="userName") String userName);

	@Select("SELECT t1.id, t1.true_name FROM t_user t1 LEFT JOIN t_user_role t2 "
			+ " on t1.id = t2.user_id WHERE t2.role_id = 3")
	List<UserVO> findCutomerManager();

	PageList<User> selectForPage(UserQuery query, PageBounds pageBounds);

	void insert(User user);

	int updatePassword(Integer id, String newPwd);

	User findById(int userId);

	void deleteBatch(String ids);

	void update(User user);

}

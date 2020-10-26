package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Integer> {

	  List<UserInfo> findByEmail(String emailId);	
	  
	  @Modifying
	  @Query("update UserInfo u set u.role = :role where u.email = :email")
	  int updatePersona(@Param("email") String email,  @Param("role") String role);
}

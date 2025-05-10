package com.example.todo_api.repository;

import com.example.todo_api.model.user.AppUser;
import com.example.todo_api.model.user.AppUserRequest;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AppUserRepository {
    @Results(id = "userMap", value = {
            @Result(property = "id", column = "user_id"),
            @Result(property = "firstname", column = "first_name"),
            @Result(property = "lastname", column = "last_name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "profile_url", column = "profile_url"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "isEnabled", column = "is_enabled"),
            @Result(property = "isLocked", column = "is_locked")
    })
    @Select("INSERT INTO users (first_name,last_name,gender,profile_url,email,password)"
            +"VALUES(#{request.firstname},#{request.lastname},#{request.gender},#{request.profile_url},#{request.email},#{request.password}) returning *")
    AppUser insertNewUser(@Param("request")AppUserRequest appUserRequest);

    @Select("""
            SELECT * FROM users WHERE email = #{email}
            """)
    @ResultMap("userMap")
    AppUser getAppUserByEmail(String email);

    @Select("""
           SELECT EXISTS  (
           SELECT 1
           FROM users
           WHERE email = #{email} 
           )
           
            """)
    boolean isEmailAlreadyExist(@Param("email") String email);


    @Select("""
            SELECT * FROM users WHERE user_id = #{id}
            """)
    @ResultMap("userMap")
    AppUser getUserById(@Param("id") Integer id);
}

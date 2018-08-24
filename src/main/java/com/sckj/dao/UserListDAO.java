package com.sckj.dao;

import com.sckj.dto.SckjUserListDTO;
import com.sckj.pojo.SckjUserList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserListDAO {

    Page<SckjUserListDTO> findUserListPage(SckjUserList sckjUserList, Pageable page);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_user_list
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_user_list
     *
     * @mbg.generated
     */
    int insert(SckjUserList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_user_list
     *
     * @mbg.generated
     */
    int insertSelective(SckjUserList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_user_list
     *
     * @mbg.generated
     */
    SckjUserList selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_user_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SckjUserList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_user_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(SckjUserList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_user_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SckjUserList record);

    List<SckjUserListDTO> selectUser();

    List<SckjUserListDTO> getUserList(Map<String,Object> map);
}
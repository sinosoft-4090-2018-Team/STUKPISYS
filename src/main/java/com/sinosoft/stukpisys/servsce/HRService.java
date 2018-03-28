package com.sinosoft.stukpisys.servsce;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface HRService {
    /**
     * 查询所有实习生信息
     * @return  List<UserInfo>
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<UserInfo> getAllInfo();

    /**
     * 查询所有实习生学历信息
     * @return  List<UserInfo>
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<Education> getEduInfo();

    /**
     * 按名字获取一个人的详细信息
     * @param name
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    UserInfo getPersonInfoByName(String name);

    /**
     * 按实习生状态查询实习生
     * @param state
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<UserInfo> getInfoByState(String state);

    /**
     * 给实习生分配部门
     * @param name
     * @param dept
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    int distPerson(String name,String dept);

    /**
     * 通过hr查询实习生
     * @param hr
     * @param List<UserInfo>
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<UserInfo> getUserIdByhrName(String hr);

    /**
     * 通过id查询label的index和数值
     * @param name
     * @param dept
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<Integer> getScoreList(Integer id);
}

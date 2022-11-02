package org.javaboy.vhr.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : sam
 * @ClassName : BaseMapper
 * @description : TODO
 * @datetime : 2022年 10月 20日 17:35
 * @version: : 1.0
 */
public interface BaseMapper<T, ID> {
    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keywords") String keywords);

    List<T> getBeanlistByBeanAndPage(@Param("page") Integer page, @Param("size") Integer size, @Param("bean") T record);

    Long getTotal(@Param("keywords") String keywords);

    Long getTotalByBean(@Param("bean") T record);

}

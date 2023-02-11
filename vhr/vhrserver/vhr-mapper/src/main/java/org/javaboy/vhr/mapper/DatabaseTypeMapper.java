package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.DatabaseType;

import java.util.List;

@Mapper
public interface DatabaseTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DatabaseType record);

    int insertSelective(DatabaseType record);

    DatabaseType selectByPrimaryKey(Integer id);

    List<DatabaseType> getAllBeanlist(@Param("keywords") String keywords);

    List<DatabaseType> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keywords") String keywords);

    Long getTotal(@Param("keywords") String keywords);

    int updateByPrimaryKeySelective(DatabaseType record);

    int updateByPrimaryKey(DatabaseType record);

    //校验是否存在重复的名称
    Long checkUniqueName(@Param("name") String name, @Param("id") Integer id);
    //校验是否存在重复的code
    Long checkUniqueCode(@Param("code") String code, @Param("id") Integer id);

    DatabaseType getByCode(@Param("code") String code);
}
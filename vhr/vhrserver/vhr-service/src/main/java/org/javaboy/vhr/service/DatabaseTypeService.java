package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.DatabaseTypeMapper;
import org.javaboy.vhr.model.DatabaseType;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "database_cache")
public class DatabaseTypeService{

    @Resource
    private DatabaseTypeMapper databaseTypeMapper;

    @CacheEvict
    public int deleteByPrimaryKey(Integer id) {
        return databaseTypeMapper.deleteByPrimaryKey(id);
    }

    @CachePut
    public int insert(DatabaseType record) {
        return databaseTypeMapper.insert(record);
    }

    @CachePut
    public int insertSelective(DatabaseType record) {
        return databaseTypeMapper.insertSelective(record);
    }

    
    public DatabaseType selectByPrimaryKey(Integer id) {
        return databaseTypeMapper.selectByPrimaryKey(id);
    }

    @Cacheable
    public List<DatabaseType> getAllBeanlist(String keywords) {
        return databaseTypeMapper.getAllBeanlist(keywords);
    }

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<DatabaseType> data = databaseTypeMapper.getBeanlistByPage(page, size, keywords);
        Long total = databaseTypeMapper.getTotal(keywords);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    @CachePut
    public int updateByPrimaryKeySelective(DatabaseType record) {
        return databaseTypeMapper.updateByPrimaryKeySelective(record);
    }

    @CachePut
    public int updateByPrimaryKey(DatabaseType record) {
        return databaseTypeMapper.updateByPrimaryKey(record);
    }

    public Long checkUniqueName(String name, Integer id) {
        return databaseTypeMapper.checkUniqueName(name, id);
    }
    public Long checkUniqueCode(String code, Integer id) {
        return databaseTypeMapper.checkUniqueCode(code, id);
    }
}

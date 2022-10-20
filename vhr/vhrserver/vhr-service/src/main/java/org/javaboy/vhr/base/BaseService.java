package org.javaboy.vhr.base;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author : sam
 * @ClassName : BaseService
 * @description : 基础service
 * @datetime : 2022年 10月 20日 17:12
 * @version: : 1.0
 */
@SuppressWarnings("all")
public abstract class BaseService<T,ID> {

    @Autowired
    private BaseMapper<T,ID> baseMapper;

    public int deleteByPrimaryKey(ID id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return baseMapper.insert(record);
    }

    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    public T selectByPrimaryKey(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    public RespPageBean getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keywords") String keywords) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<T> data = baseMapper.getBeanlistByPage(page, size, keywords);
        Long total = baseMapper.getTotal(keywords);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
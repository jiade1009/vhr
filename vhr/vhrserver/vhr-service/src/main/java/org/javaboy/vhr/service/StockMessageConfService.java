package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockMessageConfMapper;
import org.javaboy.vhr.model.StockMessageConf;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockMessageConfService
 * @description : 股票消息发送配置，确定谁接收，接收什么类型消息，以什么形式发送消息
 * @datetime : 2022年 10月 30日 10:00
 * @version: : 1.0
 */

@Service
@CacheConfig(cacheNames = "message_conf_cache")
public class StockMessageConfService extends BaseService<StockMessageConf, Integer> {

    @Resource
    private StockMessageConfMapper stockMessageConfMapper;

    /**
     * 批量新增消息配置对象，根据不同的empid，新增一条对应的配置对象
     * 在新增之前，需要先删除原有empid所对应的消息配置对象
     *
     * @param record
     * @return
     */
    @Transactional
    @Caching(evict = { @CacheEvict(allEntries=true)})
    public int batchInsert(StockMessageConf record) {
        Integer[] ids = record.getEmpids();
        for (int i = 0; i < ids.length; i++) {
            //删除原有配置
            stockMessageConfMapper.deleteByEmpid(ids[i]);
            //新增配置
            StockMessageConf vo = new StockMessageConf();
            vo.setEmpid(ids[i]);
            vo.setMessageType(record.getMessageType());
            vo.setSendType(record.getSendType());
            vo.setStatus(record.getStatus());
            stockMessageConfMapper.insert(vo);
        }
        return ids.length;
    }

    @Override
    @Caching(evict = { @CacheEvict(key = "'list_by_status_true'"),
            @CacheEvict(key = "'list_by_status_false'"),
            @CacheEvict(key = "'vo_by_id_' + #id")})
    public int deleteByPrimaryKey(Integer id) {
        return super.deleteByPrimaryKey(id);
    }

    @Override
    @Caching(evict = { @CacheEvict(key = "'list_by_status_true'"),
            @CacheEvict(key = "'list_by_status_false'")})
    public int insert(StockMessageConf record) {
        return super.insert(record);
    }

    @Override
    @Caching(evict = { @CacheEvict(key = "'list_by_status_true'"),
            @CacheEvict(key = "'list_by_status_false'")})
    public int insertSelective(StockMessageConf record) {
        return super.insertSelective(record);
    }

    @Override
    @Cacheable(key = "'vo_by_id_' + #id")
    public StockMessageConf selectByPrimaryKey(Integer id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    @Caching(evict = { @CacheEvict(key = "'list_by_status_true'"),
            @CacheEvict(key = "'list_by_status_false'"),
            @CacheEvict(key = "'vo_by_id_' + #record.empid")})
    public int updateByPrimaryKeySelective(StockMessageConf record) {
        return super.updateByPrimaryKeySelective(record);
    }

    @Override
    @Caching(evict = { @CacheEvict(key = "'list_by_status_true'"),
            @CacheEvict(key = "'list_by_status_false'"),
            @CacheEvict(key = "'vo_by_id_' + #record.empid")})
    public int updateByPrimaryKey(StockMessageConf record) {
        System.out.println("开始进入 。。。 updateByPrimaryKey 操作缓存");
        return super.updateByPrimaryKey(record);
    }

    @Cacheable(key = "'list_by_status_' + #status", unless = "#result==null")
    public List<StockMessageConf> getListByStatus(boolean status) {
        List<StockMessageConf> list = stockMessageConfMapper.getListByStatus(status);
        return list;
    }
}

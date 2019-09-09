package com.newtechcollege.cms.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.newtechcollege.cms.dao.ColMapper;
import com.newtechcollege.cms.entity.Col;
import com.newtechcollege.cms.service.ColService;

import org.springframework.stereotype.Service;

@Service
public class ColServiceImpl implements ColService {

    @Resource
    private ColMapper colMapper;  

    @Override
    public List<Map<String,Object>> list() {
        return colMapper.list();
    }

    @Override
    public List<Map<String,Object>> search(String title) {
        return colMapper.search(title);
    }

    @Override
    public Map<String,Object> getOne(int id) {
        return colMapper.getColById(id);
    }

    @Override
    public int add(Col col) {
        return colMapper.insert(col);
    }

    @Override
    public int edit(Col col) {
        return colMapper.update(col);
    }

    @Override
    public int delete(int id) {
        return colMapper.delete(id);
    }

    @Override
    public int publish(int id) {
        return colMapper.publish(id);
    }

    @Override
    public int upload(Integer id,String path){
        return  colMapper.upload(id,path);
    }
}

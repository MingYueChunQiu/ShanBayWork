package com.shanbay.shanbaywork.dao;

import com.shanbay.shanbaywork.bean.Bean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 明月春秋 on 2017/9/23.
 * BaseDao<Bean>的实现类
 *      使用LitePal框架进行实现
 */

public class LitePalDao implements BaseDao {


    @Override
    public boolean save(Bean entity) {
        return entity.save();
    }

    @Override
    public boolean update(Bean entity) {
        return entity.save();
    }

    @Override
    public boolean update(Bean entity, long id) {
        if (entity.update(id) > 0){
            return true;
        }
        return false;
    }


    @Override
    public int updateAll(Bean entity, String whereClause, String whereArgs) {
        return entity.updateAll(whereClause, whereArgs);
    }

    @Override
    public int delete(Bean entityClazz) {
        return entityClazz.delete();
    }

    @Override
    public int delete(Class entityClazz, long id) {
        return DataSupport.delete(entityClazz, id);
    }

    @Override
    public void deleteAll(Class entityClazz, String whereClause, String whereArgs) {
        DataSupport.deleteAll(entityClazz, whereClause, whereArgs);
    }

    @Override
    public Bean find(Class entityClazz, long id) {
        return (Bean) DataSupport.find(entityClazz, id);
    }


    @Override
    public List findAll(Class entityClazz) {
        return DataSupport.findAll(entityClazz);
    }
}

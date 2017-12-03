package com.shanbay.shanbaywork.dao;

import com.shanbay.shanbaywork.bean.Bean;

import java.util.List;

/**
 * Created by 明月春秋 on 2017/9/20.
 * dao模式的基类
 * 含有基本的CRUD方法
 * T:表示内部dao操作的对象类型
 */

public interface BaseDao<T extends Bean> {

    /**
     * 传入实例对象，进行保存
     * @param entity
     *              需要保存的实例对象
     */
    /**
     * 传入实例对象，进行保存
     * @param entity
     *              需要保存的实例对象
     * @return
     *              true：数据库保存成功
     *              false：数据库保存失败
     */
    public boolean save(T entity);

    /**
     * 传入实例对象，进行更新
     * 必须要是已经存储的实例对象
     * @param entity
     *              需要更新的实例对象
     */
    /**
     * 传入实例对象，进行更新
     * 必须要是已经存储的实例对象
     * @param entity
     *              需要更新的实例对象
     * @return
     *              true：数据库保存成功
     *              false：数据库保存失败
     */
    public boolean update(T entity);

    /**
     * 传入实例对象和索引进行更新
     * @param entity
     *              需要更新的实例对象
     * @param id
     *              更新的实例对象所在索引
     * @return
     *              返回更新数据库数据结果
     *                  true：更新数据成功
     *                  false：更新数据失败
     */
    public boolean update(T entity, long id);

    /**
     * 对满足要求的所有对象进行更新
     * @param entity
     *              需要使用该实例对象更新所有满足条件的记录
     * @param whereClause
     *              更新实例对象的条件
     * @param whereArgs
     *              传入更新实例对象条件中的参数
     * @return
     *              返回更新操作所影响的行数
     */
    public int updateAll(T entity, String whereClause, String whereArgs);

    /**
     * 删除此类型表中的对应索引位置的实例对象记录
     * @param entityClazz
     *              需要删除的实例对象
     * @return
     *              返回删除操作影响的行数
     *              0：删除失败
     */
    public int delete(T entityClazz);

    /**
     * 删除此类型表中的对应索引位置的实例对象记录
     * @param entityClazz
     *              需要删除的实例类型
     * @param id
     *              需要删除的实例对象所在的索引位置
     * @return
     *              返回删除操作影响的行数
     */
    public int delete(Class<T> entityClazz, long id);

    /**
     * 删除此类型表中的所有满足条件的实例对象记录
     * @param entityClazz
     *              需要删除的实例类型
     * @param whereClause
     *              删除实例对象要满足的条件
     * @param whereArgs
     *              传入删除实例对象条件中的参数
     */
    public void deleteAll(Class<T> entityClazz, String whereClause, String whereArgs);

    /**
     * 查询此实例类型中对应索引位置的数据
     * @param entityClazz
     *              需要查询的实例类型
     * @param id
     *              需要查询的实例对象所在索引
     * @return
     *              返回通过数据库查询获取的对象
     */
    public T find(Class<T> entityClazz, long id);

    /**
     * 查询此实例类型的所有实例对象
     * @param entityClazz
     *              需要查询的实例类型
     * @return
     *              将查询到的所有实例对象存入List集合中
     */
    public List<T> findAll(Class<T> entityClazz);
}

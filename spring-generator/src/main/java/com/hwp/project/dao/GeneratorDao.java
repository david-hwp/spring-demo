package com.hwp.project.dao;

import java.util.List;
import java.util.Map;

/**
 * 数据库接口
 *
 * @author Mark wolaiwod@gmail.com
 * @since 2018-07-24
 */
public interface GeneratorDao {
    List<Map<String, Object>> queryList(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}

package com.hwp.project.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.project.dao.GeneratorDao;
import com.hwp.project.utils.GenUtils;
import com.hwp.project.utils.PageUtils;
import com.hwp.project.utils.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author david-hwp
 * @email wolaiwod@gmail.com
 * @date 2016年12月19日 下午3:33:38
 */
@Service
public class SysGeneratorService {
    @Autowired
    private GeneratorDao generatorDao;

    public PageUtils queryList(Query query) {
        Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = generatorDao.queryList(query);

        return new PageUtils(list, (int) page.getTotal(), query.getLimit(), query.getPage());
    }

    private Map<String, String> queryTable(String tableName) {
        return generatorDao.queryTable(tableName);
    }

    private List<Map<String, String>> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }

    public byte[] generatorCode(String[] tableNames, String module) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, module, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}

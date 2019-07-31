/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CodeGenerator
 * Author:   whyxs
 * Date:     2019/7/11 10:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.common.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成
 *
 * @author whyxs
 * @create 2019/7/11
 * @since 1.0.0
 */
public class CodeGenerator {
    public static void main(String[] args){
        CodeGenerator();
    }

    public static void CodeGenerator(){
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setOutputDir("C:\\Users\\whyxs\\Desktop"); // 生成路径
        config.setFileOverride(true);  // 文件覆盖
        config.setOpen(false);//是否打开目录
        config.setEnableCache(false);//xml是否开启二级缓存
        config.setActiveRecord(true); // 是否支持AR模式
        config.setAuthor("whyxs"); // 作者

        config.setServiceName("%sService");//接口名不以I开头s

        //2. 数据源配置
        DataSourceConfig dsConfig  = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL);  // 设置数据库类型
        dsConfig.setDriverName("com.mysql.jdbc.Driver");
        dsConfig.setUrl("jdbc:mysql://localhost:3306/storm");
        dsConfig.setUsername("root");
        dsConfig.setPassword("123456");

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
        stConfig.setCapitalMode(true); //全局大写命名
        stConfig.setDbColumnUnderline(true);  // 指定表名 字段名是否使用下划线
        stConfig.setInclude(new String[]{"portal_about"});  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.whyxs");
        pkConfig.setMapper("mapper");//dao
        pkConfig.setService("service");//servcie
        pkConfig.setController("controller");//controller
        pkConfig.setEntity("common.bean.entity");
        pkConfig.setXml("mapper");//mapper.xml

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config);
        ag.setDataSource(dsConfig);
        ag.setStrategy(stConfig);
        ag.setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }
}

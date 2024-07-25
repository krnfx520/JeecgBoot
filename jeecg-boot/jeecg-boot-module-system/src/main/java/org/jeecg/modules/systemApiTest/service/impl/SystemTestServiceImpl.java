package org.jeecg.modules.systemApiTest.service.impl;

import org.jeecg.common.util.ResultMap;
import org.jeecg.modules.systemApiTest.mapper.SystemTestMapper;
import org.jeecg.modules.systemApiTest.service.SystemTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SystemTestServiceImpl implements SystemTestService {
    public static final Logger logger = LoggerFactory.getLogger(SystemTestServiceImpl.class);

    @Autowired
    private SystemTestMapper systemTestMapper;

    @Override
    public Object testNacos() {
        ResultMap resultMap = new ResultMap();
        try {
            List<Map<String,Object>> list = systemTestMapper.testNacos();
            resultMap.set("000","操作成功！");
            resultMap.setData(list);
            return resultMap;
        }catch (Exception e) {
            logger.error("操作失败", e);
            resultMap.set("999", "操作出错！");
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // 生成随机唯一键
    static String generateUniqueKey() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 定义日期时间格式，确保生成的字符串具有唯一性和可读性
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        // 格式化当前时间为字符串
        String timeAsKey = now.format(formatter);
        // 在时间字符串前加上固定前缀
        String vcruleid = "GUIZE_"+timeAsKey;
        return vcruleid;
    }

    // 导出数据到CSV文件（无列名）
    // String filePath = "数字员工申请与子场景填报.csv";
    // exportToCSV(resultList, filePath);
    public static void exportToCSV(List<Map<String, Object>> resultList, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map<String, Object> map : resultList) {
                StringBuilder line = new StringBuilder();
                for (Object value : map.values()) {
                    line.append(value).append(",");
                }
                // 删除最后一个逗号并换行
                writer.write(line.deleteCharAt(line.length() - 1).append("\n").toString());
            }
            System.out.println("csv文件成功导出！！.");
        } catch (IOException e) {
            System.err.println("Error occurred while writing to CSV: " + e.getMessage());
        }
    }

    // 获取昨日时间字符
    static String generateYesterday() {
        // 获取昨天时间
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        // 定义日期时间格式，确保生成的字符串具有唯一性和可读性
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 格式化当前时间为字符串
        String timeAsKey = yesterday.format(formatter);
        // 在时间字符串前加上固定前缀
        //String vcruleid = "GUIZE_"+timeAsKey;
        return timeAsKey;
    }

    // 生成随机数
    static int generateRandomNumber(int a, int b) {
        Random random = new Random();
        // 直接计算从a到b的随机数，包括a和b
        return a + random.nextInt(b - a + 1);
    }



}

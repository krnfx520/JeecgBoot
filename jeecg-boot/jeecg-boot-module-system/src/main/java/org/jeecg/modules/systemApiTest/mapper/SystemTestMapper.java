package org.jeecg.modules.systemApiTest.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SystemTestMapper {
    List<Map<String,Object>> testNacos();
}

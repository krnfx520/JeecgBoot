package org.jeecg.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QueryCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存放条件查询值
     */
    private Map<String, Object> condition;

    /**
     * 排序字段
     */
    protected String orderByClause;
    /**
     * 排序类型
     */
    protected String orderType = "ASC";

    private Integer rowStart = 0;
    private Integer rowEnd = 100;

    private Integer pageNo = 1;
    private Integer pageSize = 100;

    protected QueryCriteria(QueryCriteria example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.rowStart = example.rowStart;
        this.rowEnd = example.rowEnd;
    }

    public QueryCriteria() {
        condition = new HashMap<String, Object>();
    }

    public void clear() {
        this.condition.clear();
        this.orderByClause = null;
        this.rowStart = null;
        this.rowEnd = null;
    }

    /**
     * @param condition
     *            查询的条件名称
     * @param value
     *            查询的值
     */
    public QueryCriteria put(String condition, Object value) {
        this.condition.put(condition, value);
        return (QueryCriteria) this;
    }

    /**
     * 得到键值，C层和S层的参数传递时取值所用<br>
     * 自行转换对象
     *
     * @param key
     *            键值
     * @return 返回指定键所映射的值
     */
    public Object get(String key) {
        return this.condition.get(key);
    }

    /**
     * @param orderByClause
     *            排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @param orderType
     *            排序类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    /**
     * @param rowStart
     *            开始记录数
     */
    public void setRowStart(Integer rowStart) {
        this.rowStart = rowStart;
    }

    public Integer getRowStart() {
        return rowStart;
    }

    /**
     * @param oracleEnd
     *            结束记录数
     */
    public void setRowEnd(Integer oracleEnd) {
        this.rowEnd = oracleEnd;
    }

    public String getAsString(String key) {
        Object object = condition.get(key);
        return object == null ? null : object.toString();
    }

    /**
     * 以Integer类型返回键值
     *
     * @param key
     *            键名
     * @return Integer 键值
     */
    public Integer getAsInteger(String key) {
        Object obj = condition.get(key);
        if (obj != null) {
            if (obj instanceof Integer)
                return (Integer) obj;
            return Integer.parseInt(obj.toString());
        }
        return null;
    }
}
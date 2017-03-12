package com.taotao.common.pojo;

import java.util.List;

/**
 * Created by lyf on 2016/11/25.
 */
public class DateGridResult {
    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}

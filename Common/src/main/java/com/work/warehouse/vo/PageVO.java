package com.work.warehouse.vo;

public class PageVO {
    private Integer current;
    private Integer size;
    private Integer total;
    private Object data;

    private PageVO() {
    }

    public static PageVO getPageVO(Integer current, Integer size, Integer total, Object data){
        return new PageVO(current, size, total, data);
    }

    private PageVO(Integer current, Integer size, Integer total, Object data) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

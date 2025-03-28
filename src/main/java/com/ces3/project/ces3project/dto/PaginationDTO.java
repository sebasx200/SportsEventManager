package com.ces3.project.ces3project.dto;

public class PaginationDTO {
    private Integer page;
    private Integer size;

    public PaginationDTO(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PaginationDTO{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}


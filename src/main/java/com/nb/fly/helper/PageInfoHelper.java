package com.nb.fly.helper;

import com.github.pagehelper.PageInfo;
import com.nb.fly.response.StallListVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description 获取分页对象
 * @author: fly
 * @date: 2019/1/14 18:56
 */
@Setter
@Getter
@Component
public class PageInfoHelper<T> {

    private T t;


    /**
     * 获取分页对象
     *
     * @return 分页数据
     */
    public PageInfo<T> getPageInfo(Page search) {
        long total = search.getTotalElements();
        int pageSize = search.getSize();
        int pageNum = search.getNumber();
        PageInfo<T> pageInfo = new PageInfo<T>(search.getContent());
        pageInfo.setTotal(total);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        long totalPage = (total % pageSize) != 0 ? (total / Long.parseLong(pageSize + "")) + 1 : (total / Long.parseLong(pageSize + ""));
        pageInfo.setHasNextPage(totalPage > pageNum);
        return pageInfo;
    }
}

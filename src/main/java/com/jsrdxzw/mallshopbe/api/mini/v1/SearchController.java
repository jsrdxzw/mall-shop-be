package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.model.Spu;
import com.jsrdxzw.mallshopbe.service.SearchService;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import com.jsrdxzw.mallshopbe.vo.Paging;
import com.jsrdxzw.mallshopbe.vo.SpuSimplifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping
    public Paging<SpuSimplifyVO> searchSpu(@RequestParam String q,
                                           @RequestParam(defaultValue = "0", required = false) Integer start,
                                           @RequestParam(defaultValue = "10", required = false) Integer count) {
        Pair<Integer, Integer> pair = CommonUtil.convertPageParams(start, count);
        Page<Spu> spuPage = searchService.searchSpu(q, pair.getFirst(), pair.getSecond());
        return Paging.createPaging(spuPage, SpuSimplifyVO::new);
    }
}

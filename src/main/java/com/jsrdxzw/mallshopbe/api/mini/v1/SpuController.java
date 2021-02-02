package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.model.Spu;
import com.jsrdxzw.mallshopbe.service.SpuService;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import com.jsrdxzw.mallshopbe.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {
    @Autowired
    private SpuService spuService;

    @GetMapping("/id/{id}/detail")
    public Spu getSpuDetail(@PathVariable @Positive Long id) {
        return spuService.getSpuById(id);
    }

    @GetMapping("/id/{id}/simplify")
    public SpuSimplifyVO getSpuSimplify(@PathVariable @Positive Long id) {
        Spu spu = spuService.getSpuById(id);
        SpuSimplifyVO spuSimplifyVO = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, spuSimplifyVO);
        return spuSimplifyVO;
    }

    @GetMapping("/latest")
    public Page<Spu> getLatestSkuList(
            @RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(name = "count", required = false, defaultValue = "10") Integer count
    ) {
        Pair<Integer, Integer> pair = CommonUtil.convertPageParams(start, count);
        return spuService.getLatestSpuPage(pair.getFirst(), pair.getSecond());
    }

    @GetMapping("/category/{id}")
    public Page<Spu> getSpuByCategoryId(@PathVariable @Positive Long id,
                                        @RequestParam(name = "is_root", defaultValue = "false") Boolean isRoot,
                                        @RequestParam(name = "start", defaultValue = "0") Integer start,
                                        @RequestParam(name = "count", defaultValue = "10") Integer count) {
        Pair<Integer, Integer> pair = CommonUtil.convertPageParams(start, count);
        return spuService.getLatestSpuByCategory(id, isRoot, pair.getFirst(), pair.getSecond());
    }
}

package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.model.Spu;
import com.jsrdxzw.mallshopbe.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Service
public class SpuService {
    @Autowired
    private SpuRepository spuRepository;

    public Spu getSpuById(Long id) {
        return spuRepository.findById(id).orElseThrow(() -> new NotFoundException(30003));
    }

    public Page<Spu> getLatestSpuPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return spuRepository.findAllByOrderByCreateTimeDesc(pageRequest);
    }

    public Page<Spu> getLatestSpuByCategory(Long cid, Boolean isRoot, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (isRoot) {
            return spuRepository.findByRootCategoryIdOrderByCreateTimeDesc(cid, pageRequest);
        } else {
            return spuRepository.findByCategoryIdOrderByCreateTimeDesc(cid, pageRequest);
        }
    }
}

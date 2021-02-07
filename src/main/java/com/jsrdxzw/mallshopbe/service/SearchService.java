package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.model.Spu;
import com.jsrdxzw.mallshopbe.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Service
public class SearchService {
    @Autowired
    private SpuRepository spuRepository;

    public Page<Spu> searchSpu(String q, Integer page, Integer size) {
        String likeKeyword = "%" + q + "%";
        Pageable pageable = PageRequest.of(page, size);
        return spuRepository.findByTitleLikeOrSubtitleLike(likeKeyword, likeKeyword, pageable);
    }
}

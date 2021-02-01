package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.model.Banner;
import com.jsrdxzw.mallshopbe.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author xuzhiwei
 * @date 2021-02-01
 */
@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    public Banner getByName(String name) {
        Banner banner = bannerRepository.findFirstByName(name);
        if (Objects.isNull(banner)) {
            throw new NotFoundException(30005);
        }
        return banner;
    }
}

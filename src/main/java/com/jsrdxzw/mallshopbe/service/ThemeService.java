package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.model.Theme;
import com.jsrdxzw.mallshopbe.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> findByNames(List<String> nameList) {
        return themeRepository.selectByNames(nameList);
    }

    public Theme findByName(String name) {
        return themeRepository.findFirstByName(name).orElseThrow(() -> new NotFoundException(30003));
    }
}

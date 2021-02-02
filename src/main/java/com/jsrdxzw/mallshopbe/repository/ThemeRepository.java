package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    /**
     * findFirstByName
     *
     * @param name
     * @return
     */
    Optional<Theme> findFirstByName(String name);

    /**
     * selectByNames
     *
     * @param nameList
     * @return
     */
    @Query("select t from Theme t where t.name in (:nameList)")
    List<Theme> selectByNames(List<String> nameList);
}

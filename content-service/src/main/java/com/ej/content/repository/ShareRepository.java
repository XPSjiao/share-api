package com.ej.content.repository;

import com.ej.content.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: ej
 * @create: 2022-09-06
 **/

public interface ShareRepository extends JpaRepository<Share, Integer> {
}

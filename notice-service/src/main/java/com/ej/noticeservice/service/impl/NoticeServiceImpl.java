package com.ej.noticeservice.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ej.noticeservice.entity.Notice;
import com.ej.noticeservice.repository.NoticeRepository;
import com.ej.noticeservice.service.NoticeService;

import javax.annotation.Resource;

/**
 * @description:
 * @author: ej
 * @create: 2022-09-25
 **/

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeRepository noticeRepository;

    @Override
    public Notice getLatestNotice() {
        Sort sort = Sort.by("createTime").descending();
        return noticeRepository.findByShowFlag(true, sort).get(0);
    }
}

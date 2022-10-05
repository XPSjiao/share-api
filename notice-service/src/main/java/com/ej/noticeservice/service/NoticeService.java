package com.ej.noticeservice.service;

import com.ej.noticeservice.entity.Notice;

/**
 * @description:
 * @author: ej
 * @create: 2022-09-25
 **/

public interface NoticeService {
    /**
     * 获取最新通知
     *
     * @return notice
     */
    Notice getLatestNotice();
}


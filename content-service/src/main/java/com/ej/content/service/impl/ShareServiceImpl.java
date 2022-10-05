package com.ej.content.service.impl;

import com.ej.content.domain.dto.ShareAuditDto;
import com.ej.content.domain.dto.ShareDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ej.content.domain.entity.Share;
import com.ej.content.repository.ShareRepository;
import com.ej.content.service.ShareService;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: ej
 * @create: 2022-09-06
 **/

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    final private ShareRepository shareRepository;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }


    @Override
    public List<Share> findAll() {
        return shareRepository.findAll();
    }


    @Override
    public Page<Share> getAll(int page,int size,String status){
        // 1. 根据创建时间排序
        Sort sort = Sort.by("createTime").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        return shareRepository.findAll(pageable);
    }


    @Override
    public Share auditShare(ShareAuditDto shareAuditDto) throws IllegalAccessException {
        Share share = shareRepository.findById(shareAuditDto.getId()).orElse(null);
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalAccessException("参数非法！改分享已审核！");
        }
        share.setAuditStatus(shareAuditDto.getAuditStatusEnum().toString());
        share.setReason(shareAuditDto.getReason());
        share.setShowFlag(shareAuditDto.getShowFlag());
        return shareRepository.saveAndFlush(share);
    }
}

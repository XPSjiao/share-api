package com.ej.contentservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ej.contentservice.domain.entity.Share;

/**
 * @description:
 * @author: syhan
 * @create: 2022-09-06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareDto {
    private Share share;
    private String nickname;
    private String avatar;
}

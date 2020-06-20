package com.wuling.xbloger.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
@Setter
@Getter
public class ContributeDayDTO {

    @JsonIgnore
    private LocalDate localDate;
    private Long timestamp;
    private Integer core;
    private Integer contributeCount;

    public ContributeDayDTO(LocalDate localDate) {
        this.localDate = localDate;
        this.timestamp = 0L;
        this.core = 0;
        this.contributeCount = 0;
    }
}

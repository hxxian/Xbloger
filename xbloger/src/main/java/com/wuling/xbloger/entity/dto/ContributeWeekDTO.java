package com.wuling.xbloger.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
@Setter
@Getter
public class ContributeWeekDTO {

    private Integer year;
    private Integer weekOfYear;
    private List<ContributeDayDTO> daysOfWeek;

    public ContributeWeekDTO(Integer year, Integer weekOfYear, List<ContributeDayDTO> daysOfWeek) {
        this.year = year;
        this.weekOfYear = weekOfYear;
        this.daysOfWeek = daysOfWeek;
    }

}

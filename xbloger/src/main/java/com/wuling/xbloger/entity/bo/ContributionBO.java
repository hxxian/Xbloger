package com.wuling.xbloger.entity.bo;

import com.wuling.xbloger.entity.dto.ContributeWeekDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
@Setter
@Getter
public class ContributionBO {

    private List<ContributeWeekDTO> weeks;

    public ContributionBO(List<ContributeWeekDTO> weeks) {
        this.weeks = weeks;
    }
}

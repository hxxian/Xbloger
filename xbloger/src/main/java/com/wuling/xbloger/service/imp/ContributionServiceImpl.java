package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.Contribution;
import com.wuling.xbloger.entity.bo.ContributionBO;
import com.wuling.xbloger.entity.dto.ContributeDayDTO;
import com.wuling.xbloger.entity.dto.ContributeWeekDTO;
import com.wuling.xbloger.enumeration.ContributionEnum;
import com.wuling.xbloger.mapper.ContributionMapper;
import com.wuling.xbloger.service.ContributionService;
import com.wuling.xbloger.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    private ContributionMapper contributionMapper;

    @Override
    public ContributionBO listContributionInCurrYear() {
        int total = 371;
        LocalDate now = LocalDate.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        int limit = total - (7 - dayOfWeek) - 1;

        List<Contribution> contributions = contributionMapper.listContributionByYear(limit);
        if (contributions != null && !contributions.isEmpty()) {
            Map<String, Integer> contributeMap = new HashMap<>();
            Map<String, Integer> contributeCountMap = new HashMap<>();

            for (Contribution c : contributions) {
                LocalDate localDate = DateUtil.date2LocalDate(c.getGmtCreate());
                if (contributeMap.containsKey(localDate.toString())) {
                    int currCore = contributeMap.get(localDate.toString());
                    contributeMap.put(localDate.toString(), currCore + getCore(c.getType()));
                    contributeCountMap.put(localDate.toString(), contributeCountMap.get(localDate.toString()) + 1);
                } else {
                    contributeMap.put(localDate.toString(), getCore(c.getType()));
                    contributeCountMap.put(localDate.toString(), 1);
                }
            }

            List<ContributeDayDTO> days = new ArrayList<>(total);
            days.add(new ContributeDayDTO(now));

            for (int i = 1; i <= limit; i++) {
                days.add(new ContributeDayDTO(now.plusDays(-i)));
            }

            for (int i = 1; i < (total - limit); i++) {
                days.add(new ContributeDayDTO(now.plusDays(i)));
            }

            Map<Integer, ContributeWeekDTO> weekMap = new HashMap<>();

            for (int i = 0; i < days.size(); i++) {
                LocalDate localDate = days.get(i).getLocalDate();
                if (contributeMap.containsKey(localDate.toString())) {
                    days.get(i).setCore(contributeMap.get(localDate.toString()));
                    days.get(i).setTimestamp(DateUtil.localDate2Date(localDate).getTime());
                    days.get(i).setContributeCount(contributeCountMap.get(localDate.toString()));
                }

                int weekOfYear = DateUtil.getWeekOfYear(days.get(i).getLocalDate());
                if (weekMap.containsKey(weekOfYear)) {
                    ContributeWeekDTO week = weekMap.get(weekOfYear);
                    week.getDaysOfWeek().add(days.get(i));
                } else {
                    List<ContributeDayDTO> daysOfWeek = new ArrayList<>();
                    daysOfWeek.add(days.get(i));
                    ContributeWeekDTO week = new ContributeWeekDTO(weekOfYear, daysOfWeek);
                    weekMap.put(weekOfYear, week);
                }
            }

            // TODO 排序时还要处理不同年份，因为不同年份的周数可能一样
            List<ContributeWeekDTO> weekList = weekMap.values().stream().collect(Collectors.toList());
            Collections.sort(weekList, Comparator.comparingInt(ContributeWeekDTO::getWeekOfYear));

            for (int i = 0; i < weekList.size(); i++) {
                List<ContributeDayDTO> dayList = weekList.get(i).getDaysOfWeek();
                Collections.sort(dayList, Comparator.comparingLong(ContributeDayDTO::getTimestamp));
            }
            ContributionBO contributionBO = new ContributionBO(weekList);
            return contributionBO;
        }

        return null;
    }

    private Integer getCore(Integer type) {
        for (ContributionEnum c : ContributionEnum.values()) {
            if (type == c.getTypeId()) {
                return c.getCore();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int total = 371;
        LocalDate now = LocalDate.now();
        System.out.println(now.toString());
        int dayOfWeek = now.getDayOfWeek().getValue();
        int limit = total - (7 - dayOfWeek) - 1;
//        for (int i = 1; i <= limit; i++) {
//
//        }
    }

}

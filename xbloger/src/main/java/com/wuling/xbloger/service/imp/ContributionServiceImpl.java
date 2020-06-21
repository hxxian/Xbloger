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
//        long start = System.currentTimeMillis();

        int total = 371;
        LocalDate now = LocalDate.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        int limit = total - (7 - dayOfWeek) - 1;

        // 查询有活动记录的数据
        List<Contribution> contributions = contributionMapper.listContributionByYear(limit);

        Map<String, Integer> contributeMap = new HashMap<>();
        Map<String, Integer> contributeCountMap = new HashMap<>();

        if (contributions != null && !contributions.isEmpty()) {

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
        }

        // 创建371个date数据（1年52周 + 1周）
        List<ContributeDayDTO> days = new ArrayList<>(total);
        days.add(new ContributeDayDTO(now));

        for (int i = 1; i <= limit; i++) {
            days.add(new ContributeDayDTO(now.plusDays(-i)));
        }

        for (int i = 1; i < (total - limit); i++) {
            days.add(new ContributeDayDTO(now.plusDays(i)));
        }

        Map<String, ContributeWeekDTO> weekMap = new HashMap<>();

        for (int i = 0; i < days.size(); i++) {
            LocalDate localDate = days.get(i).getLocalDate();
            int currYear = localDate.getYear();

            // 如果匹配，则表示当前date有活动记录，设置分值
            if (contributeMap.containsKey(localDate.toString())) {
                days.get(i).setCore(contributeMap.get(localDate.toString()));
                days.get(i).setContributeCount(contributeCountMap.get(localDate.toString()));
            }

            days.get(i).setTimestamp(DateUtil.localDate2Date(localDate).getTime());

            int weekOfYear = DateUtil.getWeekOfYear(days.get(i).getLocalDate());

            // 如果出现date的月份是12月份，同时又是第1周，则表示date的周编号与下一年的第一周同属一周，因此将其编置到下一年的第1周
            if (weekOfYear == 1 && localDate.getMonth().getValue() == 12) {
                currYear++;
            }

            String weekOfYearKey = currYear + "-" + weekOfYear;
            if (weekMap.containsKey(weekOfYearKey)) {
                ContributeWeekDTO week = weekMap.get(weekOfYearKey);
                week.getDaysOfWeek().add(days.get(i));
            } else {
                List<ContributeDayDTO> daysOfWeek = new ArrayList<>();
                daysOfWeek.add(days.get(i));
                ContributeWeekDTO week = new ContributeWeekDTO(currYear, weekOfYear, daysOfWeek);
                weekMap.put(weekOfYearKey, week);
            }
        }

        // 给53个周排序，递增排序
        List<ContributeWeekDTO> weekList = weekMap.values().stream().collect(Collectors.toList());
        Collections.sort(weekList, (w1, w2) -> {
            if (!w1.getYear().equals(w2.getYear())) {
                return w1.getYear() - w2.getYear();
            }
            return w1.getWeekOfYear() - w2.getWeekOfYear();
        });

        // 给每一周的7天排序，使其保持星期一到星期天的顺序排列
        for (int i = 0; i < weekList.size(); i++) {
            List<ContributeDayDTO> dayList = weekList.get(i).getDaysOfWeek();
            Collections.sort(dayList, (d1, d2) -> (int) (d1.getTimestamp() - d2.getTimestamp()));
        }

        ContributionBO contributionBO = new ContributionBO(weekList);
        long end = System.currentTimeMillis();

//        System.out.println(end - start);
        return contributionBO;
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

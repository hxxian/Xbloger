package com.wuling.xbloger.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xian
 * @Description: 语录摘（说说）
 * @Date:create in 2020/5/12 8:06
 */
@Setter
@Getter
@TableName("diary")
public class Diary {

    @TableId(value = "id", type = IdType.AUTO)
    private Long did;
    @TableField("content")
    private String content;

    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_update")
    private Date gmtUpdate;

}

package com.wuling.xbloger.enumeration;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc: 博客动态（贡献）枚举
 */
public enum ContributionEnum {

    CREATE_ARTICLE(1, "新增文章", 75),
    UPDATE_ARTICLE(2, "更新文章", 10),
    CREATE_DIARY(3, "发表说说", 5),
    REPLY_COMMENT(4, "回复评论", 10);

    /**
     * 贡献类型ID
     */
    private Integer typeId;
    /**
     * 贡献描述
     */
    private String desc;
    /**
     * 贡献权重（分值）
     */
    private Integer core;

    ContributionEnum(Integer typeId, String desc, Integer core) {
        this.typeId = typeId;
        this.desc = desc;
        this.core = core;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }
}

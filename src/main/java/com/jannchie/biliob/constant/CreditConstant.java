package com.jannchie.biliob.constant;

/**
 * @author jannchie
 */
public enum CreditConstant {
    /**
     * CHECK_IN: Every eight hour, user can sign in once time. WATCH_AD: Every eight hour, user can
     * watch ad to earn credit once time.
     *
     * <p>MODIFY_NAME: User modify his username.
     *
     * <p>SET_FORCE_OBSERVE: User set one video or author permanently be observed.
     *
     * <p>SET_STRONG_OBSERVE: User set one video or author be observed strongly and the frequency is
     * once an hour.
     *
     * <p>SET_KICHIKU_OBSERVE: User set one video or author be observed kichiku and the frequency is
     * once a minutes.
     *
     * <p>ASK_QUESTION: User are able to ask question and I will give my answer.
     *
     * <p>DONATE: If user try to donate money to me, he will earn credit.
     *
     * <p>REFRESH_AUTHOR_DATA: Refresh author data immediately.
     *
     * <p>REFRESH_AUTHOR_DATA: Refresh video data immediately.
     */
    CHECK_IN(10D, "签到"),
    WATCH_AD(10D, "点击广告"),
    MODIFY_NAME(-50D, "改名为 %s"),
    SET_AUTHOR_FORCE_OBSERVE(-200D, "设置 mid:%s 强制追踪"),
    SET_VIDEO_FORCE_OBSERVE(-200D, "设置 BV%s 强制追踪"),
    SET_STRONG_OBSERVE(-150D, "设置高频率追踪一周"),
    SET_KICHIKU_OBSERVE(-300D, "设置鬼畜级频率追踪一周"),
    ASK_QUESTION(-30D, "提出问题"),
    DONATE(100D, "试图捐款"),
    REFRESH_AUTHOR_DATA(-5D, "立即刷新ID为 %s 的UP主数据"),
    REFRESH_VIDEO_DATA(-1D, "立即刷新ID为 %s 的视频数据"),
    DANMAKU_AGGREGATE(-10D, "对 av%s 进行弹幕分析"),
    ALWAYS_FAIL(-999999999D, "不可能完成的任务"),
    LIKE_COMMENT(-0.1D, "喜欢编号为 %s 的观测记录"),
    BE_LIKE_COMMENT(0.1D, "编号为 %s 的观测记录被喜欢"),
    MODIFY_MAIL(-50D, "改邮箱为 %s"),
    POST_COMMENT(-1D, "在 %s 下发布观测记录"),
    INIT_AUTHOR_LIST(-20D, "创建名为 %s 的UP主群组"),
    FORK_AUTHOR_LIST(-20D, "分支ID为 %s 的UP主群组"),
    BE_FORKED_AUTHOR_LIST(10D, "ID为 %s 的UP主群组被分支"),
    MODIFY_AUTHOR_LIST_INFO(-50D, "修改ID为 %s 的UP主群组"),
    STAR_AUTHOR_LIST(-1D, "收藏ID为 %s 的UP主群组"),
    BE_STARED_AUTHOR_LIST(0.5D, "ID为 %s 的UP主群组被收藏"),
    JOIN_GUESSING(0D, "参加ID为 %s 的预测项目"),
    GIVE_CREDIT(0D, "赠礼获得：%s"),
    PUNISH_CREDIT(0D, "违规扣除：%s"),
    GUESSING_REVENUE(0D, "获得ID为 %s 的预测项目赏金"),
    POST_AGENDA(-50D, "提出标题为 %s 的议题"),
    REFRESH_VIDEO_DATA_BY_BVID(-1D, "立即刷新 BV%s 的视频数据");

    private Double value;
    private String msg;

    CreditConstant(Double value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    CreditConstant(Double value) {
        this.value = value;
    }

    public <T> String getMsg(T d) {
        return String.format(msg, d);
    }

    public <T, U> String getMsg(T d, U e) {
        return String.format(msg, d, e);
    }

    public <T, U, V> String getMsg(T d, U e, V f) {
        return String.format(msg, d, e, f);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}

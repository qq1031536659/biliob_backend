package com.jannchie.biliob.service;

import com.jannchie.biliob.exception.UserAlreadyFavoriteVideoException;
import com.jannchie.biliob.exception.VideoAlreadyFocusedException;
import com.jannchie.biliob.model.Video;
import com.jannchie.biliob.utils.Message;
import com.jannchie.biliob.utils.MySlice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jannchie
 */
@Service
public interface VideoService {

    /**
     * 获得视频详情
     *
     * @param aid  视频id
     * @param type 0: original data; 1: aggregated by day
     * @return 视频详情
     */
    @Deprecated
    Video getVideoDetails(Long aid, Integer type);

    /**
     * 提交一个作品追踪
     *
     * @param aid 视频id
     * @return 成功观测的应答
     * @throws UserAlreadyFavoriteVideoException 用户已关注视频
     * @throws VideoAlreadyFocusedException      视频已在观测
     */
    ResponseEntity<Message> postVideoByAid(Long aid)
            throws UserAlreadyFavoriteVideoException, VideoAlreadyFocusedException;

    /**
     * 获取视频页
     *
     * @param aid      搜索视频id
     * @param text     搜索文本
     * @param page     页数
     * @param pagesize 页大小
     * @param sort     排序
     * @param days     天数
     * @return 视频页
     */
    MySlice<Video> getVideo(
            Long aid, String text, Integer page, Integer pagesize, Integer sort, Integer days);

    /**
     * 获取作者其他视频
     *
     * @param aid      视频id
     * @param mid      作者id
     * @param page     页数
     * @param pagesize 页大小
     * @return 视频切片
     */
    MySlice<Video> getAuthorOtherVideo(Long aid, Long mid, Integer page, Integer pagesize);

    /**
     * Get author top video.
     *
     * @param mid      author id
     * @param page     no use
     * @param pagesize the number of displayed video
     * @param sort     0: order by view || 1: order by publish datetime.
     * @return slice of author's video
     */
    MySlice<Video> getAuthorTopVideo(Long mid, Integer page, Integer pagesize, Integer sort);

    /**
     * Get my video.
     *
     * @return one of my video
     */
    Video getMyVideo();

    /**
     * Get top online video in one day.
     *
     * @return top online video response.
     */
    ResponseEntity listOnlineVideo();

    /**
     * Get the number of video.
     *
     * @return the number of video.
     */
    Long getNumberOfVideo();


    /**
     * get aggregated video data
     *
     * @param aid video id
     * @return video
     */
    Video getAggregatedData(Long aid);

    /**
     * get popular keyword
     *
     * @return popular keyword list
     */
    List getPopularTag();

    /**
     * get guest prefer keyword
     *
     * @param data video id visit count map
     * @return keyword map
     */
    Map getPreferKeyword(Map<String, Integer> data);

    /**
     * get recommend video
     *
     * @param data     keyword of tag
     * @param page     page
     * @param pagesize pagesize
     * @return recommend video list
     */
    ArrayList<Video> getRecommendVideoByTag(
            Map<String, Integer> data, Integer page, Integer pagesize);

    /**
     * get top online video
     *
     * @return top online video
     */
    Map getTopOnlineVideo();

    /**
     * get most popular tag
     *
     * @return most popular tag
     */
    List listMostPopularTag();

    /**
     * 更新视频更新频率
     */
    void updateObserveFreq();
}

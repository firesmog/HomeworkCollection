package com.readboy.homeworkcollection.net;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RequestInterface {

    @DELETE("eqs/{eq_id}?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> deleteWrong(
            @Path("eq_id") String WrongQuestionId
    );

    @PUT("eqs/{eq_id}/er?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> fixReason(
            @Path("eq_id") String WrongQuestionId,
            @Query("er") int errReason
    );

    /**
     * @param queryMap 参数Map 包括：
     *                 sids ：章节id，多个id以逗号进行分割，默认所有章节
     *                 er ： 逗号分割的错因，默认为全部
     * @param userId   用户id
     * @param bookId   教材id
     * @param page     页码，第几页（page>=1）
     * @param fix      是否订正，-1为全部，0为未订正，1为已订正
     * @param t        时间筛选，-1为全部，0：上一周，1：近半个月，2：近一个月，3：近三个月
     * @return
     */
    @GET("eqs?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> getWrongQuestions(
            @QueryMap Map<String, String> queryMap,
            @Query("userId") int userId,
            @Query("bookId") int bookId,
            @Query("page") int page,
            @Query("fix") int fix,
            @Query("t") int t
    );

    /**
     * 大章和小节都在此处获取， 小节为大章的子节点
     *
     * @param paramBookId 书本id
     * @param userId      用户id
     */
    @GET("books/{paramBookId}?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> getChapter(
            @Path("paramBookId") int paramBookId,
            @Query("userId") int userId
    );

    /**
     * 获取章节错题
     *
     * @param bookId 书本id
     * @param userId 用户id
     */
    @GET("eqs/counts?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> getWrongCount(
            @Query("bookId") int bookId,
            @Query("userId") int userId
    );

    /**
     * 开始精准测评 获取第一题
     *
     * @param bookId    书本id
     * @param userId    用户id
     * @param sectionId 章节id
     * @param cityId    城市id
     * @param grade     年级
     */
    @GET("eva/start?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> startEvaluate(
            @Query("bookId") int bookId,
            @Query("userId") int userId,
            @Query("sectionId") int sectionId,
            @Query("cityId") int cityId,
            @Query("grade") int grade
    );


    /**
     * 上传答题情况并获取下一题
     *
     * @param test_id     本次测试的id
     * @param questionId  上一题的id
     * @param testPointId 题目所属考点的id
     * @param status      作答情况1-正确、0-不会、-1-错误
     * @param pt          预估时间
     * @param diff        题目难度
     * @param abilityId   逗号分割的能力维度id,example:101,102
     */
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("eva/{test_id}/answer-stat?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> nextQuestion(
            @Path("test_id") String test_id,
            @Field("questionId") String questionId,
            @Field("tpointId") String testPointId,
            @Field("status") int status,
            @Field("at") long at,
            @Field("pt") float pt,
            @Field("diff") int diff,
            @Field("abilityId") String abilityId,
            @Field("answer") String answer
    );


    /**
     * 上传最后一题大体情况并获取本次答题结果
     *
     * @param test_id     本次测试的id
     * @param questionId  最后一题的id
     * @param testPointId 所属考点id
     * @param status      作答情况1-正确、0-不会、-1-错误
     * @param status      作答情况1-正确、0-不会、-1-错误
     * @param pt          预估时间
     * @param diff        题目难度
     * @param abilityId   逗号分割的能力维度id,example:101,102
     */
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("eva/{test_id}/post-result?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> postAnswer(
            @Path("test_id") String test_id,
            @Field("questionId") String questionId,
            @Field("tpointId") String testPointId,
            @Field("status") int status,
            @Field("at") long at,
            @Field("pt") float pt,
            @Field("diff") int diff,
            @Field("abilityId") String abilityId,
            @Field("answer") String answer
    );


    /**
     * 考点专项学数据
     *
     * @param id 考点id
     */
    @GET("testpoints/{tpoint_id}/special?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> keyPointLearn(
            @Path("tpoint_id") long id

    );


    /**
     * 知识点专项学数据
     *
     * @param id        知识点id
     * @param sectionId 章节id
     */
    @GET("keypoints/{keypoint_id}/special?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> knowledgeLearn(
            @Path("keypoint_id") long id,
            @Query("sectionId") int sectionId
    );

    /**
     * 从主界面获取简易报告
     *
     * @param bookId 书本id
     * @param userId 用户id
     */
    @GET("reports/simple?apiToken=" + BaseRequest.API_TOKEN + "&from=home")
    Observable<RequestResult> simpleReportFromHome(
            @Query("bookId") int bookId,
            @Query("userId") int userId
    );

    /**
     * 从精准测评获取简易报告
     *
     * @param bookId 书本id
     * @param userId 用户id
     * @param testId 上一次测试的id
     */
    @GET("reports/simple?apiToken=" + BaseRequest.API_TOKEN + "&from=eva")
    Observable<RequestResult> simpleReportFromEva(
            @Query("bookId") int bookId,
            @Query("userId") int userId,
            @Query("testId") String testId
    );


    /**
     * 从智能学习获取简易报告
     *
     * @param bookId    书本id
     * @param userId    用户id
     * @param sectionId 章节id
     */
    @GET("reports/simple?apiToken=" + BaseRequest.API_TOKEN + "&from=smart")
    Observable<RequestResult> simpleReportFromSmart(
            @Query("bookId") int bookId,
            @Query("userId") int userId,
            @Query("sectionId") int sectionId
    );

    /**
     * 获取知识图谱
     *
     * @param sectionId 章节id
     * @param userId    用户id
     * @param cityId    城市id
     */
    @GET("graphs?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> getGraph(
            @Query("sectionId") int sectionId,
            @Query("userId") int userId,
            @Query("cityId") int cityId
    );




    /**
     * 考点专项学-考点精练开始作答
     *
     * @param testPointId 考点id
     * @param userId      用户id
     * @param bookId      书本id
     * @param sectionId   章节id
     * @param cityId      城市id
     */
    @GET("testpoints/{tpoint_id}/eva/start?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> startTest(
            @Path("tpoint_id") long testPointId,
            @Query("userId") int userId,
            @Query("bookId") int bookId,
            @Query("sectionId") int sectionId,
            @Query("cityId") int cityId
    );


    /**
     * 考点精练作答统计 每答完一题 提交一次
     *
     * @param testPointId 考点id
     * @param test_id     作答id
     * @param questionId  问题id
     * @param status      作答情况，1-正确、0-不会、-1-错误，默认为0
     * @param pt          预估时间，单位s
     * @param diff        题目难度，对应题目的难度属性（默认为0，但是目前没有难度0的题目）
     * @param abilityId   逗号分割的能力维度id
     */
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("testpoints/{tpoint_id}/eva/{test_id}/answer-stat?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> postTestStat(
            @Path("tpoint_id") long testPointId,
            @Path("test_id") String test_id,
            @Field("questionId") long questionId,
            @Field("status") int status,
            @Field("at") long answerTime,
            @Field("pt") float pt,
            @Field("diff") int diff,
            @Field("abilityId") String abilityId,
            @Field("answer") String answer
    );


    /**
     * 考点精讲提交作答 当最后一题答完 提交答案
     *
     * @param testPointId 考点id
     * @param test_id     作答id
     * @param questionId  问题id
     * @param status      作答情况，1-正确、0-不会、-1-错误，默认为0
     * @param answerTime  作答时间，单位s
     * @param pt          预估时间，单位s
     * @param diff        题目难度，对应题目的难度属性（默认为0，但是目前没有难度0的题目）
     * @param abilityId   逗号分割的能力维度id
     */
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("testpoints/{tpoint_id}/eva/{test_id}/post-result?apiToken=" + BaseRequest.API_TOKEN)
    Observable<RequestResult> finishTest(
            @Path("tpoint_id") long testPointId,
            @Path("test_id") String test_id,
            @Field("questionId") long questionId,
            @Field("status") int status,
            @Field("at") long answerTime,
            @Field("pt") float pt,
            @Field("diff") int diff,
            @Field("abilityId") String abilityId,
            @Field("answer") String answer
    );


  
}

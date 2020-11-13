package com.tetravalstartups.anandsuper.api;

import com.tetravalstartups.anandsuper.auth.LoginResponse;
import com.tetravalstartups.anandsuper.auth.OTPResponse;
import com.tetravalstartups.anandsuper.auth.Register;
import com.tetravalstartups.anandsuper.modules.course.CourseResponse;
import com.tetravalstartups.anandsuper.modules.course.CourseTopic;
import com.tetravalstartups.anandsuper.modules.course.GetVideoList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserLogin {

    @FormUrlEncoded
    @POST("Register")
    Call<Register> doUserRegister(@Field("name") String name,
                                  @Field("contact") String contact,
                                  @Field("email") String email,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("Verify-OTP")
    Call<OTPResponse> verifyUser(@Field("user_id") String user_id,
                                 @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> doLogin(@Field("username") String username,
                                @Field("password") String password);

    @POST("Get-All-Course")
    Call<CourseResponse> courses();

    @FormUrlEncoded
    @POST("Get-Course-Topic")
    Call<CourseTopic> getCourseTopic(@Field("course_id") String course_id,
                                     @Field("language") String language);

    @FormUrlEncoded
    @POST("Get-Video-List")
    Call<GetVideoList> getVideoList(@Field("topic_id") String topic_id);
}

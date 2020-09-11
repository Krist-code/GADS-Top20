package com.example.gads_top20;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface GadsApi {
    static final String  GADS_URL_ID = "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    static final String KRIST_URL_ID = "1FAIpQLSdQ7I2BpL5bkuheHqGBymkaO4kKOHQLENy303yU_GP5DNyRqw/formResponse";

    static final String NAME_KEY = "i3";
    static final String SURE_NAME_KEY = "i7";
    static final String EMAIL_KEY = "i11";
    static final String PROJECT_LINK_KEY = "i15";

    //    creating post for request
    @POST(GADS_URL_ID)
    @FormUrlEncoded
    Call<PostSubmit> createPost(
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.1824927963") String email,
            @Field("entry.284483984") String projectLink
    );

    @POST(KRIST_URL_ID)
    @FormUrlEncoded
    Call<PostSubmit> createPostForKristo(
            @Field(NAME_KEY) String firstName,
            @Field(SURE_NAME_KEY) String lastName,
            @Field(EMAIL_KEY) String email,
            @Field(PROJECT_LINK_KEY) String projectLink
    );
}

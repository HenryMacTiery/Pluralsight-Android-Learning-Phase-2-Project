package com.timac.pluralsightlp2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiSkill {

    @GET("api/skilliq")
    Call<List<SkillIQ>> getSkillIQs();

}

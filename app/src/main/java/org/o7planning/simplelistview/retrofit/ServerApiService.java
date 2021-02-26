package org.o7planning.simplelistview.retrofit;


import org.o7planning.simplelistview.retrofit.Retrofit_Models.UserRequest;
import org.o7planning.simplelistview.retrofit.Retrofit_Models.UserResponse;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApiService {

    @POST("/api/users")
    Call<UserResponse> createUser(@Body UserRequest userRequest);

}

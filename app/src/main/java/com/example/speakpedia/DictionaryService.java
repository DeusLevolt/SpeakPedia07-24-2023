package com.example.speakpedia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DictionaryService {

    @GET("api/v3/references/sd3/json/{word}")
    Call<WordResponse[]> getWordDefinition(@Path("word") String word, @Query("key") String apiKey);
}

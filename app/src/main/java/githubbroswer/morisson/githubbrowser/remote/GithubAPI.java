package githubbroswer.morisson.githubbrowser.remote;

import githubbroswer.morisson.githubbrowser.data.GithubMessage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubAPI {

    @GET("/search/repositories")
    Call<GithubMessage> loadRepositories(@Query("q") String name);
}

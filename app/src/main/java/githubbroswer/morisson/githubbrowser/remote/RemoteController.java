package githubbroswer.morisson.githubbrowser.remote;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import githubbroswer.morisson.githubbrowser.R;
import githubbroswer.morisson.githubbrowser.data.GithubMessage;
import githubbroswer.morisson.githubbrowser.view.ApiContentProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteController implements Callback<GithubMessage> {

    private final static String BASE_URL = "https://api.github.com";
    private ApiContentProvider apiContentProvider;


    public RemoteController(ApiContentProvider contentProvider) {
        this.apiContentProvider = contentProvider;
    }


    public void init(String repoName) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GithubAPI api = retrofit.create(GithubAPI.class);

        Call<GithubMessage> call = api.loadRepositories(repoName);

        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<GithubMessage> call, Response<GithubMessage> response) {
        GithubMessage temp = response.body();
        if (temp != null) apiContentProvider.uploadData(temp.getItems());
        else {
            apiContentProvider.failedUpload();
        }
    }

    @Override
    public void onFailure(Call<GithubMessage> call, Throwable t) {

        apiContentProvider.failedUpload();
    }
}
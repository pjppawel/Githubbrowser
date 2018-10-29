package githubbroswer.morisson.githubbrowser.view;

import java.util.ArrayList;

import githubbroswer.morisson.githubbrowser.data.Repo;

public interface ApiContentProvider {

    void uploadData(ArrayList<Repo> repo);

    void failedUpload();
}

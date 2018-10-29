package githubbroswer.morisson.githubbrowser.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import githubbroswer.morisson.githubbrowser.R;
import githubbroswer.morisson.githubbrowser.data.Repo;
import githubbroswer.morisson.githubbrowser.remote.RemoteController;
import githubbroswer.morisson.githubbrowser.view.adapter.RepoAdapter;

import static githubbroswer.morisson.githubbrowser.view.MainActivity.REPO_NAME_KEY;

public class ListActivity extends AppCompatActivity implements ApiContentProvider {

    private ArrayList<Repo> items;
    private ProgressBar progressBar;
    private RepoAdapter repoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        progressBar = findViewById(R.id.progressBar);
        Bundle extras = getIntent().getExtras();
        RemoteController controller = new RemoteController(this);
        String repoName;

        if (extras != null) {
            repoName = extras.getString(REPO_NAME_KEY);
            controller.init(repoName);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void uploadData(ArrayList<Repo> repos) {
        items = repos;
        items.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        repoAdapter = new RepoAdapter((items));
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(repoAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void failedUpload() {
        progressBar.setVisibility(View.GONE);
        TextView error = findViewById(R.id.error);
        error.setText(getString(R.string.download_data_error));
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sort, menu);
        menu.getItem(0).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_name:
                if (!item.isChecked()) {
                    items.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
                    updateMenu(item);
                }
                return true;
            case R.id.action_language:
                if (!item.isChecked()) {
                    items.sort((p1, p2) -> p1.getLanguage().compareTo(p2.getLanguage()));
                    updateMenu(item);
                }
                return true;
            case R.id.action_rating:
                if (!item.isChecked()) {
                    Collections.sort(items, Comparator.comparingInt(p -> p.getStargazers_count()));
                    updateMenu(item);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateMenu(MenuItem item) {
        item.setChecked(true);
        repoAdapter.notifyDataSetChanged();
    }

}

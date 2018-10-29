package githubbroswer.morisson.githubbrowser.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import githubbroswer.morisson.githubbrowser.R;
import githubbroswer.morisson.githubbrowser.data.Repo;
import githubbroswer.morisson.githubbrowser.remote.RemoteController;

public class MainActivity extends AppCompatActivity {

    public static final String REPO_NAME_KEY = "REPO_NAME";

    private Button buttonFind;
    private EditText editName;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        buttonFind = findViewById(R.id.btn_find);
        editName = findViewById(R.id.edit_name);

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editName.getText() != null) {
                    String repoName = editName.getText().toString();
                    Intent intent = new Intent(context, ListActivity.class);
                    intent.putExtra(REPO_NAME_KEY, repoName);
                    startActivity(intent);
                }
            }
        });
    }
}

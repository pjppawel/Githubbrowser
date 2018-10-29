package githubbroswer.morisson.githubbrowser.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import githubbroswer.morisson.githubbrowser.R;
import githubbroswer.morisson.githubbrowser.data.Repo;

public class RepoAdapter extends
        RecyclerView.Adapter<RepoAdapter.MyViewHolder> {

    private List<Repo> repoList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView fullName;
        public TextView language;
        public TextView stargazer;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            fullName = view.findViewById(R.id.full_name);
            language = view.findViewById(R.id.language);
            stargazer = view.findViewById(R.id.score);
        }
    }

    public RepoAdapter(List<Repo> repoList) {
        this.repoList = repoList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Repo repo = repoList.get(position);
        holder.name.setText(repo.getName());
        holder.fullName.setText(repo.getFull_name());
        holder.stargazer.setText(Integer.toString(repo.getStargazers_count()));
        holder.language.setText(repo.getLanguage());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_repo, parent, false);
        return new MyViewHolder(view);
    }
}
package githubbroswer.morisson.githubbrowser.data;

import java.util.ArrayList;

public class GithubMessage {

    private String total_count;
    private String incomplete_results;

    public ArrayList<Repo> getItems() {
        return items;
    }

    public void setItems(ArrayList<Repo> items) {
        this.items = items;
    }

    ArrayList<Repo> items;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(String incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

}

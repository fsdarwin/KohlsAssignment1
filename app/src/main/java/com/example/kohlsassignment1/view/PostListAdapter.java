package com.example.kohlsassignment1.view;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kohlsassignment1.R;
import com.example.kohlsassignment1.data.model.Post;
import com.example.kohlsassignment1.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.RepoViewHolder>{

    private PostSelectedListener postSelectedListener;
    private final List<Post> data = new ArrayList<>();

    RepoListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, postSelectedListener repoSelectedListener) {
        this.postSelectedListener = postSelectedListener;
        viewModel.getPosts().observe(lifecycleOwner, posts -> {
            data.clear();
            if (posts != null) {
                data.addAll(posts);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name) TextView repoNameTextView;
        @BindView(R.id.tv_repo_description) TextView repoDescriptionTextView;
        @BindView(R.id.tv_forks) TextView forksTextView;
        @BindView(R.id.tv_stars) TextView starsTextView;

        private Post post;

        RepoViewHolder(View itemView, PostSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(repo != null) {
                    postSelectedListener.onPostSelected(repo);
                }
            });
        }

        void bind(Post post) {
            this.repo = post;
            repoNameTextView.setText(repo.name);
            repoDescriptionTextView.setText(repo.description);
            forksTextView.setText(String.valueOf(repo.forks));
            starsTextView.setText(String.valueOf(repo.stars));
        }
    }
}

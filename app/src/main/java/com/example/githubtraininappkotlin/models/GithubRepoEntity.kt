package com.example.githubtraininappkotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos_table")
data class GithubRepoEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    val id: Int,

    @ColumnInfo(name = "node_id")
    @SerializedName("node_id")
    @Expose
    val nodeId: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    val name: String?,

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    @Expose
    val fullName: String?,

    @ColumnInfo(name = "private")
    @SerializedName("private")
    @Expose
    val mPrivate: Boolean,

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    @Expose
    val htmlUrl: String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    val description: String?,

    @ColumnInfo(name = "fork")
    @SerializedName("fork")
    @Expose
    val fork: Boolean,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    val url: String?,

    @ColumnInfo(name = "forks_url")
    @SerializedName("forks_url")
    @Expose
    val forksUrl: String?,

    @ColumnInfo(name = "keys_url")
    @SerializedName("keys_url")
    @Expose
    val keysUrl: String?,

    @ColumnInfo(name = "collaborators_url")
    @SerializedName("collaborators_url")
    @Expose
    val collaboratorsUrl: String?,

    @ColumnInfo(name = "teams_url")
    @SerializedName("teams_url")
    @Expose
    val teamsUrl: String?,

    @ColumnInfo(name = "hooks_url")
    @SerializedName("hooks_url")
    @Expose
    val hooksUrl: String?,

    @ColumnInfo(name = "issue_events_url")
    @SerializedName("issue_events_url")
    @Expose
    val issueEventsUrl: String?,

    @ColumnInfo(name = "events_url")
    @SerializedName("events_url")
    @Expose
    val eventsUrl: String?,

    @ColumnInfo(name = "assignees_url")
    @SerializedName("assignees_url")
    @Expose
    val assigneesUrl: String?,

    @ColumnInfo(name = "branches_url")
    @SerializedName("branches_url")
    @Expose
    val branchesUrl: String?,

    @ColumnInfo(name = "tags_url")
    @SerializedName("tags_url")
    @Expose
    val tagsUrl: String?,

    @ColumnInfo(name = "blobs_url")
    @SerializedName("blobs_url")
    @Expose
    val blobsUrl: String?,

    @ColumnInfo(name = "git_tags_url")
    @SerializedName("git_tags_url")
    @Expose
    val gitTagsUrl: String?,

    @ColumnInfo(name = "git_refs_url")
    @SerializedName("git_refs_url")
    @Expose
    val gitRefsUrl: String?,

    @ColumnInfo(name = "trees_url")
    @SerializedName("trees_url")
    @Expose
    val treesUrl: String?,

    @ColumnInfo(name = "statuses_url")
    @SerializedName("statuses_url")
    @Expose
    val statusesUrl: String?,

    @ColumnInfo(name = "languages_url")
    @SerializedName("languages_url")
    @Expose
    val languagesUrl: String?,

    @ColumnInfo(name = "stargazers_url")
    @SerializedName("stargazers_url")
    @Expose
    val stargazersUrl: String?,

    @ColumnInfo(name = "contributors_url")
    @SerializedName("contributors_url")
    @Expose
    val contributorsUrl: String?,

    @ColumnInfo(name = "subscribers_url")
    @SerializedName("subscribers_url")
    @Expose
    val subscribersUrl: String?,

    @ColumnInfo(name = "subscription_url")
    @SerializedName("subscription_url")
    @Expose
    val subscriptionUrl: String?,

    @ColumnInfo(name = "commits_url")
    @SerializedName("commits_url")
    @Expose
    val commitsUrl: String?,

    @ColumnInfo(name = "git_commits_url")
    @SerializedName("git_commits_url")
    @Expose
    val gitCommitsUrl: String?,

    @ColumnInfo(name = "comments_url")
    @SerializedName("comments_url")
    @Expose
    val commentsUrl: String?,

    @ColumnInfo(name = "issue_comment_url")
    @SerializedName("issue_comment_url")
    @Expose
    val issueCommentUrl: String?,

    @ColumnInfo(name = "contents_url")
    @SerializedName("contents_url")
    @Expose
    val contentsUrl: String?,

    @ColumnInfo(name = "compare_url")
    @SerializedName("compare_url")
    @Expose
    val compareUrl: String?,

    @ColumnInfo(name = "merges_url")
    @SerializedName("merges_url")
    @Expose
    val mergesUrl: String?,

    @ColumnInfo(name = "archive_url")
    @SerializedName("archive_url")
    @Expose
    val archiveUrl: String?,

    @ColumnInfo(name = "downloads_url")
    @SerializedName("downloads_url")
    @Expose
    val downloadsUrl: String?,

    @ColumnInfo(name = "issues_url")
    @SerializedName("issues_url")
    @Expose
    val issuesUrl: String?,

    @ColumnInfo(name = "pulls_url")
    @SerializedName("pulls_url")
    @Expose
    val pullsUrl: String?,

    @ColumnInfo(name = "milestones_url")
    @SerializedName("milestones_url")
    @Expose
    val milestonesUrl: String?,

    @ColumnInfo(name = "notifications_url")
    @SerializedName("notifications_url")
    @Expose
    val notificationsUrl: String?,

    @ColumnInfo(name = "labels_url")
    @SerializedName("labels_url")
    @Expose
    val labelsUrl: String?,

    @ColumnInfo(name = "releases_url")
    @SerializedName("releases_url")
    @Expose
    val releasesUrl: String?,

    @ColumnInfo(name = "deployments_url")
    @SerializedName("deployments_url")
    @Expose
    val deploymentsUrl: String?,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String?,

    @ColumnInfo(name = "pushed_at")
    @SerializedName("pushed_at")
    @Expose
    val pushedAt: String?,

    @ColumnInfo(name = "git_url")
    @SerializedName("git_url")
    @Expose
    val gitUrl: String?,

    @ColumnInfo(name = "ssh_url")
    @SerializedName("ssh_url")
    @Expose
    val sshUrl: String?,

    @ColumnInfo(name = "clone_url")
    @SerializedName("clone_url")
    @Expose
    val cloneUrl: String?,

    @ColumnInfo(name = "svn_url")
    @SerializedName("svn_url")
    @Expose
    val svnUrl: String?,

    @ColumnInfo(name = "homepage")
    @SerializedName("homepage")
    @Expose
    val homepage: String?,

    @ColumnInfo(name = "size")
    @SerializedName("size")
    @Expose
    val size: Int?,

    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    @Expose
    val stargazersCount: Int?,

    @ColumnInfo(name = "watchers_count")
    @SerializedName("watchers_count")
    @Expose
    val watchersCount: Int?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    @Expose
    val language: String?,

    @ColumnInfo(name = "has_issues")
    @SerializedName("has_issues")
    @Expose
    val hasIssues: Boolean,

    @ColumnInfo(name = "has_projects")
    @SerializedName("has_projects")
    @Expose
    val hasProjects: Boolean,

    @ColumnInfo(name = "has_downloads")
    @SerializedName("has_downloads")
    @Expose
    val hasDownloads: Boolean,

    @ColumnInfo(name = "has_wiki")
    @SerializedName("has_wiki")
    @Expose
    val hasWiki: Boolean,

    @ColumnInfo(name = "has_pages")
    @SerializedName("has_pages")
    @Expose
    val hasPages: Boolean,

    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    @Expose
    val forksCount: Int?,

    @ColumnInfo(name = "archived")
    @SerializedName("archived")
    @Expose
    val archived: Boolean,

    @ColumnInfo(name = "disabled")
    @SerializedName("disabled")
    @Expose
    val disabled: Boolean,

    @ColumnInfo(name = "open_issues_count")
    @SerializedName("open_issues_count")
    @Expose
    val openIssuesCount: Int?,

    @ColumnInfo(name = "forks")
    @SerializedName("forks")
    @Expose
    val forks: Int?,

    @ColumnInfo(name = "watchers")
    @SerializedName("watchers")
    @Expose
    val watchers: Int?,

    @ColumnInfo(name = "default_branch")
    @SerializedName("default_branch")
    @Expose
    val defaultBranch: String?
)
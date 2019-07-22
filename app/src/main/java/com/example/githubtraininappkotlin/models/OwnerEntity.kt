package com.example.githubtraininappkotlin.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "owner_table")
data class OwnerEntity(

    @SerializedName("login")
    @Expose
    @ColumnInfo(name = "login")
    val login: String?,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "node_id")
    @SerializedName("note_id")
    @Expose
    val nodeId: String?,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String?,

    @ColumnInfo(name = "gravatar_id")
    @SerializedName("gravatar_id")
    @Expose
    val gravatarId: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    val url: String?,

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    @Expose
    val htmlUrl: String?,

    @ColumnInfo(name = "followers_url")
    @SerializedName("followers_url")
    @Expose
    val followersUrl: String?,

    @ColumnInfo(name = "following_url")
    @SerializedName("following_url")
    @Expose
    val followingUrl: String?,

    @ColumnInfo(name = "gists_url")
    @SerializedName("gists_url")
    @Expose
    val gistsUrl: String?,

    @ColumnInfo(name = "starred_url")
    @SerializedName("starred_url")
    @Expose
    val starredUrl: String?,

    @ColumnInfo(name = "subscriptions_url")
    @SerializedName("subscriptions_url")
    @Expose
    val subscriptionsUrl: String?,

    @ColumnInfo(name = "organizations_url")
    @SerializedName("organizations_url")
    @Expose
    val organizationsUrl: String?,

    @ColumnInfo(name = "repos_url")
    @SerializedName("repos_url")
    @Expose
    val reposUrl: String?,

    @ColumnInfo(name = "events_url")
    @SerializedName("events_url")
    @Expose
    val eventsUrl: String?,

    @ColumnInfo(name = "received_events_url")
    @SerializedName("received_events_url")
    @Expose
    val receivedEventsUrl: String?,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    @Expose
    val type: String?,

    @ColumnInfo(name = "site_admin")
    @SerializedName("site_admin")
    @Expose
    val siteAdmin: Boolean,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    val name: String?,

    @ColumnInfo(name = "company")
    @SerializedName("company")
    @Expose
    val company: String?,

    @ColumnInfo(name = "blog")
    @SerializedName("blog")
    @Expose
    val blog: String?,

    @ColumnInfo(name = "location")
    @SerializedName("location")
    @Expose
    val location: String?,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    @Expose
    val email: String?,

    @ColumnInfo(name = "hireable")
    @SerializedName("hireable")
    @Expose
    val hireable: Boolean,

    @ColumnInfo(name = "bio")
    @SerializedName("bio")
    @Expose
    val bio: String?,

    @ColumnInfo(name = "public_repos")
    @SerializedName("public_repos")
    @Expose
    val publicRepos: Int,

    @ColumnInfo(name = "public_gists")
    @SerializedName("public_gists")
    @Expose
    val publicGists: Int,

    @ColumnInfo(name = "followers")
    @SerializedName("followers")
    @Expose
    val followers: Int,

    @ColumnInfo(name = "following")
    @SerializedName("following")
    @Expose
    val following: Int,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String?,

    @ColumnInfo(name = "private_gists")
    @SerializedName("private_gists")
    @Expose
    val privateGists: Int,

    @ColumnInfo(name = "total_private_repos")
    @SerializedName("total_private_repos")
    @Expose
    val totalPrivateRepos: Int,

    @ColumnInfo(name = "owned_private_repos")
    @SerializedName("owned_private_repos")
    @Expose
    val ownedPrivateRepos: Int,

    @ColumnInfo(name = "disk_usage")
    @SerializedName("disk_usage")
    @Expose
    val diskUsage: Int,

    @ColumnInfo(name = "collaborators")
    @SerializedName("collaborators")
    @Expose
    val collaborators: Int,

    @ColumnInfo(name = "two_factor_authentication")
    @SerializedName("two_factor_authentication")
    @Expose
    val twoFactorAuthentication: Boolean
)
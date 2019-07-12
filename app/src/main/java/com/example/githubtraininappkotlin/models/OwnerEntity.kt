package com.example.githubtraininappkotlin.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owner_table")
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true)
    val ownerId: Int,
    val login: String,
    val id: Int,
    val nodeId:String,
    val avatarUrl:String,
    val gravatarId: String,
    val url:String,
    val htmlUrl:String,
    val followersUrl:String,
    val followingUrl:String,
    val gistsUrl:String,
    val starredUrl:String,
    val subscriptionsUrl:String,
    val organizationsUrl:String,
    val reposUrl:String,
    val eventsUrl:String,
    val receivedEventsUrl:String,
    val type:String,
    val siteAdmin:Boolean,
    val name:String,
    val company:String,
    val blog:String,
    val location: String,
    val email:String,
    val hireable:Boolean,
    val bio: String,
    val publicRepos:Int,
    val publicGists: Int,
    val followers:Int,
    val following: Int,
    val createdAt:String,
    val updatedAt:String,
    val privateGists:Int,
    val totalPrivateRepos:Int,
    val ownedPrivateRepos:Int,
    val diskUsage:Int,
    val collaborators:Int,
    val twoFactorAuthentication:Boolean )
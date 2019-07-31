package com.example.githubtraininappkotlin

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.githubtraininappkotlin.models.GithubRepoEntity

object Util {
    const val IS_LOGED: String = "is_loged"
    const val AUTH_HEADER: String = "authheader"

    fun displayDate(date: String): String {
        return date.substring(0, 10) + " h: " + date.substring(11, 16)
    }
}

fun getPrivacy(isPrivate: Boolean): String {
    if (isPrivate) {
         return "Private"
    } else {
        return "Public"
    }
}

@BindingAdapter("repoName")
fun TextView.setRepoName(item: GithubRepoEntity?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("repoCreated")
fun TextView.setCreatedDate(item: GithubRepoEntity?) {
    item?.let {
        text = Util.displayDate(item.createdAt!!)
    }
}

@BindingAdapter("repoUpdated")
fun TextView.setUpdatedDate(item: GithubRepoEntity?) {
    item?.let {
        text = Util.displayDate(item.updatedAt!!)
    }
}

@BindingAdapter("repoPrivacy")
fun TextView.setRepoPrivacy(item: GithubRepoEntity?) {
    item?.let {
        text = getPrivacy(item.mPrivate)
    }
}
package com.example.githubtraininappkotlin

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.githubtraininappkotlin.models.GithubRepoEntity

object Util {

    const val IS_LOGED: String = "is_loged"
    const val AUTH_HEADER: String = "authheader"
    const val CREATED_AT: String = "created_at"
    const val UPDATED_AT: String = "updated_at"
    const val PUSHED_AT: String = "pushed_at"
    const val FULL_NAME: String = "full_name"
    const val OWNER: String = "owner"
    const val COLLABORATOR: String = "collaborator"
   // const val ORGANIZATION_MEMBER: String = "organization"
    const val ORDER_BY: String = "order_by"

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
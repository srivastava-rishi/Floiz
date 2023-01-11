package com.rsstudio.flobiz.data.network.model

data class Item(
    val accepted_answer_id: Int?=null,
    val answer_count: Int?=null,
    val content_license: String?=null,
    val creation_date: Long?=null,
    val is_answered: Boolean?=null,
    val last_activity_date: Long?=null,
    val last_edit_date: Int?=null,
    val link: String?=null,
    val owner: Owner?=null,
    val question_id: Int?=null,
    val score: Int?=null,
    val tags: List<String>?=null,
    val title: String?=null,
    val view_count: Int?=null,
    val type: Int = 0
)
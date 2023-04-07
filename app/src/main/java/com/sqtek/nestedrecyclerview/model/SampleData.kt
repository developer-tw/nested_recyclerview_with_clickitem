package com.sqtek.nestedrecyclerview.model

import com.sqtek.nestedrecyclerview.R

object SampleData {
    private val courseModels = listOf(
        CourseModel(R.drawable.course1,"https://engoo.com.tw/app/daily-news/article/highlands-islands-cities-the-magic-of-scotland/RuaCPMUKEe2Pj58I1puG_w"),
        CourseModel(R.drawable.course2,"https://engoo.com.tw/app/daily-news/article/study-finds-why-gardening-is-good-for-you/UhFf6LkLEe221f9tVn_GlQ"),
        CourseModel(R.drawable.course3,"https://engoo.com.tw/app/daily-news/article/french-bulldog-becomes-most-popular-us-dog-breed/GyuvJMc0Ee2GvBOw-NIEew"),
        CourseModel(R.drawable.course4,"https://engoo.com.tw/app/daily-news/article/three-year-cruise-visits-135-countries-for-85-a-day/1f2UHMK6Ee2lN59yxslasw"),
        CourseModel(R.drawable.course5,"https://engoo.com.tw/app/daily-news/article/im-terribly-sorry-how-to-apologize-in-english/QY1m7sc0Ee2GLHdMNyUqPg"),
        CourseModel(R.drawable.course6,"https://engoo.com.tw/app/daily-news/article/japanese-scientists-create-mice-with-cells-from-two-males/-FCkIsS5Ee279ouyuxjQkA"),
        CourseModel(R.drawable.course7,"https://engoo.com.tw/app/daily-news/article/googles-ai-chatbot-bard-takes-on-microsofts-chatgpt/zzeQ_MjMEe2Z5Qcx9XxsIw"),
        CourseModel(R.drawable.course8,"https://engoo.com.tw/app/daily-news/article/archaeologists-find-earliest-evidence-of-horse-riding/PLMOHLxdEe2OOytrSUBaAA"),
    )

    val collections = listOf(
        MainModel("All Course", courseModels),
        MainModel("Want to take", courseModels.reversed()),
        MainModel("Popular Course", courseModels.shuffled()),
        MainModel("Top Rated", courseModels),
    )
}
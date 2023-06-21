package com.example.smu_quiz

open class Question (
    open var subject: String,
    open var content: String,
    open var quiznum: Int,
    open var anwser: Int,
    open var like: Boolean,
    open var correct: Boolean,
    open var choice1: String,
    open var choice2: String,
    open var choice3: String,
    open var choice4: String
)
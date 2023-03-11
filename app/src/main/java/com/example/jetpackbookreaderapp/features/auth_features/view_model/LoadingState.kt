package com.example.jetpackbookreaderapp.features.auth_features.view_model

data class LoadingState(val status: Status, val message: String? = null) {

    companion object {
        val SUCCESS = LoadingState(status = Status.SUCCESS)
        val FAILED = LoadingState(status = Status.FAILED)
        val LOADING = LoadingState(status = Status.LOADING)
        val IDLE = LoadingState(status = Status.IDLE)
    }

    enum class Status {
        SUCCESS, FAILED, LOADING, IDLE
    }
}
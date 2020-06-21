package com.line.nab.model

class Resource <out T>(val status : Status,val data : T?, val mess :String?) {
    companion object {
        fun <T> success (data : T? ) : Resource<T> {
            return Resource(Status.SUCCESS,data, null)
        }

        fun <T> error (data : T?, mess : String?) : Resource<T> {
            return Resource(Status.ERROR,data,mess)
        }

        fun <T> loading (data : T? ) : Resource<T> {
            return Resource(Status.LOADING,data, null)
        }
    }
}
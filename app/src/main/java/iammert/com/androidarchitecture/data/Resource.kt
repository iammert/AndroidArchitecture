package iammert.com.androidarchitecture.data

data class Resource<out T>(
    @JvmField val status: Status,
    @JvmField val data: T?,
    @JvmField val message: String?
) {
    companion object {
        @JvmStatic
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data, null)

        @JvmStatic
        fun <T> error(message: String?, data: T?): Resource<T> = Resource(Status.ERROR, data, message)

        @JvmStatic
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }
}

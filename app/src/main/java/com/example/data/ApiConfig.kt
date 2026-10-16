package com.example.data

/**
 * Configuration for the Hatifi External Phone Specs Data Connection.
 * The endpoint URL can be swapped directly here, or changed at runtime in the app.
 */
object ApiConfig {

    /**
     * Default remote data source URL for phone specifications.
     * Easily swapped here with any alternative REST/JSON endpoint.
     */
    const val DEFAULT_API_URL = "https://raw.githubusercontent.com/aztechno4/hatifi-phones-api/main/phones.json"

    /**
     * Periodic auto-refresh interval (24 hours in milliseconds).
     */
    const val REFRESH_INTERVAL_MILLIS = 24 * 60 * 60 * 1000L

    /**
     * Network request timeout in seconds.
     */
    const val NETWORK_TIMEOUT_SECONDS = 10L

    /**
     * TikTok Owner Account details.
     */
    const val TIKTOK_HANDLE = "@az.techno4"
    const val TIKTOK_URL = "https://www.tiktok.com/@az.techno4"
}

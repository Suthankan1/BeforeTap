package com.suthankan.beforetap.domain

import java.net.URI
import java.net.URISyntaxException

data class LocalWebLink(
    val scheme: String,
    val host: String,
    val port: Int?,
    val hasUserInfo: Boolean
) {
    companion object {
        private val domain = Regex(
            "[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?" +
                "(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)*\\.?"
        )

        fun parse(input: String): LocalWebLink? {
            val text = input.trim(' ', '\t', '\r', '\n')
            if (text.length > 8_192 || text.any { it !in '!'..'~' || it == '\\' }) return null
            val uri = try {
                URI(text)
            } catch (_: URISyntaxException) {
                return null
            }

            val scheme = uri.scheme?.lowercase()
            if (scheme != "http" && scheme != "https") return null
            val authority = uri.rawAuthority ?: return null
            if (authority.endsWith(':') || authority.count { it == '@' } > 1) return null
            val host = uri.host?.lowercase() ?: return null
            if (!validHost(host)) return null

            val port = uri.port.takeUnless { it == -1 }
            if (port != null && port !in 1..65_535) return null
            return LocalWebLink(scheme, host, port, uri.rawUserInfo != null)
        }

        private fun validHost(host: String): Boolean {
            // URI validates the IPv6 literal. Scoped IPv6 names are outside this parser.
            if (host.startsWith('[') && host.endsWith(']')) return '%' !in host
            if (host.all { it in '0'..'9' || it == '.' }) {
                val octets = host.split('.')
                return octets.size == 4 && octets.all { octet ->
                    val value = octet.toIntOrNull()
                    value != null && value in 0..255 && value.toString() == octet
                }
            }
            return host.length <= 254 && domain.matches(host)
        }
    }
}

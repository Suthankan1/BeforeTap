import Darwin
import Foundation

struct LocalWebLink: Equatable {
    let scheme: String
    let host: String
    let port: Int?
    let hasUserInfo: Bool

    static func parse(_ input: String) -> Self? {
        let text = input.trimmingCharacters(in: CharacterSet(charactersIn: " \t\r\n"))
        guard text.utf8.count <= 8_192,
              text.utf8.allSatisfy({ (33...126).contains($0) && $0 != 92 }),
              let separator = text.range(of: "://"),
              let parts = URLComponents(string: text, encodingInvalidCharacters: false),
              let scheme = parts.scheme?.lowercased(), scheme == "http" || scheme == "https",
              let encodedHost = parts.encodedHost, !encodedHost.contains("%") else { return nil }

        let authority = text[separator.upperBound...].prefix { !"/?#".contains($0) }
        guard !authority.hasSuffix(":"), authority.filter({ $0 == "@" }).count <= 1 else {
            return nil
        }

        let host = encodedHost.lowercased()
        guard validHost(host), parts.port.map({ (1...65_535).contains($0) }) ?? true else {
            return nil
        }

        return Self(scheme: scheme, host: host, port: parts.port, hasUserInfo: authority.contains("@"))
    }

    private static func validHost(_ host: String) -> Bool {
        if host.hasPrefix("["), host.hasSuffix("]") {
            var address = in6_addr()
            return String(host.dropFirst().dropLast()).withCString {
                inet_pton(AF_INET6, $0, &address) == 1
            }
        }
        if host.utf8.allSatisfy({ (48...57).contains($0) || $0 == 46 }) {
            let octets = host.split(separator: ".", omittingEmptySubsequences: false)
            return octets.count == 4 && octets.allSatisfy { octet in
                guard let value = UInt8(octet) else { return false }
                return String(value) == octet
            }
        }
        let domain = #/^[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?(?:\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)*\.?$/#
        return host.utf8.count <= 254 && host.wholeMatch(of: domain) != nil
    }
}

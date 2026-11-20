import Foundation

enum Verdict: String, Codable, CaseIterable {
    case knownMalicious = "KNOWN_MALICIOUS"
    case suspicious = "SUSPICIOUS"
    case noKnownThreats = "NO_KNOWN_THREATS"
    case unableToVerify = "UNABLE_TO_VERIFY"

    var title: String {
        switch self {
        case .knownMalicious: "Known malicious"
        case .suspicious: "Suspicious"
        case .noKnownThreats: "No known threats found"
        case .unableToVerify: "Unable to verify"
        }
    }
}

struct Evidence: Identifiable, Codable {
    let id: String
    let title: String
    let explanation: String
    let source: String
}

struct VerificationResult: Identifiable {
    let id: UUID
    let displayHost: String
    let verdict: Verdict
    let checkedAt: Date
    let evidence: [Evidence]
    let limitations: [String]
    let isDemo: Bool
}

enum DemoFixture {
    static let suspicious = VerificationResult(
        id: UUID(),
        displayHost: "verify-login.example",
        verdict: .suspicious,
        checkedAt: .now,
        evidence: [
            Evidence(id: "unfamiliar-domain",
                     title: "Unfamiliar domain",
                     explanation: "The visible brand and the destination are not the same.",
                     source: "Illustrative local signal")
        ],
        limitations: ["No live threat-intelligence lookup was performed."],
        isDemo: true
    )
}

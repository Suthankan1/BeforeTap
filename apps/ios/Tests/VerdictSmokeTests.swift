import Foundation

@main
struct VerdictSmokeTests {
    static func main() throws {
        let codes = ["KNOWN_MALICIOUS", "SUSPICIOUS", "NO_KNOWN_THREATS", "UNABLE_TO_VERIFY"]
        precondition(Set(Verdict.allCases.map(\.rawValue)) == Set(codes), "Unexpected verdict set")

        for code in codes {
            let data = Data("\"\(code)\"".utf8)
            let verdict = try JSONDecoder().decode(Verdict.self, from: data)
            let encoded = try JSONEncoder().encode(verdict)
            precondition(verdict.rawValue == code && encoded == data, "Verdict round trip failed")
        }

        let result = DemoFixture.suspicious
        precondition(result.verdict == .suspicious && result.isDemo, "Unexpected demo verdict")
        precondition(result.displayHost.hasSuffix(".example"), "Expected a fictional demo host")
        precondition(result.limitations.contains { $0.contains("No live") }, "Missing demo limitation")
        print("Passed: four verdict round trips and demo provenance")
    }
}

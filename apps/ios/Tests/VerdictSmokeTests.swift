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
        try checkFixtureDecoding()
        print("Passed: verdict round trips, demo provenance, and fixture decoding")
    }

    private static func checkFixtureDecoding() throws {
        let fixtures = URL(fileURLWithPath: "fixtures/results", isDirectory: true)
        let expectations: [(String, Verdict)] = [
            ("known-malicious", .knownMalicious),
            ("suspicious", .suspicious),
            ("no-known-threats", .noKnownThreats),
            ("unable-to-verify", .unableToVerify)
        ]

        for (filename, verdict) in expectations {
            let data = try Data(contentsOf: fixtures.appendingPathComponent("\(filename).demo.json"))
            let result = try DemoResultFixture.decode(data)
            precondition(result.verdict == verdict)
            precondition(result.displayHost.hasSuffix(".example"))
            precondition(result.checkedAt.formatted(.iso8601) == "2026-11-20T00:00:00Z")
            precondition(!result.limitations.isEmpty)
            precondition(!result.checks.isEmpty)
            if verdict == .unableToVerify {
                precondition(result.checks.first?.reason == "Provider timeout")
            }
            if verdict == .knownMalicious {
                precondition(result.evidence.first?.source == "Fictional fixture")
            }
        }

        let suspicious = try Data(contentsOf: fixtures.appendingPathComponent("suspicious.demo.json"))
        let json = try JSONSerialization.jsonObject(with: suspicious)
        guard var fields = json as? [String: Any] else { preconditionFailure("Expected JSON object") }
        fields["verdict"] = "FUTURE_VERDICT"
        let future = try DemoResultFixture.decode(JSONSerialization.data(withJSONObject: fields))
        precondition(future.verdict == .unableToVerify)

        fields["fixture"] = false
        do {
            _ = try DemoResultFixture.decode(JSONSerialization.data(withJSONObject: fields))
            preconditionFailure("Expected the decoder to reject a non-demo result")
        } catch DecodingError.dataCorrupted {
            return
        }
    }
}

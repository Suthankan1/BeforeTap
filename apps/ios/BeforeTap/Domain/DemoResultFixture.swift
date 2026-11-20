import Foundation

struct DemoResultFixture: Decodable {
    struct Evidence: Decodable {
        let code: String
        let severity: String
        let explanation: String
        let source: String
    }

    struct Check: Decodable {
        let name: String
        let state: String
        let reason: String?
    }

    let displayHost: String
    let verdict: Verdict
    let checkedAt: Date
    let evidence: [Evidence]
    let checks: [Check]
    let limitations: [String]

    private enum CodingKeys: String, CodingKey {
        case fixture, displayHost, verdict, checkedAt, evidence, checks, limitations
    }

    init(from decoder: Decoder) throws {
        let fields = try decoder.container(keyedBy: CodingKeys.self)
        guard try fields.decode(Bool.self, forKey: .fixture) else {
            throw DecodingError.dataCorruptedError(
                forKey: .fixture, in: fields, debugDescription: "Expected a demo fixture."
            )
        }
        displayHost = try fields.decode(String.self, forKey: .displayHost)
        let verdictCode = try fields.decode(String.self, forKey: .verdict)
        verdict = Verdict(rawValue: verdictCode) ?? .unableToVerify
        checkedAt = try fields.decode(Date.self, forKey: .checkedAt)
        evidence = try fields.decode([Evidence].self, forKey: .evidence)
        checks = try fields.decode([Check].self, forKey: .checks)
        limitations = try fields.decode([String].self, forKey: .limitations)
    }

    static func decode(_ data: Data) throws -> Self {
        let decoder = JSONDecoder()
        decoder.dateDecodingStrategy = .iso8601
        return try decoder.decode(Self.self, from: data)
    }
}

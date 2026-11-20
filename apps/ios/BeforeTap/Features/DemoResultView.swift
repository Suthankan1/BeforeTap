import SwiftUI

struct DemoResultView: View {
    let result: VerificationResult
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 18) {
                Label("DEMO — NO LIVE SCAN", systemImage: "info.circle")
                    .font(.caption.bold())
                    .foregroundStyle(.orange)

                Text(result.verdict.title)
                    .font(.largeTitle.bold())
                Text(result.displayHost)
                    .foregroundStyle(.secondary)

                ForEach(result.evidence) { item in
                    VStack(alignment: .leading, spacing: 6) {
                        Text(item.title).font(.headline)
                        Text(item.explanation)
                        Text("Source: \(item.source)")
                            .font(.caption)
                            .foregroundStyle(.secondary)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding()
                    .background(.orange.opacity(0.1), in: RoundedRectangle(cornerRadius: 20))
                }

                ForEach(result.limitations, id: \.self) { limitation in
                    Text(limitation).foregroundStyle(.secondary)
                }

                Text("Verify independently through the organization's official app or a website you enter yourself.")
                    .font(.headline)
            }
            .padding(20)
        }
        .navigationTitle("Example evidence")
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button("Close") { dismiss() }
            }
        }
    }
}

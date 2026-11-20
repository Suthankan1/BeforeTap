import SwiftUI

struct HistoryPlaceholderView: View {
    var body: some View {
        ContentUnavailableView(
            "No saved checks",
            systemImage: "clock",
            description: Text("History is not implemented in this starter.")
        )
    }
}

struct SettingsPlaceholderView: View {
    var body: some View {
        NavigationStack {
            Form {
                Section("Privacy") {
                    LabeledContent("Online reputation checks", value: "Not connected")
                    LabeledContent("History", value: "Not enabled")
                }
                Section {
                    Text("This is a design and development starter, not an operational scanner.")
                        .foregroundStyle(.secondary)
                }
            }
            .navigationTitle("Settings")
        }
    }
}

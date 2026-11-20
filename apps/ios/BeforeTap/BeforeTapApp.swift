import SwiftUI

@main
struct BeforeTapApp: App {
    var body: some Scene {
        WindowGroup {
            RootView()
        }
    }
}

private struct RootView: View {
    var body: some View {
        TabView {
            CheckHomeView()
                .tabItem { Label("Check", systemImage: "checkmark.shield") }
            HistoryPlaceholderView()
                .tabItem { Label("History", systemImage: "clock.arrow.circlepath") }
            SettingsPlaceholderView()
                .tabItem { Label("Settings", systemImage: "gearshape") }
        }
        .tint(.teal)
    }
}

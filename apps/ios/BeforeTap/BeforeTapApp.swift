import SwiftUI

@main
struct BeforeTapApp: App {
    @AppStorage("hasOnboarded") private var hasOnboarded = false
    var body: some Scene {
        WindowGroup {
            if hasOnboarded {
                RootView()
            } else {
                OnboardingView { hasOnboarded = true }
            }
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

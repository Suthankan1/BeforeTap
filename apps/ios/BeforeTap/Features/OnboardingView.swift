import SwiftUI

struct OnboardingView: View {
    let onComplete: () -> Void

    @State private var showsPrivacy = false

    private let brand = Color("BeforeTapBrand")
    private let hero = Color("OnboardingHero")
    private let heroInk = Color("OnboardingInk")

    var body: some View {
        NavigationStack {
            page(
                title: "A pause can protect you.",
                subtitle: "Check before you click, pay or share information.",
                actionTitle: "Get started",
                action: { showsPrivacy = true }
            ) {
                VStack(alignment: .leading, spacing: 14) {
                    HStack(spacing: 12) {
                        artwork("OnboardingShield", size: 46)
                        VStack(alignment: .leading, spacing: 4) {
                            Text("BeforeTap").font(.largeTitle.bold())
                            Text("Pause. Check. Act.").font(.subheadline.weight(.medium))
                        }
                    }
                    Text("Understand suspicious links, messages and QR codes without guessing.")
                        .font(.body)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(22)
                .foregroundStyle(heroInk)
                .background(hero, in: .rect(cornerRadius: 23))

                promise(
                    "Know the warning signs",
                    detail: "Clear explanations, without scores.",
                    mark: "OnboardingWelcomeCheck"
                )
                promise(
                    "Keep control",
                    detail: "No automatic opening or payment.",
                    mark: "OnboardingWelcomeCheck"
                )
            }
            .toolbarVisibility(.hidden, for: .navigationBar)
            .navigationDestination(isPresented: $showsPrivacy) {
                page(
                    title: "Your messages stay yours.",
                    subtitle: "BeforeTap puts your privacy in control, right from the first check.",
                    actionTitle: "Continue",
                    action: onComplete
                ) {
                    VStack(alignment: .leading, spacing: 12) {
                        HStack(spacing: 12) {
                            artwork("OnboardingLock", size: 34)
                            Text("Private by design").font(.headline)
                        }
                        Text("Drafts stay on this device. BeforeTap uploads nothing in this demo.")
                            .font(.body)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(19)
                    .foregroundStyle(heroInk)
                    .background(hero, in: .rect(cornerRadius: 23))

                    promise(
                        "Online checks are optional",
                        detail: "BeforeTap will ask before sending a URL.",
                        mark: "OnboardingPrivacyCheck"
                    )
                    promise(
                        "No hidden tracking",
                        detail: "This demo keeps no check history.",
                        mark: "OnboardingPrivacyCheck"
                    )
                }
                .toolbarVisibility(.visible, for: .navigationBar)
                .navigationTitle("Privacy")
                .navigationBarTitleDisplayMode(.inline)
            }
        }
    }

    private func page<Content: View>(
        title: String,
        subtitle: String,
        actionTitle: String,
        action: @escaping () -> Void,
        @ViewBuilder content: () -> Content
    ) -> some View {
        VStack(spacing: 16) {
            ScrollView {
                VStack(alignment: .leading, spacing: 15) {
                    Text("BEFORETAP")
                        .font(.caption2.weight(.medium))
                        .foregroundStyle(brand)
                    Text(title)
                        .font(.largeTitle.bold())
                        .accessibilityAddTraits(.isHeader)
                    Text(subtitle)
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                    content()
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.top, 16)
            }
            Button(action: action) {
                Text(actionTitle)
                    .font(.headline)
                    .frame(maxWidth: .infinity, minHeight: 44)
            }
            .controlSize(.large)
            .buttonBorderShape(.roundedRectangle(radius: 23))
            .buttonStyle(.glassProminent)
            .tint(brand)
        }
        .padding(.horizontal, 25)
        .padding(.bottom, 10)
        .background(Color("OnboardingBackground").ignoresSafeArea())
    }

    private func promise(_ title: String, detail: String, mark: String) -> some View {
        HStack(spacing: 12) {
            artwork(mark, size: 20)
            VStack(alignment: .leading, spacing: 6) {
                Text(title).font(.subheadline.weight(.medium))
                Text(detail).font(.caption).foregroundStyle(.secondary)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(15)
        .background(Color(uiColor: .secondarySystemGroupedBackground), in: .rect(cornerRadius: 21))
    }

    private func artwork(_ name: String, size: CGFloat) -> some View {
        Image(name)
            .resizable()
            .scaledToFit()
            .frame(width: size, height: size)
            .accessibilityHidden(true)
    }
}

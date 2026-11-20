import SwiftUI

struct CheckHomeView: View {
    @State private var input = ""
    @State private var showDemo = false

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 18) {
                    Text("Check before you tap.")
                        .font(.largeTitle.bold())
                    Text("A suspicious link or message? Understand the warning signs first.")
                        .foregroundStyle(.secondary)

                    Label("A little pause. A lot more clarity.", systemImage: "checkmark.shield")
                        .font(.title3.bold())
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(20)
                        .background(.teal.opacity(0.12), in: RoundedRectangle(cornerRadius: 24))

                    TextField("Paste a URL or message", text: $input, axis: .vertical)
                        .lineLimit(2...4)
                        .textInputAutocapitalization(.never)
                        .autocorrectionDisabled()
                        .padding()
                        .background(.regularMaterial, in: RoundedRectangle(cornerRadius: 18))

                    Label("Screenshot import — planned", systemImage: "photo")
                        .foregroundStyle(.secondary)
                    Label("QR scanner — planned", systemImage: "qrcode.viewfinder")
                        .foregroundStyle(.secondary)

                    Button {
                        showDemo = true
                    } label: {
                        Text("Explore a demo result")
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 12)
                    }
                    .buttonStyle(.borderedProminent)
                    .tint(.teal)

                    Text("DEMO ONLY — this button does not check your input or contact a security provider.")
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                }
                .padding(20)
            }
            .navigationTitle("BeforeTap")
            .navigationBarTitleDisplayMode(.inline)
            .sheet(isPresented: $showDemo) {
                NavigationStack {
                    DemoResultView(result: DemoFixture.suspicious)
                }
            }
        }
    }
}

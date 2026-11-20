import XCTest

final class DemoFlowTests: XCTestCase {
    @MainActor
    func testDemoResultIsLabeledAndDismissible() {
        let app = XCUIApplication()
        app.launch()

        let demoButton = app.buttons["Explore a demo result"]
        XCTAssertTrue(demoButton.waitForExistence(timeout: 5))
        demoButton.tap()

        XCTAssertTrue(app.staticTexts["DEMO — NO LIVE SCAN"].waitForExistence(timeout: 5))
        XCTAssertTrue(app.staticTexts["Suspicious"].exists)
        XCTAssertTrue(app.staticTexts["verify-login.example"].exists)
        app.buttons["Close"].tap()

        XCTAssertTrue(demoButton.waitForExistence(timeout: 5))
    }
}

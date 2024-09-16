import SwiftUI
import shared
@main
struct iOSApp: App {
    init() {
        CharactersDiKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

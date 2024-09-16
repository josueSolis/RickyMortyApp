import SwiftUI
struct ContentView: View {
    @StateObject private var coordinator = MainCoordinator()

	var body: some View {
        NavigationStack(path: $coordinator.path) {
            coordinator.build(coordinator.currentPage)
                .navigationDestination(for: Page.self) { page in
                    coordinator.build(page)
                }.sheet(item: $coordinator.sheet) { item in
                    coordinator.buildSheet( item)
                }.navigationTitle("Home")
                .toolbarBackground(.blue, for: .navigationBar, .tabBar)
                .toolbarBackground(.visible, for: .navigationBar, .tabBar)
        }
        .environmentObject(coordinator)
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

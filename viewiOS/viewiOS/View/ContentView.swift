import SwiftUI
import core

struct ContentView: View {
    
    @ObservedObject var vm = LeleVM()
    
	var body: some View {
        ZStack {
            let currentState = vm.currentState
            if currentState.isLoading {
                ProgressView()
            } else if currentState.hasNoData {
                Text("No Data Available")
            } else if !currentState.error.isEmpty {
                Text(currentState.error)
            } else {
                List(currentState.data, id: \.id) { item in
                    Text(item.name)
                }
            }
        }
        .onAppear {
            vm.startObserving()
        }
        .onDisappear {
            vm.dispose()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

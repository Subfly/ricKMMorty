//
//  LocationsVM.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

@MainActor
class LocationsVM: ObservableObject {
    
    private let stateMachine: LocationListStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: LocationListState = LocationListState(
        isLoading: true,
        error: "",
        data: []
    )
    
    init() {
        self.stateMachine = LocationListStateMachine(scope: nil)
    }
    
    func startObserving() {
        self.handle = self.stateMachine.currentState.subscribe { [weak self] state in
            if let state = state {
                self?.currentState = state
            }
        }
    }
    
    func dispose() {
        self.handle?.dispose()
    }
    
    func onEvent(event: LocationListEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
    func handleLoadMore(
        currentItemIndex: Int
    ) {
        DispatchQueue.main.async {
            if (currentItemIndex == self.currentState.data.count - 5) {
                self.stateMachine.onEvent(
                    event: LocationListEvent.LoadMore()
                )
            }
        }
    }
    
}

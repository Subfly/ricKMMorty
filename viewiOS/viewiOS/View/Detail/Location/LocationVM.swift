//
//  LocationVM.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

@MainActor
class LocationVM: ObservableObject {
    
    private let stateMachine: LocationStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: LocationState = LocationState(
        isLoading: true,
        error: "",
        data: nil
    )
    
    init() {
        self.stateMachine = LocationStateMachine(scope: nil)
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
    
    func onEvent(event: LocationEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
}

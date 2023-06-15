//
//  LeleVM.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 6.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

@MainActor
class LeleVM: ObservableObject {
    private let stateMachine: CharacterListStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: CharacterListState = CharacterListState(
        isLoading: true,
        error: "",
        data: []
    )
    
    init() {
        self.stateMachine = CharacterListStateMachine(scope: nil)
    }
    
    func startObserving() {
        stateMachine.currentState.subscribe { state in
            if let state = state {
                self.currentState = state
            }
        }
    }
    
    func dispose() {
        handle?.dispose()
    }
    
}

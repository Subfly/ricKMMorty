//
//  CharacterVM.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

@MainActor
class CharacterVM: ObservableObject {
    
    private let stateMachine: CharacterStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: CharacterState = CharacterState(
        isLoading: true,
        error: "",
        data: nil
    )
    
    init() {
        self.stateMachine = CharacterStateMachine(scope: nil)
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
    
    func onEvent(event: CharacterEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
}

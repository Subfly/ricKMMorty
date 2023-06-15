//
//  EpisodeVM.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

@MainActor
class EpisodeVM: ObservableObject {
    
    private let stateMachine: EpisodeStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: EpisodeState = EpisodeState(
        isLoading: true,
        error: "",
        data: nil
    )
    
    init() {
        self.stateMachine = EpisodeStateMachine(scope: nil)
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
    
    func onEvent(event: EpisodeEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
}

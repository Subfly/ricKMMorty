//
//  EpisodesVM.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

@MainActor
class EpisodesVM: ObservableObject {
    
    private let stateMachine: EpisodeListStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: EpisodeListState = EpisodeListState(
        isLoading: true,
        error: "",
        data: []
    )
    
    init() {
        self.stateMachine = EpisodeListStateMachine(scope: nil)
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
    
    func onEvent(event: EpisodeListEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
    func handleLoadMore(
        currentItemIndex: Int
    ) {
        DispatchQueue.main.async {
            if (currentItemIndex == self.currentState.data.count - 5) {
                self.stateMachine.onEvent(
                    event: EpisodeListEvent.LoadMore()
                )
            }
        }
    }
    
}

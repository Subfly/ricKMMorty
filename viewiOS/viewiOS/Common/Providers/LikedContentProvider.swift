//
//  LikedContentProvider.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

class LikedContentProvider : ObservableObject {
    
    private let stateMachine: LikedContentStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState = LikedContentState(
        isLoading: true,
        likedCharacters: [],
        likedCharacterIds: [],
        likedEpisodes: [],
        likedEpisodeIds: [],
        likedLocations: [],
        likedLocationIds: []
    )
    
    init() {
        self.stateMachine = LikedContentStateMachine(scope: nil)
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
    
    func onEvent(event: LikedContentEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
}

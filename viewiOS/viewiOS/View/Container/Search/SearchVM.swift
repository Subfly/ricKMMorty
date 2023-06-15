//
//  SearchVM.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 13.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

@MainActor
class SearchVM: ObservableObject {
    
    private let stateMachine: SearchStateMachine
    private var uiStateHandle: DisposableHandle?
    private var filterStatetHandle: DisposableHandle?
    private var queryStateHandle: DisposableHandle?
    
    @Published
    var uiState: SearchState = SearchState(
        isLoading: false,
        error: "",
        searchedCharacters: [],
        searchedEpisodes: [],
        searchedLocations: []
    )
    
    @Published
    var filterState: SearchFilterState = SearchFilterState(
        appliedFilter: ContentFilter.character,
        characterFilterOptions: CharacterFilterOptions(
            status: CharacterStatus.none,
            gender: CharacterGender.none,
            searchOn: CharacterQueryAcceptField.name
        ),
        episodeFilterOptions: EpisodeFilterOptions(
            searchOn: EpisodeQueryAcceptField.name
        ),
        locationFilterOptions: LocationFilterOptions(
            searchOn: LocationQueryAcceptField.name
        )
    )
    
    @Published
    var queryState: String = ""
    
    init() {
        self.stateMachine = SearchStateMachine(scope: nil)
    }
    
    func startObserving() {
        self.uiStateHandle = self.stateMachine.state.subscribe { [weak self] state in
            if let state = state {
                print(state)
                self?.uiState = state
            }
        }
        self.filterStatetHandle = self.stateMachine.filterState.subscribe { [weak self] state in
            if let state = state {
                self?.filterState = state
            }
        }
        self.queryStateHandle = self.stateMachine.queryState.subscribe { [weak self] state in
            if let state = state {
                self?.queryState = state as String
            }
        }
    }
    
    func dispose() {
        self.uiStateHandle?.dispose()
        self.filterStatetHandle?.dispose()
        self.queryStateHandle?.dispose()
    }
    
    func onEvent(event: SearchEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
    func handleLoadMore(
        currentItemIndex: Int
    ) {
        DispatchQueue.main.async {
            switch self.filterState.appliedFilter {
            case .character:
                if (currentItemIndex == self.uiState.searchedCharacters.count - 5) {
                    self.stateMachine.onEvent(
                        event: SearchEvent.LoadMore()
                    )
                }
                break;
            case .episode:
                if (currentItemIndex == self.uiState.searchedEpisodes.count - 5) {
                    self.stateMachine.onEvent(
                        event: SearchEvent.LoadMore()
                    )
                }
                break;
            case .location:
                if (currentItemIndex == self.uiState.searchedLocations.count - 5) {
                    self.stateMachine.onEvent(
                        event: SearchEvent.LoadMore()
                    )
                }
                break;
            default: break;
            }
        }
    }
    
}


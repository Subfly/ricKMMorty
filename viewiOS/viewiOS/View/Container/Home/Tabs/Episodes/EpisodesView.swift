//
//  EpisodesView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct EpisodesView: View {
    
    @ObservedObject
    var vm = EpisodesVM()
    
    var body: some View {
        ZStack {
            let state = vm.currentState
            
            if (state.isLoading && state.data.isEmpty) {
                ProgressView()
            }
            
            if (!state.error.isEmpty) {
                Text(state.error)
            } else {
                List(
                    Array(state.data.enumerated()),
                    id: \.element.id
                ) { index, episode in
                    NavigationLink {
                        EpisodeView(
                            episodeId: episode.id,
                            episodeTitle: episode.name
                        )
                    } label: {
                        EpisodeListItem(model: episode)
                            .onAppear {
                                vm.handleLoadMore(
                                    currentItemIndex: index
                                )
                            }
                    }.buttonStyle(.plain)
                }
            }
            
        }
        .navigationTitle("Episodes")
        .navigationBarTitleDisplayMode(.large)
        .onAppear {
            vm.startObserving()
        }
        .onDisappear {
            vm.dispose()
        }
    }
}

struct EpisodesView_Previews: PreviewProvider {
    static var previews: some View {
        EpisodesView()
    }
}

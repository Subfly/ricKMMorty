//
//  EpisodeView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct EpisodeView: View {
    
    let episodeId: Int32
    let episodeTitle: String
    
    @ObservedObject
    var vm = EpisodeVM()
    
    @EnvironmentObject
    var likedContentProvider: LikedContentProvider
    
    var body: some View {
        ZStack {
            let state = vm.currentState
            
            if (state.isLoading) {
                ProgressView()
            }
            
            if (!state.error.isEmpty) {
                Text(state.error)
            } else {
                if let model = vm.currentState.data {
                    List {
                        Section {
                            if !model.episode.isEmpty {
                                HStack {
                                    HStack {
                                        Image(systemName: "tag")
                                            .resizable()
                                            .scaledToFit()
                                            .frame(
                                                width: 24,
                                                height: 24,
                                                alignment: .center
                                            )
                                        Text("Episode")
                                    }
                                    Spacer()
                                    EpisodeTagContainer(
                                        episode: model.episode
                                    )
                                }
                            }
                            if !model.airDate.isEmpty {
                                HStack {
                                    HStack {
                                        Image(systemName: "calendar")
                                            .resizable()
                                            .scaledToFit()
                                            .frame(
                                                width: 24,
                                                height: 24,
                                                alignment: .center
                                            )
                                        Text("Air Date")
                                    }
                                    Spacer()
                                    Text(model.airDate)
                                }
                            }
                        } header: {
                            HStack {
                                Text("Episode Info")
                                Spacer()
                                Image(systemName: "info.circle")
                            }
                        }
                        
                        Section {
                            ForEach(model.characters, id: \.id) { character in
                                NavigationLink {
                                    CharacterView(
                                        characterId: character.id,
                                        characterName: character.name
                                    )
                                } label: {
                                    CharacterListItem(model: character)
                                }.buttonStyle(.plain)
                            }
                        } header: {
                            HStack {
                                Text("Characters")
                                Spacer()
                                Image(systemName: "person.3.fill")
                            }
                        }


                    }
                }
            }
            
        }
        .navigationTitle(episodeTitle)
        .navigationBarTitleDisplayMode(.large)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    let isLiked = likedContentProvider.currentState.likedEpisodeIds.contains(
                        KotlinInt(value: episodeId)
                    )
                    
                    if isLiked {
                        vm.onEvent(
                            event: EpisodeEvent.DislikeEpisode()
                        )
                    } else {
                        vm.onEvent(
                            event: EpisodeEvent.LikeEpisode()
                        )
                    }
                    
                } label: {
                    Image(
                        systemName: (
                            likedContentProvider.currentState.likedEpisodeIds.contains(
                                KotlinInt(value: episodeId)
                            )
                        ) ? "heart.fill" : "heart"
                    )
                }
            }
        }
        .onAppear {
            vm.startObserving()
            vm.onEvent(
                event: EpisodeEvent.InitWithId(id: episodeId)
            )
        }
        .onDisappear {
            vm.dispose()
        }
    }
}

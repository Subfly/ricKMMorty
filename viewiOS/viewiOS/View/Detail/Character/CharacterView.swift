//
//  CharacterView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct CharacterView: View {
    
    let characterId: Int32
    let characterName: String
    
    @ObservedObject
    var vm = CharacterVM()
    
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
                        RicKMMortyImageView(
                            imageUrl: model.imageUrl,
                            placeholderIconName: "photo"
                        )
                        .cornerRadius(8)
                        .listRowBackground(EmptyView())
                        .listRowInsets(EdgeInsets())
                        Section {
                            CharacterInformationContainerView(
                                species: model.species,
                                gender: model.gender,
                                status: model.status,
                                type: model.type
                            )
                        } header: {
                            HStack {
                                Text("Character Info")
                                Spacer()
                                Image(systemName: "info.circle")
                            }
                        }
                        
                        if let origin = model.originLocation {
                            Section {
                                NavigationLink {
                                    LocationView(
                                        locationId: origin.id,
                                        locationName: origin.name
                                    )
                                } label: {
                                    LocationListItem(model: origin)
                                }.buttonStyle(.plain)

                            } header: {
                                HStack {
                                    Text("Birth Place")
                                    Spacer()
                                    Image(systemName: "figure.and.child.holdinghands")
                                }
                            }
                        }
                        
                        if let lastLocation = model.lastLocation {
                            Section {
                                NavigationLink {
                                    LocationView(
                                        locationId: lastLocation.id,
                                        locationName: lastLocation.name
                                    )
                                } label: {
                                    LocationListItem(model: lastLocation)
                                }.buttonStyle(.plain)
                            } header: {
                                HStack {
                                    Text("Last Seen Location")
                                    Spacer()
                                    Image(systemName: "location")
                                }
                            }
                        }
                        
                        Section {
                            ForEach(model.episodes, id: \.id) { episode in
                                NavigationLink {
                                    EpisodeView(
                                        episodeId: episode.id,
                                        episodeTitle: episode.name
                                    )
                                } label: {
                                    EpisodeListItem(model: episode)
                                }.buttonStyle(.plain)

                            }
                        } header: {
                            HStack {
                                Text("Episodes")
                                Spacer()
                                Image(systemName: "play.tv")
                            }
                        }


                    }
                }
            }
            
        }
        .navigationTitle(characterName)
        .navigationBarTitleDisplayMode(.large)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    let isLiked = likedContentProvider.currentState.likedCharacterIds.contains(
                        KotlinInt(value: characterId)
                    )
                    
                    if isLiked {
                        vm.onEvent(
                            event: CharacterEvent.DislikeCharacter()
                        )
                    } else {
                        vm.onEvent(
                            event: CharacterEvent.LikeCharacter()
                        )
                    }
                    
                } label: {
                    Image(
                        systemName: (
                            likedContentProvider.currentState.likedCharacterIds.contains(
                                KotlinInt(value: characterId)
                            )
                        ) ? "heart.fill" : "heart"
                    )
                }
            }
        }
        .onAppear {
            vm.startObserving()
            vm.onEvent(
                event: CharacterEvent.InitWithId(id: characterId)
            )
        }
        .onDisappear {
            vm.dispose()
        }
        
    }
}

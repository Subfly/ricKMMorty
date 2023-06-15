//
//  LocationView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct LocationView: View {
    
    let locationId: Int32
    let locationName: String
    
    @ObservedObject
    var vm = LocationVM()
    
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
                            if !model.dimension.isEmpty {
                                HStack {
                                    HStack {
                                        Image(systemName: "cube.transparent")
                                            .resizable()
                                            .scaledToFit()
                                            .frame(
                                                width: 24,
                                                height: 24,
                                                alignment: .center
                                            )
                                        Text("Dimension")
                                    }
                                    Spacer()
                                    Text(model.dimension)
                                }
                            }
                            if !model.type.isEmpty {
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
                                        Text("Type")
                                    }
                                    Spacer()
                                    Text(model.type)
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
                            ForEach(model.residents, id: \.id) { character in
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
        .navigationTitle(locationName)
        .navigationBarTitleDisplayMode(.large)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    let isLiked = likedContentProvider.currentState.likedLocationIds.contains(
                        KotlinInt(value: locationId)
                    )
                    
                    if isLiked {
                        vm.onEvent(
                            event: LocationEvent.DislikeLocation()
                        )
                    } else {
                        vm.onEvent(
                            event: LocationEvent.LikeLocation()
                        )
                    }
                    
                } label: {
                    Image(
                        systemName: (
                            likedContentProvider.currentState.likedLocationIds.contains(
                                KotlinInt(value: locationId)
                            )
                        ) ? "heart.fill" : "heart"
                    )
                }
            }
        }
        .onAppear {
            vm.startObserving()
            vm.onEvent(
                event: LocationEvent.InitWithId(id: locationId)
            )
        }
        .onDisappear {
            vm.dispose()
        }
    }
}

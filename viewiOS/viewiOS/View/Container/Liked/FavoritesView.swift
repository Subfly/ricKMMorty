//
//  FavoritesView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct FavoritesView: View {
    
    @EnvironmentObject
    var likedContentProvider: LikedContentProvider
    
    @ObservedObject
    var vm = FavoritesVM()
    
    @State
    var showWubbaLubbaDubDub = false
    
    @State
    var showFilterOptions = false
    
    var body: some View {
        NavigationView {
            ZStack {
                if vm.appliedFilters.isEmpty {
                    VStack {
                        Image(systemName: "line.3.horizontal.decrease.circle")
                            .resizable()
                            .scaledToFill()
                            .frame(
                                width: 100,
                                height: 100
                            )
                            .padding()
                        Text("It seems you have not applied\nany filters!")
                            .font(.title2)
                            .fontWeight(.medium)
                            .multilineTextAlignment(.center)
                            .fixedSize()
                            .padding()
                        Text("What were you thinking while you were\ndisabling all that filters?\n\nApply some of them to see some favorites.")
                            .italic()
                            .multilineTextAlignment(.center)
                            .fixedSize()
                            .padding(.horizontal)
                    }
                    .opacity(0.5)
                } else {
                    if (
                        likedContentProvider.currentState.likedCharacters.isEmpty
                        && likedContentProvider.currentState.likedEpisodes.isEmpty
                        && likedContentProvider.currentState.likedLocations.isEmpty
                    ) {
                        VStack {
                            Image(systemName: "heart")
                                .resizable()
                                .scaledToFill()
                                .frame(
                                    width: 100,
                                    height: 100
                                )
                                .padding()
                            Text("It seems you have not liked any content yet!")
                                .font(.title2)
                                .fontWeight(.medium)
                                .multilineTextAlignment(.center)
                                .padding()
                            Text("Start by pressing the heart icon on any of the\ncharacter, episode or location screen.")
                                .italic()
                                .multilineTextAlignment(.center)
                                .padding(.horizontal)
                        }
                        .opacity(0.5)
                    } else {
                        List {
                            if vm.appliedFilters.contains(ContentFilter.character) {
                                Section {
                                    if likedContentProvider.currentState.likedCharacters.isEmpty {
                                        Text("No liked characters found...")
                                    } else {
                                        ForEach(
                                            likedContentProvider.currentState.likedCharacters,
                                            id: \.id
                                        ) { character in
                                            NavigationLink {
                                                CharacterView(
                                                    characterId: character.id,
                                                    characterName: character.name
                                                )
                                            } label: {
                                                CharacterListItem(model: character)
                                            }.buttonStyle(.plain)
                                        }
                                    }
                                } header: {
                                    HStack {
                                        Text("Characters")
                                        Spacer()
                                        Image(systemName: "person.3.fill")
                                    }
                                }
                            }
                            
                            if vm.appliedFilters.contains(ContentFilter.episode) {
                                Section {
                                    if likedContentProvider.currentState.likedEpisodes.isEmpty {
                                        Text("No liked episodes found...")
                                    } else {
                                        ForEach(
                                            likedContentProvider.currentState.likedEpisodes,
                                            id: \.id
                                        ) { episode in
                                            NavigationLink {
                                                EpisodeView(
                                                    episodeId: episode.id,
                                                    episodeTitle: episode.name
                                                )
                                            } label: {
                                                EpisodeListItem(model: episode)
                                            }.buttonStyle(.plain)
                                        }
                                    }
                                } header: {
                                    HStack {
                                        Text("Episodes")
                                        Spacer()
                                        Image(systemName: "play.tv")
                                    }
                                }
                            }
                            
                            if vm.appliedFilters.contains(ContentFilter.location) {
                                Section {
                                    if likedContentProvider.currentState.likedLocations.isEmpty {
                                        Text("No liked locations found...")
                                    } else {
                                        ForEach(
                                            likedContentProvider.currentState.likedLocations,
                                            id: \.id
                                        ) { location in
                                            NavigationLink {
                                                LocationView(
                                                    locationId: location.id,
                                                    locationName: location.name
                                                )
                                            } label: {
                                                LocationListItem(model: location)
                                            }.buttonStyle(.plain)
                                        }
                                    }
                                } header: {
                                    HStack {
                                        Text("Locations")
                                        Spacer()
                                        Image(systemName: "location")
                                    }
                                }
                            }
                        }
                    }
                }
            }
            .navigationTitle("Favorites")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        showFilterOptions = true
                    } label: {
                        Image(systemName: "line.3.horizontal.decrease.circle")
                    }
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        showWubbaLubbaDubDub = true
                    } label: {
                        Image(systemName: "info.circle")
                    }
                }
            }
            .sheet(
                isPresented: $showFilterOptions,
                onDismiss: {
                    withAnimation {
                        showFilterOptions = false
                    }
                }
            ) {
                
                let filters: [ContentFilter] = [
                    ContentFilter.character,
                    ContentFilter.episode,
                    ContentFilter.location
                ]
                
                List {
                    Section {
                        ForEach(filters, id: \.ordinal) { filter in
                            Toggle(
                                isOn: Binding(
                                    get: {
                                        vm.appliedFilters.contains(filter)
                                    },
                                    set: { _ in
                                        vm.updateFilters(selectedFilter: filter)
                                    }
                                )
                            ) {
                                Text(filter.toDisplayText())
                            }
                        }
                    } header: {
                        HStack {
                            Text("Apply Filters")
                                .font(.headline)
                            Spacer()
                            Image(systemName: "line.3.horizontal.decrease.circle")
                                .font(.headline)
                        }
                    }
                }
                .presentationDetents([.fraction(0.25)])
                
            }
            .alert(isPresented: $showWubbaLubbaDubDub) {
                Alert(
                    title: Text("Wubba Lubba Dub Dub!"),
                    message: Text("You either die a hero or live long enough to become a villain..."),
                    primaryButton: .destructive(
                        Text("Be a Rick!"),
                        action: {
                            likedContentProvider.onEvent(
                                event: LikedContentEvent.WubbaLubbaDubDub()
                            )
                            showWubbaLubbaDubDub = false
                        }
                    ),
                    secondaryButton: .cancel(
                        Text("Be a Morty"),
                        action: {
                            showWubbaLubbaDubDub = false
                        }
                    )
                )
            }
        }
    }
}

struct FavoritesView_Previews: PreviewProvider {
    static var previews: some View {
        FavoritesView()
    }
}

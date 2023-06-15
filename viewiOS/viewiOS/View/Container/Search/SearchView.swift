//
//  SearchView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct SearchView: View {
    
    @ObservedObject
    var vm = SearchVM()
    
    @State
    var showContentFilterChangeSheet = false
    
    @State
    var showChangeFilterOptionsSheet = false
    
    @State
    private var filterTypeHolder: ContentFilter = ContentFilter.character
    
    var body: some View {
        NavigationView {
            ZStack {
                VStack {
                    Image(systemName: "magnifyingglass.circle")
                        .resizable()
                        .frame(
                            width: 100,
                            height: 100,
                            alignment: .center
                        )
                        .padding(.bottom)
                    Text("Start Typing To Search")
                        .font(.title2)
                        .fontWeight(.medium)
                        .multilineTextAlignment(.center)
                        .padding()
                    Text("Start typing on the bar above to\nsearch on \(vm.filterState.appliedFilter.toDisplayText())s")
                        .italic()
                        .multilineTextAlignment(.center)
                        .padding(.horizontal)
                }
                .frame(
                    maxWidth: .infinity,
                    maxHeight: .infinity,
                    alignment: .center
                )
                .opacity(0.5)
            }
            .navigationTitle("Search")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        withAnimation {
                            showContentFilterChangeSheet = true
                        }
                    } label: {
                        SearchContentFilterChangeButton(
                            currentFilter: vm.filterState.appliedFilter
                        )
                    }

                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        showChangeFilterOptionsSheet = true
                    } label: {
                        Image(systemName: "line.3.horizontal.decrease.circle")
                    }
                }
            }
        }
        // MARK: SEARCH
        .searchable(text: $vm.queryState) {
            let state = vm.uiState
            let characters = state.searchedCharacters
            let episodes = state.searchedEpisodes
            let locations = state.searchedLocations
            
            if !characters.isEmpty {
                ForEach(characters, id: \.id) { character in
                    NavigationLink {
                        CharacterView(
                            characterId: character.id,
                            characterName: character.name
                        )
                    } label: {
                        CharacterListItem(model: character)
                    }.buttonStyle(.plain)
                }
            } else if !episodes.isEmpty {
                ForEach(episodes, id: \.id) { episode in
                    NavigationLink {
                        EpisodeView(
                            episodeId: episode.id,
                            episodeTitle: episode.name
                        )
                    } label: {
                        EpisodeListItem(model: episode)
                    }.buttonStyle(.plain)
                }
            } else if !locations.isEmpty {
                ForEach(locations, id: \.id) { location in
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
            
        }
        .onChange(of: vm.queryState) { newQuery in
            vm.onEvent(
                event: SearchEvent.OnQueryChanged(
                    newQuery: newQuery
                )
            )
        }
        .onSubmit(of: .search) {
            vm.onEvent(
                event: SearchEvent.OnQueryChanged(
                    newQuery: vm.queryState
                )
            )
        }
        // MARK: END OF SEARCH
        // MARK: CONTENT FILTER OPTIONS
        .sheet(isPresented: $showContentFilterChangeSheet) {
            let filters = [
                ContentFilter.character,
                ContentFilter.episode,
                ContentFilter.location
            ]
            
            VStack(
                alignment: .center
            ) {
                Text("Apply a Filter")
                    .font(.body)
                    .fontWeight(.bold)
                    .padding()
                Picker(
                    selection: $filterTypeHolder,
                    label: Text("Apply a Filter")
                ) {
                    ForEach(filters, id: \.self) { filterType in
                        Text(filterType.toDisplayText())
                    }
                }
                .pickerStyle(.wheel)
            }
            .presentationDetents([.fraction(0.25)])
            .onAppear {
                filterTypeHolder = vm.filterState.appliedFilter
            }
        }
        .onChange(of: filterTypeHolder) { newFilter in
            vm.onEvent(
                event: SearchEvent.OnFilterChanged(
                    newFilter: newFilter
                )
            )
        }
        // MARK: END OF CONTENT FILTER OPTIONS
        // MARK: FILTER OPTIONS
        .sheet(isPresented: $showChangeFilterOptionsSheet) {
            let appliedFilter = vm.filterState.appliedFilter
            
            if (appliedFilter == .character) {
                
                List {
                    
                    Section {
                        
                        let searchOnFields = [
                            CharacterQueryAcceptField.name,
                            CharacterQueryAcceptField.species,
                            CharacterQueryAcceptField.type
                        ]
                        
                        ForEach(searchOnFields, id: \.self) { field in
                            let currentOptions = vm.filterState.characterFilterOptions
                            Button {
                                vm.onEvent(
                                    event: SearchEvent.OnCharacterFilterOptionsChanged(
                                        newOptions: currentOptions.doCopy(
                                            status: currentOptions.status,
                                            gender: currentOptions.gender,
                                            searchOn: field
                                        )
                                    )
                                )
                            } label: {
                                HStack {
                                    Text(field.toDisplayText())
                                    Spacer()
                                    Image(
                                        systemName: currentOptions.searchOn == field ? "checkmark.square" : "square"
                                    )
                                }
                            }
                        }
                        
                    } header: {
                        HStack {
                            Text("Search on")
                            Spacer()
                            Image(systemName: "magnifyingglass")
                        }
                    }
                    
                    Section {
                        
                        let genderFields = [
                            CharacterGender.male,
                            CharacterGender.female,
                            CharacterGender.genderless,
                            CharacterGender.unknown,
                            CharacterGender.none
                        ]
                        
                        ForEach(genderFields, id: \.self) { field in
                            let currentOptions = vm.filterState.characterFilterOptions
                            Button {
                                vm.onEvent(
                                    event: SearchEvent.OnCharacterFilterOptionsChanged(
                                        newOptions: currentOptions.doCopy(
                                            status: currentOptions.status,
                                            gender: field,
                                            searchOn: currentOptions.searchOn
                                        )
                                    )
                                )
                            } label: {
                                HStack {
                                    Text(field.toDisplayText())
                                    Spacer()
                                    Image(
                                        systemName: currentOptions.gender == field ? "checkmark.square" : "square"
                                    )
                                }
                            }
                        }
                        
                    } header: {
                        HStack {
                            Text("Gender")
                            Spacer()
                            Image(systemName: "figure.wave")
                        }
                    }
                    
                    Section {
                        
                        let statusFields = [
                            CharacterStatus.alive,
                            CharacterStatus.dead,
                            CharacterStatus.none
                        ]
                        
                        ForEach(statusFields, id: \.self) { field in
                            let currentOptions = vm.filterState.characterFilterOptions
                            Button {
                                vm.onEvent(
                                    event: SearchEvent.OnCharacterFilterOptionsChanged(
                                        newOptions: currentOptions.doCopy(
                                            status: field,
                                            gender: currentOptions.gender,
                                            searchOn: currentOptions.searchOn
                                        )
                                    )
                                )
                            } label: {
                                HStack {
                                    Text(field.toDisplayText())
                                    Spacer()
                                    Image(
                                        systemName: currentOptions.status == field ? "checkmark.square" : "square"
                                    )
                                }
                            }
                        }
                        
                    } header: {
                        HStack {
                            Text("Status")
                            Spacer()
                            Image(systemName: "face.smiling")
                        }
                    }
                    
                }.presentationDetents([.fraction(0.8)])
                
            } else if (appliedFilter == .episode) {
                let fields = [
                    EpisodeQueryAcceptField.name,
                    EpisodeQueryAcceptField.episodeCode
                ]
                List {
                    Section {
                        ForEach(fields, id: \.self) { field in
                            Button {
                                vm.onEvent(
                                    event: SearchEvent.OnEpisodeFilterOptionsChanged(
                                        newOptions: EpisodeFilterOptions(
                                            searchOn: field
                                        )
                                    )
                                )
                            } label: {
                                HStack {
                                    Text(field.toDisplayText())
                                    Spacer()
                                    Image(
                                        systemName: vm.filterState.episodeFilterOptions.searchOn == field ? "checkmark.square" : "square"
                                    )
                                }
                            }

                        }
                    } header: {
                        HStack {
                            Text("Search on")
                            Spacer()
                            Image(systemName: "magnifyingglass")
                        }
                    }
                }
                .presentationDetents([.fraction(0.2)])
            } else if (appliedFilter == .location) {
                let fields = [
                    LocationQueryAcceptField.name,
                    LocationQueryAcceptField.dimension,
                    LocationQueryAcceptField.type
                ]
                List {
                    Section {
                        ForEach(fields, id: \.self) { field in
                            Button {
                                vm.onEvent(
                                    event: SearchEvent.OnLocationFilterOptionsChanged(
                                        newOptions: LocationFilterOptions(
                                            searchOn: field
                                        )
                                    )
                                )
                            } label: {
                                HStack {
                                    Text(field.toDisplayText())
                                    Spacer()
                                    Image(
                                        systemName: vm.filterState.locationFilterOptions.searchOn == field ? "checkmark.square" : "square"
                                    )
                                }
                            }

                        }
                    } header: {
                        HStack {
                            Text("Search on")
                            Spacer()
                            Image(systemName: "magnifyingglass")
                        }
                    }
                }
                .presentationDetents([.fraction(0.25)])
            }
            
        }
        // MARK: END OF FILTER OPTIONS
        .onAppear {
            vm.startObserving()
        }
        .onDisappear {
            vm.dispose()
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}

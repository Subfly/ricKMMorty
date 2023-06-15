//
//  CharactersView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct CharactersView: View {
    
    @ObservedObject
    var vm = CharactersVM()
    
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
                ) { index, character in
                    NavigationLink {
                        CharacterView(
                            characterId: character.id,
                            characterName: character.name
                        )
                    } label: {
                        CharacterListItem(model: character)
                            .onAppear {
                                vm.handleLoadMore(
                                    currentItemIndex: index
                                )
                            }
                    }.buttonStyle(.plain)
                }
            }
            
        }
        .navigationTitle("Characters")
        .navigationBarTitleDisplayMode(.large)
        .onAppear {
            vm.startObserving()
        }
        .onDisappear {
            vm.dispose()
        }
        
    }
    
}

struct CharactersView_Previews: PreviewProvider {
    static var previews: some View {
        CharactersView()
    }
}

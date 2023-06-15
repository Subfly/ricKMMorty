//
//  LocationsView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct LocationsView: View {
    
    @ObservedObject
    var vm = LocationsVM()
    
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
                ) { index, location in
                    NavigationLink {
                        LocationView(
                            locationId: location.id,
                            locationName: location.name
                        )
                    } label: {
                        LocationListItem(model: location)
                            .onAppear {
                                vm.handleLoadMore(
                                    currentItemIndex: index
                                )
                            }
                    }.buttonStyle(.plain)
                }
            }
            
        }
        .navigationTitle("Locations")
        .navigationBarTitleDisplayMode(.large)
        .onAppear {
            vm.startObserving()
        }
        .onDisappear {
            vm.dispose()
        }
    }
}

struct LocationsView_Previews: PreviewProvider {
    static var previews: some View {
        LocationsView()
    }
}

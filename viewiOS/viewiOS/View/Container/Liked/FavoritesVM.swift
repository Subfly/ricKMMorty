//
//  FavoritesViewModel.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 13.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core

class FavoritesVM : ObservableObject {
    
    @Published
    var appliedFilters: [ContentFilter] = [
        ContentFilter.character,
        ContentFilter.episode,
        ContentFilter.location
    ]
    
    func updateFilters(selectedFilter: ContentFilter) {
        let filterAlreadyExists = appliedFilters.contains(selectedFilter)
        
        if !filterAlreadyExists {
            appliedFilters.append(selectedFilter)
        } else {
            let filterIndex = appliedFilters.firstIndex(of: selectedFilter)
            if let index = filterIndex {
                appliedFilters.remove(at: index)
            }
        }
    }
    
}

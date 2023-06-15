//
//  SearchContentFilterChangeButton.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 13.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct SearchContentFilterChangeButton: View {
    
    private let iconToShow: String

    init(currentFilter: ContentFilter) {
        switch currentFilter {
        case .character:
            iconToShow = "person.3.fill"
            break;
        case .episode:
            iconToShow = "play.tv"
            break;
        case .location:
            iconToShow = "globe.europe.africa"
            break;
        default:
            iconToShow = "camera.filters"
            break;
        }
    }
    
    var body: some View {
        Image(systemName: iconToShow)
    }
}

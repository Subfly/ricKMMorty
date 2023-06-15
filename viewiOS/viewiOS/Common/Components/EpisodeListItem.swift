//
//  EpisodeListItem.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct EpisodeListItem: View {
    
    let model: EpisodeModel
    
    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 4) {
                Text(model.name)
                    .font(.headline)
                Text(model.airDate)
                    .font(.subheadline)
            }
            Spacer()
            EpisodeTagContainer(
                episode: model.episode
            )
        }
    }
}

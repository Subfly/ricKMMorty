//
//  LocationListItem.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct LocationListItem: View {
    let model: LocationModel
    
    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 4) {
                Text(model.name)
                    .font(.headline)
                Text(model.dimension)
                    .font(.subheadline)
            }
            Spacer()
            LocationTypeContainer(
                type: model.type
            )
        }
    }
}

private struct LocationTypeContainer: View {
    
    let type: String
    
    var body: some View {
        Text(type)
            .font(.system(size: 12))
            .padding(8)
            .padding(.horizontal, 4)
            .background(
                Color.gray.opacity(0.8)
            )
            .cornerRadius(8)
    }
    
}

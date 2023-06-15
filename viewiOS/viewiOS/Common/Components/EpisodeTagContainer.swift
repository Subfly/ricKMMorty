//
//  EpisodeTagCONTAINER.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct EpisodeTagContainer: View {
    
    var stringToShow: String = ""
    
    init(episode: String) {
        let seperatorIndex = episode.firstIndex(of: "E")
        if let index = seperatorIndex {
            let firstPart = episode[..<index]
            let secondPart = episode[index...]
            stringToShow = "\(firstPart) / \(secondPart)"
        }
    }
    
    var body: some View {
        Text(stringToShow)
            .font(.system(size: 12))
            .padding(8)
            .padding(.horizontal, 4)
            .background(
                Color.gray.opacity(0.8)
            )
            .cornerRadius(8)
    }
    
}

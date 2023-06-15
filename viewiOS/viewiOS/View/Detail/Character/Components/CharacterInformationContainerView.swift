//
//  CharacterInformationContainerView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct CharacterInformationContainerView: View {
    
    let species: String
    let gender: CharacterGender
    let status: CharacterStatus
    let type: String
    
    var statusIconToShow: String
    
    init(species: String, gender: CharacterGender, status: CharacterStatus, type: String) {
        self.species = species
        self.gender = gender
        self.status = status
        self.type = type
        
        switch status {
        case .alive:
            statusIconToShow = "face.smiling"
            break
        case.dead:
            statusIconToShow = "heart.slash"
            break
        case .unknown:
            statusIconToShow = "questionmark"
            break
        default:
            statusIconToShow = "questionmark"
        }
        
    }
    
    
    var body: some View {
        
        if !species.isEmpty {
            HStack {
                HStack {
                    Image(systemName: "allergens")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 24,
                            height: 24,
                            alignment: .center
                        )
                    Text("Species")
                }
                Spacer()
                Text(species)
            }
        }
        
        if gender != .none {
            HStack {
                HStack {
                    Image(systemName: "figure.wave")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 24,
                            height: 24,
                            alignment: .center
                        )
                    Text("Gender")
                }
                Spacer()
                Text(gender.toDisplayText())
            }
        }
        
        if status != .none {
            HStack {
                HStack {
                    Image(systemName: statusIconToShow)
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 24,
                            height: 24,
                            alignment: .center
                        )
                    Text("Status")
                }
                Spacer()
                Text(status.toDisplayText())
            }
        }
        
        if !type.isEmpty {
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
                Text(type)
            }
        }
        
    }
}

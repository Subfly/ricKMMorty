//
//  CharacterListItem.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI
import core

struct CharacterListItem: View {
    
    let model: CharacterModel
    
    var body: some View {
        HStack {
            HStack {
                if (!model.imageUrl.isEmpty) {
                    RicKMMortyImageView(
                        imageUrl: model.imageUrl,
                        placeholderIconName: "person.circle"
                    )
                    .frame(
                        width: 52,
                        height: 52,
                        alignment: .center
                    )
                    .clipShape(Circle())
                    .padding(.trailing, 8)
                }
                VStack(
                    alignment: .leading
                ) {
                    Text(model.name)
                        .font(.body)
                    Text(model.species)
                        .font(.caption)
                }
            }
            Spacer()
            DeadOrAliveIndicator(
                status: model.status
            )
        }
    }
}

private struct DeadOrAliveIndicator: View {
    
    @State
    var showAsGray = false
    
    let status: CharacterStatus
    
    var iconToShow: String
    var colorToUse: Color
    
    init(
        status: CharacterStatus
    ) {
        
        self.status = status
        
        switch status {
        case .alive:
            self.iconToShow = "face.smiling";
            self.colorToUse = Color("AliveColor");
            break
        case .dead:
            self.iconToShow = "heart.slash";
            self.colorToUse = Color("DeadColor");
            break
        default:
            self.iconToShow = "questionmark";
            self.colorToUse = Color("UnknownStatusColor");
        }
        
    }
    
    var body: some View {
        Image(systemName: iconToShow)
            .foregroundColor(showAsGray ? Color.gray : colorToUse)
            .onAppear {
                withAnimation(
                    Animation.easeInOut.repeatForever(
                        autoreverses: true
                    ).speed(0.25)
                ) {
                    showAsGray.toggle()
                }
            }
            .padding(.trailing)
            .padding(
                .trailing,
                (status == .none || status == .unknown) ? 4 : 0
            )
    }
    
}

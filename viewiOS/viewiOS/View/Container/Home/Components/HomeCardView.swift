//
//  HomeCardView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HomeCardView: View {
    
    let title: String
    let imagePath: String
    let icon: String
    let color: Color
    let idealHeight: CGFloat
    let idealWidth: CGFloat
    
    var body: some View {
        ZStack {
            color
            
            Image(imagePath)
                .resizable()
                .scaledToFill()
                .frame(
                    maxWidth: idealWidth,
                    maxHeight: idealHeight * 0.8
                )
                .cornerRadius(12)
                .frame(
                    maxWidth: .infinity,
                    maxHeight: .infinity,
                    alignment: .topLeading
                )
            
            VStack {
                Spacer()
                HStack {
                    Text(title)
                        .font(.title)
                    Spacer()
                    Image(systemName: icon)
                        .font(.title)
                }
            }
            .padding(.vertical, 8)
            .padding(.horizontal)
        }
        .frame(
            idealWidth: idealWidth,
            idealHeight: idealHeight
        )
        .cornerRadius(12)
        .padding()
    }
}

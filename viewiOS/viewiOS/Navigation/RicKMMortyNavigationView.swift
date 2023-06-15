//
//  RicKMMortyNavigationView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct RicKMMortyNavigationView: View {
    
    @State
    var path = NavigationPath()
    
    @ObservedObject
    var likedContentProvider = LikedContentProvider()
    
    @ObservedObject
    var themeManager = ThemeManager()
    
    var body: some View {
        NavigationStack(path: $path) {
            MainContainer()
                .tint(themeManager.colorState)
                .preferredColorScheme(themeManager.colorScheme)
        }
        .onAppear {
            likedContentProvider.startObserving()
            themeManager.startObserving()
        }
        .onDisappear {
            likedContentProvider.dispose()
            themeManager.dispose()
        }
        .environmentObject(likedContentProvider)
        .environmentObject(themeManager)
    }

}

struct RicKMMortyNavigationView_Previews: PreviewProvider {
    static var previews: some View {
        RicKMMortyNavigationView()
    }
}

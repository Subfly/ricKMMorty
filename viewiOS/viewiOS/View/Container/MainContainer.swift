//
//  MainContainer.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MainContainer: View {
    
    @State
    var currentTab: MainContainerDestinations = .home
    
    var body: some View {
        TabView(selection: $currentTab) {
            HomeView()
                .tabItem {
                    Image(systemName: "house")
                    Text("Home")
                }
                .tag(MainContainerDestinations.home)
            SearchView()
                .tabItem {
                    Image(systemName: "magnifyingglass")
                    Text("Search")
                }
                .tag(MainContainerDestinations.search)
            FavoritesView()
                .tabItem {
                    Image(systemName: "heart")
                    Text("Favorites")
                }
                .tag(MainContainerDestinations.favorites)
        }
    }
}

struct MainContainer_Previews: PreviewProvider {
    static var previews: some View {
        MainContainer()
    }
}

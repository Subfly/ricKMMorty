//
//  HomeView.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import core

struct HomeView: View {
    
    @EnvironmentObject
    var themeManager: ThemeManager
    
    @State
    var showThemeChangeSheet = false
    
    var body: some View {
        GeometryReader { proxy in
            NavigationView {
                ScrollView {
                    VStack(spacing: 0) {
                        
                        NavigationLink {
                            CharactersView()
                        } label: {
                            HomeCardView(
                                title: "Characters",
                                imagePath: "CharacterImage",
                                icon: "person.3.fill",
                                color: Color("BluePortalColor"),
                                idealHeight: proxy.size.height / 3,
                                idealWidth: proxy.size.width
                            )
                        }.buttonStyle(.plain)

                        
                        NavigationLink {
                            EpisodesView()
                        } label: {
                            HomeCardView(
                                title: "Episodes",
                                imagePath: "EpisodeImage",
                                icon: "play.tv",
                                color: Color("YellowPortalColor"),
                                idealHeight: proxy.size.height / 3,
                                idealWidth: proxy.size.width
                            )
                        }.buttonStyle(.plain)

                        
                        NavigationLink {
                            LocationsView()
                        } label: {
                            HomeCardView(
                                title: "Locations",
                                imagePath: "LocationImage",
                                icon: "globe.europe.africa",
                                color: Color("GreenPortalColor"),
                                idealHeight: proxy.size.height / 3,
                                idealWidth: proxy.size.width
                            )
                        }.buttonStyle(.plain)

                        
                    }
                }
                .navigationTitle("Home")
                .toolbar {
                    ToolbarItem(placement: .navigationBarTrailing) {
                        Button {
                            withAnimation {
                                showThemeChangeSheet = true
                            }
                        } label: {
                            Image(systemName: "gear")
                        }
                    }
                }
                .sheet(isPresented: $showThemeChangeSheet) {
                    List {
                        Section {
                            let themeConfigs = [
                                ThemeConfiguration.light,
                                ThemeConfiguration.dark,
                                ThemeConfiguration.system
                            ]
                            ForEach(themeConfigs, id: \.self) { config in
                                Button {
                                    themeManager.onEvent(
                                        event: ThemeEvent.SetThemeConfiguration(
                                            newConfiguration: config
                                        )
                                    )
                                } label: {
                                    HStack {
                                        Text(config.toDisplayText())
                                        Spacer()
                                        Image(
                                            systemName: themeManager.themeState.selectedThemeConfiguration == config ? "checkmark.square" : "square"
                                        )
                                    }
                                }

                            }
                        } header: {
                            HStack {
                                Text("Theme Configuration")
                                Spacer()
                                Image(systemName: "paintbrush")
                            }
                        }
                        Section {
                            let portals = [
                                PortalTheme.blue,
                                PortalTheme.green,
                                PortalTheme.yellow
                            ]
                            ForEach(portals, id: \.self) { portal in
                                Button {
                                    themeManager.onEvent(
                                        event: ThemeEvent.SetPortal(
                                            newPortalTheme: portal
                                        )
                                    )
                                } label: {
                                    HStack {
                                        Text(portal.toDisplayText())
                                        Spacer()
                                        Image(
                                            systemName: themeManager.themeState.selectedPortalTheme == portal ? "checkmark.square" : "square"
                                        )
                                    }
                                }

                            }
                        } header: {
                            HStack {
                                Text("Apply Portal")
                                Spacer()
                                Image(systemName: "paintpalette")
                            }
                        }
                    }
                    .presentationDetents([.fraction(0.5)])
                }
            }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}

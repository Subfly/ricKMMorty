//
//  ThemeManager.swift
//  ricKMMorty
//
//  Created by Ali Taha Dinçer on 13.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import core

@MainActor
class ThemeManager: ObservableObject {
    
    private let stateMachine: ThemeStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var themeState: ThemeState = ThemeState(
        selectedPortalTheme: PortalTheme.blue,
        selectedThemeConfiguration: ThemeConfiguration.system
    )
    
    @Published
    var colorState: Color = Color.clear
    
    @Published
    var colorScheme: ColorScheme? = nil
    
    init() {
        self.stateMachine = ThemeStateMachine(scope: nil)
    }
    
    func startObserving() {
        self.handle = self.stateMachine.state.subscribe { [weak self] state in
            if let state = state {
                self?.themeState = state
                if let scheme = self?.calculateConfigFromState(state: state) {
                    self?.colorScheme = scheme
                }
                if let color = self?.calculateColorFromState(state: state) {
                    self?.colorState = color
                }
            }
        }
    }
    
    func dispose() {
        self.handle?.dispose()
    }
    
    func onEvent(event: ThemeEvent) {
        self.stateMachine.onEvent(event: event)
    }
    
    private func calculateConfigFromState(state: ThemeState) -> ColorScheme? {
        let themeConfig = state.selectedThemeConfiguration
        switch themeConfig {
        case .system:
            return nil
        case .dark:
            return .dark
        case .light:
            return .light
        default:
            return nil
        }
    }
    
    private func calculateColorFromState(state: ThemeState) -> Color {
        let themeConfig = state.selectedThemeConfiguration
        let portal = state.selectedPortalTheme
        
        if(themeConfig == .system) {
            switch portal {
            case .blue:
                return Color("BluePortalColor")
            case .green:
                return Color("GreenPortalColor")
            case .yellow:
                return Color("YellowPortalColor")
            default:
                return Color("BluePortalColor")
            }
        } else if (themeConfig == .light) {
            switch portal {
            case .blue:
                return Color("BluePortalColor")
            case .green:
                return Color("GreenPortalColor")
            case .yellow:
                return Color("YellowPortalColor")
            default:
                return Color("BluePortalColor")
            }
        } else if (themeConfig == .dark) {
            switch portal {
            case .blue:
                return Color("BluePortalColor")
            case .green:
                return Color("GreenPortalColor")
            case .yellow:
                return Color("YellowPortalColor")
            default:
                return Color("BluePortalColor")
            }
        }
        
        return Color("BluePortalColor")
        
    }
    
}

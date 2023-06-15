//
//  ViewExtensions.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension View {
    public func shouldLoadMore(
        currentIndex: Int,
        contentSize: Int
    ) -> Bool {
        return currentIndex == contentSize - 5
    }
}

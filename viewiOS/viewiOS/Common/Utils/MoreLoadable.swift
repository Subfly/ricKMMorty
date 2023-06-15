//
//  MoreLoadable.swift
//  viewiOS
//
//  Created by Ali Taha Dinçer on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

protocol MoreLoadable {
    func checkShouldLoadMore(
        itemIndex: Int,
        doOnLoadMore: @escaping () -> Void
    ) -> Void {
        
    }
}

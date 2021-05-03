//
//  Stock.swift
//  iosApp
//
//  Created by Rafael Garcia Fernandez on 11/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

struct Stock: Identifiable {
    let id: String
    let name: String
    let price: Money
}

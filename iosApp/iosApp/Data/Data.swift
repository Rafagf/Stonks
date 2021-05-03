//
//  Data.swift
//  iosApp
//
//  Created by Rafael Garcia Fernandez on 11/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

class Data {
    static func getData() -> [Stock] {
        return [
            Stock(id: "APL", name: "Apple", price: Money(whole: 1399, cents: 23)),
            Stock(id: "FIVE", name: "Five Below", price: Money(whole: 123, cents: 23)),
            Stock(id: "ABDP", name: "Animal Dynamics", price: Money(whole: 12, cents: 23))
        ]
    }
}

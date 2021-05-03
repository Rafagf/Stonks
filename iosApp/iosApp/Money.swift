//
//  Money.swift
//  iosApp
//
//  Created by Rafael Garcia Fernandez on 11/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

struct Money {
    let whole: Int
    let cents: Int
    
    func toString() -> String {
        return "\(whole) \(String(format: "%02d", cents))$"
    }
};

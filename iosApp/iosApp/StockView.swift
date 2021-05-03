//
//  StockView.swift
//  iosApp
//
//  Created by Rafael Garcia Fernandez on 11/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct StockView: View {
    @State var stock: Stock
    var body: some View {
        HStack {
            Text(stock.id)
                .bold()
                .frame(alignment: .leading)
                .padding(.leading, 24)
            Text(stock.name)
                .frame(alignment: .leading)
            Spacer()
            Text(stock.price.toString())
            Button(action: {
                print("clicked")
            }, label: {
                Image(systemName:"trash").imageScale(.large)
            })
            .buttonStyle(BorderlessButtonStyle())
        }
    }
}

struct StockView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            StockView(stock: Stock(id: "APL", name: "Apple", price: Money(whole: 1399, cents: 23))
            )
        
        }
    }
}


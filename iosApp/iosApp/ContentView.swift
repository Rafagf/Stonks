import SwiftUI

struct ContentView: View {
    @State var stocks: [Stock]
    @State var isStockLookupShowing: Bool = false
    var body: some View {
        NavigationView {
            List {
                ForEach(stocks) { stock in
                    StockView(stock: stock)
                }
            }
            .navigationBarTitle(Text("📈 Stonks tracker"))
            .navigationBarItems(trailing:
                Button(action: {
                    isStockLookupShowing = true
                }) {
                    Image(systemName: "plus.circle").imageScale(.large)
                }.sheet(isPresented: $isStockLookupShowing, onDismiss: {}, content: {
                    StockLookupView()
                })
            )
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            ContentView(stocks: [Stock(id: "APL", name: "Apple", price: Money(whole: 1399, cents: 23))]
            )
        
        }
    }
}

